package grub.entities;

import javax.persistence.*;

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



}
