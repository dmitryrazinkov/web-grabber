import grab.strategies.OnChangeStrategy;
import grab.app.Config;
import grab.entities.GrabResult;
import grab.entities.Scripts;
import grab.entities.ScriptsForRun;
import grab.entities.StringScriptOutput;
import grab.services.GrabResultService;
import grab.services.ScriptsForRunService;
import grab.services.ScriptsService;
import grab.services.StringScriptOutputService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Config.class)
public class TestOnChangeStrategy {

    @Autowired
    ScriptsService scriptsService;

    @Autowired
    GrabResultService grabResultService;

    @Autowired
    OnChangeStrategy onChangeStrategy;

    @Autowired
    StringScriptOutputService stringScriptOutputService;

    @Autowired
    ScriptsForRunService scriptsForRunService;

    Scripts testScript;

    ScriptsForRun testScriptForRun;

    StringScriptOutput one;

    StringScriptOutput two;

    @Before
    public void before() {
        testScript = new Scripts("testScript");
        testScript = scriptsService.save(testScript);
        testScriptForRun = new ScriptsForRun("", testScript);
        testScriptForRun = scriptsForRunService.add(testScriptForRun);
    }


    @Test
    public void isChangedMustReturnFalse() {
        Date now = new Date();
        one = stringScriptOutputService.addOne(new StringScriptOutput("same", false, ""));
        grabResultService.addOne(new GrabResult(now, testScriptForRun, one));
        now = new Date();
        two = stringScriptOutputService.addOne(new StringScriptOutput("same", false, ""));
        grabResultService.addOne(new GrabResult(now, testScriptForRun, two));
        List<GrabResult> results = grabResultService.findLastTwo(testScriptForRun.getId());
        assertFalse(onChangeStrategy.isChanged(results.get(0), results.get(1)));
    }

    @Test
    public void isChangedMustReturnTrue() {
        Date now = new Date();
        one = stringScriptOutputService.addOne(new StringScriptOutput("same", false, ""));
        grabResultService.addOne(new GrabResult(now, testScriptForRun, one));
        now = new Date();
        two = stringScriptOutputService.addOne(new StringScriptOutput("not_same", false, ""));
        grabResultService.addOne(new GrabResult(now, testScriptForRun, two));
        List<GrabResult> results = grabResultService.findLastTwo(testScriptForRun.getId());
        assertTrue(onChangeStrategy.isChanged(results.get(0), results.get(1)));
    }

    @After
    public void after() {
        grabResultService.deleteByScript(testScriptForRun);
        stringScriptOutputService.delete(one);
        stringScriptOutputService.delete(two);
        scriptsForRunService.delete(testScriptForRun);
        scriptsService.delete(testScript);
    }
}
