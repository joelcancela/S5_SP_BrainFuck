package unice.polytech.polystirN.brainfuck.language;

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
     * @param p is the index of the memory cell currently used
     * @param M is the current memory state
     * @return true if the operation ended well, else false
     * @throws Exception MemoryOverflowError if the operation goes out of bounds of the memory
     * @throws Exception PointerPositionOutOfBounds if the pointer position
     * is recognized as invalid (out of bounds of memory capacity)
     */
    public boolean doOperation(int p, byte[] M) throws Exception {

        //Test pointer position
        if((p<0) || (p>29999)){
            throw new Exception("PointerPositionOutOfBounds");
        }
        //Test overflow
        if ((((float) ((M[p]) & 0x00FF)) / 255) == 255) {
            throw new Exception("MemoryOverflowError");
        }
        M[p]++;
        return true;
    }
}

	