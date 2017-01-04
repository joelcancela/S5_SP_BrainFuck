package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.codeGenerator.CGenerator;
import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.language.Macro;
import unice.polytech.polystirN.brainfuck.language.MacroWithParam;
import unice.polytech.polystirN.brainfuck.language.Procedure;

import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Class used to read texts and translate them into instructions
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class TextReader extends Reader {
    private BufferedReader buffer;
    private int c;
    private InstructionFactory factory;
    private CGenerator cGenerator;

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

    public TextReader(String filename, CGenerator cGenerator) throws Exception {
        buffer = new BufferedReader(new FileReader(filename));
        this.factory = cGenerator.getFactory();
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
        String VOID = "void";
     

       
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
        	if(factory.getMapInstruction().get(macro[0].trim())!=null && factory.getMapInstruction().get(macro[0].trim()).getClass().equals(MacroWithParam.class)){
        		if(factory.getInstruction(macro[0].trim()).getClass().equals(MacroWithParam.class))
        		if(Integer.parseInt(macro[1].trim())>=0){
        			keyword = macro[0].trim();
        			((MacroWithParam) factory.getMapInstruction().get(keyword)).setParam(Integer.parseInt(macro[1].trim()));
        		}
        	}
        }
        if(keyword.contains(VOID) && keyword.trim().length()>VOID.length()){
        	keyword = procedureRead(keyword);
        }
        if(keyword.split("\\(").length ==2 && keyword.split("\\)").length ==2 && keyword.trim().split(";").length==1){
        	if(factory.getInstruction(keyword.split("\\(")[0].trim())!=null && factory.getInstruction(keyword.split("\\(")[0].trim()) instanceof Procedure){
        		String[] paramettre = keyword.split("\\(")[1].split("\\)")[0].split(",");
            	keyword = keyword.split("\\(")[0].trim();
        		((Procedure)factory.getInstruction(keyword.split("\\(")[0].trim())).setParam(paramettre);
        	}
        }
        c = buffer.read();
        return keyword;

    }
    /**
     * 
     * @param procedure
     * @throws Exception
     */
    private String procedureRead(String procedure) throws Exception{
    	String Void = "void";
    	String nomPro,corp ="",param[];
    	procedure = procedure.trim().substring(Void.length(), procedure.trim().length()).trim();
    	nomPro = procedure.split("\\(")[0].trim();
    	if(nomPro.split(" ").length > 1){
    		throw new SyntaxErrorException("the name of the function should not contain spaces");
    	}
    	if(procedure.split("\\(").length > 2 || procedure.split("\\)").length >2 ){
    		throw new SyntaxErrorException("the function should not contain too many ( or )");
    	}
    	if(!procedure.split("\\(")[1].contains(")")){
    		throw new SyntaxErrorException("the function should contain  ( param )");
    	}
    	param = procedure.split("\\(")[1].split("\\)")[0].split(",");
    	for(int i=0;i<param.length;i++){
    		param[i] = param[i].trim();
    	}
    	procedure = procedure.split("\\)")[1];
    	if(procedure.contains("{")){
    		if(procedure.contains("}")){
    			corp = procedure.split("\\{")[0];
    		}
    		else {
    			if(procedure.split("\\{").length == 2)
    				corp = procedure.split("\\{")[1];
    			while(c!=-1 && c!='}'){
    				c = buffer.read();
    				if(c=='}'){
    					c=' ';
    					break;
    				}else{
    					corp+=Character.toString((char) c);
    				}
    				
    			}
    		}
    	}
    	else{
    		c = buffer.read();
    		if(c=='{'){
    			while(c!=-1 && c!='}'){
				
    				if(c=='}'){
    					c=' ';
    					break;
    				}else{
    					corp+=(char)c;
    				}
    				c = buffer.read();
    			}
    			
    		}
    		else throw new SyntaxErrorException("the function should contain { and }");
    	}
    	factory.getMapInstruction().put(nomPro.trim(), new Procedure(nomPro.trim(),corp,param, factory));
    	return "?"+nomPro;
    }
    /**
     * 
     * Stock the equivalent of the macros to be able to use them after
     * @throws SyntaxErrorException if the character is not part of the syntax
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
                    macros = macros.trim().substring(define.length(), macros.trim().length()).trim();
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
                        	factory.getMapInstruction().put(word.trim(), new MacroWithParam(macros.trim(),factory));
                        	else throw new SyntaxErrorException("<your word> must be !="+ word);
                        	
                        }
                    
                    else {
                    
                    	if(factory.getInstruction(word.trim())==null)
                    		factory.getMapInstruction().put(word.trim(), new Macro(macros.trim(),factory));
                    	else throw new SyntaxErrorException("<your word> must be !="+ word);
                    	
                    }
                    }
                    else factory.getMapInstruction().put(word.trim(), new Macro(macros.trim(),factory));
                   
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

