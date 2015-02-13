package grab.services;

import grab.entities.GrabResult;
import grab.entities.ScriptsForRun;
import grab.repositories.GrabResultRepository;
import grab.repositories.StringScriptOutputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrabResultService {
    @Autowired
    GrabResultRepository grabResultRepository;

    @Autowired
    StringScriptOutputRepository stringScriptOutputRepository;

    @Transactional
    public GrabResult addOne(GrabResult grabResult) {
        return grabResultRepository.save(grabResult);
    }

    @Transactional
    public List<GrabResult> findByScript(ScriptsForRun script) {
        List<GrabResult> grabResults = new ArrayList<GrabResult>();
        for (GrabResult grabResult : grabResultRepository.findByScript(script)) {
            grabResults.add(grabResult);
        }
        return grabResults;
    }

    @Transactional
    public void delete(GrabResult grabResult) {
        grabResultRepository.delete(grabResult);
    }

    @Transactional
    public void deleteByScript(ScriptsForRun script) {
        List<GrabResult> grabResults = grabResultRepository.findByScript(script);
        grabResultRepository.deleteByScript(script);
        for (GrabResult grabResult : grabResults) {
            stringScriptOutputRepository.delete(stringScriptOutputRepository.findOne(
                    grabResult.getStringScriptOutput().getId()));
        }
    }

    @Transactional
    public List<GrabResult> findLastTwo(Integer id) {
        List<GrabResult> grabResults = new ArrayList<GrabResult>();
        for (GrabResult grabResult : grabResultRepository.findLastTwo(id)) {
            grabResults.add(grabResult);
        }
        return grabResults;
    }

    @Transactional
    public Integer countOfRecord() {
        return grabResultRepository.countOfRecord();
    }
}
