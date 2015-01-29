package grub.parsers;

public class ArgParser {
    public static String CasperParser(String string) {
        return string.replaceAll(".+(\\r\\n|\\n|\\r|$)","--$0");
    }
}
