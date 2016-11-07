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
    Interpreter intrptr;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void interpretBfFiles() throws Exception {
        //Nominal case
        //Test left&right operations :
        intrptr = new Interpreter("./examples/L1/LEFT&RIGHT.bf");
        intrptr.interpretFile();
        assertEquals(1, intrptr.getMemory().getP());
    }

    @Test
    public void interpretBmpFiles() throws Exception {

        //Image Interpreter
        intrptr = new Interpreter("./examples/images/12345.bmp");
        intrptr.interpretFile();
        assertEquals(1, intrptr.getMemory().getCells()[0]);
        assertEquals(2, intrptr.getMemory().getCells()[1]);
        assertEquals(3, intrptr.getMemory().getCells()[2]);
        assertEquals(4, intrptr.getMemory().getCells()[3]);
        assertEquals(5, intrptr.getMemory().getCells()[4]);

        //Image Interpreter
        intrptr = new Interpreter("./examples/images/54321to33441.bmp");
        intrptr.interpretFile();
        assertEquals(3, intrptr.getMemory().getCells()[0]);
        assertEquals(3, intrptr.getMemory().getCells()[1]);
        assertEquals(4, intrptr.getMemory().getCells()[2]);
        assertEquals(4, intrptr.getMemory().getCells()[3]);
        assertEquals(1, intrptr.getMemory().getCells()[4]);

        intrptr = new Interpreter("./examples/images/out35.bmp");
        intrptr.interpretFile();
        assertEquals(35, intrptr.getMemory().getCells()[0]);//35 is # in ascii


        try {
            intrptr = new Interpreter("./examples/images/BadSquare1.bmp");
            intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #1 is not monochrome", e.getMessage());
        }

        try {
            intrptr = new Interpreter("./examples/images/BadSquare4.bmp");
            intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #4 is not monochrome", e.getMessage());
        }
        try {
            intrptr = new Interpreter("./examples/images/BadSquare5.bmp");
            intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #5 is not monochrome", e.getMessage());
        }
        try {
            intrptr = new Interpreter("./examples/images/BadSquare6.bmp");
            intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #6 is not monochrome", e.getMessage());
        }
        try {
            intrptr = new Interpreter("./examples/images/BadSquare7.bmp");
            intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #7 is not monochrome", e.getMessage());
        }
        try {
            intrptr = new Interpreter("./examples/images/BadSquare11.bmp");
            intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #11 is not monochrome", e.getMessage());
        }
        try {
            intrptr = new Interpreter("./examples/images/BadSquare12.bmp");
            intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #12 is not monochrome", e.getMessage());
        }
        try {
            intrptr = new Interpreter("./examples/images/BadSquare25.bmp");
            intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #25 is not monochrome", e.getMessage());
        }

        //Anomaly cases
        //Test with out of bounds left :
        try {
            intrptr = new Interpreter("./examples/L1/LEFTError.bf");
            intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBoundsException", e.getClass().getSimpleName());
            assertEquals("pointer can't be moved to the left (already at position 0)", e.getMessage());
        }

        //Test with out of bounds right :
        try {
            intrptr = new Interpreter("./examples/L1/RIGHTError.bf");
            intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBoundsException", e.getClass().getSimpleName());
            assertEquals("pointer can't be moved to the right (already at position 29999)", e.getMessage());
        }
    }

    @Test
    public void rewriteBfFile() throws Exception {

    }

    @Test
    public void rewriteBmpFile() throws Exception {

    }


    @Test
    public void checkWellFormed() throws Exception {
        //Check tests :
        //Bien formé :
        try {
            intrptr = new Interpreter("./examples/L2/helloworld.bf");
            intrptr.check();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkBadFormed() throws Exception {
        //Mal formé :
        try {
            intrptr = new Interpreter("./examples/L2/JUMP5.bf");
            intrptr.check();
        } catch (Exception e) {
            assertEquals("BadLoopException", e.getClass().getSimpleName());
            assertEquals("Loop without end : Missing BACK operator", e.getMessage());
        }
        try {
            intrptr = new Interpreter("./examples/L2/JUMPBACK6.bf");
            intrptr.check();
        } catch (Exception e) {
            assertEquals("BadLoopException", e.getClass().getSimpleName());
            assertEquals("Loop without start : Missing JUMP operator", e.getMessage());
        }
        try {
            intrptr = new Interpreter("./examples/L2/BADPROG.bf");
            intrptr.check();
        } catch (Exception e) {
            assertEquals("SyntaxErrorException", e.getClass().getSimpleName());
            assertEquals("Incorrect word operator", e.getMessage());
        }

    }
}
