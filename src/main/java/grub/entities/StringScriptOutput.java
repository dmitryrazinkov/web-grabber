package grub.entities;

import javax.persistence.*;

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
    private GrubResult grubResult;

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

    public GrubResult getGrubResult() {
        return grubResult;
    }

    public void setGrubResult(GrubResult grubResult) {
        this.grubResult = grubResult;
    }

    public StringScriptOutput() {
    }

    public StringScriptOutput(String stringResult, boolean error, String errorMessage) {
        this.stringResult = stringResult;
        this.error = error;
        this.errorMessage = errorMessage;
    }
}
