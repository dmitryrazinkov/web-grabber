package grub.components;

import grub.Frame.ChangeAlertDialog;
import grub.Strategy.OnChangeStrategy;
import grub.entities.GrubResult;
import grub.entities.ScriptsForRun;
import grub.entities.StringScriptOutput;
import grub.services.GrubResultService;
import grub.services.ScriptsForRunService;
import grub.services.StringScriptOutputService;
import grub.whithCasper.CasperAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    @Autowired
    ScriptGrub scriptGrub;

    @Autowired
    GrubResultService grubResultService;

    @Autowired
    CasperAccessor casperAccessor;

    @Autowired
    OnChangeStrategy onChangeStrategy;

    @Autowired
    StringScriptOutputService stringScriptOutputService;

    @Autowired
    ScriptsForRunService scriptsForRunService;

    private String path = "D:\\testing.js";

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void grub() {
        log.debug("task run");

        for (ScriptsForRun scriptsForRun : scriptsForRunService.allScripts()) {
            Date now = new Date();

            try {
                File file = new File(path);
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                fileOutputStream.write(scriptsForRun.getScript().getFile());
                fileOutputStream.close();

                if (scriptsForRun.getArgs() == null) {
                    scriptsForRun.setArgs("");
                }
                StringScriptOutput stringScriptOutput = casperAccessor.execute(path, scriptsForRun.getArgs());
                stringScriptOutputService.addOne(stringScriptOutput);
                grubResultService.addOne(new GrubResult(now, scriptsForRun, stringScriptOutput));

                if (scriptsForRun.getScript().getDescription() != null && scriptsForRun.getScript().getDescription().equals("onchange")) {
                    List<GrubResult> lastTwo = grubResultService.findLastTwo(scriptsForRun.getId());
                    if (lastTwo.size() == 2) {
                        if (onChangeStrategy.isChanged(lastTwo.get(0), lastTwo.get(1))) {
                            ChangeAlertDialog dialog = new ChangeAlertDialog(scriptsForRun.getScript().getName(),
                                    scriptsForRun.getScript().getSite().getUrl().toString(), now);
                            dialog.pack();
                            dialog.setVisible(true);
                            log.debug("GhangeAlertDialog open");
                        }
                    }
                }
                file.delete();
            } catch (Exception e) {
                log.error("Failed create tmp file and execute casperJs", e);
            }
        }
        log.debug("task end");
    }


}
