package unice.polytech.polystirN.brainfuck.exceptions;

/**
 * Exception class for loop without end
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class BadLoopException extends Exception {
    public BadLoopException(String message) {
        super(message);
    }
}
