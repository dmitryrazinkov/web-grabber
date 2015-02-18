package grab.components;

import grab.entities.GrabResult;
import grab.entities.ScriptsForRun;
import grab.entities.StringScriptOutput;
import grab.parsers.JsonParser;
import grab.services.GrabResultService;
import grab.services.ScriptsForRunService;
import grab.services.StringScriptOutputService;
import grab.strategies.OnChangeStrategy;
import grab.accessors.CasperAccessor;
import grab.accessors.HarvestAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Component
public class ScriptProcess {
    private static final Logger log = LoggerFactory.getLogger(ScriptProcess.class);

    @Autowired
    CasperAccessor casperAccessor;

    @Autowired
    HarvestAccessor harvestAccessor;

    @Autowired
    StringScriptOutputService stringScriptOutputService;

    @Autowired
    GrabResultService grabResultService;

    @Autowired
    ScriptsForRunService scriptsForRunService;

    @Autowired
    OnChangeStrategy onChangeStrategy;

    /**
     * All script works and add records in DB
     *
     * @param scriptsForRun current script
     * @param path          path
     */
    @Transactional
    public void run(ScriptsForRun scriptsForRun, String path) {
        StringScriptOutput stringScriptOutput;
        if (scriptsForRun.getScript().isCasper()) {
            stringScriptOutput = casperAccessor.execute(path, scriptsForRun.getArgs());
        } else {
            stringScriptOutput = harvestAccessor.execute(path, scriptsForRun.getArgs());
        }
        log.debug("{} script result: {}", scriptsForRun.getScript().getName(), stringScriptOutput.getStringResult());

        stringScriptOutput = jsonProcess(stringScriptOutput, scriptsForRun);

        Date now = new Date();
        stringScriptOutput = stringScriptOutputService.addOne(stringScriptOutput);
        grabResultService.addOne(new GrabResult(now, scriptsForRun, stringScriptOutput));

        if (stringScriptOutput.isError()) {
            scriptsForRun.setErrorMessage(stringScriptOutput.getErrorMessage());
            scriptsForRunService.add(scriptsForRun);
        }

        processScriptResult(scriptsForRun, now);
    }

    /**
     * Method for checking the data changes, if required
     *
     * @param scriptsForRun current script
     * @param now           now date
     */
    private void processScriptResult(ScriptsForRun scriptsForRun, Date now) {
        String description = getString(scriptsForRun.getScript().getDescription());
        if (!description.equals("onchange")) {
            log.debug("not onchange");
            return;
        }

        List<GrabResult> lastTwo = grabResultService.findLastTwo(scriptsForRun.getId());
        if (lastTwo.size() != 2) {
            log.debug("don't find last two");
            scriptsForRun.setStatus("Data don't changed");
            scriptsForRunService.add(scriptsForRun);
            return;
        }

        if (onChangeStrategy.isChanged(lastTwo.get(0), lastTwo.get(1))) {
            log.debug("Data changed \n Script: {}\n Site: {} \n Time: {} ",
                    scriptsForRun.getScript().getName(),
                    scriptsForRun.getScript().getSite().getUrl().toString(), now);
            scriptsForRun.setStatus("Data changed!!");
            scriptsForRunService.add(scriptsForRun);
            return;
        }
        scriptsForRun.setStatus("Data don't changed");
        scriptsForRunService.add(scriptsForRun);
    }

    /**
     * @param description description
     * @return description or an empty string if null
     */
    private String getString(String description) {
        return description != null ? description : "";
    }

    private StringScriptOutput jsonProcess(StringScriptOutput stringScriptOutput, ScriptsForRun scriptsForRun) {
        if (!scriptsForRun.getScript().isCasper()) {
            return stringScriptOutput;
        }
        if (JsonParser.isError(stringScriptOutput.getStringResult())) {
            stringScriptOutput.setStringResult("");
            stringScriptOutput.setError(true);
            stringScriptOutput.setErrorMessage("Script invalid(selectors invalid) or args incorrect");
            return stringScriptOutput;
        }
        stringScriptOutput.setStringResult(JsonParser.getResult(stringScriptOutput.getStringResult()));
        return stringScriptOutput;
    }
}