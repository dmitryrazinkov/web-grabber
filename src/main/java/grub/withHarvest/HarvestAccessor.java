package grub.withHarvest;

import grub.entities.StringScriptOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;

import java.io.FileNotFoundException;
import java.util.Map;

import static grub.parsers.ArgParser.HarvestParser;

@Service
public class HarvestAccessor {
    private static final Logger log = LoggerFactory.getLogger(HarvestAccessor.class);

    public StringScriptOutput execute(String path, String args) {
        log.debug("HarvestAccessor start");
        try {
            ScraperConfiguration config = new ScraperConfiguration(path);
            Scraper scraper = new Scraper(config, "C:\\");
            if(!args.isEmpty()) {
                Map<String,String> map= HarvestParser(args);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    scraper.addVariableToContext(entry.getKey(), entry.getValue());
                }
            }
            scraper.setDebug(true);
            scraper.execute();
            log.debug("HarvestAccessor end");
            return new StringScriptOutput(scraper.getContext().getVar("out").toString(), false);
        } catch (FileNotFoundException e) {
            log.error("File not found", e);
            return new StringScriptOutput("", true);
        } catch (Exception e) {
            log.error("Harvest can't be execute", e);
            return new StringScriptOutput("", true);
        }

    }

}
