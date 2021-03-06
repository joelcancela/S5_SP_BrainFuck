package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.exceptions.BadLoopException;
import unice.polytech.polystirN.brainfuck.exceptions.PointerPositionOutOfBoundsException;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * Class used to specify the Back operator behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */

public class Back implements Operator {

	/**
	 * This method checks the content of the current memory cell.
	 *
	 * @param interpreter memory (M and P) of the current program and all of the following operations.
	 * @throws PointerPositionOutOfBoundsException if the pointer value is inferior to 0.
	 */
	@Override
	public void execute(Interpreter interpreter) throws Exception {
		int dp = (interpreter.getMemory().getCells()[interpreter.getMemory().getP()] & 0x00FF);

		//Anomaly case :
		if (dp < 0)
			throw new PointerPositionOutOfBoundsException("current memory have illegal value (inferior to 0)");
		if (!interpreter.isInALoop())
			throw new BadLoopException("Loop without start : Missing JUMP operator");

	}

	@Override
	public String toString() {
		return "]";
	}

	public String generateC(int indentLevel, int consecutive) {
		if (consecutive <= 0)
			return "";
		String indentation = "";
		for (int i = 0; i < indentLevel + 1; i++)
			indentation = indentation + "\t";
		return indentation + "}\n" + generateC(indentLevel - 1, consecutive - 1);
	}

}
