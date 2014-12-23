package whithCasper;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CasperAccessor {
    private InputStream iStream;
    private OutputStream outStream;

    private ArrayList<String> listFromCmd = new ArrayList<String>();

    public ArrayList<String> getListFromCmd() {
        return listFromCmd;
    }

    public CasperAccessor() throws IOException {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe");
        builder.redirectErrorStream(true);
        Process cmd = builder.start();

        iStream = cmd.getInputStream();

        outStream = cmd.getOutputStream();
    }

    public void google(String request) {
        PrintWriter pWriter = new PrintWriter(outStream);
        pWriter.println("chcp 65001");
        pWriter.println("cd src\\main\\resources\\casperJs\\");
        pWriter.println("casperjs google.js " + request);
        pWriter.flush();
        pWriter.close();
        InputStreamReader reader = new InputStreamReader(iStream);
        Scanner scan = new Scanner(reader);
        while (scan.hasNextLine()) {
            listFromCmd.add(scan.nextLine());
        }

    }


}
