package grab.repositories;

import grab.entities.StringScriptOutput;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StringScriptOutputRepository extends CrudRepository<StringScriptOutput, Integer> {

}
