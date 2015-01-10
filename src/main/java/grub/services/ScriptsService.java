package grub.services;

import grub.entities.Scripts;
import grub.repositories.ScriptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScriptsService {
    @Autowired
    ScriptsRepository scriptsRepository;

    @Transactional
    public List<Scripts> allScripts() {
        List<Scripts> scripts = new ArrayList<Scripts>();
        for (Scripts script : scriptsRepository.findAll()) {
            scripts.add(script);
            System.out.println(script.getName());
        }
        return scripts;
    }

    @Transactional
    public Scripts findByName(String name) {
        return scriptsRepository.findByName(name);
    }
}
