package unice.polytech.polystirN.brainfuck.language;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import unice.polytech.polystirN.brainfuck.exceptions.PointerPositionOutOfBoundsException;
import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * Class used to specify the Jump operator behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */

public class Jump implements Operator {
	private int nbOuvert;
	private List<String> file;
	
    /**
     * This method checks if the content of the current memory cell. 
     * This operator does nothing if the value is different of 0.
     * Else, this operator will read the next instruction until he meet the BACK operator.
     *
     * @param memory is the memory of the current program (M and P)
     * @return true if the loop is over
     * @throws PointerPositionOutOfBoundsException if the pointer value is inferior to 0
     */
	@Override
	public boolean doOperation(Interpreter interpreter) throws PointerPositionOutOfBoundsException, Exception {
		int dp = interpreter.getMemory().getCells()[interpreter.getMemory().getP()];
		int i = 0;
		boolean premierParcours = true;
		String instruction = "";
		
		nbOuvert = 1;
		file = new ArrayList<String>();
		
		//Anomaly case :
        if (dp < 0)
            throw new PointerPositionOutOfBoundsException("current memory have illegal value (inferior to 0)");
		
        
       //Nominal case :
		switch(interpreter.getClass().getSimpleName()){
			case "InterpreterText":
				BufferedReader buffer = (BufferedReader) interpreter.getBuffer();
		        while(interpreter.getMemory().getCells()[interpreter.getMemory().getP()] != 0){
		        	while(nbOuvert != 0){
		        		if(premierParcours == true){
		        			instruction = buffer.readLine();
			        		file.add(instruction);
			        		
			        		iteration(instruction, interpreter);
		        		}else{
		        			instruction = file.get(i);
	
			        		iteration(instruction, interpreter);
			        		i++;
		        		}
		        	}
		        	premierParcours = false;
		        	nbOuvert = 1;
		        	i = 0;
		        }
		        iteration(file.get(file.size()), interpreter);
				break;
				
			case "InterpreterImage":
				break;
		}

        return true;
	}
	
	/**
	 * 
	 * @param instruction
	 * @param interpreter
	 * @return
	 * @throws Exception
	 */
	public boolean iteration(String instruction, Interpreter interpreter) throws Exception{
		if(instruction.equals("JUMP") || instruction.equals("["))
			nbOuvert++;
		
		if(instruction.equals("BACK") || instruction.equals("]"))
			nbOuvert--;
		
		if (interpreter.getOperatorsKeywords().get(instruction.trim()) == null) {
            throw new SyntaxErrorException("Invalid keyword operator");
        }
		interpreter.getOperatorsKeywords().get(instruction.trim()).doOperation(interpreter);
		
		return true;
	}
	
}
