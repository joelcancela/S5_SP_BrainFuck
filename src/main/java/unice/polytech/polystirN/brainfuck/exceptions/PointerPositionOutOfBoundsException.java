package unice.polytech.polystirN.brainfuck.exceptions;

/**
 * Exception class for pointer positions out of bounds
 * (positive and negative)
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class PointerPositionOutOfBoundsException extends Exception {

    public PointerPositionOutOfBoundsException(String message) {
        super(message);
    }
}
