package unice.polytech.polystirN.brainfuck.language;

import java.io.FileNotFoundException;

import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * Class Increment
 * 
 * @author JoÃ«l CANCELA VAZ and Pierre Rainero
 * @author Tanguy Invernizzi and Aghiles Dziri
 */


public class Increment implements Operator {
	
	/**
	 * methode doOperation increment the courent case of memory 
	 * and @return boolean if the case is incremented else return an exception
	 */

	
	public boolean doOperation(int p,byte[] M) throws Exception{
			
		
		    M[p]++;
		   
		    /**
		     * overflow or not
		     */
		    
			if((((float)((M[p]) & 0x00FF))/255)==0) {
				
				throw new Exception("dépassement de capacitie memoire");
			
			}
			return true;
			
	}

	

}

	
