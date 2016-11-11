package unice.polytech.polystirN.brainfuck.interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;

/**
 * Class used to read texts and translate them into instructions
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
class TextReader extends Reader {
	private BufferedReader buffer; //buffer used to read pictures
	private int c;
	private InstructionFactory factory=new InstructionFactory();
	
    /**
     * TextReader constructor
     *
     * @param filename is the name of the file to be read
     */
    public TextReader(String filename,Interpreter inte) throws Exception {
        buffer = new BufferedReader(new FileReader(filename));
    }
    public TextReader(String filename) throws Exception {
        buffer = new BufferedReader(new FileReader(filename));
    }

    /**
     * Getter for the buffer
     *
     * @return buffer being a BufferedReader
     */
    @Override
    public Object getBuffer() {
        return buffer;
    }

    /**
     * Checks if there is another character to read
     *
     * @return true if the current character is not the last, else false
     */
    @Override
    public boolean hasNext() throws Exception {
    	boolean returnValue = false;
    	
        buffer.mark(1);
        if(buffer.read() != -1)
        	returnValue = true;
        buffer.reset();
        
        return returnValue;
    }

    /**
     * Reads the next character or keyword and translates it into an instruction
     *
     * @return String being the instruction
     */
    @Override
    public String next() throws Exception {
        int c;
        String keyword ="";
        String macros="MULTI_DECR";
        

        c = buffer.read();

        if (( 'A' <= c && 'Z' >= c ) || ('a' <= c && 'z' >= c)) {
            while ((char) c != '\r' && (char) c != '\n' && c != -1) {//c!=1 required because we read in the buffer
                keyword += ((char) c);
                c = buffer.read();
            }
            if(keyword.length()>9){
            	if(keyword.substring(0,macros.length()+1).equals(macros+" ")){
            		factory.setAttMacro(Integer.parseInt(keyword.substring(macros.length(),keyword.length()).trim()));
            		return macros;
            	}
            }
            
        }
        else {
        	
            keyword = this.macrosRead(c);
        }
        
        return keyword;
    }
    public String macrosRead(int c) throws Exception{
    	String define="DEFINE";
    	String tab[];
    	if(c!='$')
    		return Character.toString((char) c);
    	else {
    		String macros = buffer.readLine();
    		if(!macros.substring(0,define.length()).equals(define))
    			throw new SyntaxErrorException("$DEFINE <your word> <instruction>");
    		else{
    			macros=macros.substring(define.length(),macros.length()).trim();
    			tab=macros.split(" ");
    			if(tab.length==3)
    				if(tab[1].equals("MULTI_DECR")){
    					factory.put(tab[0],Integer.parseInt(tab[2]));
    				return "";
    				}
    		if(tab.length==2){
    			if(tab[1].equals("MULTI_DECR"))
    				throw new SyntaxErrorException("$DEFINE <your word> <MULTI_DECR param>");
    			factory.putI(tab[0].trim(), factory.getInstruction(tab[1].trim()));
    			return "";
    		}
    		else throw new SyntaxErrorException("$DEFINE <your word> <instruction>");
    		}
    	}
		
    }

}

