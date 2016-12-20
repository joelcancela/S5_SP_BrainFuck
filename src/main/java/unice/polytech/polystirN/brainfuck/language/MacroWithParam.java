package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.interpreter.InstructionFactory;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

public class MacroWithParam extends Macro{
	private Integer param=null;
	/**
	 * constructor
	 * @param ins
	 * @param factory
	 * @throws Exception
	 */
	public MacroWithParam(String ins,InstructionFactory factory) throws Exception {
		super(ins,factory);
	}
	/**
	 * methode execute
	 */
	@Override
	public void execute(Interpreter interpreter) throws Exception {
		if(param == null)
			throw new SyntaxErrorException("Incorrect word operator");
		for(int i=0;i<param;i++){
			super.execute(interpreter);
		}
		
	}
	/**
	 * set parameter of macros
	 * change value of param
	 * @param param
	 */
	public void setParam(int param) {
		this.param = param;
	}
	/**
	 * get a String of all instruction of a macro
	 * @return Sring
	 * @throws SyntaxErrorException 
	 */
	public String toString(){
		String S= "";
		if(param == null)
			throw new RuntimeException();
		
		if(getInstructions().size()==0)
			return " ";
		
		for(int i=0;i<param;i++){
			for(int j=0;j<getInstructions().size();j++){
				S+=getInstructions().get(j);
			}
		}
		
		return S;
	}
	/**
	 * get parametre of a macro
	 * @return param 
	 */
	public int getParam() {
		return param;
	}
	/**
	 * get number of instruction in a macro
	 */
	@Override
	public int getNumberOfinstruction(){
		return super.getNumberOfinstruction() * param;
	}
}
