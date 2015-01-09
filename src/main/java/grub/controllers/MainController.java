package grub.controllers;

import grub.components.Sites;
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
    Sites sites;

    @Autowired
    GrubResultService grubResultService;

    @Autowired
    ScriptsService scriptsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        //sites.init();
        if (!sites.getScripts().isEmpty()) {
            modelMap.addAttribute("sites", sites.getScripts());
        }
        modelMap.addAttribute("onTaskSites", sites.getScriptsForGrub());
        return "index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addSite(@ModelAttribute("site1") String site1, ModelMap modelMap) {
        sites.addScriptForGrub(scriptsService.findByName(site1));
        if (!sites.getScripts().isEmpty()) {
            modelMap.addAttribute("sites", sites.getScripts());
        }

        modelMap.addAttribute("onTaskSites", sites.getScriptsForGrub());

        return "index";
    }

    @RequestMapping("/delete/{site}")
    public String delete(@PathVariable String site) {
        sites.deleteScriptFromGrub(scriptsService.findByName(site));
        return "redirect:/";
    }

    @RequestMapping("/{site}")
    public String details(@PathVariable String site, ModelMap modelMap) {
        modelMap.addAttribute("resultList", grubResultService.findByScript(scriptsService.findByName(site)));
        return "details";
    }


}
