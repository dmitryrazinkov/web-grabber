package grub.entities;

import javax.persistence.*;

/**
 * Used for storage string representation of script and error messages
 */
@Entity
public class StringScriptOutput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(9000)")
    private String stringResult;

    private boolean error;

    private String errorMessage;

    @OneToOne(mappedBy = "stringScriptOutput", fetch = FetchType.EAGER)
    private GrabResult grabResult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStringResult() {
        return stringResult;
    }

    public void setStringResult(String stringResult) {
        this.stringResult = stringResult;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public GrabResult getGrabResult() {
        return grabResult;
    }

    public void setGrabResult(GrabResult grabResult) {
        this.grabResult = grabResult;
    }

    public StringScriptOutput() {
    }

    public StringScriptOutput(String stringResult, boolean error, String errorMessage) {
        this.stringResult = stringResult;
        this.error = error;
        this.errorMessage = errorMessage;
    }
}
