package grub.components;

import grub.entities.GrubResult;
import grub.services.GrubResultService;
import grub.whithCasper.CasperAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

@Component
public class ScheduledTasks {
    @Autowired
    Sites sites;

    @Autowired
    GrubResultService grubResultService;

    @Scheduled(fixedDelay = 1000)
    public void grub() throws IOException {
        System.out.print("task run");
        for(String site: sites.getSitesForGrub()) {
            CasperAccessor casperAccessor=new CasperAccessor();
            if (site.equals("google")) {
                casperAccessor.google("google");
            }

            Date now=new Date(Calendar.getInstance().getTime().getTime());
            grubResultService.addOne(new GrubResult(now,site,""));
        }
    }
}
