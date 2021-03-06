package unice.polytech.polystirN.brainfuck.debug;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Tests class for the metrics
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class MetricsTest {
	@Before
	public void setUp() {
		//NoOutputs
		System.setOut(new PrintStream(new OutputStream() {
			public void write(int b) {
				//DO NOTHING
			}
		}));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void INCRC0by7MetricsProgramSize() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L1/usual/INCRC0by7.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(7, inte.getMetrics().getProgramSize());
	}

	@Test
	public void INCRC0by7MetricsExecMove() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L1/usual/INCRC0by7.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(7, inte.getMetrics().getExecMove());
	}

	@Test
	public void INCRC0by7MetricsDataMove() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L1/usual/INCRC0by7.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(0, inte.getMetrics().getDataMove());
	}

	@Test
	public void INCRC0by7MetricsDataWrite() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L1/usual/INCRC0by7.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(7, inte.getMetrics().getDataWrite());
	}

	@Test
	public void INCRC0by7MetricsDataRead() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L1/usual/INCRC0by7.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(0, inte.getMetrics().getDataRead());
	}

	@Test
	public void JUMP0MetricsProgramSize() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpSimple.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(11, inte.getMetrics().getProgramSize());
	}

	@Test
	public void JUMP0MetricsExecMove() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpSimple.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(3, inte.getMetrics().getExecMove());
	}

	@Test
	public void JUMP0MetricsDataMove() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpSimple.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(0, inte.getMetrics().getDataMove());
	}

	@Test
	public void JUMP0MetricsDataRead() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpSimple.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(1, inte.getMetrics().getDataRead());
	}

	@Test
	public void JUMP0MetricsDataWrite() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpSimple.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(1, inte.getMetrics().getDataWrite());
	}

	@Test
	public void JumpSimple2MetricsProgramSize() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpSimple2.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(12, inte.getMetrics().getProgramSize());
	}

	@Test
	public void JumpSimple2MetricsExecMove() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpSimple2.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(36, inte.getMetrics().getExecMove());
	}

	@Test
	public void JumpSimple2MetricsDataMove() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpSimple2.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(11, inte.getMetrics().getDataMove());
	}

	@Test
	public void JumpSimple2MetricsDataWrite() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpSimple2.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(15, inte.getMetrics().getDataWrite());
	}

	@Test
	public void JumpSimple2MetricsDataRead() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpSimple2.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(7, inte.getMetrics().getDataRead());
	}

	@Test
	public void JumpInternalLoopMetricsProgramSize() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpInternalLoop.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(18, inte.getMetrics().getProgramSize());
	}

	@Test
	public void JumpInternalLoopMetricsExecMove() throws Exception {
		Interpreter inte = new Interpreter(getClass().getResource("/L2/usual/jumpInternalLoop.bf").getFile());
		inte.interpretFile();
		Assert.assertEquals(60, inte.getMetrics().getExecMove());
	}
}
