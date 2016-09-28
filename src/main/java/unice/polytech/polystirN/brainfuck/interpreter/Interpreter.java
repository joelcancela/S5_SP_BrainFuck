package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.computationalModel.Memory;
import unice.polytech.polystirN.brainfuck.language.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;


/**
 * Model the virtual machine interpreting the
 * brainfuck language. The execution context of a program P is a tuple CP = (M, p, i)
 * M is the previously defined memory
 * p is an index (pointer) to the memory cell currently used by the program
 * i is the current value of the memory case M[p]
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Interpreter {

    /**
     * memory represents the memory M
     * p represents the pointer
     * symbols is an HashMap linking each operator character to their correct operator
     * buffer is BufferedReader used to read files
     */
	private Memory memory;
    private HashMap<Character, Operator> symbols;
    private BufferedReader buffer;
    

    /**
     * This method specifies the file read by the interpreter,
     * it also resets the memory and the pointer and
     * sets the correct symbols to interpret as operators
     *
     * @param filename is the name of the file to read
     * @throws Exception if the file doesn't have the correct extension (.bf)
     */
    public Interpreter(String filename) throws Exception {
        symbols = new HashMap<Character, Operator>();
        symbols.put('+', new Increment());
        symbols.put('-', new Decrement());
        symbols.put('<', new Left());
        symbols.put('>', new Right());
        if (!filename.matches("(.*).bf")) {
            throw new Exception("IncorrectFileType");
        }
        buffer = new BufferedReader(new FileReader(filename));
    }

    /**
     * Called when a file has to be read.
     * Throws an error when a character in the file isn't supported
     * by the brainfuck language.
     *
     * @return true if the file was successfully read, false if not.
     * @throws Exception SyntaxError
     */
    public boolean readfile() throws Exception {
        int c;
        while ((c = buffer.read()) != -1) {
            if (((char) c != '\r') && ((char) c != '\n')) {
                if (symbols.get((char) c) == null) {
                    throw new Exception("SyntaxError");
                }
                symbols.get((char) c).doOperation(this);
            }
        }
        return false;
    }

    /**
     * Get the current memory.
     *
     * @return The current state of the memory.
     */
    public byte[] getMemory() {
        return memory;
    }

    /**
     * Get the current memory cell index
     *
     * @return Which memory cell is selected.
     */
    public int getP() {
        return p;
    }

    /**
     * Set the current memory cell index.
     *
     * @param nP New value of p.
     */
    public void setP(int nP) {
        p = nP;
    }
}
