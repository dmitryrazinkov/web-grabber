package grub.components;

import grub.entities.ScriptsForRun;
import grub.generators.PathGenerator;
import grub.services.ScriptsForRunService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Class used for run scripts on a schedule
 */
@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    ScriptsForRunService scriptsForRunService;

    @Autowired
    ScriptProcess scriptProcess;

    private String path;

    /**
     *  Method to execute scripts on a schedule */
    @Scheduled(cron = "${cron.schedule}")
    public void grub() {
        log.debug("task run");

        for (ScriptsForRun scriptsForRun : scriptsForRunService.allScripts()) {

            try {
                File file = PathGenerator.generate(scriptsForRun.getScript().isCasper());
                path = file.getPath();
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                fileOutputStream.write(scriptsForRun.getScript().getFile());
                fileOutputStream.close();

                scriptProcess.run(scriptsForRun, path);

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
