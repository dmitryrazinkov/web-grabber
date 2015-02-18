package grab.strategies;

import grab.entities.ScriptsForRun;
import grab.entities.StringScriptOutput;
import grab.services.ScriptsForRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OnWaitStrategy {
    @Autowired
    ScriptsForRunService scriptsForRunService;


    public void onWait(ScriptsForRun scriptsForRun, StringScriptOutput stringScriptOutput) {
        if (stringScriptOutput.getStringResult().equals("true")) {
            scriptsForRun.setStatus("expected data available");
            scriptsForRunService.add(scriptsForRun);
        }
    }
}
