package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.computationalModel.Memory;
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
     * @param meme is the memory of the current program (M and P)
     * @return true if the current memory cell was shifted by one to the right
     * @throws Exception PointerMaximumValueError if the pointer has already reached
     * the value of 299999 so it can't be moved to the right
     * @throws Exception PointerPositionOutOfBounds if the pointer position
     * is recognized as invalid (out of bounds of memory capacity)
     */
    public boolean doOperation(Memory meme) throws Exception {
    	int p = meme.getP();
    	
        //Anomaly cases :
        if (p == 29999)
            throw new Exception("PointerMaximumValueError");
        if (p < 0)
            throw new Exception("PointerPositionOutOfBounds");

        //Nominal case :
        meme.setP(p + 1);
        return true;
    }

}