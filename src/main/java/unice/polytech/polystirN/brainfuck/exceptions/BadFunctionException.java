package unice.polytech.polystirN.brainfuck.exceptions;

/**
 * Exception class for malformed function
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class BadFunctionException extends Exception {

    private static final long serialVersionUID = -6832017286379417581L;

    public BadFunctionException(String message) {
        super(message);
    }
}