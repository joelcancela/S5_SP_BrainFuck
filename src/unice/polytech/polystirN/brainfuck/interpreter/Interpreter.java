package unice.polytech.polystirN.brainfuck.interpreter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import unice.polytech.polystirN.brainfuck.language.Operator;

/**
 * Model the virtual machine interpreting the
 * brainfuck language. 
 * 
 * @author Joël CANCELA VAZ and Pierre Rainero
 * @author Tanguy Invernizzi and Aghiles Dziri
 */
public class Interpreter {
	
	private Byte[]							memory;
	private int								p;
	private HashMap<Character, Operator>	symbols;
	private BufferedReader					buffer;
	
	/**
     * Constructor for objects of class Interpreter.
	 * @throws FileNotFoundException 
     */
	public Interpreter(String fileName) throws FileNotFoundException,Throwable {
		if (!fileName.matches("(.*).bf")) {
			throw new Throwable("IncorrectFileType");
		}
		p = 0;
		symbols = new HashMap<Character, Operator>();
		symbols.put('+', new Operator());
		symbols.put('-', new Operator());
		memory = new Byte[30000];
		buffer = new BufferedReader(new FileReader(fileName));
	}
	
    /**
     * Called when a file is need to be read.
     * Throws an error when a character in the file isn't supported
     * by the brainfuck language.
     * 
     * @param filename The name of the file.
     * @return True if the file was successfully read, false if not.
     * @throws SyntaxError
     */
    public boolean readfile() throws Throwable {
    	int c;
    	
		while((c = this.buffer.read()) != -1) {
			if (symbols.get((char)c) == null) {
				throw new Throwable("SyntaxError");
			}
			//symbols.get(c); doOp
		}
		return false;
	}
    
    /**
     * Get the current memory.
     * @return What state the memory is in.
     */
    public Byte[] getMemory() {
    	return memory;
    }

    /**
     * Get the pointer to the memory cell currently used by the program.
     * @return The index of the current cell in the memory array used. 
     */
    public int getP () {
    	return p;
    }
}
