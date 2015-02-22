package grab.parsers;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for json parse
 */
public class JsonParser {
    private static final Logger log = LoggerFactory.getLogger(JsonParser.class);

    /**
     * Parse result from json string
     *
     * @param json json message
     * @return script result
     */
    public static String getResult(String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            return jsonObject.getString("result");
        } catch (JSONException e) {
            log.error("can't parse result ", e);
            return "";
        }
    }

    /**Parse error from json string
     * @param json json message
     * @return script error
     */
    public static boolean isError(String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            return Boolean.parseBoolean(jsonObject.getString("error"));
        } catch (JSONException e) {
            log.error("can't parse error ", e);
            return true;
        }
    }
}
