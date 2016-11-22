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

    private Operator INCR, DECR, LEFT, RIGHT, IN, OUT, JUMP, BACK;
	private int attMacro;
    private static HashMap<String,String> map = new HashMap();
    private static HashMap<String,String> mapMacrosParam = new HashMap();
    /**
     * InstructionFactory constructor
     *
     * @throws FileNotFoundException if the input file does not exist
     */
    public InstructionFactory() throws FileNotFoundException {
    	mapMacrosParam.put("MULTI_DECR", "-");
    	mapMacrosParam.put("TO_DIGIT", "------------------------------------------------");
        INCR = new Increment();
        DECR = new Decrement();
        LEFT = new Left();
        RIGHT = new Right();
        IN = new In();
        OUT = new Out();
        JUMP = new Jump();
        BACK = new Back();
    }

    /**
     * InstructionFactory constructor with in and out
     *
     * @param inputFile  is the filename to replace the input (if null, keyboard by default)
     * @param outputFile is the filename to replace the output (if null, console by default)
     * @throws FileNotFoundException if the input file does not exist
     */
    public InstructionFactory(String inputFile, String outputFile) throws FileNotFoundException {
        this();
        IN = new In(inputFile);
        OUT = new Out(outputFile);

    }

    /**
     * Translate instructions into operators
     *
     * @param instruction a string to be translated into an operator
     * @return Operator being the translation of the instruction
     */

    public Operator getInstruction(String instruction) {
        switch (instruction) {
            case "INCR":
            case "+":
            case "#FFFFFF":
                return INCR;
            case "DECR":
            case "-":
            case "#4B0082":
                return DECR;
            case "LEFT":
            case "<":
            case "#9400D3":
                return LEFT;
            case "RIGHT":
            case ">":
            case "#0000FF":
                return RIGHT;
            case "IN":
            case ",":
            case "#FFFF00":
                return IN;
            case "OUT":
            case ".":
            case "#00FF00":
                return OUT;
            case "JUMP":
            case "[":
            case "#FF7F00":
                return JUMP;
            case "BACK":
            case "]":
            case "#FF0000":
                return BACK;
            default:
            	return null;
        }

    }
    /**
     * 
     * @param macros
     * @return
     */
    public String getEquivalentInstruction(String macros){
    	return map.get(macros);
    }

	/**
     * Translate instructions into colors
     *
     * @param instruction is the long or short syntax string to translate into a color
     * @return Integer being the RGB hexadecimal code of the color
     */
    public int getColor(String instruction) {
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
    
   

    public String getShortSyntax(String instruction){
        switch (instruction) {
            case "INCR":
            case "#FFFFFF":
                return "+";
            case "DECR":
            case "#4B0082":
                return "-";
            case "LEFT":
            case "#9400D3":
                return "<";
            case "RIGHT":
            case "#0000FF":
                return ">";
            case "IN":
            case "#FFFF00":
                return ",";
            case "OUT":
            case "#00FF00":
                return ".";
            case "JUMP":
            case "#FF7F00":
                return "[";
            case "BACK":
            case "#FF0000":
                return "]";
            default:
                return instruction;
        }
    }
    
   	public static HashMap<String, String> getMapMacrosParam() {
   		return mapMacrosParam;
   	}

   	public static void setMapMacrosParam(HashMap<String, String> mapMacrosParam) {
   		InstructionFactory.mapMacrosParam = mapMacrosParam;
   	}
    public void setAttMacro(int arg){
    	this.attMacro=arg;
    }
    public int getAttMacro(){
    	return attMacro;
    }
	public static void put(String s,String string) {
		map.put(s, string);
	}
}

