package grub.Strategy;

import grub.entities.GrubResult;
import grub.services.GrubResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OnChangeStrategy {
    private static final Logger log = LoggerFactory.getLogger(OnChangeStrategy.class);

    @Autowired
    GrubResultService grubResultService;

    public boolean isChanged(GrubResult one, GrubResult two) {
        if (one.getStringScriptOutput().getStringResult().equals(two.getStringScriptOutput().getStringResult())) {
            return false;
        } else return true;
    }

}
