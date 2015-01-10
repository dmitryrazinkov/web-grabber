package grub.whithCasper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class CasperAccessor {
    private static final Logger log = LoggerFactory.getLogger(CasperAccessor.class);
    public String execute(String path) {
        log.debug("CasperAccessor start");

        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec("casperjs " + path);
            p.waitFor();
            if (p.exitValue()!=0) {
                log.error("CasperJs can't be execute, checking PATH");
                return "error";
            }
            else {
                log.debug("CasperJs calling success");
            }
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            log.error("Failed access to CasperJs",e);
        }
        log.debug("CasperAccessor done");
        return output.toString();

    }


}
