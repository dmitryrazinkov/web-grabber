import grub.Strategy.OnChangeStrategy;
import grub.app.Config;
import grub.entities.Scripts;
import grub.services.ScriptsService;
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
    OnChangeStrategy onChangeStrategy;

    @Ignore
    @Test
    public void test(){
        Scripts script=scriptsService.findByName("dollar rate");
        assertTrue(onChangeStrategy.isChanged(script));
    }
}
