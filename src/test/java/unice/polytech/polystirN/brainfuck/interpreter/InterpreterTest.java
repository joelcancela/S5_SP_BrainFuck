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
    	Interpreter a=null;
    	
        //Nominal case
        //Test left&right operations :
        try {
        	a= new Interpreter("./examples/left&right1.bf");
            a.readfile();
            assertEquals(1, a.getMemory().getP());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Anomaly cases
        //Test with out of bounds left :
        try {
        	a= new Interpreter("./examples/left_outMin.bf");
            a.readfile();
        } catch (Exception e) {
            assertEquals("PointerMinimumValueError", e.getMessage());
        }

        //Test with out of bounds right :
        try {
        	a= new Interpreter("./examples/right_outMax.bf");
            a.readfile();
        } catch (Exception e) {
            assertEquals("PointerMaximumValueError", e.getMessage());
        }
    }
}
