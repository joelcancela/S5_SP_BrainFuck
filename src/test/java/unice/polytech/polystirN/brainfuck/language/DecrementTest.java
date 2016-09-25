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

        //Cas nominal 1, avec fichier vide
        Interpreter a = Interpreter.getInstance();
        try {
            a.init("./examples/empty.bf");
            a.readfile();
            a.affiche(0);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        //Cas nominal 2, decrementation de c0 3 fois apres incrementation 3 fois
        try {
            a.init("./examples/decrementSimple.bf");
            a.readfile();
            a.affiche(0);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        //Cas d'anomalie 1, decrementation de c0 7 fois
        try {
            a.init("./examples/decrementC0by7.bf");
            a.readfile();
            a.affiche(0);
        } catch (Throwable t) {
            assertEquals("Memory underflow error", t.getMessage());
        }


        //Cas d'anomalie 2, decrementation de c0 à c-1
        try {
            a.init("./examples/decrementError.bf");
            a.p = -1;
            a.readfile();
        } catch (Exception e) {
            assertEquals("-1", e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}