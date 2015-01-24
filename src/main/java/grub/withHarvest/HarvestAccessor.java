package grub.withHarvest;

import grub.entities.StringScriptOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;

import java.io.FileNotFoundException;

@Service
public class HarvestAccessor {
    private static final Logger log = LoggerFactory.getLogger(HarvestAccessor.class);

    public StringScriptOutput execute(String path) {
        log.debug("HarvestAccessor start");
        try {
            ScraperConfiguration config=new ScraperConfiguration(path);
            Scraper scraper=new Scraper(config,"C:\\");
            scraper.setDebug(true);
            scraper.execute();
            log.debug("HarvestAccessor end");
            return new StringScriptOutput(scraper.getContext().getVar("out").toString(),false);
        } catch (FileNotFoundException e) {
            log.error("File not found",e);
            return new StringScriptOutput("",true);
        } catch (Exception e) {
            log.error("Harvest can't be execute",e);
            return  new StringScriptOutput("",true);
        }

    }

}
