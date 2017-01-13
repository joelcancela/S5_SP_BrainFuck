package unice.polytech.polystirN.brainfuck.exceptions;

/**
 * Exception class for pointer positions out of bounds
 * (positive and negative)
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class PointerPositionOutOfBoundsException extends Exception {

	private static final long serialVersionUID = 4951164788640972956L;

	public PointerPositionOutOfBoundsException(String message) {
		super(message);
	}
}
