package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * Class used to specify the Right operator behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */

public class Right implements Operator {

    /**
     * This method moves the pointer by one to the right
     *
     * @param p is the index of the memory cell currently used
     * @param M is the current memory state
     * @return true if the current memory cell was shifted by one to the right
     * @throws Exception PointerMaximumValueError if the pointer has already reached
     * the value of 299999 so it can't be moved to the right
     * @throws Exception PointerPositionOutOfBounds if the pointer position
     * is recognized as invalid (out of bounds of memory capacity)
     */
    public boolean doOperation(Interpreter inte) throws Exception {
    	int p = inte.getP();
    	
        //Anomaly cases :
        if (p == 29999)
            throw new Exception("PointerMaximumValueError");
        if (p < 0)
            throw new Exception("PointerPositionOutOfBounds");

        //Nominal case :
        inte.setP(p + 1);
        return true;
    }

}