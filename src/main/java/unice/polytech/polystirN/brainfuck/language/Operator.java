package unice.polytech.polystirN.brainfuck.language;

/**
 * Interface used to specify the operators behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public interface Operator {
    /**
     * This method execute all the operator operations.
     *
     * @param p is the index of the memory cell currently used
     * @param M is the current memory state
     * @return true if the operation ended well, else false
     * @throws Exception if the operation goes against memory capacity
     */
    public boolean doOperation(int p, byte[] M) throws Exception;
}
