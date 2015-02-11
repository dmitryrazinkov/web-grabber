package grub.components;

import grub.entities.GrabResult;
import grub.entities.ScriptsForRun;
import grub.entities.StringScriptOutput;
import grub.generators.PathGenerator;
import grub.services.GrabResultService;
import grub.services.ScriptsForRunService;
import grub.services.StringScriptOutputService;
import grub.strategies.OnChangeStrategy;
import grub.withCasper.CasperAccessor;
import grub.withHarvest.HarvestAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

/**
 * Class used for run scripts on a schedule
 */
@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    GrabResultService grabResultService;

    @Autowired
    CasperAccessor casperAccessor;

    @Autowired
    HarvestAccessor harvestAccessor;

    @Autowired
    OnChangeStrategy onChangeStrategy;

    @Autowired
    StringScriptOutputService stringScriptOutputService;

    @Autowired
    ScriptsForRunService scriptsForRunService;

    private String path;

    /**
     *  Method to execute scripts on a schedule */
    @Scheduled(cron = "${cron.schedule}")
    public void grub() {
        log.debug("task run");

        for (ScriptsForRun scriptsForRun : scriptsForRunService.allScripts()) {
            Date now = new Date();

            try {
                File file = PathGenerator.generate(scriptsForRun.getScript().isCasper());
                path = file.getPath();
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                fileOutputStream.write(scriptsForRun.getScript().getFile());
                fileOutputStream.close();

                processScript(scriptsForRun, now);

                file.delete();
            } catch (NullPointerException e) {
                log.error("Pathname is null", e);
            } catch (FileNotFoundException e) {
                log.error("File not found", e);
            } catch (Exception e) {
                log.error("Failed create tmp file and execute casperJs", e);
            }
        }
        log.debug("task end");
    }

    /**
     *  All script works and add records in DB
     *  @param scriptsForRun current script
     *  @param now now date*/
    @Transactional
    private void processScript(ScriptsForRun scriptsForRun, Date now) {
        StringScriptOutput stringScriptOutput;
        if (scriptsForRun.getScript().isCasper()) {
            stringScriptOutput = casperAccessor.execute(path, scriptsForRun.getArgs());
        } else {
            stringScriptOutput = harvestAccessor.execute(path, scriptsForRun.getArgs());
        }
        log.debug("{} script result: {}", scriptsForRun.getScript().getName(), stringScriptOutput.getStringResult());

        stringScriptOutput = stringScriptOutputService.addOne(stringScriptOutput);
        grabResultService.addOne(new GrabResult(now, scriptsForRun, stringScriptOutput));

        if (stringScriptOutput.isError()) {
            scriptsForRun.setErrorMessage(stringScriptOutput.getErrorMessage());
            scriptsForRunService.add(scriptsForRun);
        }

        processScriptResult(scriptsForRun, now);
    }

    /**
     *  Method for checking the data changes, if required
     *  @param scriptsForRun current script
     *  @param now now date */
    private void processScriptResult(ScriptsForRun scriptsForRun, Date now) {
        String description = getString(scriptsForRun.getScript().getDescription());
        if (!description.equals("onchange")) {
            log.debug("not onchange");
            return;
        }

        List<GrabResult> lastTwo = grabResultService.findLastTwo(scriptsForRun.getId());
        if (lastTwo.size() != 2) {
            log.debug("don't find last two");
            return;
        }

        if (onChangeStrategy.isChanged(lastTwo.get(0), lastTwo.get(1))) {
            log.debug("Data changed \n Script: {}\n Site: {} \n Time: {} ",
                    scriptsForRun.getScript().getName(),
                    scriptsForRun.getScript().getSite().getUrl().toString(), now);
            scriptsForRun.setChanged(true);
            scriptsForRunService.add(scriptsForRun);
        }
    }

    /**
     *  @param description description
     *  @return description or an empty string if null */
    private String getString(String description) {
        return description != null ? description : "";
    }


}
