package grub.components;

import grub.whithCasper.CasperAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScheduledTasks {
    @Autowired
    Sites sites;

    @Scheduled(fixedDelay = 1000000000)
    public void grub() throws IOException {
        CasperAccessor casperAccessor=new CasperAccessor();
        System.out.print("task run");
        for(String site: sites.getSitesForGrub()) {
            if (site.equals("google")) {
                casperAccessor.google("google");
            }
        }
    }
}
