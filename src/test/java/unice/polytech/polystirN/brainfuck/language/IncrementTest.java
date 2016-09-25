package unice.polytech.polystirN.brainfuck.language;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

import static org.junit.Assert.*;

/**
 * @author Joël CANCELA VAZ and Pierre Rainero
 * @author Tanguy Invernizzi and Aghiles Dziri
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
        //Cas nominal 1, avec fichier vide
        Interpreter a = null;
        try {
            a = new Interpreter("./examples/empty.bf");
            a.readfile();
            a.affiche(0);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        //Cas nominal 2, incrementation de c0, 255 fois
        try {
            a = new Interpreter("./examples/incrementMax255.bf");
            a.readfile();
            a.affiche(0);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        //Cas nominal 3, incrementation de c0, 7 fois
        try {
            a = new Interpreter("./examples/incrementC0by7.bf");
            a.readfile();
            a.affiche(0);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        //Cas d'anomalie 1, incrementation de c0, 256 fois
        try {
            a = new Interpreter("./examples/incrementError256.bf");
            a.readfile();
            a.affiche(0);
        } catch (Throwable t) {
            assertEquals("Memory overflow error", t.getMessage());
        }


        //Cas d'anomalie 2, incrementation de c-1
        try {
            a = new Interpreter("./examples/incrementMax255.bf");
            a.setP(-1);
            a.readfile();
        } catch (Exception e) {
            assertEquals("-1", e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

}