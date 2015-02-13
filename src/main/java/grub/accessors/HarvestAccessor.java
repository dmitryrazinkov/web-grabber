package grub.accessors;

import grub.entities.StringScriptOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;

import java.io.FileNotFoundException;
import java.util.Map;

import static grub.parsers.ArgParser.harvestParser;

/**
 * Service for access and execute Harvest
 */
@Service
public class HarvestAccessor {
    private static final Logger log = LoggerFactory.getLogger(HarvestAccessor.class);

    /**
     * Access to Harvest and execute script
     * @param path path of Harvest script file
     * @param args args for script
     * @return result of script running*/
    public StringScriptOutput execute(String path, String args) {
        log.debug("HarvestAccessor start");
        try {
            ScraperConfiguration config = new ScraperConfiguration(path);
            Scraper scraper = new Scraper(config, "");
            if (!args.isEmpty()) {
                Map<String, String> map = harvestParser(args);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    scraper.addVariableToContext(entry.getKey(), entry.getValue());
                }
            }
            scraper.setDebug(true);
            scraper.execute();
            log.debug("HarvestAccessor end");
            return new StringScriptOutput(scraper.getContext().getVar("out").toString(), false, "");
        } catch (FileNotFoundException e) {
            log.error("File not found", e);
            return new StringScriptOutput("", true, "Can't create tmp file in Harvest");
        } catch (Exception e) {
            log.error("Harvest can't be execute", e);
            return new StringScriptOutput("", true, "Harvest can't be execute");
        }

    }

}
