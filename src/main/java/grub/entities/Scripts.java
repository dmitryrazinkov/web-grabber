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
    @Basic(fetch=FetchType.LAZY)
    private byte[] file;

    private String name;

    private String description;

    @OneToMany(mappedBy = "script", fetch = FetchType.EAGER)
    List<GrubResult> results =new ArrayList<GrubResult>();

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

  //  public Scripts(String name) {
 //       this.name = name;
 //   }
}
