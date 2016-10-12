package unice.polytech.polystirN.brainfuck.language;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * Class used to specify the In operator behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class In implements Operator {

	private String filename;
	private Scanner sc;
	private BufferedReader buffer;

	/**
	 * Constructor of the In class
	 * @param The filename where In is supposed to read, or null if the operator 
	 * 		  is supposed to read on the standard input.
	 */
	public In(String filename) {
		this.filename=filename;
	}

	/**
	 *  Read a character, either on the standard output or in a file, and put
	 *  it in the current cell memory as the new value of the cell, erasing
	 *  it's previous content.
	 *  @param object of type Interpreter
	 *  @return true if the character was successfully written, false if not.
	 */
	@Override
	public boolean doOperation(Interpreter interpreter) throws Exception {
		if(filename == null) {
			sc = new Scanner(System.in);
			interpreter.getMemory().getCells()[interpreter.getMemory().getP()]=(byte)sc.next().charAt(0);
		}
		else {
			buffer = new BufferedReader(new FileReader(filename));
			interpreter.getMemory().getCells()[interpreter.getMemory().getP()]=(byte)buffer.read();	
			buffer.close();
		}
		return true;
	}
}
