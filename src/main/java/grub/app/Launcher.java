package grub.app;

import com.google.common.io.Files;
import grub.entities.Scripts;
import grub.entities.Site;
import grub.repositories.ScriptsRepository;
import grub.repositories.SiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

public class Launcher {
    private static final Logger log = LoggerFactory.getLogger(Launcher.class);

    public static void main(final String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.getProperties().setProperty("db-strategy", args[0]);
                ApplicationContext context = SpringApplication.run(Config.class, args);
                try {
                    DataSource dataSource = (DataSource) context.getBean("dataSource");
                    if (!dataSource.getConnection().isValid(1000)) {
                        log.error("not valid db settings");
                        ((ConfigurableApplicationContext) context).close();
                    }
                    if (args[0].equals("create")) {
                        SiteRepository siteRepository = context.getBean(SiteRepository.class);
                        ScriptsRepository scriptsRepository = context.getBean(ScriptsRepository.class);
                        Site site;
                        try {
                            site = siteRepository.save(new Site(new URL("http://yandex.ru/")));
                            scriptsRepository.save(new Scripts("onchange", site, "yandex-pogoda", "", true, Files.toByteArray(
                                    ResourceUtils.getFile("classpath:casperJs/pogoda-yandex.js"))));
                            site = siteRepository.save(new Site(new URL("http://moex.com/")));
                            scriptsRepository.save(new Scripts("onchange", site, "dollar rate", "", true, Files.toByteArray(
                                    ResourceUtils.getFile("classpath:casperJs/moex.js"))));
                            site = siteRepository.save(new Site(new URL("http://vk.com/")));
                            scriptsRepository.save(new Scripts("onchange", site, "vk", "email=\npass=\npage=", true,
                                    Files.toByteArray(ResourceUtils.getFile("classpath:casperJs/vk.js"))));
                            site = siteRepository.save(new Site(new URL("http://mail.ru/")));
                            scriptsRepository.save(new Scripts("onchange", site, "oil", "", false, Files.toByteArray(
                                    ResourceUtils.getFile("classpath:harvest/oil.xml"))));
                            site = siteRepository.save(new Site(new URL("http://lenta.ru/")));
                            scriptsRepository.save(new Scripts("onchange", site, "lenta", "", false, Files.toByteArray(
                                    ResourceUtils.getFile("classpath:harvest/lenta.xml"))));
                        } catch (MalformedURLException e) {
                            log.error("Format url wrong");
                        } catch (IOException e) {
                            log.error("Can't get file");
                        }
                    }
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
                } catch (SQLException e) {
                    log.error("not valid db settings");
                    ((ConfigurableApplicationContext) context).close();
                }

            }
        });
    }
}
