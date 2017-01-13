package unice.polytech.polystirN.brainfuck.exceptions;

/**
 * Exception class for memory underflows
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class MemoryUnderflowException extends Exception {

	private static final long serialVersionUID = -6398541119771581849L;

	public MemoryUnderflowException(String message) {
		super(message);
	}
}
