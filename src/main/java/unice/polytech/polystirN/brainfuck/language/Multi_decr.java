package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

public class Multi_decr implements Operator {
	private Operator decr=new Decrement();
	private  static int  nbDecr = 0;
	/**
	 * the methode execute
	 * @param interpreter
	 */
	@Override
	public void execute(Interpreter interpreter) throws Exception {
		for(int i=0;i<nbDecr;i++){
		decr.execute(interpreter);
		}
	}
	/**
	 * rewrite - instruction i time 
	 * @param i number of - instruction
	 */

	public void rewrite(int i) {
		for(int j=0;j<i;j++){
			System.out.print("-");
		}
	}
	/**
	 * surcharge de la methode rewrite
	 */
	public void rewrite() {
		this.rewrite(this.nbDecr);
	}
	/**
	 * set the param of the macros multi_decr
	 * @param nbD
	 */
	public void setNbDecr(int nbD){
		nbDecr = nbD;
	}
	/**
	 * return the value of the param of multi_decr
	 * @return nbDecr
	 */
	public int getNbDecr(){
		return nbDecr;
	}
	
	@Override
    public String toString(){
    	return "MULTI_DECR";
    }
	
	

	
	
	
}
