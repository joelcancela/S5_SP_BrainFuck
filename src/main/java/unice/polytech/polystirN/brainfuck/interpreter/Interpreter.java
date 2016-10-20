package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.computationalModel.Memory;
import unice.polytech.polystirN.brainfuck.exceptions.BadLoopException;
import unice.polytech.polystirN.brainfuck.exceptions.IncorrectFileTypeException;
import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.language.*;

import java.util.HashMap;


/**
 * Models the virtual machine interpreting the
 * brainfuck language.
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Interpreter {

    private Memory memory;
    private HashMap<String, Operator> operatorsKeywords; //HashMap linking each operator character to their correct operator
    private Reader reader;
    private boolean inALoop;


    /**
     * Constructor for Interpreter
     *
     * @param filename is the name of the file to be interpreted
     * @throws IncorrectFileTypeException if the filename has an invalid extension
     */
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
        operatorsKeywords.put("IN", new In());
        operatorsKeywords.put("OUT", new Out(null));
        operatorsKeywords.put(",", new In());
        operatorsKeywords.put(".", new Out(null));
        memory = new Memory();

        if (filename.matches("(.*).bf")) {
            reader = new TextReader(filename);
        } else if (filename.matches("(.*).bmp")) {
            reader = new ImageReader(filename);
        } else {
            throw new IncorrectFileTypeException("Invalid type of file (not .bf and not .bmp");
        }
        
        inALoop = false;
    }

    /**
     * Sets the correct operatorsKeywords to interpret as operators
     *
     * @param filename   is the name of the file to be interpreted
     * @param inputFile  is the name of the file to replace the input (if null, keyboard by default)
     * @param outputFile is the name of the file to replace the output (if null, console by default)
     */
    public Interpreter(String filename, String inputFile, String outputFile) throws Exception {
        this(filename);
        operatorsKeywords.put("IN", new In(inputFile));
        operatorsKeywords.put("OUT", new Out(outputFile));
        operatorsKeywords.put(",", new In(inputFile));
        operatorsKeywords.put(".", new Out(outputFile));
    }

    /**
     * Interprets a file
     *
     * @return true if the interpretation has gone well
     * @throws SyntaxErrorException if an invalid symbol or keyword is encountered
     */
    public boolean interpretFile() throws Exception {
        String keyword;
        while (reader.hasNext()) {
            keyword = reader.next();
            if (!(keyword.equals("\n") || keyword.equals("\r") || keyword.equals("\t") || keyword.equals(" "))) {
                Operator op = getOperatorsKeywords().get(keyword);
                if (op == null) {
                    throw new SyntaxErrorException("Incorrect word operator");
                }
                op.execute(this);
            }
        }
        System.out.println("C"+memory.getP()+": "+memory.getCells()[memory.getP()]);
        return true;
    }

    /**
     * Called when a file has to be read.
     * Throws an error when a character in the file isn't supported
     * by the brainfuck language.
     *
     * @return true if the file was successfully read, false if not.
     * @throws SyntaxErrorException if the keyword isn't recognized as an operator
     */
    public boolean rewriteFile() throws Exception {
        String keyword;

        while (reader.hasNext()) {
            keyword = reader.next();
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
                } else if (keyword.trim().equals("OUT")) {
                    System.out.print(".");
                } else if (keyword.trim().equals("IN")) {
                    System.out.print(",");
                } else {
                    System.out.print(keyword.trim());
                }
            }
        }
        return true;
    }


    /**
     *
     * Checks if a program is well formed
     */
    public void check() throws Exception {
        String keyword;
        int nbOuvert = 0;
        
        while (reader.hasNext()) {
            keyword = reader.next();
            if (!(keyword.equals("\n") || keyword.equals("\r") || keyword.equals("\t") || keyword.equals(" "))) {
                Operator op = getOperatorsKeywords().get(keyword);
                if (op == null) {
                    throw new SyntaxErrorException("Incorrect word operator");
                }
                if(keyword.equals("JUMP") || keyword.equals("["))
                	nbOuvert++;
                if(keyword.equals("BACK") || keyword.equals("]"))
                	nbOuvert--;
                
                if(nbOuvert<0)
                	throw new BadLoopException("Loop without start : Missing JUMP operator");
            }
        }
        if(nbOuvert>0)
        	throw new BadLoopException("Loop without end : Missing BACK operator");
        if(nbOuvert<0)
        	throw new BadLoopException("Loop without start : Missing JUMP operator");
        System.out.println("The program is well formed");
    }

    /**
     * Getter for the reader attribute
     *
     * @return reader being the input reader of the interpreter
     */
    public Reader getReader() {
        return reader;
    }

    /**
     * Getter for the memory attribute
     *
     * @return memory
     */
    public Memory getMemory() {
        return memory;
    }

    /**
     * Getter for the OperatorsKeywords HashMap
     *
     * @return operatorKeywords
     */
    public HashMap<String, Operator> getOperatorsKeywords() {
        return operatorsKeywords;
    }
    
    /**
     * Return true if the execution thread is in a loop
     * 
     * @return inALoop
     */
    public boolean isInALoop(){
    	return inALoop;
    }
    
    /**
     * Change the status of inALoop to true
     */
    public void startALoop(){
    	inALoop = true;
    }
    
    /**
     * Change the status of inALoop to false
     */
    public void endALoop(){
    	inALoop = false;
    }
}
