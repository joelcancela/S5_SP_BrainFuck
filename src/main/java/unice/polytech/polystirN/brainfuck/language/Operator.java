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
     * This method executes all the operations associated with the corresponding operator.
     *
     * @param interpreter is the current instance of the interpreter
     * @throws Exception if the operation goes against memory capacity or pointer range position
     */
    void execute(Interpreter interpreter) throws Exception;
    @Override
    public String toString();

}
