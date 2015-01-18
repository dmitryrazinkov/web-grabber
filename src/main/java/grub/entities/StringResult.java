package grub.entities;

import javax.persistence.*;

@Entity
public class StringResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String stringResult;

    private boolean error;

    @OneToOne(mappedBy = "stringResult",fetch= FetchType.EAGER)
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

    public GrubResult getGrubResult() {
        return grubResult;
    }

    public void setGrubResult(GrubResult grubResult) {
        this.grubResult = grubResult;
    }

    public StringResult() {
    }

    public StringResult(String stringResult, boolean error) {
        this.stringResult = stringResult;
        this.error = error;
    }
}
