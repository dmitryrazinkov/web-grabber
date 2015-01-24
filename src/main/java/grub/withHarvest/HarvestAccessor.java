package grub.withHarvest;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;

import java.io.FileNotFoundException;

public class HarvestAccessor {
    public static void main(String[] args) {
        try {
            ScraperConfiguration config=new ScraperConfiguration("C:\\Users\\Дмитрий\\Desktop\\Новая папка\\test.xml");
            Scraper scraper=new Scraper(config,"C:\\");
            scraper.setDebug(true);
            scraper.execute();
            System.out.println(scraper.getContext().getVar("out").toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
