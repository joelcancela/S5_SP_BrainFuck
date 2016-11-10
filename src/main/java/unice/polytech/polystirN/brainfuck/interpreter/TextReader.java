package unice.polytech.polystirN.brainfuck.interpreter;

import java.io.BufferedReader;
import java.io.FileReader;

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
        String macros="MULTI_DECR",macros1="TO_DIGIT";
        String defineMacro="DEFINE ";

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
            keyword = Character.toString((char) c);
        }
        
        return keyword;
    }

}

