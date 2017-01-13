package unice.polytech.polystirN.brainfuck.language;

import org.junit.BeforeClass;
import org.junit.Test;
import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

import static org.junit.Assert.assertEquals;


/**
 * Tests class for macros with parameters
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class MacroWithParamTest {

	private static Interpreter intrptr;
	private int mask = 0xff;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@Test
	public void macrosIncr100() throws Exception {
		intrptr = new Interpreter("./examples/L3/macrosIncr100.bf");
		intrptr.interpretFile();
		assertEquals(100, intrptr.getMemory().getCells()[0] & mask);
	}

	@Test
	public void macrosParDefaut() throws Exception {
		intrptr = new Interpreter("./examples/L3/macros_avecParam.bf");
		intrptr.interpretFile();
		assertEquals(97, intrptr.getMemory().getCells()[0] & mask);
	}

	@Test
	public void macrosVide() throws Exception {
		intrptr = new Interpreter("./examples/L3/demoMacrosVide.bf");
		intrptr.interpretFile();
		assertEquals(0, intrptr.getMemory().getCells()[0] & mask);
	}

	@Test
	public void macrosErreur() throws Exception {
		intrptr = new Interpreter("./examples/L3/ErreurMacros.bf");
		try {
			intrptr.interpretFile();
		} catch (SyntaxErrorException e) {
			assertEquals("Incorrect word operator", e.getMessage());
		}
		intrptr = new Interpreter("./examples/L3/demoMacrosParamErreur.bf");
		try {
			intrptr.interpretFile();
		} catch (SyntaxErrorException e) {
			assertEquals("Incorrect word operator", e.getMessage());
		}
		try {
			intrptr = new Interpreter("./examples/L3/macros_Erreur1.bf");
			intrptr.interpretFile();
		} catch (SyntaxErrorException e) {
			assertEquals("Incorrect word operator", e.getMessage());
		}
	}
}
