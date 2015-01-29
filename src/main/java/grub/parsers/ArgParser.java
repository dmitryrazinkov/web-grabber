package grub.parsers;

public class ArgParser {
    public static String CasperParser(String string) {
        return string.replaceAll(".+(\\n|$)","--$0");
    }
}
