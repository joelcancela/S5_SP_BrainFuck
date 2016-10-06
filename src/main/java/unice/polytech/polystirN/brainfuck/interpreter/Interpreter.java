package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.computationalModel.Memory;
import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.language.*;

import java.util.HashMap;


/**
 * Model the virtual machine interpreting the
 * brainfuck language.
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public abstract class Interpreter {

    /**
     * memory represents the memory M
     * operatorsKeywords is an HashMap linking each operator character to their correct operator
     */
    private Memory memory;
    private HashMap<String, Operator> operatorsKeywords;
    
    

    /**
     * This method resets the memory and the pointer and
     * sets the correct operatorsKeywords to interpret as operators
     */
    public Interpreter() throws Exception {
        operatorsKeywords = new HashMap<>();
        operatorsKeywords.put("INCR", new Increment());
        operatorsKeywords.put("DECR", new Decrement());
        operatorsKeywords.put("LEFT", new Left());
        operatorsKeywords.put("RIGHT", new Right());
        operatorsKeywords.put("JUMP", new Jump());
        operatorsKeywords.put("IN", new In(null));
        operatorsKeywords.put("OUT", new Out(null));
//        operatorsKeywords.put("BACK", new Back());
        operatorsKeywords.put("+", new Increment());
        operatorsKeywords.put("-", new Decrement());
        operatorsKeywords.put("<", new Left());
        operatorsKeywords.put(">", new Right());
        operatorsKeywords.put(",", new In(null));
        operatorsKeywords.put(".", new Out(null));
//      operatorsKeywords.put("[", new Jump());
//      operatorsKeywords.put("]", new Back());

        memory = new Memory();
    }

    /**
     * Called when a file has to be read.
     * Throws an error when a character in the file isn't supported
     * by the brainfuck language.
     *
     * @return true if the file was successfully read, false if not.
     * @throws SyntaxErrorException if the keyword isn't recognized as an operator
     */
    public abstract boolean rewriteFile() throws Exception;
    
    public abstract boolean executeFile() throws Exception;
    
    public abstract Object getBuffer();
    /**
     * getter for memory attribute
     *
     * @return memory
     */
    public Memory getMemory() {
        return memory;
    }
    
    /**
     * getter for OperatorsKeywords
     *
     * @return operatorKeywords
     */
    public HashMap<String, Operator> getOperatorsKeywords() {
    	return operatorsKeywords;
    }

}
