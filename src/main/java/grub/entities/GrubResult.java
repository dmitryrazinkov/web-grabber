package grub.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class GrubResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private java.util.Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Sc_Id")
    private Scripts script;

    private String details;

    public GrubResult() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Scripts getScript() {
        return script;
    }

    public void setScript(Scripts script) {
        this.script = script;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public GrubResult(Date date, Scripts script, String details) {
        this.date = date;
        this.script = script;
        this.details = details;
    }
}
