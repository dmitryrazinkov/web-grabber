package grub.repositories;

import grub.entities.GrubResult;
import grub.entities.ScriptsForRun;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GrubResultRepository extends CrudRepository<GrubResult, Integer> {

    List<GrubResult> findByScript(ScriptsForRun script);

    void deleteByScript(ScriptsForRun script);

    @Query(value = "SELECT * FROM test.grub_result\n" +
            "\tWHERE res_id IN(\n" +
            "\t\tSELECT id FROM test.string_script_output\n" +
            "\t\t\tWHERE (error=0)AND(sc_id=:id))\n" +
            "\tORDER BY id DESC\n" +
            "    LIMIT 2", nativeQuery = true)
    List<GrubResult> findLastTwo(@Param("id") Integer id);

    @Query(value = "select COUNT(*) from test.grub_result", nativeQuery = true)
    Integer countOfRecord();

}
