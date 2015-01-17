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



    public StringResult() {
    }

    public StringResult(String stringResult, boolean error) {
        this.stringResult = stringResult;
        this.error = error;
    }
}
