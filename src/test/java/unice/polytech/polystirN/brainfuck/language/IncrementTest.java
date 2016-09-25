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
public class IncrementTest {
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

        //Cas nominal 2, incrementation de c0, 255 fois
        try {
        	Interpreter.init("./examples/incrementMax255.bf");
        	Interpreter.readfile();
        	m = Interpreter.getMemory();
        	assertEquals(255, m[Interpreter.getP()] & 0xFF);
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }

        //Cas nominal 3, incrementation de c0, 7 fois
        try {
        	Interpreter.init("./examples/incrementC0by7.bf");
        	Interpreter.readfile();
        	m = Interpreter.getMemory();
        	assertEquals(7, m[Interpreter.getP()] & 0xFF);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        //Cas d'anomalie 1, incrementation de c0, 256 fois
        try {
        	Interpreter.init("./examples/incrementError256.bf");
        	Interpreter.readfile();
        } catch (Throwable t) {
            assertEquals("Memory overflow error", t.getMessage());
        }


        //Cas d'anomalie 2, incrementation de c-1
        try {
        	Interpreter.init("./examples/incrementMax255.bf");
        	Interpreter.setP(-1);
        	Interpreter.readfile();
        } catch (Exception e) {
            assertEquals("-1", e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}