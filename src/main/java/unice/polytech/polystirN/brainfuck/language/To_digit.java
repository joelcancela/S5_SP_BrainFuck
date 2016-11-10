package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

public class To_digit implements Operator {
	Multi_decr multi_decr=new Multi_decr();
	/**
	 * the methode execute of To_digit
	 * @param interpreter
	 */
	@Override
	public void execute(Interpreter interpreter) throws Exception {
		multi_decr.setNbDecr(48);
		multi_decr.execute(interpreter);
	}
	/**
	 * rewrite the short instruction - 48 times
	 */
	public void rewrite(){
		multi_decr.rewrite(48);
	}

}
