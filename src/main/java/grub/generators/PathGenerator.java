package grub.generators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Class for generate tmp file
 */
public class PathGenerator {
    private static final Logger log = LoggerFactory.getLogger(PathGenerator.class);

    /**
     * Method generate tmp file
     * @param isCasper indicate which type of file must generated
     * @return generated file*/
    public static File generate(boolean isCasper) throws IOException {
        File file = null;
        if (isCasper) {
            file = File.createTempFile("cas", ".js");
        } else {
            file = File.createTempFile("harv", ".xml");
        }
        return file;
    }
}
