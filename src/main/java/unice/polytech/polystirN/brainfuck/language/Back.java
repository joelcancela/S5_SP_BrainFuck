package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.exceptions.PointerPositionOutOfBoundsException;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * Class used to specify the Back operator behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */

public class Back implements Operator {

    /**
     * This method checks the content of the current memory cell.
     *
     * @param interpreter memory (M and P) of the current program and all of the following operations.
     * @return true if the value is different to 0.
     * @throws PointerPositionOutOfBoundsException if the pointer value is inferior to 0.
     */
	@Override
	public boolean execute(Interpreter interpreter) throws PointerPositionOutOfBoundsException, Exception {
		int dp = interpreter.getMemory().getCells()[interpreter.getMemory().getP()];
		
		//Anomaly case :
        if (dp < 0)
            throw new PointerPositionOutOfBoundsException("current memory have illegal value (inferior to 0)");
		
        //Nominal case :
        if (dp > 0){
        	return true;
        } else {
        	return false;
        }
	}
	
}
