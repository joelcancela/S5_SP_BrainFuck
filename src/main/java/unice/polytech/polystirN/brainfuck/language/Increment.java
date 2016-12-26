package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.computationalModel.Memory;
import unice.polytech.polystirN.brainfuck.exceptions.MemoryOverflowException;
import unice.polytech.polystirN.brainfuck.exceptions.PointerPositionOutOfBoundsException;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

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
     * @param interpreter is the current interpreter instance
     * @throws PointerPositionOutOfBoundsException if the pointer position
     *                                             is recognized as invalid (out of bounds of memory capacity)
     * @throws MemoryOverflowException             if the operation goes out of bounds of the memory
     */
    public void execute(Interpreter interpreter) throws PointerPositionOutOfBoundsException, MemoryOverflowException {
        int p = interpreter.getMemory().getP();
        //Test pointer position
        if ((p < 0) || (p > Memory.size - 1)) {
            throw new PointerPositionOutOfBoundsException("pointer must be between 0 and 29999 included");
        }
        //Test overflow
        if (((interpreter.getMemory().getCells()[p]) & 0xFF) == 255) {
            throw new MemoryOverflowException("value can't be higher than 255");
        }
        interpreter.getMemory().getCells()[p]++;
        interpreter.getMetrics().incrementDataWrite();
    }

    @Override
    public String toString() {
        return "+";
    }

    public String generateC(int indentLevel, int consecutive) {
        String indentation = "";
        for (int i = 0; i < indentLevel; i++)
            indentation = indentation + "\t";
        return indentation + "c[p] = c[p] + " + consecutive + ";" + "\n";
    }
}

	
