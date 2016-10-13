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
        Interpreter a;

        //Nominal case
        //Test left&right operations :
        try {
            a = new Interpreter("./examples/L1/LEFT&RIGHT.bf");
            a.interpretFile();
            assertEquals(1, a.getMemory().getP());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Image Interpreter
        try {
            a = new Interpreter("./examples/images/12345.bmp");
            a.interpretFile();
            assertEquals(1, a.getMemory().getCells()[0]);
            assertEquals(2, a.getMemory().getCells()[1]);
            assertEquals(3, a.getMemory().getCells()[2]);
            assertEquals(4, a.getMemory().getCells()[3]);
            assertEquals(5, a.getMemory().getCells()[4]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Image Interpreter
        try {
            a = new Interpreter("./examples/images/54321to33441.bmp");
            a.interpretFile();
            assertEquals(3, a.getMemory().getCells()[0]);
            assertEquals(3, a.getMemory().getCells()[1]);
            assertEquals(4, a.getMemory().getCells()[2]);
            assertEquals(4, a.getMemory().getCells()[3]);
            assertEquals(1, a.getMemory().getCells()[4]);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            a = new Interpreter("./examples/images/BadSquare1.bmp");
            a.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #1 is not monochrome", e.getMessage());
        }

        try {
            a = new Interpreter("./examples/images/BadSquare4.bmp");
            a.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #4 is not monochrome", e.getMessage());
        }
        try {
            a = new Interpreter("./examples/images/BadSquare5.bmp");
            a.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #5 is not monochrome", e.getMessage());
        }
        try {
            a = new Interpreter("./examples/images/BadSquare6.bmp");
            a.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #6 is not monochrome", e.getMessage());
        }
        try {
            a = new Interpreter("./examples/images/BadSquare7.bmp");
            a.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #7 is not monochrome", e.getMessage());
        }
        try {
            a = new Interpreter("./examples/images/BadSquare11.bmp");
            a.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #11 is not monochrome", e.getMessage());
        }
        try {
            a = new Interpreter("./examples/images/BadSquare12.bmp");
            a.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #12 is not monochrome", e.getMessage());
        }
        try {
            a = new Interpreter("./examples/images/BadSquare25.bmp");
            a.interpretFile();
        } catch (Exception e) {
            assertEquals("Square #25 is not monochrome", e.getMessage());
        }

        //Anomaly cases
        //Test with out of bounds left :
        try {
            a = new Interpreter("./examples/L1/LEFTError.bf");
            a.interpretFile();
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBoundsException", e.getClass().getSimpleName());
            assertEquals("pointer can't be moved to the left (already at position 0)", e.getMessage());
        }

        //Test with out of bounds right :
        try {
            a = new Interpreter("./examples/L1/RIGHTError.bf");
            a.interpretFile();
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBoundsException", e.getClass().getSimpleName());
            assertEquals("pointer can't be moved to the right (already at position 29999)", e.getMessage());
        }
    }
}
