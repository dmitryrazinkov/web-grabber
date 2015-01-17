package grub.repositories;

import grub.entities.StringResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StringResultRepository extends CrudRepository<StringResult, Integer> {

}
