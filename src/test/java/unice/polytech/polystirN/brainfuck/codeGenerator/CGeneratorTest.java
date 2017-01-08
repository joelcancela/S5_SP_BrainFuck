package unice.polytech.polystirN.brainfuck.codeGenerator;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the C g generator class
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class CGeneratorTest {
    @Test
    public void generateEmptyFile() throws Exception {
        CGenerator cgen = new CGenerator("src/test/resources/L1/usual/empty.bf");
        cgen.generateFile();
        assertTrue(this.compareTwoFilesContent("src/test/resources/L1/usual/emptyTest.c", "src/test/resources/L1/usual/empty.c"));
    }

    @Test
    public void generateHelloWorld() throws Exception {
        CGenerator cgen = new CGenerator("src/test/resources/L2/usual/helloworld.bf");
        cgen.generateFile();
        assertTrue(this.compareTwoFilesContent("src/test/resources/L2/usual/helloworld.c", "src/test/resources/L2/usual/helloworldTest.c"));
    }

    public boolean compareTwoFilesContent(String f1, String f2) throws IOException {
        File file1 = new File(f1);
        File file2 = new File(f2);
        return FileUtils.contentEquals(file1, file2);
    }

}