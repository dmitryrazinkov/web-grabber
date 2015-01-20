package grub.services;

import grub.entities.ScriptsForRun;
import grub.repositories.ScriptsForRunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScriptsForRunService {
    @Autowired
    ScriptsForRunRepository scriptsForRunRepository;

    @Transactional
    public List<ScriptsForRun> allScripts() {
        List<ScriptsForRun> scripts = new ArrayList<ScriptsForRun>();
        for (ScriptsForRun script : scriptsForRunRepository.findAll()) {
            scripts.add(script);
        }
        return scripts;
    }

    @Transactional
    public ScriptsForRun getOne(Integer id){
        return scriptsForRunRepository.findOne(id);
    }

    @Transactional
    public ScriptsForRun add(ScriptsForRun scriptsForRun) {
        return scriptsForRunRepository.save(scriptsForRun);
    }

    @Transactional
    public void delete(ScriptsForRun scriptsForRun) {
        scriptsForRunRepository.delete(scriptsForRun);
    }
}
