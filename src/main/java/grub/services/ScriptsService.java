package grub.services;

import grub.entities.Scripts;
import grub.repositories.ScriptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScriptsService {
    @Autowired
    ScriptsRepository scriptsRepository;

    public List<Scripts> allScripts(){
        List<Scripts> scripts=new ArrayList<Scripts>();
        for(Scripts script: scriptsRepository.findAll()){
            scripts.add(script);
            System.out.println(script.getName());
        }
        return scripts;
    }
}
