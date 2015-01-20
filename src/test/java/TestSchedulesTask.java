import grub.app.Config;
import grub.components.ScheduledTasks;
import grub.entities.ScriptsForRun;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Config.class)
public class TestSchedulesTask {
    @Autowired
    ScheduledTasks scheduledTasks;

    @Autowired
    ScriptsForRunService scriptsForRunService;

    @Autowired
    ScriptsService scriptsService;

    @Autowired
    GrubResultService grubResultService;

    ScriptsForRun testScriptForRun;

    @Before
    public void before() {
        testScriptForRun = scriptsForRunService.add(new ScriptsForRun("", scriptsService.findByName("pogoda-yandex")));
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
    }
}
