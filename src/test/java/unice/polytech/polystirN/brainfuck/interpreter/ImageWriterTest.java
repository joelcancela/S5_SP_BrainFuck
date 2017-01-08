package unice.polytech.polystirN.brainfuck.interpreter;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Tests for the ImageWriter class
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
@Ignore
public class ImageWriterTest {
	ImageWriter writer;
	File file = new File(".log");

	private boolean compareTwoFilesContent(String f1, String f2) throws IOException {
		File file1 = new File(f1);
		File file2 = new File(f2);
		return FileUtils.contentEqualsIgnoreEOL(file1, file2, null);
	}

	@Test
	public void writeL1Programs() {


	}

	@Test
	public void writeL2Programs() throws Exception {
		writer = new ImageWriter(getClass().getResource("/images/helloworld.bmp").getFile());
	}

	@Test
	public void writeBadPrograms() {

	}
}
