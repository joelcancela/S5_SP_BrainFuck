package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Class used to specify the In operator behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class In implements Operator {

    private String filename;
    private BufferedReader buffer;

    /**
     * In constructor
     *
     * @param filename where In is supposed to read, or null if the operator
     *                 is supposed to read on the standard input.
     * @throws FileNotFoundException if the filename is incorrect
     */
    public In(String filename) throws FileNotFoundException {
        this.filename = filename;
        if (filename != null) {
            buffer = new BufferedReader(new FileReader(filename));
        }
    }

    /**
     * In constructor without filename, uses standard input instead (keyboard)
     */
    public In() {
        this.filename = null;
    }

    /**
     * Read a character, either on the standard output or in a file, and put
     * it in the current cell memory as the new value of the cell, erasing
     * it's previous content.
     *
     * @param interpreter is the current Interpreter instance
     */
    @Override
    public void execute(Interpreter interpreter) throws Exception {
        if (filename == null) {
            Scanner sc = new Scanner(System.in);
            interpreter.getMemory().getCells()[interpreter.getMemory().getP()] = (byte) sc.next().charAt(0);
        } else {
            interpreter.getMemory().getCells()[interpreter.getMemory().getP()] = (byte) buffer.read();
        }
    }
}
