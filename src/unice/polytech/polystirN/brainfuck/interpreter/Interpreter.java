package unice.polytech.polystirN.brainfuck.interpreter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import unice.polytech.polystirN.brainfuck.language.Increment;

import unice.polytech.polystirN.brainfuck.language.Operator;

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
	private  byte[]						memory={-128};
	/** 
	 * p is a pointer to the memory cell currently used by the program
	 */
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
		//I put that increment for now
		symbols.put('+', new Increment());
		//symbols.put('-', new Operator());
		memory = new byte[30000];
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
			symbols.get((char)c).doOperation(p, memory); 
		}
		return false;
	}
    /**
    *methode affiche to transform the byte number to integer
    *and show it
    *@return void
    */
    public void affiche(int p){
    	System.out.println((int)((memory[p]) & 0x00FF));	
    }
    /**
     * Get the current memory.
     * @return What state the memory is in.
     */
    public byte[] getMemory() {
    	return memory;
    }
    public void setP(int p) {
		this.p = p;
	}
	/**
     * Get the pointer to the memory cell currently used by the program.
     * @return The index of the current cell in the memory array used. 
     */
    public int getP () {
    	return p;
    }
}
