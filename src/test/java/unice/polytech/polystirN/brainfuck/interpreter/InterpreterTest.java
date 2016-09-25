package unice.polytech.polystirN.brainfuck.interpreter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InterpreterTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void doOperation() throws Throwable {
        Interpreter a;

        //TUN :
        //Tests left&Right :
        //Sans saut de lignes :
        a = new Interpreter("./examples/left&right1.bf");
        try {
            a.readfile();
        } catch (Exception e) {

        }

        //TUA :
        //Tests left :
        /*a = new Interpreter("./examples/left_outMin.bf");
        try {
	    	a.readfile();
        } catch(Exception e){
        	assertEquals("p = 0 -1 | Can't move to the left",e.getMessage());
        	assertEquals(0,a.getP());
        }
        
        //Tests right :
    	a = new Interpreter("./examples/right_outMax.bf");
        try {
	    	a.readfile();
        } catch(Exception e){
        	assertEquals("p = 29999 +1 | Can't move to the right",e.getMessage());
        	assertEquals(29999,a.getP());
        }*/
    }
}
