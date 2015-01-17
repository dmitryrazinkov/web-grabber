package grub.components;

import grub.Frame.ChangeAlertDialog;
import grub.Strategy.OnChangeStrategy;
import grub.entities.GrubResult;
import grub.entities.Scripts;
import grub.entities.StringResult;
import grub.services.GrubResultService;
import grub.services.StringResultService;
import grub.whithCasper.CasperAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

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
    StringResultService stringResultService;

    private String path = "D:\\testing.js";

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void grub() {
        log.debug("task run");

        for (Scripts script : scriptGrub.getScriptsForGrub()) {
            Date now = new Date();

            try {
                File file = new File(path);
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                fileOutputStream.write(script.getFile());
                fileOutputStream.close();
                if(script.getArgs()==null) {
                    script.setArgs("");
                }
                StringResult stringResult=casperAccessor.execute(path,script.getArgs());
                stringResultService.addOne(stringResult);
                grubResultService.addOne(new GrubResult(now, script, stringResult));
                if (script.getDescription() != null && script.getDescription().equals("onchange")) {
                    if (onChangeStrategy.isChanged(script)) {
                        ChangeAlertDialog dialog = new ChangeAlertDialog(script.getName(),
                                script.getSite().getUrl().toString(), now);
                        dialog.pack();
                        dialog.setVisible(true);
                        log.debug("GhangeAlertDialog open");
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
