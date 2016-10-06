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
public class In implements Operator{
	private String filename;
	private Scanner sc;
	private BufferedReader buffer;
	/**
	 * constructor
	 * @param filename if it exist else null
	 */
	public In(String filename){
		this.filename=filename;
	}
	/**
	 * methode doOperation read a charater on the Console or on a file
	 *  @param object of type Interpreter
	 *  @return boolean
	 */
	@Override
	public boolean doOperation(Interpreter interpreter) throws Exception {
		if(filename==null){
			sc = new Scanner(System.in);
			interpreter.getMemory().getCells()[interpreter.getMemory().getP()]=sc.nextByte();
		}
		else{
			try{
		
				buffer = new BufferedReader(new FileReader(filename));
				interpreter.getMemory().getCells()[interpreter.getMemory().getP()]=(byte)buffer.read();
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
			buffer.close();
		}
		
		return true;
	}
	
	

}
