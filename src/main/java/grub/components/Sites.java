package grub.components;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Sites {
    private List<String> sites=new ArrayList<String>();
    private List<String> sitesForGrub=new ArrayList<String>();

    public List<String> getSites() {
        return sites;
    }

    public Sites(){
        sites.add("google");
    }

    public void addSiteForGrub(String site){
        sites.remove(site);
        sitesForGrub.add(site);

    }
}
