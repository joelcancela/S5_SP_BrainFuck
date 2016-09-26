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
        //Nominal case
        //Test left&right operations :
        Interpreter.init("./examples/left&right1.bf");
        try {
            Interpreter.readfile();
            assertEquals(1, Interpreter.getP());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Anomaly cases
        //Test with out of bounds left :
        Interpreter.init("./examples/left_outMin.bf");
        try {
            Interpreter.readfile();
        } catch (Exception e) {
            assertEquals("PointerMinimumValueError", e.getMessage());
        }

        //Test with out of bounds right :
        Interpreter.init("./examples/right_outMax.bf");
        try {
            Interpreter.readfile();
        } catch (Exception e) {
            assertEquals("PointerMaximumValueError", e.getMessage());
        }
    }
}
