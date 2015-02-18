package grab.controllers;

import grab.entities.Scripts;
import grab.entities.ScriptsForRun;
import grab.services.GrabResultService;
import grab.services.ScriptsForRunService;
import grab.services.ScriptsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("")
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    @Autowired
    GrabResultService grabResultService;

    @Autowired
    ScriptsService scriptsService;

    @Autowired
    ScriptsForRunService scriptsForRunService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        if (!scriptsService.allScripts().isEmpty()) {
            modelMap.addAttribute("sites", scriptsService.allScripts());
        }
        modelMap.addAttribute("onTaskScripts", scriptsForRunService.allScripts());
        return "index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addSite(@ModelAttribute("site1") String site1, @ModelAttribute("args") String args, ModelMap modelMap) {
        Scripts script = scriptsService.findByName(site1);
        scriptsForRunService.add(new ScriptsForRun(args, script, script.getDefaultStatus()));
        log.debug("args: {}", args);
        if (!scriptsService.allScripts().isEmpty()) {
            modelMap.addAttribute("sites", scriptsService.allScripts());
        }
        modelMap.addAttribute("onTaskScripts", scriptsForRunService.allScripts());
        return "index";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        grabResultService.deleteByScript(scriptsForRunService.getOne(id));
        scriptsForRunService.delete(scriptsForRunService.getOne(id));
        return "redirect:/";
    }

    @RequestMapping("/{id}")
    public String details(@PathVariable Integer id, ModelMap modelMap) {
        ScriptsForRun scriptsForRun = scriptsForRunService.getOne(id);
        if (!scriptsForRun.getStatus().equals(scriptsForRun.getScript().getDefaultStatus())) {
            scriptsForRun.setStatus(scriptsForRun.getScript().getDefaultStatus());
            scriptsForRun = scriptsForRunService.add(scriptsForRun);
        }
        if (scriptsForRun.getErrorMessage() != null) {
            scriptsForRun.setErrorMessage(null);
            scriptsForRun = scriptsForRunService.add(scriptsForRun);
        }
        modelMap.addAttribute("resultList", grabResultService.findByScript(scriptsForRun));
        return "details";
    }

    @RequestMapping(value = "/ajaxArgs", method = RequestMethod.GET)
    public
    @ResponseBody
    String ajaxArgs(@RequestParam String script) {
        return scriptsService.findByName(script).getArgs();
    }

    @RequestMapping("/tableReload")
    public String reload(ModelMap modelMap) {
        modelMap.addAttribute("onTaskScripts", scriptsForRunService.allScripts());
        return "table";
    }


}
