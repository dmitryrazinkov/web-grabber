import org.junit.Test;

import java.util.Map;

import static grub.parsers.ArgParser.CasperParser;
import static grub.parsers.ArgParser.HarvestParser;
import static org.junit.Assert.*;

public class TestArgParser {
    @Test
    public void harvestParserResultMustContainsCorrectKeyAndValue(){
        String s="p=2\nl=1";
        Map<String,String> map= HarvestParser(s);
        assertTrue(map.containsKey("p"));
        assertTrue(map.containsKey("l"));
        assertTrue(map.containsValue("2"));
        assertTrue(map.containsValue("1"));
    }

    @Test
    public void casperParserMustReturnCorrectResult(){
        String s="d=1\nl=2";
        s=CasperParser(s);
        assertEquals(s,"--d=1\n--l=2");
    }


}
