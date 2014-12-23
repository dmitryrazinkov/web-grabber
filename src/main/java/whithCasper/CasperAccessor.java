package whithCasper;

import java.io.*;
import java.util.Scanner;

public class CasperAccessor {
    public static void main(String[] args) throws IOException {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe");
        builder.redirectErrorStream(true);
        Process cmd = builder.start();

        final InputStream iStream = cmd.getInputStream();
        new Thread(new Runnable() {
            public void run() {
                InputStreamReader reader = new InputStreamReader(iStream);
                Scanner scan = new Scanner(reader);
                while (scan.hasNextLine()) {
                    System.out.println(scan.nextLine());
                }
            }
        }).start();

        OutputStream outStream = cmd.getOutputStream();
        PrintWriter pWriter = new PrintWriter(outStream);
        pWriter.println("chcp 65001");
        pWriter.println("cd src\\main\\resources\\casperJs\\");
        pWriter.println("casperjs google.js");
        pWriter.flush();
        pWriter.close();
    };
}
