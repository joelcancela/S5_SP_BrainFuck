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
public abstract class Interpreter {
    /**
     * M is the previously defined memory
     */
    //I have changed the Type Byte to byte
    private static byte[] memory = {-128};
    /**
     * p is a pointer to the memory cell currently used by the program
     */
    private static int p;
    private static HashMap<Character, Operator> symbols;
    private static BufferedReader buffer;

    /**
     * This method specifies the file read by the interpreter
     * also resets the memory and the pointer
     *
     * @param filename is the name of the file read
     * @throws Exception if the file doesn't have the correct extension
     */
    public static void init(String filename) throws Exception {
    	symbols = new HashMap<Character, Operator>();
        symbols.put('+', new Increment());
        symbols.put('-', new Decrement());
        symbols.put('<', new Left());
        symbols.put('>', new Right());
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
    public static boolean readfile() throws Exception {
        int c;
        while ((c = buffer.read()) != -1) {
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
     * Get the current memory.
     *
     * @return What state the memory is in.
     */
    public static byte[] getMemory() {
        return memory;
    }
    
    /**
     * Get the current memory cell.
     *
     * @return What memory cell is selected.
     */
    public static int getP() {
        return p;
    }
    
    /**
     * Set the current memory cell.
     *
     * @param nP New value of p.
     */
    public static void setP(int nP) {
        p = nP;
    }
}
