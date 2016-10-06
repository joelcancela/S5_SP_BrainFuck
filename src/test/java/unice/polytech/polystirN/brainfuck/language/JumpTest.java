package unice.polytech.polystirN.brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;
import unice.polytech.polystirN.brainfuck.interpreter.InterpreterText;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the JumpTest operator class
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class JumpTest {
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
            a = new InterpreterText("./examples/L2/JUMP1.bf");
            a.executeFile();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
