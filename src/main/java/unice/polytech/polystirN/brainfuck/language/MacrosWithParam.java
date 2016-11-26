package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.interpreter.InstructionFactory;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

public class MacrosWithParam extends Macros{
	private int param;
	public MacrosWithParam(String ins,InstructionFactory factory) throws Exception {
		super(ins,factory);
		
	}
	@Override
	public void execute(Interpreter interpreter) throws Exception {
		for(int i=0;i<param;i++){
			for(int j=0;j<super.getInstructions().size();j++){
				super.getInstructions().get(j).execute(interpreter);
			}
		}
		
	}
	public void setParam(int param) {
		this.param = param;
	}
}
