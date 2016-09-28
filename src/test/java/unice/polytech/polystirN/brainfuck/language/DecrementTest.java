package unice.polytech.polystirN.brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

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
            a =new Interpreter("./examples/empty.bf");
            a.readfile();
            assertEquals(0, a.getMemory().getCells()[a.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Nominal case 2, decrementation of c0 3 times then incrementation 3 times
        try {
            a =new Interpreter("./examples/decrementSimple.bf");
            a.readfile();
            assertEquals(1, a.getMemory().getCells()[a.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Anomaly cases
        //Anomaly case 1, decrementation of c0 7 times
        try {
            a =new Interpreter("./examples/decrementC0by7.bf");
            a.readfile();
        } catch (Exception e) {
            assertEquals("MemoryUnderflowError", e.getMessage());
        }


        //Anomaly case 2, decrementation of c-1
        try {
            a =new Interpreter("./examples/decrementError.bf");
            a.getMemory().setP(-1);
            a.readfile();
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBounds", e.getMessage());
        }
    }

}