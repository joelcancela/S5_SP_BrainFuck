package unice.polytech.polystirN.brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;
import unice.polytech.polystirN.brainfuck.interpreter.InterpreterText;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Decrement operator class
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class DecrementTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void doOperation() throws Exception {
    	Interpreter a=null;

        //Nominal cases
        //Nominal case 1, with an empty file
        try {
            a = new InterpreterText("./examples/empty.bf");
            a.executeFile();
            assertEquals(0, a.getMemory().getCells()[a.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Nominal case 2, decrementation of c0 3 times then incrementation 3 times
        try {
            a = new InterpreterText("./examples/L1/DECRSimple.bf");
            a.executeFile();
            assertEquals(0, a.getMemory().getCells()[a.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Anomaly cases
        //Anomaly case 1, decrementation of c0
        try {
            a = new InterpreterText("./examples/L1/DECRError.bf");
            a.executeFile();
        } catch (Exception e) {
            assertEquals("MemoryUnderflowException", e.getClass().getSimpleName());
            assertEquals("value can't be negative", e.getMessage());
        }
        
        /*--------------------------------------------------------------------------
        les tests pour les syntax court
		--------------------------------------------------------------------------*/
        //Nominal cases
        //Nominal case 1, with an empty file
        try {
            a = new InterpreterText("./examples/empty.bf");
            a.executeFile();
            assertEquals(0, a.getMemory().getCells()[a.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Nominal case 2, decrementation of c0 3 times then incrementation 3 times
        try {
            a = new InterpreterText("./examples/decrementSimple.bf");
            a.executeFile();
            assertEquals(0, a.getMemory().getCells()[a.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Anomaly cases
        //Anomaly case 1, decrementation of c0
        try {
            a = new InterpreterText("./examples/decrementError.bf");
            a.executeFile();
        } catch (Exception e) {
            assertEquals("MemoryUnderflowException", e.getClass().getSimpleName());
            assertEquals("value can't be negative", e.getMessage());
        }
    }

}