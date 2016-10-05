package unice.polytech.polystirN.brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;
import unice.polytech.polystirN.brainfuck.interpreter.InterpreterText;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Increment operator class
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class IncrementTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void doOperation() throws Exception {
    	Interpreter a=null;
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

        //Nominal case 2, incrementation of c0 255 times
        try {
        	a = new InterpreterText("./examples/incrementMax255.bf");
            a.executeFile();
            assertEquals(255, a.getMemory().getCells()[a.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Nominal case 3, incrementation of c0 7 times
        try {
        	a = new InterpreterText("./examples/incrementC0by7.bf");
            a.executeFile();
            assertEquals(7, a.getMemory().getCells()[a.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Anomaly cases
        //Anomaly case 1, incrementation of c0 256 times
        try {
        	a = new InterpreterText("./examples/L1/INCRError256.bf");
            a.executeFile();
        } catch (Exception e) {
            assertEquals("MemoryOverflowException", e.getClass().getSimpleName());
            assertEquals("value can't be higher than 255", e.getMessage());
        }

        
        
        

        //Anomaly case 2, incrementation of c-1
        try {
        	a = new InterpreterText("./examples/incrementMax255.bf");
            a.getMemory().setP(-1);
            a.executeFile();
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBoundsException", e.getClass().getSimpleName());
            assertEquals("pointer must be between 0 and 29999 included", e.getMessage());
        }
        
        
      /*--------------------------------------------------------------------------
        les tests pour les syntax longue
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

        //Nominal case 2, incrementation of c0 255 times
        try {
        	a = new InterpreterText("./examples/L1/INCRMax255.bf");
            a.executeFile();
            assertEquals(255, a.getMemory().getCells()[a.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Nominal case 3, incrementation of c0 7 times
        try {
        	a = new InterpreterText("./examples/L1/INCRC0by7.bf");
            a.executeFile();
            assertEquals(7, a.getMemory().getCells()[a.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Anomaly cases
        //Anomaly case 1, incrementation of c0 256 times
        try {
        	a = new InterpreterText("./examples/L1/INCRError256.bf");
            a.executeFile();
        } catch (Exception e) {
            assertEquals("MemoryOverflowException", e.getClass().getSimpleName());
            assertEquals("value can't be higher than 255", e.getMessage());
        }

        
        
        

        //Anomaly case 2, incrementation of c-1
        try {
        	a = new InterpreterText("./examples/L1/INCRMax255.bf");
            a.getMemory().setP(-1);
            a.executeFile();
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBoundsException", e.getClass().getSimpleName());
            assertEquals("pointer must be between 0 and 29999 included", e.getMessage());
        }
    }

}