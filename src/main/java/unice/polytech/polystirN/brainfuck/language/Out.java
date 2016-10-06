package unice.polytech.polystirN.brainfuck.language;

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
	/**
	 * constructor
	 * @param filename if it exist else null
	 */
	public Out(String filename){
		this.filename=filename;
	}
	/**
	 * methode doOperation write a charater on the Console or on a file
	 *  @param object of type Interpreter
	 *  @return boolean
	 */
	@Override
	public boolean doOperation(Interpreter interpreter) throws Exception {
		if(filename==null){
			System.out.println((char)(interpreter.getMemory().getCells()[interpreter.getMemory().getP()] & 0xFF));
		}
		else {
			try
			{
			    FileWriter fichierw = new FileWriter (filename,true);
			    fichierw.write(((char)(interpreter.getMemory().getCells()[interpreter.getMemory().getP()] & 0xFF)));
			    fichierw.close();
			}
			catch (IOException exception)
			{
			    System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
			}
			
		}
		return true;
	}
	
	
	
}