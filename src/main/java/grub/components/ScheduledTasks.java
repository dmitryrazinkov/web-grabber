package grub.components;

import grub.services.GrubResultService;
import grub.whithCasper.CasperAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScheduledTasks {
    @Autowired
    Sites sites;

    @Autowired
    GrubResultService grubResultService;

    @Autowired
    CasperAccessor casperAccessor;

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void grub() throws IOException {
        System.out.println("task run");
//        for (String site : sites.getSitesForGrub()) {
//            Date now = new Date();
        //    grubResultService.addOne(new GrubResult(now, site, casperAccessor.execute(site)));
//        }
    }


}
