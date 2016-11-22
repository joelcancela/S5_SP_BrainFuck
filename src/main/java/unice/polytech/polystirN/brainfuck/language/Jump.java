package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.exceptions.BadLoopException;
import unice.polytech.polystirN.brainfuck.exceptions.PointerPositionOutOfBoundsException;
import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to specify the Jump operator behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */

public class Jump implements Operator {
    private int nbOuvert;
    private List<String> queue;
    private String trace = "";

    /**
     * This method checks the content of the current memory cell.
     * This operator does nothing if the value is different of 0.
     * Else, this operator will read the next instruction until he meet the BACK operator. All operators encountered are executed if the memory cell is different to 0.
     *
     * @param interpreter memory (M and P) of the current program and all of the following operations.
     * @throws PointerPositionOutOfBoundsException if the pointer value is inferior to 0.
     */
    @Override
    public void execute(Interpreter interpreter) throws Exception {
        int dp = interpreter.getMemory().getCells()[interpreter.getMemory().getP()] & 0xFF;
        int i = 0;
        String instruction;
        int nbParcours = 0;

        nbOuvert = 1;
        queue = new ArrayList<>();
        trace = "";

        //Anomaly case :
        if (dp < 0)
            throw new PointerPositionOutOfBoundsException("current memory have illegal value (inferior to 0)");

       if(interpreter.isTrace()){
    	   	trace+="\npointer after : "+interpreter.getMemory().getP()+"\n";
    	   	trace+=interpreter.getMemory().toString();
    	   	trace+="----------------------------\n";
        }
        
    	
        //Special case : (value of initial memory case is already equals to 0)
        if (interpreter.getMemory().getCells()[interpreter.getMemory().getP()] == 0) {
            interpreter.getMetrics().incrementDataRead();
            while (nbOuvert != 0) {
                instruction = getNextInstruction(interpreter);
                switch (instruction) {
	                case "JUMP":
	                case "[":
	                case "#FF7F00" :
	                    nbOuvert++;
	                    break;
	                case "BACK":
	                case "]":
	                case "#FF0000":
	                    nbOuvert--;
	                    break;
                }
                if (!instruction.equals("NOI")) {
                    interpreter.getMetrics().incrementProgSize();
                }
            }
            interpreter.getMetrics().incrementExecMove();
        } else {
            //Nominal case :
            interpreter.getMetrics().incrementDataRead();
            interpreter.startALoop();
            while (interpreter.isInALoop() && interpreter.getMemory().getCells()[interpreter.getMemory().getP()] != 0) { //Tant que dp ne vaut pas 0 (sort immédiatement si ma case vaut 0)
            	interpreter.getMetrics().incrementDataRead();
            	if (nbParcours>1){
	            	interpreter.getMetrics().incrementExecMove();
            	}
            	while (nbOuvert != 0) { //Tant qu'il reste des boucles à parcourir
                    if (nbParcours==0) { //Renseigne la liste d'instructions (queue) composant la boucle la plus englobante
                        instruction = getNextInstruction(interpreter);
                        if (!instruction.equals("NOI")) {
                            queue.add(instruction);
                            iteration(instruction, interpreter, false, 0);
                            interpreter.getMetrics().incrementProgSize();
                        }
                    } else { //Exécute les instructions de la boucle englobante
                        instruction = queue.get(i);
                        i = iteration(instruction, interpreter, true, i);
                        i++;
                    }
                }
                //Iteration suivante dans la boucle brainfuck :
            	nbParcours++;
                nbOuvert = 1;
                i = 0;
            }
            interpreter.endALoop();
        }

    }

    /**
     * Analyze an instruction of loop.
     *
     * @param instruction the operation to analyze.
     * @param interpreter memory (M and P) of the current program and all of the following operations.
     * @param execute     true for execute the operation, false otherwise.
     * @param index       position in the operations list's (queue).
     * @return the current index in the operations list's.
     */
    private int iteration(String instruction, Interpreter interpreter, boolean execute, int index) throws Exception {
        //Incrémente/décrémente le nombre de boucles à parcourir
        switch (instruction) {
            case "JUMP":
            case "[":
            case "#FF7F00" :
                nbOuvert++;
                break;
            case "BACK":
            case "]":
            case "#FF0000":
                nbOuvert--;
                break;
        }
        //------------------------------------------------------

        //Si on est pas dans la première itération du while et qu'on exécute :
        if (execute) {
            if (queue.get(index).equals("JUMP") || queue.get(index).equals("#FF7F00") || queue.get(index).equals("[")) {
            	interpreter.getMetrics().incrementExecMove();
            	if(interpreter.isTrace()){
        	    	trace+=interpreter.getMetrics().getExecMove() + " : "+instruction+"\n";
        	    	trace+="pointer : "+interpreter.getMemory().getP();
        	    	trace+="\npointer after : "+interpreter.getMemory().getP()+"\n";
                	trace+=interpreter.getMemory().toString();
                	trace+="----------------------------\n";
            	}
            	index = internalLoop(index, interpreter); //Crée une boucle interne
            } else {
                executeInstruction(instruction, interpreter); //Exécute l'opération
            }
            return index;
        }

        return index;
    }

