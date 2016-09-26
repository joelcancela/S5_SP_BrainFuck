package unice.polytech.polystirN.brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Right operator class
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
        Right r = new Right();

        //Nominal cases
        //Nominal case 1, pointer at position 0
    	Interpreter.setP(0);
        assertEquals(true, r.doOperation(Interpreter.getP(), new byte[30000]));
        assertEquals(1, Interpreter.getP());

        //Nominal case 2, pointer at position 14999
        Interpreter.setP(14999);
        assertEquals(true, r.doOperation(Interpreter.getP(), new byte[30000]));
        assertEquals(15000, Interpreter.getP());

        //Nominal case 3, pointer at position 29998
        Interpreter.setP(29998);
        assertEquals(true, r.doOperation(Interpreter.getP(), new byte[30000]));
        assertEquals(29999, Interpreter.getP());

        //Anomaly cases
        //Anomaly case 1, shift pointer to the right when it's at illegal position (-1)
        try {
            r.doOperation(-1, new byte[30000]);
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBounds", e.getMessage());
        }

        //Anomaly case 2, shift pointer to the right when it's at position 29999
        try {
            r.doOperation(29999, new byte[30000]);
        } catch (Exception e) {
            assertEquals("PointerMaximumValueError", e.getMessage());
        }
    }

}