package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.computationalModel.Memory;
import unice.polytech.polystirN.brainfuck.exceptions.MemoryOverflowException;
import unice.polytech.polystirN.brainfuck.exceptions.PointerPositionOutOfBoundsException;

/**
 * Class used to specify the Increment operator behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Increment implements Operator {

    /**
     * This method execute all the Increment operator operations.
     *
     * @param memory is the current Memory state
     * @return true if the operation ended well, else false
     * @throws PointerPositionOutOfBoundsException if the pointer position
     *                                             is recognized as invalid (out of bounds of memory capacity)
     * @throws MemoryOverflowException             if the operation goes out of bounds of the memory
     */
    public boolean doOperation(Memory memory) throws PointerPositionOutOfBoundsException, MemoryOverflowException {
        int p = memory.getP();

        //Test pointer position
        if ((p < 0) || (p > 29999)) {
            throw new PointerPositionOutOfBoundsException("pointer must be between 0 and 29999 included");
        }
        //Test overflow
        if (((memory.getCells()[p]) & 0xFF) == 255) {
            throw new MemoryOverflowException("value can't be higher than 255");
        }
        memory.getCells()[p]++;
        return true;
    }
}

	
