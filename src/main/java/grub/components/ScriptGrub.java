package grub.components;

import grub.entities.Scripts;
import grub.services.ScriptsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ScriptGrub {
    private static final Logger log = LoggerFactory.getLogger(ScriptGrub.class);
    @Autowired
    ScriptsService scriptsService;

    private List<Scripts> scripts = new ArrayList<Scripts>();

    public List<Scripts> getScriptsForGrub() {
        return scriptsForGrub;
    }

    private List<Scripts> scriptsForGrub = new ArrayList<Scripts>();

    public List<Scripts> getScripts() {
        return scripts;
    }


    public ScriptGrub() {
    }

    @PostConstruct
    public void init() {
        for (Scripts script : scriptsService.allScripts()) {
            scripts.add(script);
        }
        log.debug("ScriptGrub initialized");
    }

    public void addScriptForGrub(Scripts script) {
        Iterator<Scripts> it = scripts.iterator();
        while (it.hasNext()) {
            if (it.next().getName().equals(script.getName()))
                it.remove();
        }
        scriptsForGrub.add(script);
    }

    public void deleteScriptFromGrub(Scripts script) {
        Iterator<Scripts> it = scriptsForGrub.iterator();
        while (it.hasNext()) {
            if (it.next().getName().equals(script.getName()))
                it.remove();
        }
        scripts.add(script);
    }
}
