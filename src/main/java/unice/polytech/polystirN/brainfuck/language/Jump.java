package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.exceptions.PointerPositionOutOfBoundsException;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * Class used to specify the Jump operator behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */

public class Jump implements Operator {

    /**
     * This method checks if the content of the current memory cell. 
     * This operator does nothing if the value is different of 0.
     * Else, this operator will read the next instruction until he meet the BACK operator.
     *
     * @param memory is the memory of the current program (M and P)
     * @return true if it's not equal to 0 and false if it's equal to 0
     * @throws PointerPositionOutOfBoundsException if the pointer value is inferior to 0
     */
	@Override
	public boolean doOperation(Interpreter interpreter) throws PointerPositionOutOfBoundsException {
		int p = interpreter.getMemory().getP();
		int nbOuvert = 1;
		
		//Anomaly case :
        if (p < 0)
            throw new PointerPositionOutOfBoundsException("current memory have illegal value (inferior to 0)");
        
        //Nominal case :
        if(interpreter.getMemory().getCells()[p] == 0){
        	while(nbOuvert != 0){
        		
        	}
        }
        
        return true;
	}
	
}
