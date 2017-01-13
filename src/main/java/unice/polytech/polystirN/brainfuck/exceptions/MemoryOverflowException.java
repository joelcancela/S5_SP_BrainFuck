package unice.polytech.polystirN.brainfuck.exceptions;

/**
 * Exception class for memory overflows
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class MemoryOverflowException extends Exception {

	private static final long serialVersionUID = 4610418598834851745L;

	public MemoryOverflowException(String message) {
		super(message);
	}
}
