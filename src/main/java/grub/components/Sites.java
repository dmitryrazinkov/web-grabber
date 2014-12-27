package grub.components;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Sites {
    private List<String> sites = new ArrayList<String>();

    public List<String> getSitesForGrub() {
        return sitesForGrub;
    }

    private List<String> sitesForGrub = new ArrayList<String>();

    public List<String> getSites() {
        return sites;
    }

    public Sites() {
        sites.add("google");
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
