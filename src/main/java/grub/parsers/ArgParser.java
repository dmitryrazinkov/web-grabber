package grub.parsers;

import java.util.HashMap;
import java.util.Map;

public class ArgParser {
    public static String CasperParser(String string) {
        return string.replaceAll(".+(\\r\\n|\\n|\\r|$)","--$0");
    }

    public static Map<String,String> HarvestParser(String string) {
        Map<String,String> map=new HashMap<String,String>();
        String[] args=string.split("\\r\\n|\\n|\\r");
        for (int i=0;i<args.length;i++) {
            String arg = args[i];
            String[] keyValue = arg.split("=");
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }
/*
    public static void main(String[] args) {
        String s="p=2\nl=1";
        Map<String,String> map= HarvestParser(s);
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
    }
 */
}
