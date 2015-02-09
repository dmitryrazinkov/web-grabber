package grub.controllers;

import grub.entities.ScriptsForRun;
import grub.services.GrubResultService;
import grub.services.ScriptsForRunService;
import grub.services.ScriptsService;
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
    GrubResultService grubResultService;

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
        scriptsForRunService.add(new ScriptsForRun(args, scriptsService.findByName(site1)));
        log.debug("args: {}", args);
        if (!scriptsService.allScripts().isEmpty()) {
            modelMap.addAttribute("sites", scriptsService.allScripts());
        }
        modelMap.addAttribute("onTaskScripts", scriptsForRunService.allScripts());
        return "index";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        grubResultService.deleteByScript(scriptsForRunService.getOne(id));
        scriptsForRunService.delete(scriptsForRunService.getOne(id));
        return "redirect:/";
    }

    @RequestMapping("/{id}")
    public String details(@PathVariable Integer id, ModelMap modelMap) {
        ScriptsForRun scriptsForRun = scriptsForRunService.getOne(id);
        if (scriptsForRun.isChanged()) {
            scriptsForRun.setChanged(false);
            scriptsForRun = scriptsForRunService.add(scriptsForRun);
        }
        if (scriptsForRun.getErrorMessage() != null) {
            scriptsForRun.setErrorMessage(null);
            scriptsForRun = scriptsForRunService.add(scriptsForRun);
        }
        modelMap.addAttribute("resultList", grubResultService.findByScript(scriptsForRun));
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
