package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.computationalModel.Memory;
import unice.polytech.polystirN.brainfuck.exceptions.IncorrectFileTypeException;
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
public class Interpreter {

    /**
     * memory represents the memory M
     * operatorsKeywords is an HashMap linking each operator character to their correct operator
     */
    private Memory memory;
    private HashMap<String, Operator> operatorsKeywords;
    private Reader reader;
    private String inputFile;
    private String outputFile;


    /**
     * This method resets the memory and the pointer and
     * sets the correct operatorsKeywords to interpret as operators
     */
    public Interpreter(String filename, String inputFile, String outputFile) throws Exception {
        this(filename);
        operatorsKeywords.put("IN", new In(inputFile));
        operatorsKeywords.put("OUT", new Out(outputFile));
        operatorsKeywords.put(",", new In(inputFile));
        operatorsKeywords.put(".", new Out(outputFile));
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public Interpreter(String filename) throws Exception {
        operatorsKeywords = new HashMap<>();
        operatorsKeywords.put("INCR", new Increment());
        operatorsKeywords.put("DECR", new Decrement());
        operatorsKeywords.put("LEFT", new Left());
        operatorsKeywords.put("RIGHT", new Right());
        operatorsKeywords.put("JUMP", new Jump());
        operatorsKeywords.put("BACK", new Back());
        operatorsKeywords.put("+", new Increment());
        operatorsKeywords.put("-", new Decrement());
        operatorsKeywords.put("<", new Left());
        operatorsKeywords.put(">", new Right());
        operatorsKeywords.put("[", new Jump());
        operatorsKeywords.put("]", new Back());
        operatorsKeywords.put("IN", new In(null));
        operatorsKeywords.put("OUT", new Out(null));
        operatorsKeywords.put(",", new In(null));
        operatorsKeywords.put(".", new Out(null));
        memory = new Memory();
        
        if(filename.matches("(.*).bf")){
        	reader = new TextReader(filename);
        }else if(filename.matches("(.*).bmp")){
        	reader = new ImageReader(filename);
        }else{
        	throw new IncorrectFileTypeException("Invalid type of file (not .bf and not .bmp");
        }
        
    }

    /**
     * Called when a file has to be read.
     * Throws an error when a character in the file isn't supported
     * by the brainfuck language.
     *
     * @return true if the file was successfully read, false if not.
     * @throws SyntaxErrorException if the keyword isn't recognized as an operator
     */
    public boolean rewriteFile() throws Exception{
        String keyword;

        while (reader.hasNext()) {
        	keyword =  reader.next();
            if (getOperatorsKeywords().get(keyword.trim()) == null) {
                for (int i = 0; i < keyword.trim().length(); i++) {
                    if (getOperatorsKeywords().get(keyword.trim().substring(i, i + 1)) != null) {
                        System.out.print(keyword.trim().substring(i, i + 1));
                    } else {
                        System.out.println();
                        throw new SyntaxErrorException("Invalid keyword operator");
                    }
                }
            } else {
                if (keyword.trim().equals("INCR")) {
                    System.out.print("+");
                } else if (keyword.trim().equals("DECR")) {
                    System.out.print("-");
                } else if (keyword.trim().equals("RIGHT")) {
                    System.out.print(">");
                } else if (keyword.trim().equals("LEFT")) {
                    System.out.print("<");
                } else if (keyword.trim().equals("JUMP")) {
                    System.out.print("[");
                } else if (keyword.trim().equals("BACK")) {
                    System.out.print("]");
                } else {
                    System.out.println();
                    throw new SyntaxErrorException("Invalid keyword operator");
                }
            }
        }
        return true;
    }

    public boolean executeFile() throws Exception{
    	  String keyword = "";
          int c;
          
          while (reader.hasNext()) {
        	  keyword = reader.next();
              if(!(keyword.equals("\n") || keyword.equals("\r") || keyword.equals("\t") || keyword.equals(" "))){
	        	  Operator op = getOperatorsKeywords().get(keyword);
	              if (getOperatorsKeywords().get(keyword) == null) {
	                  throw new SyntaxErrorException("Incorrect word operator");
	              }
	              op.execute(this);
	              keyword = "";
        	  }
          }
          
          return true;
    }

    public Reader getReader(){
    	return reader;
    };

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
    
    public void check() {
        System.out.println("I checked the file !");
    }

}
