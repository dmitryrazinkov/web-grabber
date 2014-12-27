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
import java.util.List;

@Component
public class ScheduledTasks {
    @Autowired
    Sites sites;

    @Autowired
    GrubResultService grubResultService;

    @Scheduled(fixedDelay = 1000)
    public void grub() throws IOException {
        System.out.print("task run");
        for (String site : sites.getSitesForGrub()) {
            CasperAccessor casperAccessor = new CasperAccessor();
            casperAccessor.execute(site);

            List<String> list=casperAccessor.getListFromCmd();
            Date now = new Date(Calendar.getInstance().getTime().getTime());
            grubResultService.addOne(new GrubResult(now, site, list.get(9)+";"+list.get(10)));
        }
    }
}
