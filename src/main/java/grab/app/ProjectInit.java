package grab.app;

import com.google.common.io.Files;
import grab.entities.Scripts;
import grab.entities.Site;
import grab.repositories.ScriptsRepository;
import grab.repositories.SiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

public class ProjectInit implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(ProjectInit.class);

    private String[] args;

    ProjectInit(String[] args) {
        this.args = args;
    }

    @Override
    public void run() {
        System.getProperties().setProperty("db-strategy", args[0]);
        ApplicationContext context = SpringApplication.run(Config.class, args);
        checkDbConfiguration(context);
        if (args[0].equals("create")) {
            initDB(context);
        }
        openBrowserTab();
    }

    private void initDB(ApplicationContext context) {
        SiteRepository siteRepository = context.getBean(SiteRepository.class);
        ScriptsRepository scriptsRepository = context.getBean(ScriptsRepository.class);
        Site site;
        try {
            site = siteRepository.save(new Site(new URL("http://yandex.ru/")));
            scriptsRepository.save(new Scripts("onchange", site, "yandex-pogoda", "", true,
                    Files.toByteArray(
                            ResourceUtils.getFile("classpath:casperJs/pogoda-yandex.js")), "Data don't changed"));
            site = siteRepository.save(new Site(new URL("http://moex.com/")));
            scriptsRepository.save(new Scripts("onchange", site, "dollar rate", "", true,
                    Files.toByteArray(
                            ResourceUtils.getFile("classpath:casperJs/moex.js")), "Data don't changed"));
            site = siteRepository.save(new Site(new URL("http://vk.com/")));
            scriptsRepository.save(new Scripts("onchange", site, "vk", "email=\npass=\npage=", true,
                    Files.toByteArray(ResourceUtils.getFile("classpath:casperJs/vk.js")), "Data don't changed"));
            site = siteRepository.save(new Site(new URL("http://mail.ru/")));
            scriptsRepository.save(new Scripts("onchange", site, "oil", "", false, Files.toByteArray(
                    ResourceUtils.getFile("classpath:harvest/oil.xml")), "Data don't changed"));
            site = siteRepository.save(new Site(new URL("http://lenta.ru/")));
            scriptsRepository.save(new Scripts("onchange", site, "lenta", "", false, Files.toByteArray(
                    ResourceUtils.getFile("classpath:harvest/lenta.xml")), "Data don't changed"));
            scriptsRepository.save(new Scripts("onwait", site, "euro", "lessThan=", true, Files.toByteArray(
                    ResourceUtils.getFile("classpath:casperJs/euro.js")), "Expected data don't available"));
            site = siteRepository.save(new Site(new URL("http://bdt.ru/")));
            scriptsRepository.save(new Scripts("onwait", site, "bdt", "page=", true, Files.toByteArray(
                    ResourceUtils.getFile("classpath:casperJs/bdt.js")), "Expected data don't available"));
        } catch (MalformedURLException e) {
            log.error("Format url wrong", e);
        } catch (IOException e) {
            log.error("Can't get file", e);
        }
    }

    private void checkDbConfiguration(ApplicationContext context) {
        try {
            DataSource dataSource = (DataSource) context.getBean("dataSource");
            if (!dataSource.getConnection().isValid(1000)) {
                log.error("not valid db settings");
                ((ConfigurableApplicationContext) context).close();
            }
        } catch (SQLException e) {
            log.error("not valid db settings", e);
            ((ConfigurableApplicationContext) context).close();
        }
    }

    private void openBrowserTab() {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URL("http://localhost:4040").toURI());
            } catch (IOException e) {
                log.error("Desktop api is not supported", e);
            } catch (Exception e) {
                log.error("Failed open browser tab", e);
            }
        }
    }
}
