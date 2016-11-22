package unice.polytech.polystirN.brainfuck.interpreter;

/**
 * Abstract class for the input reader
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public abstract class Reader {

    public abstract boolean hasNext() throws Exception;

    public abstract String next() throws Exception;
}
