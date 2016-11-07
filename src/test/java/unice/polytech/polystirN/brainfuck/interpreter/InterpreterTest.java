package unice.polytech.polystirN.brainfuck.interpreter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the Interpreter class
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class InterpreterTest {
    private Interpreter intrptr;
    private int mask = 0xff;
    private String inputMock = getClass().getResource("/L2/mocks/input.txt").getFile();

    @Test
    public void interpretBfLongUsualFiles() throws Exception {

        //Simple decrements after increments
        intrptr = new Interpreter(getClass().getResource("/L1/usual/DECRSimple.bf").getFile());
        intrptr.interpretFile();
        assertEquals(0, intrptr.getMemory().getP());//Pointer hasn't moved
        assertEquals(0, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:0

        //Empty file
        intrptr = new Interpreter(getClass().getResource("/L1/usual/empty.bf").getFile());
        intrptr.interpretFile();
        assertEquals(0, intrptr.getMemory().getP());//Pointer hasn't moved
        assertEquals(0, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:0

        //Increment 7 times
        intrptr = new Interpreter(getClass().getResource("/L1/usual/INCRC0by7.bf").getFile());
        intrptr.interpretFile();
        assertEquals(0, intrptr.getMemory().getP());//Pointer hasn't moved
        assertEquals(7, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:7

        //Increment 255 times
        intrptr = new Interpreter(getClass().getResource("/L1/usual/INCRMax255.bf").getFile());
        intrptr.interpretFile();
        assertEquals(0, intrptr.getMemory().getP());//Pointer hasn't moved
        assertEquals(255, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:255

        //Moving the pointer left and right
        intrptr = new Interpreter(getClass().getResource("/L1/usual/LEFT&RIGHT.bf").getFile());
        intrptr.interpretFile();
        assertEquals(1, intrptr.getMemory().getP());//Pointer has moved to 1 at the end
        assertEquals(0, intrptr.getMemory().getCells()[0] & mask);//Result of this program is C0:0
    }

    @Test
    public void interpretBfLongErrorFiles() throws Exception {

        //Value of the current cell goes under 0
        try {
            intrptr = new Interpreter(getClass().getResource("/L1/errors/DECRError.bf").getFile());
            intrptr.interpretFile();
            fail();
        } catch (Exception e) {
            assertEquals("MemoryUnderflowException", e.getClass().getSimpleName());
            assertEquals("value can't be negative", e.getMessage());
        }

        //Value of the current cell goes above 255
        try {
            intrptr = new Interpreter(getClass().getResource("/L1/errors/INCRError256.bf").getFile());
            intrptr.interpretFile();
            fail();
        } catch (Exception e) {
            assertEquals("MemoryOverflowException", e.getClass().getSimpleName());
            assertEquals("value can't be higher than 255", e.getMessage());
        }

        //Value of the pointer goes under 0
        try {
            intrptr = new Interpreter(getClass().getResource("/L1/errors/LEFTError.bf").getFile());
            intrptr.interpretFile();
            fail();
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBoundsException", e.getClass().getSimpleName());
            assertEquals("pointer can't be moved to the left (already at position 0)", e.getMessage());
        }

        //Value of the pointer goes above 29999 (Max capacity-1)
        try {
            intrptr = new Interpreter(getClass().getResource("/L1/errors/RIGHTError.bf").getFile());
            intrptr.interpretFile();
            fail();
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBoundsException", e.getClass().getSimpleName());
            assertEquals("pointer can't be moved to the right (already at position 29999)", e.getMessage());
        }

    }

    @Test
    public void interpretBfShortUsualFiles() throws Exception {
        //Writes "Bonjour" using loops
        intrptr = new Interpreter(getClass().getResource("/L2/usual/bonjour.bf").getFile());
        intrptr.interpretFile();
        assertEquals(6, intrptr.getMemory().getP());//Result of this program is C6:114
        assertEquals(66, intrptr.getMemory().getCells()[0] & mask);//66 is B in ascii
        assertEquals(111, intrptr.getMemory().getCells()[1] & mask);//111 is o in ascii
        assertEquals(110, intrptr.getMemory().getCells()[2] & mask);//110 is n in ascii
        assertEquals(106, intrptr.getMemory().getCells()[3] & mask);//106 is j in ascii
        assertEquals(111, intrptr.getMemory().getCells()[4] & mask);//111 is o in ascii
        assertEquals(117, intrptr.getMemory().getCells()[5] & mask);//117 is u in ascii
        assertEquals(114, intrptr.getMemory().getCells()[6] & mask);//114 is r in ascii

    }

    @Test
    public void interpretBfShortErrorFiles() throws Exception {
//        //Prints a cat of the inputMock file
//        intrptr = new Interpreter(getClass().getResource("/L2/errors/cat.bf").getFile(),inputMock,null);
//        intrptr.interpretFile();
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
