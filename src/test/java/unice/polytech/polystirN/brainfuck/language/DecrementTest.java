package unice.polytech.polystirN.brainfuck.language;

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
    private Interpreter intrptr;

    @Test
    public void execute() throws Exception {

        try {
            intrptr = new Interpreter(getClass().getResource("/L1/usual/empty.bf").getFile());
            intrptr.interpretFile();
            assertEquals(0, intrptr.getMemory().getCells()[intrptr.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            intrptr = new Interpreter(getClass().getResource("/L1/usual/DECRSimple.bf").getFile());
            intrptr.interpretFile();
            assertEquals(0, intrptr.getMemory().getCells()[intrptr.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            intrptr = new Interpreter(getClass().getResource("/L2/usual/decrementSimple.bf").getFile());
            intrptr.interpretFile();
            assertEquals(0, intrptr.getMemory().getCells()[intrptr.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void executeError() throws Exception {

        try {
            intrptr = new Interpreter(getClass().getResource("/L1/errors/DECRError.bf").getFile());
            intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("MemoryUnderflowException", e.getClass().getSimpleName());
            assertEquals("value can't be negative", e.getMessage());
        }

        try {
            intrptr = new Interpreter(getClass().getResource("/L2/errors/decrementError.bf").getFile());
            intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("MemoryUnderflowException", e.getClass().getSimpleName());
            assertEquals("value can't be negative", e.getMessage());
        }
    }

}