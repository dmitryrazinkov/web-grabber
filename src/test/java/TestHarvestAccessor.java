import grab.app.Config;
import grab.entities.StringScriptOutput;
import grab.accessors.HarvestAccessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Config.class)
public class TestHarvestAccessor {
    @Autowired()
    HarvestAccessor harvestAccessor;

    @Test()
    public void errorIsReturnedIfFileNotFound() {
        StringScriptOutput result = harvestAccessor.execute("testNotFile.xml", "");
        assertEquals(result.isError(), true);
    }

    @Test
    public void errorNotReturnedIfFileExistAndValid() {
        StringScriptOutput result = harvestAccessor.execute("harvest//oil.xml", "");
        assertFalse(result.equals(false));
    }

    @Test
    public void returnedResultIfScriptExistAndValid() {
        StringScriptOutput result = harvestAccessor.execute("harvest//oil.xml", "");
        assertNotNull(result.getStringResult());
    }

}
