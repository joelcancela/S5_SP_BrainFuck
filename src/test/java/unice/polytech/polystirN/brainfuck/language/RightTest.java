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
        Interpreter a=null;

        //Nominal cases
        //Nominal case 1, pointer at position 0
        a = new Interpreter("./examples/empty.bf");
        assertEquals(true, r.doOperation(a.getMemory()));
        assertEquals(1, a.getMemory().getP());

        //Nominal case 2, pointer at position 14999
        a = new Interpreter("./examples/empty.bf");
        a.getMemory().setP(14999);
        assertEquals(true, r.doOperation(a.getMemory()));
        assertEquals(15000, a.getMemory().getP());

        //Nominal case 3, pointer at position 29998
        a = new Interpreter("./examples/empty.bf");
        a.getMemory().setP(29998);
        assertEquals(true, r.doOperation(a.getMemory()));
        assertEquals(29999, a.getMemory().getP());

        //Anomaly cases
        //Anomaly case 1, shift pointer to the right when it's at illegal position (-1)
        try {
        	a = new Interpreter("./examples/empty.bf");
            r.doOperation(a.getMemory());
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBounds", e.getMessage());
        }

        //Anomaly case 2, shift pointer to the right when it's at position 29999
        try {
        	a = new Interpreter("./examples/empty.bf");
            r.doOperation(a.getMemory());
        } catch (Exception e) {
            assertEquals("PointerMaximumValueError", e.getMessage());
        }
    }

}