package grub.repositories;

import grub.entities.GrubResult;
import grub.entities.Scripts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GrubResultRepository extends CrudRepository<GrubResult, Integer> {

    List<GrubResult> findByScript(Scripts script);

}
