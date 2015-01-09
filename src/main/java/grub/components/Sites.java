package grub.components;

import grub.entities.Scripts;
import grub.services.ScriptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Sites {
    @Autowired
    ScriptsService scriptsService;

    private List<String> sites = new ArrayList<String>();

    public List<String> getSitesForGrub() {
        return sitesForGrub;
    }

    private List<String> sitesForGrub = new ArrayList<String>();

    public List<String> getSites() {
        return sites;
    }


    public Sites() {
        //  sites.add("google");

    }

    public void init(){

        for (Scripts script: scriptsService.allScripts()) {
            sites.add(script.getName());
        }
    }

    public void addSiteForGrub(String site) {
        sites.remove(site);
        sitesForGrub.add(site);
    }

    public void deleteSiteFromGrub(String site) {
        sitesForGrub.remove(site);
        sites.add(site);
    }
}
