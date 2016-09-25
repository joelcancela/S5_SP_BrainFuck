package unice.polytech.polystirN.brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

import static org.junit.Assert.assertEquals;

/**
 * Tests unitaires pour Left
 *
 * @author Joël CANCELA VAZ and Pierre Rainero
 * @author Tanguy Invernizzi and Aghiles Dziri
 */
public class LeftTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void doOperation() throws Exception {
        //TUN :
        Interpreter bfckI = Interpreter.getInstance();
        Left l = new Left();
        bfckI.p = 1;
        assertEquals(true, l.doOperation(bfckI.p, new byte[30000]));
        assertEquals(0, bfckI.p);

        bfckI.p=14999;
        assertEquals(true, l.doOperation(bfckI.p, new byte[30000]));
        assertEquals(14998, bfckI.p);

        bfckI.p=29999;
        assertEquals(true, l.doOperation(bfckI.p, new byte[30000]));
        assertEquals(29998, bfckI.p);

        //TUA :
        try {
            l.doOperation(0, new byte[30000]);
        } catch (Exception e) {
            assertEquals("p = 0 -1 | Can't move to the left", e.getMessage());
        }
        try {
            l.doOperation(30000, new byte[30000]);
        } catch (Exception e) {
            assertEquals("p > 30000 | Wrong memory", e.getMessage());
        }

    }

}
