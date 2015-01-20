package grub.repositories;

import grub.entities.ScriptsForRun;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptsForRunRepository extends CrudRepository<ScriptsForRun, Integer> {

    //List<Integer> findScId();
}
