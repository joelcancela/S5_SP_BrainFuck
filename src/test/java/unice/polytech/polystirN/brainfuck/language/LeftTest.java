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

        //Nominal cases
        //Nominal case 1, pointer at position 1
        Interpreter.setP(1);
        assertEquals(true, l.doOperation(Interpreter.getP(), new byte[30000]));
        assertEquals(0, Interpreter.getP());

        //Nominal case 2, pointer at position 14999
        Interpreter.setP(14999);
        assertEquals(true, l.doOperation(Interpreter.getP(), new byte[30000]));
        assertEquals(14998, Interpreter.getP());

        //Nominal case 3, pointer at position 29999
        Interpreter.setP(29999);
        assertEquals(true, l.doOperation(Interpreter.getP(), new byte[30000]));
        assertEquals(29998, Interpreter.getP());

        //Anomaly cases
        //Anomaly case 1, shift pointer to the left when it's at position 0
        try {
            l.doOperation(0, new byte[30000]);
        } catch (Exception e) {
            assertEquals("PointerMinimumValueError", e.getMessage());
        }

        //Anomaly case 2, shift pointer to the left when it's at illegal position (30000)
        try {
            l.doOperation(30000, new byte[30000]);
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBounds", e.getMessage());
        }

    }

}
