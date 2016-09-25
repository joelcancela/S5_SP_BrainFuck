package unice.polytech.polystirN.brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

import static org.junit.Assert.assertEquals;

/**
 * Units tests for Left
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
        //TUN :
    	Interpreter.setP(1);
        Left l = new Left();
        assertEquals(true, l.doOperation(Interpreter.getP(), new byte[30000]));
        assertEquals(0, Interpreter.getP());

        Interpreter.setP(14999);
        assertEquals(true, l.doOperation(Interpreter.getP(), new byte[30000]));
        assertEquals(14998, Interpreter.getP());

        Interpreter.setP(29999);
        assertEquals(true, l.doOperation(Interpreter.getP(), new byte[30000]));
        assertEquals(29998, Interpreter.getP());

        //TUA :
        try {
            l.doOperation(0, new byte[30000]);
        } catch (Exception e) {
            assertEquals("p = 0 -1 | Can't move to the left", e.getMessage());
        }
        try {
            l.doOperation(30000, new byte[30000]);
        } catch (Exception e) {
            assertEquals("p > 30000 | Wrong memory", e.getMessage());
        }

    }

}
