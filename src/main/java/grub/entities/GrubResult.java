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
    @JoinColumn(name = "scId")
    private Scripts script;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resId")
    private StringResult stringResult;

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

    public StringResult getStringResult() {
        return stringResult;
    }

    public void setStringResult(StringResult stringResult) {
        this.stringResult = stringResult;
    }

    public GrubResult(Date date, Scripts script, StringResult stringResult) {
        this.date = date;
        this.script = script;
        this.stringResult =stringResult;
    }
}
