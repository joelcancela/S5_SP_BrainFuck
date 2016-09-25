package unice.polytech.polystirN.brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests unitaires pour Right
 *
 * @author Joël CANCELA VAZ and Pierre Rainero
 * @author Tanguy Invernizzi and Aghiles Dziri
 */
public class RightTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void doOperation() throws Exception {
        //TUN :
        Right r = new Right();
        assertEquals(true, r.doOperation(0, new byte[30000]));
        assertEquals(true, r.doOperation(14999, new byte[30000]));
        assertEquals(true, r.doOperation(29998, new byte[30000]));

        //TUA :
        try {
            r.doOperation(-1, new byte[30000]);
        } catch (Exception e) {
            assertEquals("p < 0 | Wrong memory", e.getMessage());
        }
        try {
            r.doOperation(29999, new byte[30000]);
        } catch (Exception e) {
            assertEquals("p = 29999 +1 | Can't move to the right", e.getMessage());
        }

    }

}