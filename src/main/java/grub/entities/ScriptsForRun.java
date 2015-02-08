package grub.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ScriptsForRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sc_id")
    private Scripts script;

    private String args;

    private boolean changed;

    @OneToMany(mappedBy = "script", fetch = FetchType.EAGER)
    List<GrubResult> results = new ArrayList<GrubResult>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public Scripts getScript() {
        return script;
    }

    public void setScript(Scripts script) {
        this.script = script;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean change) {
        this.changed = change;
    }

    public List<GrubResult> getResults() {
        return results;
    }

    public void setResults(List<GrubResult> results) {
        this.results = results;
    }

    public ScriptsForRun() {
    }

    public ScriptsForRun(String args, Scripts script) {
        this.args = args;
        this.script = script;
    }
}
