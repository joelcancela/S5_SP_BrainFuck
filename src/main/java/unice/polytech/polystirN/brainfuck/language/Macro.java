package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.interpreter.InstructionFactory;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

import java.util.ArrayList;

public class Macro implements Operator {
	private ArrayList<Operator> instructions ;//for save the instruction of macros
	private InstructionFactory factory ;
	/**
	 * constructor
	 * @param ins
	 * @param factory
	 * @throws Exception
	 */
	public Macro(String ins,InstructionFactory factory) throws Exception{
		instructions = new ArrayList();
		this.factory = factory;
		madeInstruction(ins);
	}
	/**
	 * Method execute
	 */
	@Override
	public void execute(Interpreter interpreter) throws Exception {
		for(int j=0;j<instructions.size();j++){
			instructions.get(j).execute(interpreter);
		}
	}
	/**
	 * @param macrosEquivalent
	 * @return list of the instruction of a macros
	 * @throws Exception
	 */
	public ArrayList<Operator> madeInstruction(String macrosEquivalent) throws Exception{
		String instruction[] = macrosEquivalent.split("/");
		if(macrosEquivalent.equals(""))return instructions;
		int i=0;
		while(i<instruction.length){
			int j=0;
			
			if(instruction[i].split(" ").length == 2)
				if(isInt(instruction[i].split(" ")[1])){
						if(factory.getInstruction(instruction[i].split(" ")[0]) instanceof MacroWithParam){
							((MacroWithParam) factory.getInstruction(instruction[i].split(" ")[0])).setParam(Integer.parseInt(instruction[i].split(" ")[1]));
							instruction[i] = factory.getInstruction(instruction[i].split(" ")[0]).toString();
						}
						else throw new SyntaxErrorException("Incorrect word operator");		
				}
			char car = instruction[i].charAt(0);
			if (( 'A' <= car && 'Z' >= car)||( 'a' <= car && 'z' >= car)) {
		        		Operator op = factory.getInstruction(instruction[i].trim());
		        		if(op == null)	 
		        			throw new SyntaxErrorException("Incorrect word operator");
		       
		        		instructions.add(op);
				}
				else {
					while(j<instruction[i].length()){
						Operator op = null;
						String shortInstruction = instruction[i].substring(j,j+1);
						if(shortInstruction!=" "){
							op = factory.getInstruction(shortInstruction);
				    		if(op == null)	 
				    			throw new SyntaxErrorException("Incorrect word operator");
				    	instructions.add(op);
						}
						j++;
					}
		
			
				}
			i++;
	}
		return instructions;

	}
	
	/**
	 * get arrayList of the instructions of a macros
	 * @return
	 */
	public ArrayList<Operator> getInstructions() {
		return instructions;
	}
	/**
	 * get all instruction of a macros
	 * @return String
	 * @throws SyntaxErrorException 
	 */
	public String toString(){
		String S= "";
		if(instructions.size()==0)
			return " ";
		for(int i=0;i<instructions.size();i++){
			S+=instructions.get(i).toString();
		}
		return S;
	}

	@Override
	public String generateC(int indentLevel, int consecutive) {
		String cCode = "";
        for (int i = 0; i < consecutive; i++) {
		    for(int j=0; j<instructions.size(); j++) {
		    	cCode = cCode + instructions.get(j).generateC(indentLevel, consecutive);
		    }
        }
		return cCode;
	}

	/**
	 * number of instruction in a macro
	 * @return
	 */
	public int getNumberOfinstruction(){
		return instructions.size();
	}
	  /**
     * Verifies if a string is an integer
     *
     * @param chaine is a string to check
     * @return boolean if the string is an integer else false
     */
    private static boolean isInt(String chaine) {
        boolean valeur = true;
        char[] tab = chaine.toCharArray();

        for (char carac : tab) {
            if (!Character.isDigit(carac) && valeur) {
                valeur = false;
            }
        }

        return valeur;
    }


}