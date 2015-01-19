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
    @JoinColumn(name = "sc_id")
    private Scripts script;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "res_id")
    private StringScriptOutput stringScriptOutput;

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

    public StringScriptOutput getStringScriptOutput() {
        return stringScriptOutput;
    }

    public void setStringScriptOutput(StringScriptOutput stringScriptOutput) {
        this.stringScriptOutput = stringScriptOutput;
    }

    public GrubResult(Date date, Scripts script, StringScriptOutput stringScriptOutput) {
        this.date = date;
        this.script = script;
        this.stringScriptOutput = stringScriptOutput;
    }
}
