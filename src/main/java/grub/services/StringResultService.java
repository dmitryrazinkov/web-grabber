package grub.services;

import grub.entities.StringResult;
import grub.repositories.StringResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StringResultService {
    @Autowired
    StringResultRepository stringResultRepository;

    @Transactional
    public StringResult addOne(StringResult stringResult) {
        return stringResultRepository.save(stringResult);
    }
}
