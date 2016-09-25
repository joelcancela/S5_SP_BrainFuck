package unice.polytech.polystirN.brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Left l = new Left();
        assertEquals(true, l.doOperation(1, new byte[30000]));
        assertEquals(true, l.doOperation(14999, new byte[30000]));
        assertEquals(true, l.doOperation(29999, new byte[30000]));

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
