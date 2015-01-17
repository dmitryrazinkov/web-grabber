import grub.Strategy.OnChangeStrategy;
import grub.app.Config;
import grub.entities.Scripts;
import grub.services.GrubResultService;
import grub.services.ScriptsService;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

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

    Scripts testScript;

    @Ignore
    @Test
    public void test() {
        Scripts script = scriptsService.findByName("dollar rate");
        assertTrue(onChangeStrategy.isChanged(script));
    }

    @Before
    public void before() {
        testScript = new Scripts("testScript");
        scriptsService.save(testScript);
    }

    /*
    @Test
    public void testFalse() {
        Date now = new Date();
        grubResultService.addOne(new GrubResult(now, testScript, "same"));
        now = new Date();
        grubResultService.addOne(new GrubResult(now, testScript, "same"));
        assertFalse(onChangeStrategy.isChanged(testScript));
    }

    @Test
    public void testTrue() {
        Date now = new Date();
        grubResultService.addOne(new GrubResult(now, testScript, "same"));
        now = new Date();
        grubResultService.addOne(new GrubResult(now, testScript, "dontSame"));
        assertTrue(onChangeStrategy.isChanged(testScript));
    }

    @Test
    public void testOne() {
        Date now = new Date();
        grubResultService.addOne(new GrubResult(now, testScript, "same"));
        assertFalse(onChangeStrategy.isChanged(testScript));
    }

    @Test
    public void testNull() {
        assertFalse(onChangeStrategy.isChanged(testScript));
    }
*/
    @After
    public void after() {
        grubResultService.deleteByScript(testScript);
        scriptsService.delete(testScript);
    }
}
