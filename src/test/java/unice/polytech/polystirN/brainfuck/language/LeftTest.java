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
        Interpreter a=null;

        //Nominal cases
        //Nominal case 1, pointer at position 1
        a = new Interpreter("./examples/empty.bf");
        a.setP(1);
        assertEquals(true, l.doOperation(a));
        assertEquals(0, a.getP());

        //Nominal case 2, pointer at position 14999
        a = new Interpreter("./examples/empty.bf");
        a.setP(14999);
        assertEquals(true, l.doOperation(a));
        assertEquals(14998, a.getP());

        //Nominal case 3, pointer at position 29999
        a = new Interpreter("./examples/empty.bf");
        a.setP(29999);
        assertEquals(true, l.doOperation(a));
        assertEquals(29998, a.getP());

        //Anomaly cases
        //Anomaly case 1, shift pointer to the left when it's at position 0
        try {
        	a = new Interpreter("./examples/empty.bf");
            l.doOperation(a);
        } catch (Exception e) {
            assertEquals("PointerMinimumValueError", e.getMessage());
        }

        //Anomaly case 2, shift pointer to the left when it's at illegal position (30000)
        try {
        	a = new Interpreter("./examples/empty.bf");
        	a.setP(30000);
            l.doOperation(a);
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBounds", e.getMessage());
        }

    }

}
