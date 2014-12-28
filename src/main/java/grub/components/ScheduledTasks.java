package grub.components;

import grub.entities.GrubResult;
import grub.services.GrubResultService;
import grub.whithCasper.CasperAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@Component
public class ScheduledTasks {
    @Autowired
    Sites sites;

    @Autowired
    GrubResultService grubResultService;

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void grub() throws IOException {
        System.out.print("task run");
        for (String site : sites.getSitesForGrub()) {
            CasperAccessor casperAccessor = new CasperAccessor();
            casperAccessor.execute(site);

            List<String> list = casperAccessor.getListFromCmd();
            java.util.Date now = new java.util.Date(Calendar.getInstance().getTime().getTime());

            grubResultService.addOne(new GrubResult(now, site, message(list)));
        }
    }

    public String message(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i < list.size() - 1; i++) {
            sb.append(list.get(i)).append(" ");
        }
        return sb.toString();
    }
}
