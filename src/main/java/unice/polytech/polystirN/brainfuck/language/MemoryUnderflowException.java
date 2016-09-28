package unice.polytech.polystirN.brainfuck.language;

/**
 * Exception class for memory underflows
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class MemoryUnderflowException extends Exception {

    public MemoryUnderflowException(String message) {
        super(message);
    }
}
