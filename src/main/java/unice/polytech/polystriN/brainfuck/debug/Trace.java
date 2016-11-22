package unice.polytech.polystriN.brainfuck.debug;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Trace {
	static public File traceFile;
    static public FileWriter fileWriter;
    static public PrintWriter printWriter;
    static public boolean state;
    
    public void start(String programName) throws IOException{
    	traceFile = new File (programName+".log");
    	fileWriter = new FileWriter (traceFile);
    	state=true;
    }
    
    public void write(String line) throws IOException{
    	if(state==true)
    		fileWriter.write(line);
    }
    
    public boolean isOpen(){
    	return state;
    }
    
    public void close() throws IOException{
    	if(state==true)
    		fileWriter.close();
    	state=false;
    }
    
    public PrintWriter getPrintWriter() throws IOException{
    	fileWriter.write("\n");
    	printWriter = new PrintWriter(fileWriter);
    	return printWriter;
    }
    
    public void closePrint(){
    	printWriter.close();
    }
}
