package grub.generators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class PathGenerator {
    private static final Logger log = LoggerFactory.getLogger(PathGenerator.class);

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
