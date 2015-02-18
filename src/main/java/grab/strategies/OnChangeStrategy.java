package grab.strategies;

import grab.entities.GrabResult;
import grab.entities.ScriptsForRun;
import grab.services.GrabResultService;
import grab.services.ScriptsForRunService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Class for indicate changes in script results
 */
@Component
public class OnChangeStrategy {
    private static final Logger log = LoggerFactory.getLogger(OnChangeStrategy.class);

    @Autowired
    ScriptsForRunService scriptsForRunService;

    @Autowired
    GrabResultService grabResultService;


    /**
     * Method compare to result of any script
     *
     * @param one first result
     * @param two second result
     * @return true if changed, false otherwise
     */
    public boolean isChanged(GrabResult one, GrabResult two) {
        if (one.getStringScriptOutput().getStringResult().equals(two.getStringScriptOutput().getStringResult())) {
            return false;
        } else return true;
    }

    public void changeProcess(ScriptsForRun scriptsForRun) {
        List<GrabResult> lastTwo = grabResultService.findLastTwo(scriptsForRun.getId());
        if (lastTwo.size() != 2) {
            log.debug("don't find last two");
            scriptsForRun.setStatus("Data don't changed");
            scriptsForRunService.add(scriptsForRun);
            return;
        }

        if (isChanged(lastTwo.get(0), lastTwo.get(1))) {
            log.debug("Data changed \n Script: {}\n Site: {} \n Time: {} ",
                    scriptsForRun.getScript().getName(),
                    scriptsForRun.getScript().getSite().getUrl().toString(), new Date());
            scriptsForRun.setStatus("Data changed!!");
            scriptsForRunService.add(scriptsForRun);
            return;
        }
        scriptsForRun.setStatus("Data don't changed");
        scriptsForRunService.add(scriptsForRun);
    }

}
