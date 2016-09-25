package unice.polytech.polystirN.brainfuck.interpreter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
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
    public void doOperation() throws Throwable {
        //TUN :
        //Tests left&Right :
    	Interpreter.init("./examples/left&right1.bf");
        try {
        	Interpreter.readfile();
        	assertEquals(1,Interpreter.getP());
        } catch (Exception e) {
        	
        }

        //TUA :
        //Tests left :
    	Interpreter.init("./examples/left_outMin.bf");
        try {
        	Interpreter.readfile();
        } catch (Exception e) {
        	assertEquals("p = 0 -1 | Can't move to the left",e.getMessage());
        }

        //Tests right :
    	Interpreter.init("./examples/right_outMax.bf");
        try {
        	Interpreter.readfile();
        } catch (Exception e) {
        	assertEquals("p = 29999 +1 | Can't move to the right",e.getMessage());
        }
    }
}
