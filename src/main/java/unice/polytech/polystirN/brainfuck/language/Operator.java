package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * Interface used to specify the operators behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public interface Operator {
    /**
     * This method execute all the operations associated with the corresponding operator.
     *
     * @param memory is the memory (M and P)
     * @return true if the operation ended well, else false
     * @throws Exception if the operation goes against memory capacity or pointer range position
     */
    public boolean doOperation(Interpreter interpreter) throws Exception;
    
}
