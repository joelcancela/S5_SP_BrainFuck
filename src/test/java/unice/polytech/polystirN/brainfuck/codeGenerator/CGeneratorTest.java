package unice.polytech.polystirN.brainfuck.codeGenerator;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Tests for the C generator class
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class CGeneratorTest {
	@Test
	public void generateEmptyFile() throws Exception {
		CGenerator cgen = new CGenerator(getClass().getResource("/L1/usual/empty.bf").getFile());
		cgen.generateFile();
		assertTrue(compareTwoFilesContent(getClass().getResource("/L1/usual/emptyTest.c").getFile(), getClass().getResource("/L1/usual/empty.c").getFile()));
	}

	@Test
	public void generateHelloWorld() throws Exception {
		CGenerator cgen = new CGenerator(getClass().getResource("/L2/usual/helloworld.bf").getFile());
		cgen.generateFile();
		assertTrue(compareTwoFilesContent(getClass().getResource("/L2/usual/helloworld.c").getFile(), getClass().getResource("/L2/usual/helloworldTest.c").getFile()));
	}

	private boolean compareTwoFilesContent(String f1, String f2) throws IOException {
		File file1 = new File(f1);
		File file2 = new File(f2);
		return FileUtils.contentEqualsIgnoreEOL(file1, file2, null);
	}

}