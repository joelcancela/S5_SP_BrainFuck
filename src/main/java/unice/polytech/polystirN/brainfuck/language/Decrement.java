package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

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
     * @param p is the index of the memory cell currently used
     * @param M is the current memory state
     * @return true if the operation ended well, else false
     * @throws Exception MemoryUnderflowError if the operation goes out of bounds of the memory
     * @throws Exception PointerPositionOutOfBounds if the pointer position
     * is recognized as invalid (out of bounds of memory capacity)
     */
    public boolean doOperation(Interpreter inte) throws Exception {
    	int p = inte.getP();
    	
        //Test pointer position
        if(p<0 || p>29999){
            throw new Exception("PointerPositionOutOfBounds");
        }
        //Test underflow
        if (((inte.getMemory()[p]) & 0xFF) == 0) {
            throw new Exception("MemoryUnderflowError");
        }
        inte.getMemory()[p]--;
        return true;
    }
}

