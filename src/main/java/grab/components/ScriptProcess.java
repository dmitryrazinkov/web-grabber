package grab.components;

import grab.accessors.CasperAccessor;
import grab.accessors.HarvestAccessor;
import grab.entities.GrabResult;
import grab.entities.ScriptsForRun;
import grab.entities.StringScriptOutput;
import grab.parsers.JsonParser;
import grab.services.GrabResultService;
import grab.services.ScriptsForRunService;
import grab.services.StringScriptOutputService;
import grab.strategies.OnChangeStrategy;
import grab.strategies.OnWaitStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;

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

    @Autowired
    OnWaitStrategy onWaitStrategy;

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

        processScriptResult(scriptsForRun, stringScriptOutput);
    }

    /**
     * Method for checking the data changes, if required
     *
     * @param scriptsForRun current script
     */
    private void processScriptResult(ScriptsForRun scriptsForRun, StringScriptOutput stringScriptOutput) {
        String description = getString(scriptsForRun.getScript().getDescription());

        if (description.equals("onchange")) {
            log.debug("onchange");
            onChangeStrategy.changeProcess(scriptsForRun);
            return;
        }

        if (description.equals("onwait")) {
            log.debug("onwait");
            onWaitStrategy.onWait(scriptsForRun, stringScriptOutput);
            return;
        }


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