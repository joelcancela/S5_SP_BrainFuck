package unice.polytech.polystirN.brainfuck.interpreter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 07/11/2016.
 */
public class MetricsTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void INCRC0by7MetricsProgramSize() throws Exception {
        Interpreter inte = new Interpreter(getClass().getResource("/L1/usual/INCRC0BY7.bf").getFile());
        inte.interpretFile();
        assertEquals(7,inte.getProgramSize());
    }

    @Test
    public void INCRC0by7MetricsExecMove() throws Exception {
        Interpreter inte = new Interpreter(getClass().getResource("/L1/usual/INCRC0BY7.bf").getFile());
        inte.interpretFile();
        assertEquals(7,inte.getExecMove());
    }

    @Test
    public void INCRC0by7MetricsDataMove() throws Exception {
        Interpreter inte = new Interpreter(getClass().getResource("/L1/usual/INCRC0BY7.bf").getFile());
        inte.interpretFile();
        assertEquals(0,inte.getDataMove());
    }

    @Test
    public void INCRC0by7MetricsDataWrite() throws Exception {
        Interpreter inte = new Interpreter(getClass().getResource("/L1/usual/INCRC0BY7.bf").getFile());
        inte.interpretFile();
        assertEquals(7,inte.getDataWrite());
    }

    @Test
    public void INCRC0by7MetricsDataRead() throws Exception {
        Interpreter inte = new Interpreter(getClass().getResource("/L1/usual/INCRC0BY7.bf").getFile());
        inte.interpretFile();
        assertEquals(0,inte.getDataRead());
    }

    @Test
    public void JUMP0MetricsProgramSize() throws Exception {
        Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpSimple.bf").getFile());
        inte.interpretFile();
        assertEquals(11,inte.getProgramSize());
    }

    @Test
    public void JUMP0MetricsExecMove() throws Exception {
        Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpSimple.bf").getFile());
        inte.interpretFile();
        assertEquals(3,inte.getExecMove());
    }

    @Test
    public void JUMP0MetricsDataMove() throws Exception {
        Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpSimple.bf").getFile());
        inte.interpretFile();
        assertEquals(0,inte.getDataMove());
    }

    @Test
    public void JUMP0MetricsDataRead() throws Exception {
        Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpSimple.bf").getFile());
        inte.interpretFile();
        assertEquals(2,inte.getDataRead());
    }
}
