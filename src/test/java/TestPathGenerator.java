import grub.generators.PathGenerator;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class TestPathGenerator {

    @Test
    public void generatorMustCreateFile() {
        File fileCasper = PathGenerator.generate(true);
        File fileHarvest = PathGenerator.generate(false);
        assertNotNull(fileCasper);
        assertNotNull(fileHarvest);
    }
}
