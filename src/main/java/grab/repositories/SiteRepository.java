package grab.repositories;

import grab.entities.Site;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.net.URL;

@Repository
public interface SiteRepository extends CrudRepository<Site, URL> {
}
