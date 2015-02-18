import com.google.common.io.Files;
import grab.app.Config;
import grab.components.ScheduledTasks;
import grab.entities.Scripts;
import grab.entities.ScriptsForRun;
import grab.entities.Site;
import grab.repositories.ScriptsRepository;
import grab.repositories.SiteRepository;
import grab.services.GrabResultService;
import grab.services.ScriptsForRunService;
import grab.services.ScriptsService;
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
    GrabResultService grabResultService;

    ScriptsForRun testScriptForRun;

    Site site;

    Scripts script;

    @Before
    public void before() throws IOException {
        site = siteRepository.save(new Site(new URL("http://ya.ru/")));
        script = scriptsRepository.save(new Scripts("onchange", site, "yandex-pogoda", "", true, Files.toByteArray(
                ResourceUtils.getFile("classpath:casperJs/pogoda-yandex.js")), "Data don't changed"));
        testScriptForRun = scriptsForRunService.add(new ScriptsForRun("", script, script.getDefaultStatus()));
    }

    @Test
    public void taskMustAddRecordAtDB() {
        Integer before = grabResultService.countOfRecord();
        scheduledTasks.grub();
        Integer after = grabResultService.countOfRecord();
        assertNotEquals(before, after);
    }

    @After
    public void after() {
        grabResultService.deleteByScript(testScriptForRun);
        scriptsForRunService.delete(testScriptForRun);
        scriptsRepository.delete(script);
        siteRepository.delete(site);
    }
}
