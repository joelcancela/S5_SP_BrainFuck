package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.interpreter.InstructionFactory;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * Class for Functions
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Function extends Procedure {
	private int returnPointeur;
	private String returnParam;
	private boolean isFixed;

	public Function(String name, String corp, String[] param2, InstructionFactory factory, int returnPointeur) {
		super(name, corp, param2, factory);
		this.returnPointeur = returnPointeur;
		isFixed = true;
	}

	public Function(String name, String corp, String[] param2, InstructionFactory factory, String returnParam) {
		super(name, corp, param2, factory);
		this.returnParam = returnParam;
		isFixed = false;
	}

	@Override
	public void execute(Interpreter interpreter) throws Exception {
		Interpreter inter = new Interpreter(interpreter);
		interpreter.getMemory().getCells()[interpreter.getMemory().getP()] = execute1(inter);
		interpreter.setMetrics(inter.getMetrics());
	}

	@Override
	protected byte execute1(Interpreter interpreter) throws Exception {
		super.execute1(interpreter);
		if (isFixed)
			return interpreter.getMemory().getCells()[returnPointeur];
		else {
			int index;
			for (index = 0; index < param.size(); index++) {
				if (param.get(index).equals(returnParam))
					break;
			}
			return interpreter.getMemory().getCells()[Integer.valueOf(array.get(index))];
		}

	}

}
