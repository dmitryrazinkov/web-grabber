package grub.services;

import grub.entities.GrubResult;
import grub.entities.Scripts;
import grub.repositories.GrubResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GrubResultService {
    @Autowired
    GrubResultRepository grubResultRepository;

    @Transactional
    public GrubResult addOne(GrubResult grubResult) {
        return grubResultRepository.save(grubResult);
    }

    @Transactional
    public List<GrubResult> findByScript(Scripts script) {
        List<GrubResult> grubResults = new ArrayList<GrubResult>();
        for (GrubResult grubResult : grubResultRepository.findByScript(script)) {
            grubResults.add(grubResult);
        }
        return grubResults;
    }

    @Transactional
    public void delete(GrubResult grubResult){
        grubResultRepository.delete(grubResult);
    }

    @Transactional
    public void deleteByScript(Scripts script){
        grubResultRepository.deleteByScript(script);
    }
}
