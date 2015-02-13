package grab.parsers;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for parse String args
 */
public class ArgParser {

    /**Parse for CasperJs
     * @param string String args
     * @return String args for casperJs */
    public static String[] casperParser(String string) {
        string = string.replaceAll(".+(\\r\\n|\\n|\\r|$)", "--$0");
        return string.split("\\r\\n|\\n|\\r");
    }

    /**Parse for Harvest
     * @param string String args
     * @return Map of args and value */
    public static Map<String, String> harvestParser(String string) {
        Map<String, String> map = new HashMap<String, String>();
        String[] args = string.split("\\r\\n|\\n|\\r");
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            String[] keyValue = arg.split("=");
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }

}
