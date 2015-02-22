import org.junit.Test;

import java.util.Map;

import static grab.parsers.ArgParser.casperParser;
import static grab.parsers.ArgParser.harvestParser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestArgParser {
    @Test
    public void harvestParserResultMustContainsCorrectKeyAndValue() {
        String s = "p=2\nl=1";
        Map<String, String> map = harvestParser(s);
        assertTrue(map.containsKey("p"));
        assertTrue(map.containsKey("l"));
        assertTrue(map.containsValue("2"));
        assertTrue(map.containsValue("1"));
    }

    @Test
    public void casperParserMustReturnCorrectResult() {
        String s = "d=1\nl=2";
        String s1[] = casperParser(s);
        assertEquals(s1[0], "--d=1");
        assertEquals(s1[1], "--l=2");
    }


}
