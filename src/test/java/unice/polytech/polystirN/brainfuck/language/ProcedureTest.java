package unice.polytech.polystirN.brainfuck.language;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

public class ProcedureTest {
    private Interpreter intrptr;

    @Test
    public void ProcedureExécution() throws Exception {
            intrptr = new Interpreter("./examples/L4/ProcedureExample1.bf");
            intrptr.interpretFile();
            assertEquals(10, intrptr.getMemory().getCells()[intrptr.getMemory().getP()] & 0xFF);
        
    }
    
    
    @Test
    public void ProcedureError() throws Exception {
        try {    	
        	intrptr = new Interpreter("./examples/L4/prb.bf");
        	intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("Incorrect word operator", e.getMessage());
        }
    }
    
    @Test
    public void ProcedureErrorSyntax() throws Exception {
    	intrptr = new Interpreter("./examples/L4/ProcedureExampleError.bf");
    	try {	
        	intrptr.interpretFile();
        } catch (Exception e) {
        	assertEquals("the function should contain { and }",e.getMessage());
           
        }
    }
    @Test
    public void functionWithoutReturn() throws Exception {
    	intrptr = new Interpreter("./examples/L4/ProcedureExampleError.bf");
    	try {	
        	intrptr.interpretFile();
        } catch (Exception e) {
        	assertEquals("the function should contain { and }",e.getMessage());
           
        }
    }
}
