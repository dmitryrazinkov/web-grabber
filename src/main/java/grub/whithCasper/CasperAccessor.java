package grub.whithCasper;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class CasperAccessor {

    public String execute(String path) {
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            // TODO handle OS errors
            p = Runtime.getRuntime().exec("casperjs " + path);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();

    }


}
