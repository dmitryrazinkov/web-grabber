package grub.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Scripts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "URL")
    private Site site;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] file;

    private String name;

    private String description;

    @OneToMany(mappedBy = "script", fetch = FetchType.EAGER)
    List<GrubResult> results = new ArrayList<GrubResult>();

    public Scripts() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GrubResult> getResults() {
        return results;
    }

    public void setResults(List<GrubResult> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scripts)) return false;

        Scripts scripts = (Scripts) o;

        if (description != null ? !description.equals(scripts.description) : scripts.description != null) return false;
        if (id != null ? !id.equals(scripts.id) : scripts.id != null) return false;
        if (name != null ? !name.equals(scripts.name) : scripts.name != null) return false;
        if (results != null ? !results.equals(scripts.results) : scripts.results != null) return false;
        if (site != null ? !site.equals(scripts.site) : scripts.site != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (site != null ? site.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (results != null ? results.hashCode() : 0);
        return result;
    }

    //  public Scripts(String name) {
    //       this.name = name;
    //   }
}