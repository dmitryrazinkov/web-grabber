package grub.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Site {
    @Id
    private URL url;

    @OneToMany(mappedBy = "site", fetch = FetchType.EAGER)
    List<Scripts> scripts=new ArrayList<Scripts>();


}
