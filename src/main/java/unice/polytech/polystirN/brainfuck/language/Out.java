package unice.polytech.polystirN.brainfuck.language;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * Class used to specify the Out operator behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Out implements Operator {
	
	private String filename;
	private File fichier;

	/**
	 * Constructor of the Out class
	 * @param The filename where out is supposed to write, or null if the operator 
	 * 		  is supposed to write on the standard output.
	 */
	public Out(String filename) {
		this.filename=filename;
	}

	/**
	 *  Write the current cell memory content as an ASCII character, either on the standard output or in a file.
	 *  @param object of type Interpreter
	 *  @return true if the character was successfully written, false if not.
	 */
	@Override
	public boolean doOperation(Interpreter interpreter) throws Exception {
		if(filename==null) {
			System.out.print((char)(interpreter.getMemory().getCells()[interpreter.getMemory().getP()] & 0xFF));
		}
		else {
				fichier = new File(filename);
				FileWriter fichierw = new FileWriter (filename,true);
				fichierw.write(((char)(interpreter.getMemory().getCells()[interpreter.getMemory().getP()] & 0xFF)));
				fichierw.close();
				
		}
		return true;
	}
}