    /**
     * Make an other loop in a loop (support object).
     *
     * @param index       position in the operations list's (queue)
     * @param interpreter memory (M and P) of the current program and all of the following operations.
     * @return the current index in the operations list's.
     */
    private int internalLoop(int index, Interpreter interpreter) throws Exception {
        int originalIndex = index; //Index à l'entrée de la boucle
        int internalIndex; //Index à la sortie, utile si et seulement si on a une autre boucle dans cette boucle et ainsi desuite

        //DEBUT BOUBLE INTERNE
        interpreter.getMetrics().incrementDataRead();
        while (interpreter.isInALoop() && interpreter.getMemory().getCells()[interpreter.getMemory().getP()] != 0) { //Tant que cette boucle n'est pas terminée
            index = originalIndex;
            interpreter.getMetrics().incrementDataRead();
            while ((!queue.get(index).equals("BACK")) && (!queue.get(index).equals("]") && (!queue.get(index).equals("#FF0000") ))) {
                index++;
                if (queue.get(index).equals("JUMP") || queue.get(index).equals("[") || queue.get(index).equals("#FF7F00")) { //Si on a une boucle dans cette boucle
                    nbOuvert++;
                    internalIndex = internalLoop(index, interpreter); //Lance récursivement des boucles internes et récupère l'index dans la liste des instructions
                    index = internalIndex + 1;
                }
                executeInstruction(queue.get(index), interpreter); //Les "JUMP" dans un JUMP ne sont donc jamais exécutés pour ne pas perdre les instructions stockées dans "queue"
            }
            //FIN BOUCLE INTERNE

        }
        while ((!(queue.get(index).equals("BACK"))) && (!(queue.get(index).equals("]"))) && (!(queue.get(index).equals("#FF0000")))) {//Permet de ne pas repasser par les instructions de la boucle si la case mémoire vaut 0
            index++;
        }
        nbOuvert--;
        return index;
    }

    /**
     * Execute an instruction of loop.
     *
     * @param instruction the operation to execute.
     * @param interpreter memory (M and P) of the current program and all of the following operations.
     * @throws SyntaxErrorException if the keyword is invalid
     */
    private boolean executeInstruction (String instruction, Interpreter interpreter) throws Exception {
    	interpreter.getMetrics().incrementExecMove();
    	
    	if(interpreter.isTrace()){
	    	trace+=interpreter.getMetrics().getExecMove() + " : "+instruction+"\n";
	    	trace+="pointer : "+interpreter.getMemory().getP();
    	}
    	if (interpreter.getFactory().getInstruction(instruction) == null) {
            throw new SyntaxErrorException("Invalid keyword operator");
        }
        interpreter.getFactory().getInstruction(instruction.trim()).execute(interpreter);
        if(interpreter.isTrace()){
        	trace+="\npointer after : "+interpreter.getMemory().getP()+"\n";
        	trace+=interpreter.getMemory().toString();
        	trace+="----------------------------\n";
        }
        return true;
    }

    /**
     * @param interpreter is the current interpreter instance
     * @return String being the next instruction
     * @throws SyntaxErrorException if the keyword is invalid
     * @throws BadLoopException is the loop has no closing
     */
    private String getNextInstruction(Interpreter interpreter) throws Exception {
        String instruction;

        if(!interpreter.getReader().hasNext())
            throw new BadLoopException("Loop without end : Missing BACK operator");

        instruction = interpreter.getReader().next();

        if (!(instruction.equals("\n") || instruction.equals("\r") || instruction.equals("\t") || instruction.equals(" ") || instruction.equals("#"))) {
            Operator op = interpreter.getFactory().getInstruction(instruction);
            if (op == null) {
                throw new SyntaxErrorException("Incorrect word operator");
            }
            return instruction;
        }else if (instruction.equals("#")){
            instruction = interpreter.getReader().next();
            while(interpreter.getReader().hasNext() && (!(instruction.equals("\n")) || (instruction.equals("\r")))){
                instruction = interpreter.getReader().next();
            }
        }

        return "NOI";
    }
    @Override
    public String toString(){
        return "[";
    }
    
    public String getTrace(){
    	return trace;
    }

}
