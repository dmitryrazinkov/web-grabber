package grab.services;

import grab.entities.StringScriptOutput;
import grab.repositories.StringScriptOutputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StringScriptOutputService {
    @Autowired
    StringScriptOutputRepository stringScriptOutputRepository;

    @Transactional
    public StringScriptOutput addOne(StringScriptOutput stringScriptOutput) {
        return stringScriptOutputRepository.save(stringScriptOutput);
    }

    @Transactional
    public void delete(StringScriptOutput stringScriptOutput) {
        stringScriptOutputRepository.delete(stringScriptOutput);
    }

}