package unice.polytech.polystirN.brainfuck.exceptions;

/**
 * Exception class for malformed squares in images
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class BadSquareColorException extends Exception {
    public BadSquareColorException(int number) {
        super("Square #" + number + " is not monochrome");
    }
}
