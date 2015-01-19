package grub.services;

import grub.entities.StringScriptOutput;
import grub.repositories.StringResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StringResultService {
    @Autowired
    StringResultRepository stringResultRepository;

    @Transactional
    public StringScriptOutput addOne(StringScriptOutput stringScriptOutput) {
        return stringResultRepository.save(stringScriptOutput);
    }


}
