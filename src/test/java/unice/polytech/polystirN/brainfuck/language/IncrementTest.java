package unice.polytech.polystirN.brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

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
        byte[] m;

        //Nominal cases
        //Nominal case 1, with an empty file
        try {
            Interpreter.init("./examples/empty.bf");
            Interpreter.readfile();
            m = Interpreter.getMemory();
            assertEquals(0, m[Interpreter.getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Nominal case 2, incrementation of c0 255 times
        try {
            Interpreter.init("./examples/incrementMax255.bf");
            Interpreter.readfile();
            m = Interpreter.getMemory();
            assertEquals(255, m[Interpreter.getP()] & 0xFF);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Nominal case 3, incrementation of c0 7 times
        try {
            Interpreter.init("./examples/incrementC0by7.bf");
            Interpreter.readfile();
            m = Interpreter.getMemory();
            assertEquals(7, m[Interpreter.getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Anomaly cases
        //Anomaly case 1, incrementation of c0 256 times
        try {
            Interpreter.init("./examples/incrementError256.bf");
            Interpreter.readfile();
        } catch (Exception e) {
            assertEquals("Memory overflow error", e.getMessage());
        }


        //Anomaly case 2, incrementation of c-1
        try {
            Interpreter.init("./examples/incrementMax255.bf");
            Interpreter.setP(-1);
            Interpreter.readfile();
        } catch (Exception e) {
            assertEquals("PointerPositionOutOfBounds", e.getMessage());
        }
    }

}