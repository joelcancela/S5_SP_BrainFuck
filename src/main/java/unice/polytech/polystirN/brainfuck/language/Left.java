package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.computationalModel.Memory;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * Class used to specify the Left operator behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */

public class Left implements Operator {

    /**
     * This method moves the pointer by one to the left
     *
     * @param meme is the memory of the current program (M and P)
     * @return true if the current memory cell was shifted by one to the left
     * @throws Exception PointerMinimumValueError if the pointer has already reached
     * the value of 0 so it can't be moved to the left
     * @throws Exception PointerPositionOutOfBounds if the pointer position
     * is recognized as invalid (out of bounds of memory capacity)
     */
    public boolean doOperation(Memory meme) throws Exception {
    	int p = meme.getP();
    	
        //Anomaly cases :
        if (p == 0)
            throw new Exception("PointerMinimumValueError");
        if (p > 29999)
            throw new Exception("PointerPositionOutOfBounds");

        //Nominal case :
        meme.setP(p - 1);
        return true;
    }

}
