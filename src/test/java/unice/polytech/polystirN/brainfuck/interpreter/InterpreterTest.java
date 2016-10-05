package unice.polytech.polystirN.brainfuck.interpreter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Interpreter class
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class InterpreterTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void doOperation() throws Exception {
        Interpreter a;

        //Nominal case
        //Test left&right operations :
        try {
            a = new InterpreterText("./examples/L1/LEFT&RIGHT.bf");
            a.executeFile();
            assertEquals(1, a.getMemory().getP());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Anomaly cases
        //Test with out of bounds left :
        try {
            a = new InterpreterText("./examples/L1/LEFTError.bf");
            a.executeFile();
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBoundsException",e.getClass().getSimpleName());
            assertEquals("pointer can't be moved to the left (already at position 0)", e.getMessage());
        }

        //Test with out of bounds right :
        try {
            a = new InterpreterText("./examples/L1/RIGHTError.bf");
            a.executeFile();
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBoundsException",e.getClass().getSimpleName());
            assertEquals("pointer can't be moved to the right (already at position 29999)", e.getMessage());
        }
    }
}
