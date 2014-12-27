package grub.services;

import grub.entities.GrubResult;
import grub.repositories.GrubResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrubResultService {
    @Autowired
    GrubResultRepository grubResultRepository;

    public GrubResult addOne(GrubResult grubResult) {
        return grubResultRepository.save(grubResult);
    }
}
