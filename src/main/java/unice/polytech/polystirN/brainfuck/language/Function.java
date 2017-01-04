package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.interpreter.InstructionFactory;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

public class Function extends Procedure {

	public Function(String name, String corp, String[] param2, InstructionFactory factory) {
		super(name, corp, param2, factory);
	}
	
	@Override
	protected void execute1(Interpreter interpreter) throws Exception{
		super.execute1(interpreter);
		
		
	}

}
