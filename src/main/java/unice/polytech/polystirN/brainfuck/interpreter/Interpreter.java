package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.language.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;


/**
 * Model the virtual machine interpreting the
 * brainfuck language. The execution context of a program P is a tuple CP = (M, p, i)
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Interpreter {
    /**
     * M is the previously defined memory
     */
    private byte[] memory = {-128};
    /**
     * p is a pointer to the memory cell currently used by the program
     */
    public static int p;
    private HashMap<Character, Operator> symbols;
    private BufferedReader buffer;
    /**
     * Unique instance not initialized
     */
    private static Interpreter INSTANCE = null;

    /**
     * Constructor for objects of class Interpreter.
     */
    private Interpreter() {
        symbols = new HashMap<Character, Operator>();
        symbols.put('+', new Increment());
        symbols.put('-', new Decrement());
        symbols.put('<', new Left());
        symbols.put('>', new Right());
    }


    /**
     * Access point for unique instance of Interpreter
     */
    public static synchronized Interpreter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Interpreter();
        }
        return INSTANCE;
    }


    /**
     * This method specifies the file read by the interpreter
     * also resets the memory and the pointer
     *
     * @param filename is the name of the file read
     * @throws Exception if the file doesn't have the correct extension
     */
    public void init(String filename) throws Exception {
        if (!filename.matches("(.*).bf")) {
            throw new Exception("IncorrectFileType");
        }
        buffer = new BufferedReader(new FileReader(filename));
        p = 0;
        memory = new byte[30000];
    }

    /**
     * Called when a file is need to be read.
     * Throws an error when a character in the file isn't supported
     * by the brainfuck language.
     *
     * @return True if the file was successfully read, false if not.
     * @throws Exception SyntaxError
     */
    public boolean readfile() throws Exception {
        int c;
        while ((c = this.buffer.read()) != -1) {
            if (((char) c != '\r') && ((char) c != '\n')) {
                if (symbols.get((char) c) == null) {
                    throw new Exception("SyntaxError");
                }
                symbols.get((char) c).doOperation(p, memory);
            }
        }
        return false;
    }

    /**
     * methode affiche to transform the byte number to integer
     * and show it
     *
     * @return void
     */
    public void affiche(int p) {
        System.out.println((int) ((memory[p]) & 0x00FF));
    }

    /**
     * Get the current memory.
     *
     * @return What state the memory is in.
     */
    public byte[] getMemory() {
        return memory;
    }
}
