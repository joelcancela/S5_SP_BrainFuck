package unice.polytech.polystirN.brainfuck.exceptions;

/**
 * Exception class for incorrect file types
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class IncorrectFileTypeException extends Exception {

	private static final long serialVersionUID = 906034342661260277L;

	public IncorrectFileTypeException(String message) {
		super(message);
	}
}
