package unice.polytech.polystirN.brainfuck.exceptions;

/**
 * Exception class for incorrect file types (different from *.bf)
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class IncorrectFileTypeException extends Exception {
    public IncorrectFileTypeException(String message) {
        super(message);
    }
}
