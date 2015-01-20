import grub.strategy.OnChangeStrategy;
import grub.app.Config;
import grub.entities.GrubResult;
import grub.entities.Scripts;
import grub.entities.ScriptsForRun;
import grub.entities.StringScriptOutput;
import grub.services.GrubResultService;
import grub.services.ScriptsForRunService;
import grub.services.ScriptsService;
import grub.services.StringScriptOutputService;
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
    GrubResultService grubResultService;

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
        testScript=scriptsService.save(testScript);
        testScriptForRun=new ScriptsForRun("",testScript);
        testScriptForRun=scriptsForRunService.add(testScriptForRun);
    }


    @Test
    public void isChangedMustReturnFalse() {
        Date now = new Date();
        one= stringScriptOutputService.addOne(new StringScriptOutput("same",false));
        grubResultService.addOne(new GrubResult(now, testScriptForRun, one));
        now = new Date();
        two= stringScriptOutputService.addOne(new StringScriptOutput("same",false));
        grubResultService.addOne(new GrubResult(now, testScriptForRun, two));
        List<GrubResult> results=grubResultService.findLastTwo(testScriptForRun.getId());
        assertFalse(onChangeStrategy.isChanged(results.get(0),results.get(1)));
    }

    @Test
    public void isChangedMustReturnTrue() {
        Date now = new Date();
        one= stringScriptOutputService.addOne(new StringScriptOutput("same",false));
        grubResultService.addOne(new GrubResult(now, testScriptForRun, one));
        now = new Date();
        two= stringScriptOutputService.addOne(new StringScriptOutput("not_same",false));
        grubResultService.addOne(new GrubResult(now, testScriptForRun, two));
        List<GrubResult> results=grubResultService.findLastTwo(testScriptForRun.getId());
        assertTrue(onChangeStrategy.isChanged(results.get(0),results.get(1)));
    }

    @After
    public void after() {
        grubResultService.deleteByScript(testScriptForRun);
        stringScriptOutputService.delete(one);
        stringScriptOutputService.delete(two);
        scriptsForRunService.delete(testScriptForRun);
        scriptsService.delete(testScript);
    }
}
