package grub.components;

import grub.entities.GrubResult;
import grub.entities.Scripts;
import grub.services.GrubResultService;
import grub.whithCasper.CasperAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Component
public class ScheduledTasks {
    @Autowired
    Sites sites;

    @Autowired
    GrubResultService grubResultService;

    @Autowired
    CasperAccessor casperAccessor;

    private String path="D:\\testing.js";

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void grub() throws IOException {
        System.out.println("task run");

        for (Scripts script : sites.getScriptsForGrub()) {
            Date now = new Date();

            System.out.println("run");

            File file = new File(path);
            FileOutputStream fileOutputStream=new FileOutputStream(path);
            fileOutputStream.write(script.getFile());
            fileOutputStream.close();

            grubResultService.addOne(new GrubResult(now,script,casperAccessor.execute(path)));

            file.delete();
        }
    }


}
