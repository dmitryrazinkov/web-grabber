import grub.app.Config;
import grub.entities.StringScriptOutput;
import grub.withCasper.CasperAccessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Config.class)
public class TestCasperAccessor {
    private static final Logger log = LoggerFactory.getLogger(TestCasperAccessor.class);

    @Autowired()
    CasperAccessor casperAccessor;

    @Test()
    public void errorIsReturnedIfFileNotFound() {
        StringScriptOutput result = casperAccessor.execute("testNotFile.js", "");
        assertEquals(result.isError(), true);
    }

    @Test
    public void errorNotReturnedIfFileExistAndValid() {
        StringScriptOutput result = null;
        try {
            result = casperAccessor.execute(ResourceUtils.getFile("classpath:casperJs/pogoda-yandex.js")
                    .getPath(), "");
        } catch (FileNotFoundException e) {
            log.error("Can't get file");
        }
        assertTrue(result.isError() == false);
    }

    @Test
    public void returnedResultIfScriptExistAndValid() {
        StringScriptOutput result = null;
        try {
            result = casperAccessor.execute(ResourceUtils.getFile("classpath:casperJs/pogoda-yandex.js")
                    .getPath(), "");
        } catch (FileNotFoundException e) {
            log.error("Can't get file");
        }
        assertNotNull(result.getStringResult());
    }

    @Test
    public void casperMustCorrectWorkWithSpacesAtPath() {
        StringScriptOutput result = null;
        try {
            result = casperAccessor.execute(ResourceUtils
                    .getFile("classpath:casperJs/test dir/pogoda-yandex.js")
                    .getPath(), "");
        } catch (FileNotFoundException e) {
            log.error("Can't get file");
        }
        System.out.println(result.getStringResult());
        assertTrue(result.isError() == false);

    }


}
