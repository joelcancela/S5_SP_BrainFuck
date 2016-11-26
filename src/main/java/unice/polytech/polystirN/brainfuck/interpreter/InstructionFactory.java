package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.language.*;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Class used to make translations between constants and instructions
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class InstructionFactory {

 
    private  HashMap<String, Operator> mapInstruction;

    public HashMap<String, Operator> getMapInstruction() {
		return mapInstruction;
	}

	/**
     * InstructionFactory constructor
	 * @throws Exception 
     */
    public InstructionFactory() throws Exception {
    	String to ="------------------------------------------------";
    	mapInstruction = new HashMap();
    	mapInstruction.put("INCR",new Increment());
    	mapInstruction.put("DECR",new Decrement());
    	mapInstruction.put("LEFT",new Left());
    	mapInstruction.put("RIGHT", new Right());
    	mapInstruction.put("IN",new In());
    	mapInstruction.put("OUT",new Out());
    	mapInstruction.put("JUMP",new Jump());
    	mapInstruction.put("BACK",new Back());
    	mapInstruction.put("+",new Increment());
    	mapInstruction.put("-",new Decrement());
    	mapInstruction.put("<",new Left());
    	mapInstruction.put(">", new Right());
    	mapInstruction.put(",",new In());
    	mapInstruction.put(".",new Out());
    	mapInstruction.put("[",new Jump());
    	mapInstruction.put("]",new Back());
    	mapInstruction.put("#FFFFFF",new Increment());
    	mapInstruction.put("#4B0082",new Decrement());
    	mapInstruction.put("#9400D3",new Left());
    	mapInstruction.put("#0000FF", new Right());
    	mapInstruction.put("#FFFF00",new In());
    	mapInstruction.put("#00FF00",new Out());
    	mapInstruction.put("#FF7F00",new Jump());
    	mapInstruction.put("#FF0000",new Back());
    	mapInstruction.put("TO_DIGIT",new Macros(to,this));
    	mapInstruction.put("MULTI_DECR",new MacrosWithParam("-",this));
    }

    /**
     * InstructionFactory constructor with in and out
     *
     * @param inputFile  is the filename to replace the input (if null, keyboard by default)
     * @param outputFile is the filename to replace the output (if null, console by default)
     * @throws Exception 
     */
    public InstructionFactory(String inputFile, String outputFile) throws Exception {
        this();
        mapInstruction.put(",",new  In(inputFile));
    	mapInstruction.put(".",new Out(outputFile));
    	mapInstruction.put("IN",new  In(inputFile));
    	mapInstruction.put("OUT",new Out(outputFile));
    	mapInstruction.put("#FFFF00",new In(inputFile));
    	mapInstruction.put("#00FF00",new Out(outputFile));

    }

    /**
     * Translate instructions into operators
     *
     * @param instruction a string to be translated into an operator
     * @return Operator being the translation of the instruction
     */

    public Operator getInstruction(String instruction) {
      return this.mapInstruction.get(instruction);
    }

    /**
     * Translate instructions into colors
     *
     * @param instruction is the long or short syntax string to translate into a color
     * @return Integer being the RGB hexadecimal code of the color
     */
    int getColor(String instruction) {
        switch (instruction) {
            case "INCR":
            case "+":
                return 0xFFFFFF;
            case "DECR":
            case "-":
                return 0x4B0082;
            case "LEFT":
            case "<":
                return 0x9400D3;
            case "RIGHT":
            case ">":
                return 0x0000FF;
            case "IN":
            case ",":
                return 0xFFFF00;
            case "OUT":
            case ".":
                return 0x00FF00;
            case "JUMP":
            case "[":
                return 0xFF7F00;
            case "BACK":
            case "]":
                return 0xFF0000;
            default:
                return -1;
        }
    }
}

