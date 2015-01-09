package grub.repositories;

import grub.entities.Scripts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScriptsRepository extends CrudRepository<Scripts, Integer> {
    @Query("Select s from Scripts s")
    List<Scripts> allScripts();

    Scripts findByName(String name);
}
