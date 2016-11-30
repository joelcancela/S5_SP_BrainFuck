package unice.polytech.polystirN.brainfuck.interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.language.Macros;
import unice.polytech.polystirN.brainfuck.language.MacrosWithParam;


/**
 * Class used to read texts and translate them into instructions
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
class TextReader extends Reader {
    private BufferedReader buffer;
    private int c;
    private InstructionFactory factory;

    /**
     * TextReader constructor
     *
     * @param filename is the name of the file to be read
     */
    public TextReader(String filename,Interpreter inte) throws Exception {
        buffer = new BufferedReader(new FileReader(filename));
        factory = inte.getFactory();
        c = buffer.read();
        macrosRead();
       
    }
    public TextReader(String filename,InstructionFactory factory) throws Exception {
        buffer = new BufferedReader(new FileReader(filename));
        this.factory= factory;
        c = buffer.read();
        macrosRead();
    }

    /**
     * Checks if there is another character to read
     *
     * @return true if the current character is not the last, else false
     */
    @Override
    public boolean hasNext() throws Exception {
        return c!=-1;
    }

    /**
     * Reads the next character or keyword and translates it into an instruction
     *
     * @return String being the instruction
     */
    @Override
    public String next() throws Exception {
        String keyword = "";

     
       
        if(c=='#'){
        	buffer.readLine();
        	c = ' ';
        }
        if (('A' <= c && 'Z' >= c) || ('a' <= c && 'z' >= c)) {
            while ((char) c != '\r' && (char) c != '\n' && c != -1) {//c!=1 required because we read in the buffer
                keyword += ((char) c);
                c = buffer.read();
            }
        } else {
            keyword = Character.toString((char) c);
        }
        String[] macro = keyword.split(" ");
        if(macro.length==2)
        if(isInt(macro[1].trim())){
        	if(factory.getMapInstruction().get(macro[0].trim())!=null && factory.getMapInstruction().get(macro[0].trim()).getClass().equals(MacrosWithParam.class)){
        		if(factory.getInstruction(macro[0].trim()).getClass().equals(MacrosWithParam.class))
        		if(Integer.parseInt(macro[1].trim())>=0){
        			keyword = macro[0].trim();
        			((MacrosWithParam) factory.getMapInstruction().get(macro[0].trim())).setParam(Integer.parseInt(macro[1].trim()));
        		}
        	}
        }
        c = buffer.read();
        return keyword;

    }

    /**
     * 
     * Stock the equivalent of the macros to be able to use them after
     * @throws SyntaxErrorException if the character is not part of the syntax
     * @return name of file which will be executed
     */
    private void macrosRead() throws Exception {
        String word;
        char charOfM;
        String macros;
        String define = "DEFINE";
        int i;

        while (hasNext() && (c == '$' || c == ' ' || c == '\n' || c == '\r')) {
            word = "";
            charOfM = '\0';
            if ((char) c == '$') {
                macros = buffer.readLine();
                if (macros.length() <= define.length())
                    throw new SyntaxErrorException("$DEFINE <your word> <instructions>");
                if (!macros.substring(0, define.length()).equals(define))
                    throw new SyntaxErrorException("$DEFINE <your word> <instructions>");
                else {
                    if (macros.trim().split(" ").length < 2)
                        throw new SyntaxErrorException("$DEFINE <your word> <instructions>");
                    macros = macros.substring(define.length(), macros.trim().length()).trim();
                    for (i = 0; i < macros.trim().length() && charOfM != ' '; i++) {
                        word += macros.trim().charAt(i);
                        if (i < macros.trim().length() - 1)
                            charOfM = macros.trim().charAt(i + 1);

                    }
                   
                    macros = macros.trim().substring(word.trim().length(), macros.trim().length()).trim();
                    if (word.trim().length() > 2) {
                        if (word.trim().substring(word.trim().length() - 2, word.trim().length()).equals("()")){
                        	word = word.trim().substring(0, word.trim().length()-2);
                        	if(factory.getInstruction(word.trim())==null)
                        	factory.getMapInstruction().put(word, new MacrosWithParam(macros.trim(),factory));
                        	else throw new SyntaxErrorException("<your word> must be !="+ word);
                        }
                    
                    else {
                    	if(factory.getInstruction(word.trim())==null)
                    		factory.getMapInstruction().put(word.trim(), new Macros(macros.trim(),factory));
                    	else throw new SyntaxErrorException("<your word> must be !="+ word);
                    }
                    }
                    else factory.getMapInstruction().put(word.trim(), new Macros(macros.trim(),factory));
                    
                }
            }
            c = buffer.read();
           
        }   
        
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
}

