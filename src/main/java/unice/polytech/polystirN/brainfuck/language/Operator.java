package unice.polytech.polystirN.brainfuck.language;

/**
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public interface Operator {
    public boolean doOperation(int p, byte[] M) throws Exception;
}
