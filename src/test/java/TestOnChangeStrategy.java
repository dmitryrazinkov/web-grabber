import grub.Strategy.OnChangeStrategy;
import grub.app.Config;
import grub.entities.GrubResult;
import grub.entities.Scripts;
import grub.entities.StringResult;
import grub.services.GrubResultService;
import grub.services.ScriptsService;
import grub.services.StringResultService;
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
    StringResultService stringResultService;

    Scripts testScript;

    @Before
    public void before() {
        testScript = new Scripts("testScript");
        testScript=scriptsService.save(testScript);
    }


    @Test
    public void isChangedMustReturnFalse() {
        Date now = new Date();
        StringResult one=stringResultService.addOne(new StringResult("same",true));
        grubResultService.addOne(new GrubResult(now, testScript, one));
        now = new Date();
        StringResult two=stringResultService.addOne(new StringResult("same",true));
        grubResultService.addOne(new GrubResult(now, testScript, two));
        List<GrubResult> results=grubResultService.findLastTwo(testScript.getId());
        assertFalse(onChangeStrategy.isChanged(results.get(0),results.get(1)));
    }

    @Test
    public void isChangedMustReturnTrue() {
        Date now = new Date();
        StringResult one=stringResultService.addOne(new StringResult("same",true));
        grubResultService.addOne(new GrubResult(now, testScript, one));
        now = new Date();
        StringResult two=stringResultService.addOne(new StringResult("not_same",true));
        grubResultService.addOne(new GrubResult(now, testScript, two));
        List<GrubResult> results=grubResultService.findLastTwo(testScript.getId());
        assertTrue(onChangeStrategy.isChanged(results.get(0),results.get(1)));
    }

    @After
    public void after() {
        grubResultService.deleteByScript(testScript);
        scriptsService.delete(testScript);
    }
}
