package grub.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Results of script entity
 */
@Entity
public class GrabResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private java.util.Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sc_id")
    private ScriptsForRun script;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "res_id")
    private StringScriptOutput stringScriptOutput;

    public GrabResult() {
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

    public ScriptsForRun getScript() {
        return script;
    }

    public void setScript(ScriptsForRun script) {
        this.script = script;
    }

    public StringScriptOutput getStringScriptOutput() {
        return stringScriptOutput;
    }

    public void setStringScriptOutput(StringScriptOutput stringScriptOutput) {
        this.stringScriptOutput = stringScriptOutput;
    }

    public GrabResult(Date date, ScriptsForRun script, StringScriptOutput stringScriptOutput) {
        this.date = date;
        this.script = script;
        this.stringScriptOutput = stringScriptOutput;
    }
}
