import grub.whithCasper.CasperAccessor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestCasperAccessor {

    CasperAccessor casperAccessor=new CasperAccessor();

    @Test
    public void testError() {
        String result=casperAccessor.execute("D://testNotFile.js");
        assertEquals(result,"error");
        result=casperAccessor.execute("D://test.js");
        assertFalse(result.equals("error"));
    }
}
