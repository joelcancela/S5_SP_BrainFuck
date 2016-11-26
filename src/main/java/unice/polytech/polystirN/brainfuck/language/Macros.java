package unice.polytech.polystirN.brainfuck.language;

import java.util.ArrayList;

import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.interpreter.InstructionFactory;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

public class Macros implements Operator {
	private ArrayList<Operator> instructions ;//for save the instruction of macros
	private InstructionFactory factory ;
	
	public Macros(String ins,InstructionFactory factory) throws Exception{
		instructions = new ArrayList();
		this.factory = factory;
		madeInstruction(ins);
	}
	

	@Override
	public void execute(Interpreter interpreter) throws Exception {
		for(int j=0;j<instructions.size();j++){
			instructions.get(j).execute(interpreter);
		}
	}



	public ArrayList<Operator> madeInstruction(String macrosEquivalent) throws Exception{
		String instruction[] = macrosEquivalent.split("/");
		if(macrosEquivalent.equals(""))return instructions;
		int i=0;
		while(i<instruction.length){
			int j=0;
			char car = instruction[i].charAt(0);
		    if (( 'A' <= car && 'Z' >= car)||( 'a' <= car && 'z' >= car)) {
		        Operator op = factory.getInstruction(instruction[i].trim());
		        if(op == null)	 
		    		throw new SyntaxErrorException("Incorrect word operator");
		        i++;
		    	instructions.add(op);
		    }
		    else {
		    	while(j<instruction[i].length()){
		    		Operator op = null;
		    		String shortInstruction = instruction[i].substring(j,j+1);
		    		if(shortInstruction!=" "){
		    				op = factory.getInstruction(shortInstruction);
		    				System.out.println(shortInstruction);
				    		if(op == null)	 
				    			throw new SyntaxErrorException("Incorrect word operator");
				    	instructions.add(op);
		    		}
		    	j++;
		    }
			i++;
			
		}
	}
		return instructions;

	}
	
	
	public ArrayList<Operator> getInstructions() {
		return instructions;
	}

	
}