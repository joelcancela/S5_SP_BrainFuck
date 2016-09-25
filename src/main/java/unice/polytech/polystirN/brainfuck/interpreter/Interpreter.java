package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.language.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/**
 * Model the virtual machine interpreting the
 * brainfuck language. The execution context of a program P is a tuple CP = (M, p, i)
 *
 * @author Joël CANCELA VAZ and Pierre Rainero
 * @author Tanguy Invernizzi and Aghiles Dziri
 */
public class Interpreter {
    /**
     * M is the previously defined memory
     */
    //I have changed the Type Byte to byte
    private byte[] memory = {-128};
    /**
     * p is a pointer to the memory cell currently used by the program
     */
    public static int p;
    private HashMap<Character, Operator> symbols;
    private BufferedReader buffer;

    /**
     * Constructor for objects of class Interpreter.
     *
     */

    private Interpreter(){
        symbols = new HashMap<Character, Operator>();
        symbols.put('+', new Increment());
        symbols.put('-', new Decrement());
        symbols.put('<', new Left());
        symbols.put('>', new Right());
    }

    /** Instance unique non préinitialisée */
    private static Interpreter INSTANCE = null;

    /** Point d'accès pour l'instance unique du singleton */
    public static synchronized Interpreter getInstance()
    {
        if (INSTANCE == null)
        { 	INSTANCE = new Interpreter();
        }
        return INSTANCE;
    }


    public void init(String filename) throws Exception{
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
