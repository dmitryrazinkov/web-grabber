package grub.components;

import grub.generators.PathGenerator;
import grub.strategy.OnChangeStrategy;
import grub.entities.GrubResult;
import grub.entities.ScriptsForRun;
import grub.entities.StringScriptOutput;
import grub.services.GrubResultService;
import grub.services.ScriptsForRunService;
import grub.services.StringScriptOutputService;
import grub.withCasper.CasperAccessor;
import grub.withHarvest.HarvestAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    GrubResultService grubResultService;

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

    private String path = "D:\\testing";

    @Scheduled(cron = "${cron.schedule}")
    public void grub() {
        log.debug("task run");

        for (ScriptsForRun scriptsForRun : scriptsForRunService.allScripts()) {
            Date now = new Date();

            try {
                File file = PathGenerator.generate(scriptsForRun.getScript().isCasper());
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                fileOutputStream.write(scriptsForRun.getScript().getFile());
                fileOutputStream.close();

                if (scriptsForRun.getArgs() == null) {
                    scriptsForRun.setArgs("");
                }

                StringScriptOutput stringScriptOutput;
                if (scriptsForRun.getScript().isCasper()) {
                    stringScriptOutput = casperAccessor.execute(path, scriptsForRun.getArgs());
                } else {
                    stringScriptOutput = harvestAccessor.execute(path);
                }
                stringScriptOutputService.addOne(stringScriptOutput);
                grubResultService.addOne(new GrubResult(now, scriptsForRun, stringScriptOutput));

                if (scriptsForRun.getScript().getDescription() != null &&
                        scriptsForRun.getScript().getDescription().equals("onchange")) {
                    List<GrubResult> lastTwo = grubResultService.findLastTwo(scriptsForRun.getId());
                    if (lastTwo.size() == 2) {
                        if (onChangeStrategy.isChanged(lastTwo.get(0), lastTwo.get(1))) {
                            log.debug("Data changed \n Script: {}\n Site: {} \n Time: {} ",
                                    scriptsForRun.getScript().getName(),
                                    scriptsForRun.getScript().getSite().getUrl().toString(), now);
                        }
                    }
                }
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


}
