package unice.polytech.polystirN.brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

import static org.junit.Assert.assertEquals;

/**
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class DecrementTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void doOperation() throws Exception {
    	byte[] m;
    	
        //Cas nominal 1, avec fichier vide
        try {
        	Interpreter.init("./examples/empty.bf");
        	Interpreter.readfile();
        	m = Interpreter.getMemory();
         	assertEquals(0, m[Interpreter.getP()] & 0xFF);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        //Cas nominal 2, decrementation de c0 3 fois apres incrementation 3 fois
        try {
        	Interpreter.init("./examples/decrementSimple.bf");
        	Interpreter.readfile();
        	m = Interpreter.getMemory();
         	assertEquals(0, m[Interpreter.getP()] & 0xFF);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        //Cas d'anomalie 1, decrementation de c0 7 fois
        try {
        	Interpreter.init("./examples/decrementC0by7.bf");
        	Interpreter.readfile();
        } catch (Throwable t) {
            assertEquals("Memory underflow error", t.getMessage());
        }


        //Cas d'anomalie 2, decrementation de c0 Ã  c-1
        try {
        	Interpreter.init("./examples/decrementError.bf");
        	Interpreter.setP(-1);
        } catch (Exception e) {
            assertEquals("-1", e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}