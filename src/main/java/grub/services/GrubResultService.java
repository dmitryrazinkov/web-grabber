package grub.services;

import grub.entities.GrubResult;
import grub.entities.ScriptsForRun;
import grub.repositories.GrubResultRepository;
import grub.repositories.StringScriptOutputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrubResultService {
    @Autowired
    GrubResultRepository grubResultRepository;

    @Autowired
    StringScriptOutputRepository stringScriptOutputRepository;

    @Transactional
    public GrubResult addOne(GrubResult grubResult) {
        return grubResultRepository.save(grubResult);
    }

    @Transactional
    public List<GrubResult> findByScript(ScriptsForRun script) {
        List<GrubResult> grubResults = new ArrayList<GrubResult>();
        for (GrubResult grubResult : grubResultRepository.findByScript(script)) {
            grubResults.add(grubResult);
        }
        return grubResults;
    }

    @Transactional
    public void delete(GrubResult grubResult) {
        grubResultRepository.delete(grubResult);
    }

    @Transactional
    public void deleteByScript(ScriptsForRun script) {
        List<GrubResult> grubResults = grubResultRepository.findByScript(script);
        grubResultRepository.deleteByScript(script);
        for (GrubResult grubResult : grubResults) {
            stringScriptOutputRepository.delete(stringScriptOutputRepository.findOne(
                    grubResult.getStringScriptOutput().getId()));
        }
    }

    @Transactional
    public List<GrubResult> findLastTwo(Integer id) {
        List<GrubResult> grubResults = new ArrayList<GrubResult>();
        for (GrubResult grubResult : grubResultRepository.findLastTwo(id)) {
            grubResults.add(grubResult);
        }
        return grubResults;
    }

    @Transactional
    public Integer countOfRecord() {
        return grubResultRepository.countOfRecord();
    }
}
