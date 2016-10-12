package unice.polytech.polystirN.brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;
import unice.polytech.polystirN.brainfuck.interpreter.InterpreterText;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the JumpTest operator class
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class JumpBackTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void doOperation() throws Exception {
    	Interpreter a;

        //Nominal case (syntaxe longue)-------------------------
	        //Test boucle simple operations---------------------
	    	try {
	            a = new InterpreterText("./examples/L2/JUMP0.bf");
	            a.executeFile();
	            assertEquals(1, a.getMemory().getCells()[0]);
	            assertEquals(0, a.getMemory().getCells()[1]);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        try {
	            a = new InterpreterText("./examples/L2/JUMP1.bf");
	            a.executeFile();
	            assertEquals(5, a.getMemory().getCells()[a.getMemory().getP()]);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        	//---------------------------------------------------
	        
        	//Boucles internes-----------------------------------
	        try {
	            a = new InterpreterText("./examples/L2/JUMP2.bf");
	            a.executeFile();
	            assertEquals(0, a.getMemory().getCells()[0]);
	            assertEquals(0, a.getMemory().getCells()[1]);
	            assertEquals(12, a.getMemory().getCells()[2]);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	    	/*
	    	 * Le test suivant équivaut à exécuter et tester le code suivant :
	    	 * 		int i, y, z, e1, e2, e3;
					e1 =0;e2 =0;e3 =0;
					
					for(i=2;i>0;i--){
						e1=e1+1;
						for(y=3;y>0;y--){
							e2=e2+2;
							for(z=4;z>0;z--){
								e3=e3+3;
							}
						}
					}
					System.out.println("e1 : "+e1 +" e2 : "+e2 +" e3 : "+e3);
	    	 */
	    	try {
	            a = new InterpreterText("./examples/L2/JUMP4.bf");
	            a.executeFile();
	            assertEquals(2, a.getMemory().getCells()[2]);
	            assertEquals(3, a.getMemory().getCells()[3]);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	
	        try {
	            a = new InterpreterText("./examples/L2/JUMP3.bf");
	            a.executeFile();
	            assertEquals(2, a.getMemory().getCells()[3]);
	            assertEquals(12, a.getMemory().getCells()[4]);
	            assertEquals(72, a.getMemory().getCells()[5]);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	      //---------------------------------------------------
      //--------------------------------------------------------------------
	        
	  //Anomaly case (syntaxe longue)---------------------------------------
	        try {
	            a = new InterpreterText("./examples/L2/JUMP5.bf");
	            a.executeFile();
	        } catch (Exception e) {
	            e.printStackTrace();
	            assertEquals("BadLoopException", e.getClass().getSimpleName());
	            assertEquals("Loop without end : Missing BACK operator", e.getMessage());
	        }
	  //--------------------------------------------------------------------     
	        
	  //Complete program : Hello world (syntaxe courte)---------------------
	        try {
	            a = new InterpreterText("./examples/helloworld.bf");
	            a.executeFile();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 //--------------------------------------------------------------------
    }

}