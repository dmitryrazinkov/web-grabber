package grub.repositories;

import grub.entities.GrabResult;
import grub.entities.ScriptsForRun;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GrabResultRepository extends CrudRepository<GrabResult, Integer> {

    List<GrabResult> findByScript(ScriptsForRun script);

    void deleteByScript(ScriptsForRun script);

    @Query(value = "SELECT * FROM practice.grab_result\n" +
            "\tWHERE res_id IN(\n" +
            "\t\tSELECT id FROM practice.string_script_output\n" +
            "\t\t\tWHERE (error=0)AND(sc_id=:id))\n" +
            "\tORDER BY id DESC\n" +
            "    LIMIT 2", nativeQuery = true)
    List<GrabResult> findLastTwo(@Param("id") Integer id);

    @Query(value = "select COUNT(*) from practice.grab_result", nativeQuery = true)
    Integer countOfRecord();

}
