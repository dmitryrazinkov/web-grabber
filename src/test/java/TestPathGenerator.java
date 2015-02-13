import grab.generators.PathGenerator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class TestPathGenerator {
    private static final Logger log = LoggerFactory.getLogger(TestPathGenerator.class);
    @Test
    public void generatorMustCreateFile() {
        File fileCasper = null;
        File fileHarvest = null;
        try {
            fileCasper = PathGenerator.generate(true);
            fileHarvest = PathGenerator.generate(false);
        } catch (IOException e){
            log.error("Can't create tmp file");
        }
        assertNotNull(fileCasper);
        assertNotNull(fileHarvest);
    }
}
