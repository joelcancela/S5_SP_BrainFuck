package unice.polytech.polystirN.brainfuck.language;
/**
 * Class Right
 * 
 * @author Joël CANCELA VAZ and Pierre Rainero
 * @author Tanguy Invernizzi and Aghiles Dziri
 */

public class Right implements Operator {

	/**
	 * Default constructor
	 */
	public Right(){
		
	}
	
	/**
	 * Move the pointer of the current memory cell by one to the right
	 * @param p pointer of the current memory cell
	 * @param M all memory cells
	 * @return true if the current memory cell was shifted by one to the right
	 */
	public boolean doOperation(int p, byte[] M) throws Exception {
		//Cas d'anomalie :
		if(p == 29999)
			throw new Exception("p = 29999 +1 | Can't move to the right");
		if(p < 0)
			throw new Exception("p < 0 | Wrong memory");
		
		//Cas nominal :
		p = p+1;
		return true;
	}

}