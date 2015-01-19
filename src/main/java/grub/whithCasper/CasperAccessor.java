package grub.whithCasper;

import grub.entities.StringScriptOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class CasperAccessor {
    private static final Logger log = LoggerFactory.getLogger(CasperAccessor.class);

    public StringScriptOutput execute(String path, String args) {
        log.debug("CasperAccessor start");
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec("casperjs " + path + " " +
                    args);
            p.waitFor();
            if (p.exitValue() != 0) {
                log.error("CasperJs can't be execute");
                return new StringScriptOutput("", true);
            } else {
                log.debug("CasperJs calling success");
            }
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    output.append(line + "\n");
                }
            }
        } catch (Exception e) {
            log.error("Failed access to CasperJs", e);
            return new StringScriptOutput("", true);
        }
        log.debug("Casper access done");
        if (output.toString().isEmpty()) {
            log.error("Can't get result");
            return new StringScriptOutput("", true);
        }
        return new StringScriptOutput(output.toString(), false);
    }
}
