package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.computationalModel.Memory;
import unice.polytech.polystirN.brainfuck.exceptions.MemoryUnderflowException;
import unice.polytech.polystirN.brainfuck.exceptions.PointerPositionOutOfBoundsException;

/**
 * Class used to specify the Decrement operator behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Decrement implements Operator {

    /**
     * This method execute all the decrement operator operations.
     *
     * @param meme is the current memory state
     * @return true if the operation ended well, else false
     * @throws PointerPositionOutOfBoundsException if the pointer position is recognized as invalid (out of bounds of memory capacity)
     * @throws MemoryUnderflowException            if the operation goes out of bounds of the memory
     */
    public boolean doOperation(Memory meme) throws PointerPositionOutOfBoundsException, MemoryUnderflowException {
        int p = meme.getP();

        //Test pointer position
        if (p < 0 || p > 29999) {
            throw new PointerPositionOutOfBoundsException("pointer must be between 0 and 29999 included");
        }
        //Test underflow
        if (((meme.getCells()[p]) & 0xFF) == 0) {
            throw new MemoryUnderflowException("value can't be negative");
        }
        meme.getCells()[p]--;
        return true;
    }


}

