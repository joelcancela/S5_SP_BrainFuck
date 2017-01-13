package unice.polytech.polystirN.brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Left operator class
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class LeftTest {
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void doOperation() throws Exception {
		Left l = new Left();
		Interpreter a = null;

		//Nominal cases
		//Nominal case 1, pointer at position 1
		a = new Interpreter("./examples/L1/empty.bf");
		a.getMemory().setP(1);
		l.execute(a);
		assertEquals(0, a.getMemory().getP());

		//Nominal case 2, pointer at position 14999
		a = new Interpreter("./examples/L1/empty.bf");
		a.getMemory().setP(14999);
		l.execute(a);
		assertEquals(14998, a.getMemory().getP());

		//Nominal case 3, pointer at position 29999
		a = new Interpreter("./examples/L1/empty.bf");
		a.getMemory().setP(29999);
		l.execute(a);
		assertEquals(29998, a.getMemory().getP());

		//Anomaly cases
		//Anomaly case 1, shift pointer to the left when it's at position 0
		try {
			a = new Interpreter("./examples/L1/empty.bf");
			l.execute(a);
		} catch (Exception e) {
			assertEquals("PointerPositionOutOfBoundsException", e.getClass().getSimpleName());
			assertEquals("pointer can't be moved to the left (already at position 0)", e.getMessage());
		}

		//Anomaly case 2, shift pointer to the left when it's at illegal position (30000)
		try {
			a = new Interpreter("./examples/L1/empty.bf");
			a.getMemory().setP(30000);
			l.execute(a);
		} catch (Exception e) {
			assertEquals("PointerPositionOutOfBoundsException", e.getClass().getSimpleName());
			assertEquals("pointer is at illegal position", e.getMessage());
		}

	}

}
