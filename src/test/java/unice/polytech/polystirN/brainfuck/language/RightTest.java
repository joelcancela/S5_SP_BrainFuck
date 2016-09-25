package unice.polytech.polystirN.brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

import static org.junit.Assert.assertEquals;

/**
 * Units tests for Right
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class RightTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void doOperation() throws Exception {
        //TUN :
    	Interpreter.setP(0);
        Right l = new Right();
        assertEquals(true, l.doOperation(Interpreter.getP(), new byte[30000]));
        assertEquals(1, Interpreter.getP());

        Interpreter.setP(14999);
        assertEquals(true, l.doOperation(Interpreter.getP(), new byte[30000]));
        assertEquals(15000, Interpreter.getP());

        Interpreter.setP(29998);
        assertEquals(true, l.doOperation(Interpreter.getP(), new byte[30000]));
        assertEquals(29999, Interpreter.getP());

        //TUA :
        try {
            l.doOperation(-1, new byte[30000]);
        } catch (Exception e) {
            assertEquals("p < 0 | Wrong memory", e.getMessage());
        }
        try {
            l.doOperation(29999, new byte[30000]);
        } catch (Exception e) {
            assertEquals("p = 29999 +1 | Can't move to the right", e.getMessage());
        }
    }

}