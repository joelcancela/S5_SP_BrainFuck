package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.computationalModel.Memory;
import unice.polytech.polystirN.brainfuck.exceptions.MemoryUnderflowException;
import unice.polytech.polystirN.brainfuck.exceptions.PointerPositionOutOfBoundsException;
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
     * @param interpreter is the current interpreter instance
     * @throws PointerPositionOutOfBoundsException if the pointer position is recognized as invalid (out of bounds of memory capacity)
     * @throws MemoryUnderflowException            if the operation goes out of bounds of the memory
     */
    public void execute(Interpreter interpreter) throws PointerPositionOutOfBoundsException, MemoryUnderflowException {
        int p = interpreter.getMemory().getP();

        //Test pointer position
        if (p < 0 || p > Memory.size - 1) {
            throw new PointerPositionOutOfBoundsException("pointer must be between 0 and " + (Memory.size - 1) + " included");
        }
        //Test underflow
        if (((interpreter.getMemory().getCells()[p]) & 0xFF) == 0) {
            throw new MemoryUnderflowException("value can't be negative");
        }
        interpreter.getMemory().getCells()[p]--;
        interpreter.incrementData_Write();
    }
    @Override
    public String toString(){
    	return "-";
    }

}

