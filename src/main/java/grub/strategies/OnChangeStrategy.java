package grub.strategies;

import grub.entities.GrabResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Class for indicate changes in script results
 */
@Component
public class OnChangeStrategy {
    private static final Logger log = LoggerFactory.getLogger(OnChangeStrategy.class);

    /**
     * Method compare to result of any script
     *
     * @param one first result
     * @param two second result
     * @return true if changed, false otherwise
     */
    public boolean isChanged(GrabResult one, GrabResult two) {
        if (one.getStringScriptOutput().getStringResult().equals(two.getStringScriptOutput().getStringResult())) {
            return false;
        } else return true;
    }

}
