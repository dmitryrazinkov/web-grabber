import grub.app.Config;
import grub.whithCasper.CasperAccessor;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Config.class)
public class TestCasperAccessor {

    @Autowired()
    CasperAccessor casperAccessor;
/*
    @Test()
    public void errorIsReturnedIfFileNotFound() {
        String result=casperAccessor.execute("D://testNotFile.js","");
        assertEquals(result,"error");
    }

    @Test
    public void testNotError(){
        String result=casperAccessor.execute("D://test.js","");
        assertFalse(result.equals("error"));
    }

    */
}
