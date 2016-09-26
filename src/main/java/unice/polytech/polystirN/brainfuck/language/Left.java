package unice.polytech.polystirN.brainfuck.language;

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
     * @param p is the index of the memory cell currently used
     * @param M is the current memory state
     * @return true if the current memory cell was shifted by one to the left
     * @throws Exception PointerMinimumValueError if the pointer has already reached
     * the value of 0 so it can't be moved to the left
     * @throws Exception PointerPositionOutOfBounds if the pointer position
     * is recognized as invalid (out of bounds of memory capacity)
     */
    public boolean doOperation(int p, byte[] M) throws Exception {
        //Anomaly cases :
        if (p == 0)
            throw new Exception("PointerMinimumValueError");
        if (p > 29999)
            throw new Exception("PointerPositionOutOfBounds");

        //Nominal case :
        Interpreter.setP(Interpreter.getP() - 1);
        return true;
    }

}
