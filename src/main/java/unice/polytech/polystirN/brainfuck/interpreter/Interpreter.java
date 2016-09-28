package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.computationalModel.Memory;
import unice.polytech.polystirN.brainfuck.exceptions.IncorrectFileTypeException;
import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.language.*;

import java.io.BufferedReader;
import java.io.FileReader;
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
     * buffer is the BufferedReader used to read files
     */
    private Memory memory;
    private HashMap<String, Operator> operatorsKeywords;
    private BufferedReader buffer;


    /**
     * This method specifies the file read by the interpreter,
     * it also resets the memory and the pointer and
     * sets the correct operatorsKeywords to interpret as operators
     *
     * @param filename is the name of the file to read
     * @throws Exception if the file doesn't have the correct extension (.bf)
     */
    public Interpreter(String filename) throws Exception {
        if (!filename.matches("(.*).bf")) {
            throw new IncorrectFileTypeException(filename + " must have .bf extension");
        }
        operatorsKeywords = new HashMap<>();
        operatorsKeywords.put("INCR", new Increment());
        operatorsKeywords.put("DECR", new Decrement());
        operatorsKeywords.put("LEFT", new Left());
        operatorsKeywords.put("RIGHT", new Right());
        memory = new Memory();
        buffer = new BufferedReader(new FileReader(filename));
    }

    /**
     * Called when a file has to be read.
     * Throws an error when a character in the file isn't supported
     * by the brainfuck language.
     *
     * @return true if the file was successfully read, false if not.
     * @throws SyntaxErrorException if the keyword isn't recognized as an operator
     */
    public boolean readfile() throws Exception {
        String keyword;
        while ((keyword = buffer.readLine()) != null) {
            if (operatorsKeywords.get(keyword.trim()) == null) {
                throw new SyntaxErrorException("Invalid keyword operator");
            }
            operatorsKeywords.get(keyword.trim()).doOperation(memory);
        }
        return false;
    }

    /**
     * getter for memory attribute
     *
     * @return memory
     */
    public Memory getMemory() {
        return memory;
    }

}
