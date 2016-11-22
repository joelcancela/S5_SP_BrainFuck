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
    
    /**
     * Initialize the attribute's object
     * @param programName Name for the trace file (.log)
     * @throws IOException When the file cannot be created
     */
    public void start(String programName) throws IOException{
    	traceFile = new File (programName+".log");
    	fileWriter = new FileWriter (traceFile);
    	state=true;
    }
    
    /**
     * Write a line in the trace file.
     * @param line String to write.
     * @throws IOException
     */
    public void write(String line) throws IOException{
    	if(state==true)
    		fileWriter.write(line);
    }
    
    /**
     * Return if the trace file is open or not.
     * @return true if the file is open, false otherwise.
     */
    public boolean isOpen(){
    	return state;
    }
    
    /**
     * Close the trace file.
     * @throws IOException
     */
    public void close() throws IOException{
    	if(state==true)
    		fileWriter.close();
    	state=false;
    }
    
    /**
     * Return a PrintWriter to write a trace's exceptions in the trace file.
     * @return the PrintWriter object.
     * @throws IOException
     */
    public PrintWriter getPrintWriter() throws IOException{
    	fileWriter.write("\n");
    	printWriter = new PrintWriter(fileWriter);
    	return printWriter;
    }
    
    /**
     * Close the PrintWriter.
     */
    public void closePrint(){
    	printWriter.close();
    }
}
