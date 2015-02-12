import grub.parsers.JsonParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestJsonOutput {

    String testJson = "{\"result\":\"result\",\"error\":false}";

    @Test
    public void JsonParserMustReturnCorrectResult() {
        assertEquals(JsonParser.getResult(testJson), "result");
    }

    @Test
    public void JsonParserMustReturnCorrectError() {
        assertEquals(JsonParser.isError(testJson), false);
    }


}
