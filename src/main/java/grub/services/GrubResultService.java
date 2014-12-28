package grub.services;

import grub.entities.GrubResult;
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
    public List<GrubResult> findBySite(String site) {
        List<GrubResult> grubResults = new ArrayList<GrubResult>();
        for (GrubResult grubResult : grubResultRepository.findBySite(site)) {
            grubResults.add(grubResult);
        }
        return grubResults;
    }
}
