package grub.controllers;

import grub.components.ScriptGrub;
import grub.services.GrubResultService;
import grub.services.ScriptsService;
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
    @Autowired
    ScriptGrub scriptGrub;

    @Autowired
    GrubResultService grubResultService;

    @Autowired
    ScriptsService scriptsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        //sites.init();
        if (!scriptGrub.getScripts().isEmpty()) {
            modelMap.addAttribute("sites", scriptGrub.getScripts());
        }
        modelMap.addAttribute("onTaskSites", scriptGrub.getScriptsForGrub());
        return "index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addSite(@ModelAttribute("site1") String site1, ModelMap modelMap) {
        scriptGrub.addScriptForGrub(scriptsService.findByName(site1));
        if (!scriptGrub.getScripts().isEmpty()) {
            modelMap.addAttribute("sites", scriptGrub.getScripts());
        }

        modelMap.addAttribute("onTaskSites", scriptGrub.getScriptsForGrub());

        return "index";
    }

    @RequestMapping("/delete/{site}")
    public String delete(@PathVariable String site) {
        scriptGrub.deleteScriptFromGrub(scriptsService.findByName(site));
        return "redirect:/";
    }

    @RequestMapping("/{site}")
    public String details(@PathVariable String site, ModelMap modelMap) {
        modelMap.addAttribute("resultList", grubResultService.findByScript(scriptsService.findByName(site)));
        return "details";
    }


}
