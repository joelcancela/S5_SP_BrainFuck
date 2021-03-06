package unice.polytech.polystirN.brainfuck.interpreter;

import org.junit.Before;
import org.junit.Test;

import java.io.OutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the Interpreter class
 * Non-regression tests
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class InterpreterTest {
	private Interpreter intrptr;
	private int mask = 0xff; //Use to translate bytes into integers
	private String inputMock = getClass().getResource("/mocks/input.txt").getFile();

	@Before
	public void setUp() {
		//NoOutputs
		System.setOut(new PrintStream(new OutputStream() {
			public void write(int b) {
				//DO NOTHING
			}
		}));
	}


	/**************
	 ** LEVEL 1 **
	 **************/

	@Test
	public void interpretBfLongUsualFiles() throws Exception {

		//Simple 3 increments and 3 decrements
		intrptr = new Interpreter(getClass().getResource("/L1/usual/DECRSimple.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());//Pointer hasn't moved
		assertEquals(0, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:0

		//Empty file
		intrptr = new Interpreter(getClass().getResource("/L1/usual/empty.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());//Pointer hasn't moved
		assertEquals(0, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:0

		//Increment 7 times
		intrptr = new Interpreter(getClass().getResource("/L1/usual/INCRC0by7.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());//Pointer hasn't moved
		assertEquals(7, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:7

		//Increment 255 times
		intrptr = new Interpreter(getClass().getResource("/L1/usual/INCRMax255.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());//Pointer hasn't moved
		assertEquals(255, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:255

		//Moving the pointer left and right
		intrptr = new Interpreter(getClass().getResource("/L1/usual/LEFT&RIGHT.bf").getFile());
		intrptr.interpretFile();
		assertEquals(1, intrptr.getMemory().getP());//Pointer has moved to 1 at the end
		assertEquals(0, intrptr.getMemory().getCells()[1] & mask);//Result of this program is C1:0
	}

	@Test
	public void interpretBfLongErrorFiles() throws Exception {

		//Value of the current cell goes under 0
		try {
			intrptr = new Interpreter(getClass().getResource("/L1/errors/DECRError.bf").getFile());
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("MemoryUnderflowException", e.getClass().getSimpleName());
			assertEquals("value can't be negative", e.getMessage());
		}

		//Value of the current cell goes above 255
		try {
			intrptr = new Interpreter(getClass().getResource("/L1/errors/INCRError256.bf").getFile());
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("MemoryOverflowException", e.getClass().getSimpleName());
			assertEquals("value can't be higher than 255", e.getMessage());
		}

		//Value of the pointer goes under 0
		try {
			intrptr = new Interpreter(getClass().getResource("/L1/errors/LEFTError.bf").getFile());
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("PointerPositionOutOfBoundsException", e.getClass().getSimpleName());
			assertEquals("pointer can't be moved to the left (already at position 0)", e.getMessage());
		}

		//Value of the pointer goes above 29999 (Max memory size capacity-1)
		try {
			intrptr = new Interpreter(getClass().getResource("/L1/errors/RIGHTError.bf").getFile());
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("PointerPositionOutOfBoundsException", e.getClass().getSimpleName());
			assertEquals("pointer can't be moved to the right (already at position 29999)", e.getMessage());
		}

	}

	@Test
	public void incorrectFileExtension() throws Exception {
		try {
			intrptr = new Interpreter(getClass().getResource("/L1/errors/program.bfck").getFile());
			intrptr.interpretFile();
		} catch (Exception e) {
			assertEquals("IncorrectFileTypeException", e.getClass().getSimpleName());
			assertEquals("Incorrect file's extension, expected .bf or .bmp", e.getMessage());
		}
	}

	/**************
	 ** LEVEL 2 **
	 **************/

	@Test
	public void interpretBfShortUsualFiles() throws Exception {
		//Writes "Bonjour" using loops
		intrptr = new Interpreter(getClass().getResource("/L2/usual/bonjour.bf").getFile());
		intrptr.interpretFile();
		assertEquals(6, intrptr.getMemory().getP());//Result of this program is C6:114
		assertEquals(66, intrptr.getMemory().getCells()[0] & mask);//66 is B in ascii
		assertEquals(111, intrptr.getMemory().getCells()[1] & mask);//111 is o in ascii
		assertEquals(110, intrptr.getMemory().getCells()[2] & mask);//110 is n in ascii
		assertEquals(106, intrptr.getMemory().getCells()[3] & mask);//106 is j in ascii
		assertEquals(111, intrptr.getMemory().getCells()[4] & mask);//111 is o in ascii
		assertEquals(117, intrptr.getMemory().getCells()[5] & mask);//117 is u in ascii
		assertEquals(114, intrptr.getMemory().getCells()[6] & mask);//114 is r in ascii

		//Alternate increment and decrements 3 times each
		intrptr = new Interpreter(getClass().getResource("/L2/usual/decrementSimple.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());//Result of this program is C0:0
		assertEquals(0, intrptr.getMemory().getCells()[0] & mask);//The cell number 0 is at 0

		//Writes "Hello World!" using loops
		//It uses only 5 memory cells, containing "Hello_" then "World!"
		intrptr = new Interpreter(getClass().getResource("/L2/usual/helloworld.bf").getFile());
		intrptr.interpretFile();
		assertEquals(4, intrptr.getMemory().getP());//Result of this program is C4:10
		assertEquals(0, intrptr.getMemory().getCells()[0] & mask);//0 is a space in ascii
		assertEquals(87, intrptr.getMemory().getCells()[1] & mask);//87 is W in ascii
		assertEquals(100, intrptr.getMemory().getCells()[2] & mask);//100 is d in ascii
		assertEquals(33, intrptr.getMemory().getCells()[3] & mask);//33 is ! in ascii
		assertEquals(10, intrptr.getMemory().getCells()[4] & mask);//10 is a line feed in ascii

		//In, reads the first char of the input file and write it in a memory cell
		//Here it's "L"
		intrptr = new Interpreter(getClass().getResource("/L2/usual/in.bf").getFile(), inputMock, null);
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());//Result of this program is C0:76
		assertEquals(76, intrptr.getMemory().getCells()[0] & mask);

		//Increment 7 times
		intrptr = new Interpreter(getClass().getResource("/L2/usual/incrementC0by7.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());//Pointer hasn't moved
		assertEquals(7, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:7

		//Increment 255 times
		intrptr = new Interpreter(getClass().getResource("/L2/usual/incrementMax255.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());//Pointer hasn't moved
		assertEquals(255, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:255

		intrptr = new Interpreter(getClass().getResource("/L2/usual/jumpInternalLoop.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(0, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:0

		intrptr = new Interpreter(getClass().getResource("/L2/usual/jumpInternalLoop2.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(0, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:0

		intrptr = new Interpreter(getClass().getResource("/L2/usual/jumpInternalLoop3.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(0, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:0

		intrptr = new Interpreter(getClass().getResource("/L2/usual/jumpSimple.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(1, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:1

		intrptr = new Interpreter(getClass().getResource("/L2/usual/jumpSimple2.bf").getFile());
		intrptr.interpretFile();
		assertEquals(1, intrptr.getMemory().getP());
		assertEquals(5, intrptr.getMemory().getCells()[1] & mask);//Result of this program is C1:5

		intrptr = new Interpreter(getClass().getResource("/L2/usual/left&right1.bf").getFile());
		intrptr.interpretFile();
		assertEquals(1, intrptr.getMemory().getP());
		assertEquals(0, intrptr.getMemory().getCells()[1] & mask);//Result of this program is C1:0


	}

	@Test
	public void interpretBfShortErrorFiles() throws Exception {
		intrptr = new Interpreter(getClass().getResource("/L2/errors/decrementC0by7.bf").getFile());
		try {
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("MemoryUnderflowException", e.getClass().getSimpleName());
			assertEquals("value can't be negative", e.getMessage());
		}


		intrptr = new Interpreter(getClass().getResource("/L2/errors/decrementError.bf").getFile());
		try {
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("MemoryUnderflowException", e.getClass().getSimpleName());
			assertEquals("value can't be negative", e.getMessage());
		}

		intrptr = new Interpreter(getClass().getResource("/L2/errors/incrementError256.bf").getFile());
		try {
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("MemoryOverflowException", e.getClass().getSimpleName());
			assertEquals("value can't be higher than 255", e.getMessage());
		}

		intrptr = new Interpreter(getClass().getResource("/L2/errors/left_outMin.bf").getFile());
		try {
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("PointerPositionOutOfBoundsException", e.getClass().getSimpleName());
			assertEquals("pointer can't be moved to the left (already at position 0)", e.getMessage());
		}


		intrptr = new Interpreter(getClass().getResource("/L2/errors/right_outMax.bf").getFile());
		try {
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("PointerPositionOutOfBoundsException", e.getClass().getSimpleName());
			assertEquals("pointer can't be moved to the right (already at position 29999)", e.getMessage());
		}

	}

	@Test
	public void checkMalFormed() throws Exception {
		//Mal formé :
		try {
			intrptr = new Interpreter(getClass().getResource("/L2/errors/malformed/jumpNoBack.bf").getFile());
			intrptr.check();
			fail();
		} catch (Exception e) {
			assertEquals("BadLoopException", e.getClass().getSimpleName());
			assertEquals("Loop without end : Missing BACK operator", e.getMessage());
		}
		try {
			intrptr = new Interpreter(getClass().getResource("/L2/errors/malformed/jumpBackAlone.bf").getFile());
			intrptr.check();
			fail();
		} catch (Exception e) {
			assertEquals("BadLoopException", e.getClass().getSimpleName());
			assertEquals("Loop without start : Missing JUMP operator", e.getMessage());
		}
		try {
			intrptr = new Interpreter(getClass().getResource("/L2/errors/malformed/BADPROG.bf").getFile());
			intrptr.check();
			fail();
		} catch (Exception e) {
			assertEquals("SyntaxErrorException", e.getClass().getSimpleName());
			assertEquals("Incorrect word operator", e.getMessage());
		}


	}


	@Test
	public void interpretBmpFiles() throws Exception {

		//Image Interpreter
		intrptr = new Interpreter(getClass().getResource("/images/12345.bmp").getFile());
		intrptr.interpretFile();
		assertEquals(1, intrptr.getMemory().getCells()[0] & mask);
		assertEquals(2, intrptr.getMemory().getCells()[1] & mask);
		assertEquals(3, intrptr.getMemory().getCells()[2] & mask);
		assertEquals(4, intrptr.getMemory().getCells()[3] & mask);
		assertEquals(5, intrptr.getMemory().getCells()[4] & mask);

		//Image Interpreter
		intrptr = new Interpreter(getClass().getResource("/images/54321to33441.bmp").getFile());
		intrptr.interpretFile();
		assertEquals(3, intrptr.getMemory().getCells()[0] & mask);
		assertEquals(3, intrptr.getMemory().getCells()[1] & mask);
		assertEquals(4, intrptr.getMemory().getCells()[2] & mask);
		assertEquals(4, intrptr.getMemory().getCells()[3] & mask);
		assertEquals(1, intrptr.getMemory().getCells()[4] & mask);


		try {
			intrptr = new Interpreter(getClass().getResource("/images/BadSquare1.bmp").getFile());
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("Square #1 is not monochrome", e.getMessage());
		}

		try {
			intrptr = new Interpreter(getClass().getResource("/images/BadSquare4.bmp").getFile());
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("Square #4 is not monochrome", e.getMessage());
		}
		try {
			intrptr = new Interpreter(getClass().getResource("/images/BadSquare5.bmp").getFile());
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("Square #5 is not monochrome", e.getMessage());
		}
		try {
			intrptr = new Interpreter(getClass().getResource("/images/BadSquare6.bmp").getFile());
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("Square #6 is not monochrome", e.getMessage());
		}
		try {
			intrptr = new Interpreter(getClass().getResource("/images/BadSquare7.bmp").getFile());
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("Square #7 is not monochrome", e.getMessage());
		}
		try {
			intrptr = new Interpreter(getClass().getResource("/images/BadSquare11.bmp").getFile());
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("Square #11 is not monochrome", e.getMessage());
		}
		try {
			intrptr = new Interpreter(getClass().getResource("/images/BadSquare12.bmp").getFile());
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("Square #12 is not monochrome", e.getMessage());
		}
		try {
			intrptr = new Interpreter(getClass().getResource("/images/BadSquare25.bmp").getFile());
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("Square #25 is not monochrome", e.getMessage());
		}


		intrptr = new Interpreter(getClass().getResource("/images/helloworld.bmp").getFile());
		intrptr.interpretFile();
		assertEquals(4, intrptr.getMemory().getP());//Result of this program is C4:10
		assertEquals(0, intrptr.getMemory().getCells()[0] & mask);//0 is a space in ascii
		assertEquals(87, intrptr.getMemory().getCells()[1] & mask);//87 is W in ascii
		assertEquals(100, intrptr.getMemory().getCells()[2] & mask);//100 is d in ascii
		assertEquals(33, intrptr.getMemory().getCells()[3] & mask);//33 is ! in ascii
		assertEquals(10, intrptr.getMemory().getCells()[4] & mask);//10 is a line feed in ascii


		//In, reads the first char of the input file and write it in a memory cell
		//Here it's "L"
		intrptr = new Interpreter(getClass().getResource("/images/in.bmp").getFile(), inputMock, null);
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());//Result of this program is C0:76
		assertEquals(76, intrptr.getMemory().getCells()[0] & mask);


		intrptr = new Interpreter(getClass().getResource("/images/InternalLoop.bmp").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(0, intrptr.getMemory().getCells()[0] & mask);

		intrptr = new Interpreter(getClass().getResource("/images/jumpInternalLoop.bmp").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(0, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:0

		intrptr = new Interpreter(getClass().getResource("/images/NormalLoop.bmp").getFile());
		intrptr.interpretFile();
		assertEquals(1, intrptr.getMemory().getP());
		assertEquals(5, intrptr.getMemory().getCells()[1] & mask);

		intrptr = new Interpreter(getClass().getResource("/images/out35.bmp").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(35, intrptr.getMemory().getCells()[0] & mask);
	}

	/**************
	 ** LEVEL 3 **
	 **************/

	@Test
	public void interpretBfFilesWithComments() throws Exception {

		intrptr = new Interpreter(getClass().getResource("/L3/usual/prog_com&indente.bf").getFile());
		intrptr.interpretFile();
		assertEquals(4, intrptr.getMemory().getP());
		assertEquals(0, intrptr.getMemory().getCells()[0] & mask);

		//Comments
		intrptr = new Interpreter(getClass().getResource("/L3/usual/prog_com.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(1, intrptr.getMemory().getCells()[0] & mask);

		//Does nothing only have comments
		intrptr = new Interpreter(getClass().getResource("/L3/usual/prog_fullcom.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(0, intrptr.getMemory().getCells()[0] & mask);

		intrptr = new Interpreter(getClass().getResource("/L3/usual/prog_helloCOM.bf").getFile());
		intrptr.interpretFile();
		assertEquals(4, intrptr.getMemory().getP());//Result of this program is C4:10
		assertEquals(0, intrptr.getMemory().getCells()[0] & mask);//0 is a space in ascii
		assertEquals(87, intrptr.getMemory().getCells()[1] & mask);//87 is W in ascii
		assertEquals(100, intrptr.getMemory().getCells()[2] & mask);//100 is d in ascii
		assertEquals(33, intrptr.getMemory().getCells()[3] & mask);//33 is ! in ascii
		assertEquals(10, intrptr.getMemory().getCells()[4] & mask);//10 is a line feed in ascii
	}

	@Test
	public void testMacroErrors() throws Exception {
		//This program will fail because the macros must be separated by "/"
		//In this case the suite of "f" is recognized as a whole word "ffffffffff"
		try {
			intrptr = new Interpreter(getClass().getResource("/L3/errors/macros_Erreur1.bf").getFile());
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("Incorrect word operator", e.getMessage());
		}

		//In our implementation the defines must be at the beggining
		try {
			intrptr = new Interpreter(getClass().getResource("/L3/errors/macros_Erreur2.bf").getFile());
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("Incorrect word operator", e.getMessage());
		}

		//The following call won't work : "MACF 5" because in this define : "$DEFINE MACF MACROS" MACF replaced MACROS
		try {
			intrptr = new Interpreter(getClass().getResource("/L3/errors/macros_sansParamErreur.bf").getFile());
			intrptr.interpretFile();
			fail();
		} catch (Exception e) {
			assertEquals("Incorrect word operator", e.getMessage());
		}


	}

	@Test
	public void interpretMacrosWithOneParameter() throws Exception {
		intrptr = new Interpreter(getClass().getResource("/L3/usual/demoMacros.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(97, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:97


		intrptr = new Interpreter(getClass().getResource("/L3/usual/demoMacrosTO_DIGITetMULTI_DECR.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(97, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:97

		intrptr = new Interpreter(getClass().getResource("/L3/usual/macroTEN_INCR_J.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(74, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:74, 74 IS J in ASCII

		intrptr = new Interpreter(getClass().getResource("/L3/usual/macros_avecParam.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(97, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:97

		intrptr = new Interpreter(getClass().getResource("/L3/usual/macros_example1.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(97, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:97

		intrptr = new Interpreter(getClass().getResource("/L3/usual/macros_example2.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(97, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:97

		intrptr = new Interpreter(getClass().getResource("/L3/usual/macros_sansParam.bf").getFile());
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getP());
		assertEquals(97, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:97
	}
}
