package unice.polytech.polystirN.brainfuck.exceptions;

/**
 * Exception class for malformed squares in images
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class BadSquareColorException extends Exception {

	private static final long serialVersionUID = 6770648937530744461L;

	public BadSquareColorException(int number) {
		super("Square #" + number + " is not monochrome");
	}
}
