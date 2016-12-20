package unice.polytech.polystirN.brainfuck.language;

import java.util.ArrayList;
import java.util.List;

import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.interpreter.InstructionFactory;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

public class Macro implements Operator {
	private List<Operator> instructions;//for save the instruction of macros
	private List<Operator> temp;
	private InstructionFactory factory ;
	private String inst[] ;
	private int j;
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
		for(j=0;j<instructions.size();j++){
			if(instructions.get(j) instanceof Jump){
				temp=LoopSeparate();
				new Jump(temp).execute(interpreter);
			}
			else{
				instructions.get(j).execute(interpreter);
				}
		}
	}
	/**
	 * @param macrosEquivalent
	 * @return list of the instruction of a macros
	 * @throws Exception
	 */
	public List<Operator> madeInstruction(String macrosEquivalent) throws Exception{
		String instruction[] = macrosEquivalent.split("/");
		inst = instruction;
		if(macrosEquivalent.equals(""))return instructions;
		int i=0;
		while(i<instruction.length){
			int j=0;
			if(instruction[i].trim().split(" ").length == 2)
				if(isInt(instruction[i].split(" ")[1])){
						if(factory.getInstruction(instruction[i].split(" ")[0]) instanceof MacroWithParam){
							((MacroWithParam) factory.getInstruction(instruction[i].split(" ")[0].trim())).setParam(Integer.parseInt(instruction[i].split(" ")[1].trim()));
							instruction[i] = ((MacroWithParam) factory.getInstruction(instruction[i].split(" ")[0].trim())).toString().replace(" ", "");
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
							if(isShort(shortInstruction))
								op = factory.getInstruction(shortInstruction);
							else
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
	public List<Operator> getInstructions() {
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
	/**
	 * number of instruction in a macro
	 * @return
	 */
	public int getNumberOfinstruction(){
		return instructions.size();
	}
	/**
	 * @param i index of next instruction after jump
	 * @return
	 */
	public List<Operator> LoopSeparate(){
		List<Operator> list = new ArrayList();
		int nb=1;
		for(j=j+1;j<instructions.size() && nb!=0;j++){
			if(instructions.get(j) instanceof Jump){
				nb++;
			}
			else
				if(instructions.get(j) instanceof Back){
				nb--;
				}
				
				if(instructions.get(j) instanceof MacroWithParam){
					list.addAll(((MacroWithParam) instructions.get(j)).transform());
				}else
			if(instructions.get(j) instanceof Macro){
				list.addAll(((Macro) instructions.get(j)).transform());
			}else
			list.add(instructions.get(j));
		}
		j--;
		return list;
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
    private static boolean isShort(String chaine) {
        if(chaine.equals("+")||chaine.equals("-")||chaine.equals("<")||chaine.equals(">")
        		||chaine.equals("[")||chaine.equals("]")||chaine.equals(",")||chaine.equals("."))
        	return true;
        return false;
    }
    public List<Operator> transform() {
    	String string;
    	List<Operator> array = new ArrayList();
    	string = toString().replaceAll(" ", "");
       for(int i =0;i<string.length();i++){
    	   array.add(factory.getInstruction(string.substring(i, i+1)));
       }

       return array;
    }
}