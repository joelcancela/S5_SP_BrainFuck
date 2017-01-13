package unice.polytech.polystirN.brainfuck.exceptions;

/**
 * Exception class for malformed loops
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class BadLoopException extends Exception {

	private static final long serialVersionUID = -6832017286379417581L;

	public BadLoopException(String message) {
		super(message);
	}
}
