package grub.controllers;

import grub.components.Sites;
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


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        if (!sites.getSites().isEmpty()) {
            modelMap.addAttribute("sites", sites.getSites());
        }

        modelMap.addAttribute("onTaskSites", sites.getSitesForGrub());



        return "index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addSite(@ModelAttribute("site") String site) {
        sites.addSiteForGrub(site);
        return "redirect:/";
    }

    @RequestMapping("/delete/{site}")
    public String delete(@PathVariable String site) {
        sites.deleteSiteFromGrub(site);
        return "redirect:/";
    }


}
