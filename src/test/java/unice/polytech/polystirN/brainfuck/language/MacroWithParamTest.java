package unice.polytech.polystirN.brainfuck.language;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

public class MacroWithParamTest {

	private static Interpreter intrptr;
	private int mask = 0xff; 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}


	@Test
	public void test() throws Exception {
		intrptr = new Interpreter("./examples/L3/demoMacros.bf");
		intrptr.interpretFile();
		assertEquals(130 ,intrptr.getMemory().getCells()[0] & mask);
		intrptr = new Interpreter("./examples/L3/demoMacrosTO_DIGITetMULTI_DECR.bf");
		intrptr.interpretFile();
		assertEquals(97 ,intrptr.getMemory().getCells()[0] & mask);
		intrptr = new Interpreter("./examples/L3/demoMacrosVide.bf");
		intrptr.interpretFile();
		assertEquals(0 ,intrptr.getMemory().getCells()[0] & mask);
		intrptr = new Interpreter("./examples/L3/ParamErreurMacros.bf");
		try{
			intrptr.interpretFile();
		}catch(SyntaxErrorException e){
			assertEquals("Incorrect word operator" ,e.getMessage());
		}
		intrptr = new Interpreter("./examples/L3/macros_avecParam.bf");
		intrptr.interpretFile();
		assertEquals(97 ,intrptr.getMemory().getCells()[0] & mask);
		intrptr = new Interpreter("./examples/L3/demoMacrosParamErreur.bf");
		try{
			intrptr.interpretFile();
		}catch(SyntaxErrorException e){
			assertEquals("Incorrect word operator" ,e.getMessage());
		}
	}

}
