package grab.repositories;

import grab.entities.ScriptsForRun;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptsForRunRepository extends CrudRepository<ScriptsForRun, Integer> {

}