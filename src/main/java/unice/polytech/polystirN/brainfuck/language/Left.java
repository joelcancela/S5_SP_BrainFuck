package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.exceptions.PointerPositionOutOfBoundsException;
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
     * @param interpreter memory (M and P) of the current program and all of the following operations.
     * @throws PointerPositionOutOfBoundsException if the pointer has already reached
     *                                             the value of 0 so it can't be moved to the left or if the pointer position
     *                                             is recognized as invalid (out of bounds of memory capacity)
     */
    public void execute(Interpreter interpreter) throws PointerPositionOutOfBoundsException {
        int p = interpreter.getMemory().getP();

        //Anomaly cases :
        if (p == 0)
            throw new PointerPositionOutOfBoundsException("pointer can't be moved to the left (already at position 0)");
        if (p > 29999)
            throw new PointerPositionOutOfBoundsException("pointer is at illegal position");

        //Nominal case :
        interpreter.getMemory().setP(p - 1);
        interpreter.getMetrics().incrementDataMove();
    }
    @Override
    public String toString(){
    	return "<";
    }
}
