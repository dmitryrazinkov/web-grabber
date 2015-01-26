import grub.app.Config;
import grub.entities.StringScriptOutput;
import grub.withCasper.CasperAccessor;
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
public class TestCasperAccessor {

    @Autowired()
    CasperAccessor casperAccessor;

    @Test()
    public void errorIsReturnedIfFileNotFound() {
        StringScriptOutput result = casperAccessor.execute("D://testNotFile.js", "");
        assertEquals(result.isError(), true);
    }

    @Test
    public void errorNotReturnedIfFileExistAndValid() {
        StringScriptOutput result = casperAccessor.execute("D://test.js", "");
        assertFalse(result.equals(false));
    }

    @Test
    public void returnedResultIfScriptExistAndValid() {
        StringScriptOutput result = casperAccessor.execute("D://test.js", "");
        assertNotNull(result.getStringResult());
    }


}
