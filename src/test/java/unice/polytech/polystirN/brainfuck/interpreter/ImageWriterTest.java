package unice.polytech.polystirN.brainfuck.interpreter;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the ImageWriter class
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class ImageWriterTest {
	ImageWriter writer;
	ByteArrayOutputStream baos;
	OutputStream out;
	Interpreter intrprtr;
	private int mask = 0xff; //Use to translate bytes into integers

	@Before
	public void init() throws Exception {
		File f = new File("./src/test/resources/mocks/temp.bmp");
		if (!f.exists())
			f.createNewFile();
		baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(baos));
	}


	@Test
	public void writeL1Programs() throws Exception {
		writer = new ImageWriter(getClass().getResource("/L1/usual/INCRC0by7.bf").getFile());
		writer.translate();

		assertTrue(baos.toByteArray().length != 0);

		File temp = new File("./target/test-classes/mocks/temp.bmp");
		out = new FileOutputStream(temp);
		out.write(baos.toByteArray());

		intrprtr = new Interpreter(getClass().getResource("/mocks/temp.bmp").getFile());
		intrprtr.interpretFile();
		assertEquals(0, intrprtr.getMemory().getP());//Pointer hasn't moved
		assertEquals(7, intrprtr.getMemory().getCells()[0] & mask);//Result of this program is C0:7
	}

	@Test
	public void writeL2Programs() throws Exception {
		writer = new ImageWriter(getClass().getResource("/L2/usual/helloworld.bf").getFile());
		writer.translate();

		assertTrue(baos.toByteArray().length != 0);

		File temp = new File("./target/test-classes/mocks/temp.bmp");
		out = new FileOutputStream(temp);
		out.write(baos.toByteArray());

		intrprtr = new Interpreter(getClass().getResource("/mocks/temp.bmp").getFile());
		intrprtr.interpretFile();
		assertEquals(4, intrprtr.getMemory().getP());//Result of this program is C4:10
		assertEquals(0, intrprtr.getMemory().getCells()[0] & mask);//0 is a space in ascii
		assertEquals(87, intrprtr.getMemory().getCells()[1] & mask);//87 is W in ascii
		assertEquals(100, intrprtr.getMemory().getCells()[2] & mask);//100 is d in ascii
		assertEquals(33, intrprtr.getMemory().getCells()[3] & mask);//33 is ! in ascii
		assertEquals(10, intrprtr.getMemory().getCells()[4] & mask);//10 is a line feed in ascii

	}
}
