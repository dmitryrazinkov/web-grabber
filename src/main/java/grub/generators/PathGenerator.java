package grub.generators;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class PathGenerator {
    private static final Logger log = LoggerFactory.getLogger(PathGenerator.class);

    public static File generate(boolean isCasper) {
        boolean flag = true;
        File file = null;
        while (flag) {
            String path = RandomStringUtils.randomAlphabetic(8);
            log.debug("generated {}", path);
            Properties props = null;
            try {
                props = PropertiesLoaderUtils.loadAllProperties("application.properties");
            } catch (IOException e) {
                log.error("Can't get property", e);
            }
            if (isCasper) {
                path = props.getProperty("generate.path") + path + ".js";
            } else {
                path = props.getProperty("generate.path") + path + ".xml";
            }
            log.debug("full path: {}", path);
            file = new File(path);
            log.debug("generated file exist {}", file.exists());
            log.debug("generated file isDirectory {}", file.isDirectory());
            if (!file.exists() && !file.isDirectory()) {
                flag = false;
                log.debug("file create");
            }
        }
        return file;
    }
}
