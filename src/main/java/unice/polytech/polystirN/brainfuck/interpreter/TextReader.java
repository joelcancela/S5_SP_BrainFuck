package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.language.Operator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextReader extends Reader {
    /**
     * buffer is the BufferedReader used to read files
     **/
    private BufferedReader buffer;

    public TextReader(String filename) throws Exception{
        buffer = new BufferedReader(new FileReader(filename));
    }

    @Override
    public Object getBuffer() {
        return buffer;
    }
    
    @Override
    public boolean hasNext() throws Exception{
    	buffer.mark(1);
    	return (buffer.read()!=-1) ? true : false;
    }
    
    @Override
    public String next() throws Exception{
    	int c;
    	String keyword="";
    	
    	buffer.reset();
    	c = buffer.read();
    	
    	 if ('A' <= c && 'Z' >= c) {
             while ((char) c != '\r' && (char) c != '\n' && c!=-1) {//c!=1 required because we read in the buffer
                 keyword += ((char) c);
                 c = buffer.read();
             }
    	 }else{
    		 keyword = Character.toString((char) c);
    	 }
    	 
    	 return keyword;
    }
    
}

