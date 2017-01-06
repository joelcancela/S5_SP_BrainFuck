package unice.polytech.polystirN.brainfuck.language;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

public class FunctionTest {
    private Interpreter intrptr;

    @Test
    public void functionReturningAParamater() throws Exception {
        try {
            intrptr = new Interpreter(getClass().getResource("/L4/functionExample1.bf").getFile());
            intrptr.interpretFile();
            assertEquals(100, intrptr.getMemory().getCells()[intrptr.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void functionReturningAPointeurModified() throws Exception {
        try {
            intrptr = new Interpreter(getClass().getResource("/L4/functionExample2.bf").getFile());
            intrptr.interpretFile();
            assertEquals(100, intrptr.getMemory().getCells()[intrptr.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void functionReturningAPointeurNotModified() throws Exception {
        try {
            intrptr = new Interpreter(getClass().getResource("/L4/functionExample3.bf").getFile());
            intrptr.interpretFile();
            assertEquals(0, intrptr.getMemory().getCells()[intrptr.getMemory().getP()] & 0xFF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void functionReturnUnknownParam() throws Exception {
        try {
        	intrptr = new Interpreter(getClass().getResource("/L4/functionExampleError1.bf").getFile());
        	intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("BadFunctionException", e.getClass().getSimpleName());
            assertEquals("Bad return value. Should be a pointeur value (integer between 0 and 29999 or a param).", e.getMessage());
        }
    }
    
    @Test
    public void functionWithoutReturn() throws Exception {
        try {
        	intrptr = new Interpreter(getClass().getResource("/L4/functionExampleError2.bf").getFile());
        	intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("BadFunctionException", e.getClass().getSimpleName());
            assertEquals("Missing \"return\" keyword.", e.getMessage());
        }
    }
    
    @Test
    public void functionUnknownWord() throws Exception {
        try {
        	intrptr = new Interpreter(getClass().getResource("/L4/functionExampleError3.bf").getFile());
        	intrptr.interpretFile();
        } catch (Exception e) {
            assertEquals("SyntaxErrorException", e.getClass().getSimpleName());
            assertEquals("Unknown word : ret urn x", e.getMessage());
        }
    }
}
