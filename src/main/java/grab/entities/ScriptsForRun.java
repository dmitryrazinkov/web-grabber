package grab.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Used for storage current scripts and info for it (args and other)
 */
@Entity
public class ScriptsForRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sc_id")
    private Scripts script;

    private String args;

    private String status;

    private String errorMessage;

    @OneToMany(mappedBy = "script", fetch = FetchType.EAGER)
    List<GrabResult> results = new ArrayList<GrabResult>();

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<GrabResult> getResults() {
        return results;
    }

    public void setResults(List<GrabResult> results) {
        this.results = results;
    }

    public ScriptsForRun() {
    }

    public ScriptsForRun(String args, Scripts script, String status) {
        this.args = args;
        this.script = script;
        this.status = status;
    }
}
