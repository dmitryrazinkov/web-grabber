package grub.Strategy;

import grub.entities.GrubResult;
import grub.entities.Scripts;
import grub.services.GrubResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OnChangeStrategy {
    private static final Logger log = LoggerFactory.getLogger(OnChangeStrategy.class);

    @Autowired
    GrubResultService grubResultService;

    public boolean isChanged(Scripts script) {
        List<GrubResult> results = grubResultService.findByScript(script);
        GrubResult last = null;
        GrubResult preLast = null;

        int size = results.size();
        while ((last == null || preLast == null) && size > 0) {
            if (last == null) {
                if (results.get(size - 1).getStringResult().equals("error")) {
                    size = size - 1;
                } else {
                    last = results.get(size - 1);
                    size = size - 1;
                }

            } else {
                if (results.get(size - 1).getStringResult().equals("error")) {
                    size = size - 1;
                } else {
                    preLast = results.get(size - 1);
                    size = size - 1;
                }
            }

        }
        if (last != null && preLast != null) {
            log.debug("Find last two result");
            return !last.getStringResult().equals(preLast.getStringResult());
        } else {
            log.debug("Don't find last two result");
            return false;
        }
    }
}
