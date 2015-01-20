package grub.strategy;

import grub.entities.GrubResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OnChangeStrategy {
    private static final Logger log = LoggerFactory.getLogger(OnChangeStrategy.class);

    public boolean isChanged(GrubResult one, GrubResult two) {
        if (one.getStringScriptOutput().getStringResult().equals(two.getStringScriptOutput().getStringResult())) {
            return false;
        } else return true;
    }

}
