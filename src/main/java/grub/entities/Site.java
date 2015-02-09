package grub.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Sites
 */
@Entity
public class Site {
    @Id
    private URL url;

    @OneToMany(mappedBy = "site", fetch = FetchType.EAGER)
    List<Scripts> scripts = new ArrayList<Scripts>();

    public List<Scripts> getScripts() {
        return scripts;
    }

    public void setScripts(List<Scripts> scripts) {
        this.scripts = scripts;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Site(URL url) {
        this.url = url;
    }

    public Site() {
    }
}
