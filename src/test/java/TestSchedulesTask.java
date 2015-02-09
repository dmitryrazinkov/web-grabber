import com.google.common.io.Files;
import grub.app.Config;
import grub.components.ScheduledTasks;
import grub.entities.Scripts;
import grub.entities.ScriptsForRun;
import grub.entities.Site;
import grub.repositories.ScriptsRepository;
import grub.repositories.SiteRepository;
import grub.services.GrubResultService;
import grub.services.ScriptsForRunService;
import grub.services.ScriptsService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Config.class)
public class TestSchedulesTask {
    @Autowired
    ScheduledTasks scheduledTasks;

    @Autowired
    ScriptsRepository scriptsRepository;

    @Autowired
    SiteRepository siteRepository;

    @Autowired
    ScriptsForRunService scriptsForRunService;

    @Autowired
    ScriptsService scriptsService;

    @Autowired
    GrubResultService grubResultService;

    ScriptsForRun testScriptForRun;

    Site site;

    Scripts script;

    @Before
    public void before() throws IOException {
        site = siteRepository.save(new Site(new URL("http://yandex.ru/")));
        script = scriptsRepository.save(new Scripts("onchange", site, "yandex-pogoda", "", true, Files.toByteArray(
                ResourceUtils.getFile("classpath:casperJs/pogoda-yandex.js"))));
        testScriptForRun = scriptsForRunService.add(new ScriptsForRun("", scriptsService.findByName("yandex-pogoda")));
    }

    @Test
    public void taskMustAddRecordAtDB() {
        Integer before = grubResultService.countOfRecord();
        scheduledTasks.grub();
        Integer after = grubResultService.countOfRecord();
        assertNotEquals(before, after);
    }

    @After
    public void after() {
        grubResultService.deleteByScript(testScriptForRun);
        scriptsForRunService.delete(testScriptForRun);
        scriptsRepository.delete(script);
        siteRepository.delete(site);
    }
}
