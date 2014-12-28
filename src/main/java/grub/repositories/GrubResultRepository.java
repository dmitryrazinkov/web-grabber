package grub.repositories;

import grub.entities.GrubResult;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GrubResultRepository extends CrudRepository<GrubResult, Integer> {

    List<GrubResult> findBySite(String site);
}
