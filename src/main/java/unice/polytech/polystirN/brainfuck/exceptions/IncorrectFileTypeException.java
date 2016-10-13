package unice.polytech.polystirN.brainfuck.exceptions;

/**
 * Exception class for incorrect file types
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class IncorrectFileTypeException extends Exception {
    public IncorrectFileTypeException(String message) {
        super(message);
    }
}
