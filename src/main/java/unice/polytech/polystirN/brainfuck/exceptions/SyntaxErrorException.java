package unice.polytech.polystirN.brainfuck.exceptions;

/**
 * Exception class for syntax errors in files
 * (positive and negative)
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class SyntaxErrorException extends Exception {

	private static final long serialVersionUID = 6642632363050103199L;

	public SyntaxErrorException(String message) {
		super(message);
	}
}
