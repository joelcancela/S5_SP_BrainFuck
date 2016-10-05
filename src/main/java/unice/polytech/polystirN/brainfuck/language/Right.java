package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.exceptions.PointerPositionOutOfBoundsException;
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
     * @param memory is the memory of the current program (M and P)
     * @return true if the current memory cell was shifted by one to the right
     * @throws PointerPositionOutOfBoundsException if the pointer has already reached
     *                                             the value of 299999 so it can't be moved to the right or if the pointer position
     *                                             is recognized as invalid (out of bounds of memory capacity)
     */
    public boolean doOperation(Interpreter interpreter) throws PointerPositionOutOfBoundsException {
        int p = interpreter.getMemory().getP();

        //Anomaly cases :
        if (p == 29999)
            throw new PointerPositionOutOfBoundsException("pointer can't be moved to the right (already at position 29999)");
        if (p < 0)
            throw new PointerPositionOutOfBoundsException("pointer is at illegal position");

        //Nominal case :
        interpreter.getMemory().setP(p + 1);
        return true;
    }

}