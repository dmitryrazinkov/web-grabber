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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        // scriptGrub.addScriptForGrub(scriptsService.findByName(site1));
        scriptsForRunService.add(new ScriptsForRun("", scriptsService.findByName(site1)));
        log.debug("args: {}",args);
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
        modelMap.addAttribute("resultList", grubResultService.findByScript(scriptsForRunService.getOne(id)));
        return "details";
    }


}
