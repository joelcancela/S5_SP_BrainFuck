package unice.polytech.polystirN.brainfuck.interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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
	private char keyword1;
	
    /**
     * TextReader constructor
     *
     * @param filename is the name of the file to be read
     */
    public TextReader(String filename,Interpreter inte) throws Exception {
        buffer = new BufferedReader(new FileReader(filename));
        buffer = new BufferedReader(new FileReader(macrosRead(filename)));
    }
    public TextReader(String filename) throws Exception {
        buffer = new BufferedReader(new FileReader(filename));
        buffer = new BufferedReader(new FileReader(macrosRead(filename)));
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
        

        c = buffer.read();

        if (( 'A' <= c && 'Z' >= c )) {
            while ((char) c != '\r' && (char) c != '\n' && c != -1) {//c!=1 required because we read in the buffer
                keyword += ((char) c);
                c = buffer.read();
            }   
        }
        else {
        	keyword = Character.toString((char) c);
        }
        
        return keyword;
        
    }
    /**
     * replace the macros by there equivalent in the file witch be executed
     * @param c
     * @return name of file witch be executed
     * @throws Exception
     */
    public String macrosRead(String filename) throws Exception{
    	int[] c = new int[2];
    	c[0] = buffer.read();
    	c=readDefineMacros(c[0]);
    	if(c[1]==1)
    	return MacroTransform(c[0]);
    	else return filename; 
    }
    /**
     * @throws IOException 
     * @throws SyntaxErrorException 
     * 
     */
    private String MacroTransform(int c) throws IOException, SyntaxErrorException{
    	String keyword="",fichier="./fichiertempant.bf", keyword1="";
    	FileWriter fichierTempant = new FileWriter(fichier);
    	while(c!=-1){
			 keyword="";
			 keyword1="";
			
		 if (( 'A' <= c && 'Z' >= c ) || ('a' <= c && 'z' >= c)) {
			 while ((char) c != '\r' && (char) c != '\n' && c != -1) {//c!=1 required because we read in the buffer
				 keyword += ((char) c);
				 c = buffer.read();
			 }
			 String tab[]=keyword.split(" ");
			 if(tab.length==2){
				 if(isInt(tab[1])){
					 keyword1=tab[1];
					 keyword=tab[0];
				 }
			 }
		 }
		 if(c=='#'){
			 keyword=Character.toString((char) c)+buffer.readLine();
		 }
		
			 if(factory.getEquivalentInstruction(Character.toString((char)c))!=null){
				 fichierTempant.write(System.getProperty("line.separator")+factory.getEquivalentInstruction(Character.toString((char)c)).trim());
			 }
			 else  {if((short)c!=-1 && keyword.equals(""))
				 fichierTempant.write(System.getProperty("line.separator")+Character.toString((char)c).trim());
			 }
		 if(!keyword1.trim().equals("")){
		  if(isInt(keyword1.trim())){
			  int att = Integer.parseInt(keyword1.trim());
			 		for(int j=0; j < att; j++){
			 			fichierTempant.write(System.getProperty("line.separator")+factory.getMapMacrosParam().get(keyword.trim()).trim());
			 		}
		  }}
		 		else {
		 			if(factory.getEquivalentInstruction(keyword.trim())!=null)
		 			fichierTempant.write(System.getProperty("line.separator")+factory.getEquivalentInstruction(keyword.trim()).trim());
		 			else fichierTempant.write(System.getProperty("line.separator")+keyword);
		 		}
		 c = buffer.read();
	}
    	fichierTempant.close();
   	 return fichier;
    }
    /**
     * Stock the equivalent of the macros to be able to use them after
     * @throws Exception 
     * 
     */
    private int[] readDefineMacros(int c) throws Exception{
    	String word="";
    	char  charOfM='\0';
    	int[] bool ={c,0};
    	String macros;
    	String define = "DEFINE";
    	int i;
    	
    	 while(c!=-1 && (c=='$' || c==' ' || c=='\n' || c=='\r')){
    		 word="";
    		 charOfM='\0';
    		if((char)c=='$'){
    			macros = buffer.readLine();
    			if(macros.length()<=define.length())
    				throw new SyntaxErrorException("$DEFINE <your word> <instructions>");
    			if(!macros.substring(0,define.length()).equals(define))
    				throw new SyntaxErrorException("$DEFINE <your word> <instructions>");
    			else{
    				if(macros.trim().split(" ").length < 3)
    					throw new SyntaxErrorException("$DEFINE <your word> <instructions>");
    				 macros=macros.substring(define.length(),macros.trim().length()).trim();
    				 for(i=0; i<macros.trim().length() && charOfM!=' '; i++){
    					 word+=macros.trim().charAt(i);
    					 charOfM=macros.trim().charAt(i+1);
    					 
    				 }
    				 if(macros.trim().substring(word.trim().length(),macros.trim().length()).contains("\\")){
    					macros = macros.trim().substring(word.trim().length(),macros.trim().length()).trim().replace("\\", System.getProperty("line.separator"));	
    				 }
    				 else {
    					 macros =macros.trim().substring(word.trim().length(),macros.trim().length()).trim();
    				 }
    				 if(word.trim().length()>2){
    				 if(word.trim().substring(word.trim().length()-2,word.trim().length()).equals("()"))
    					 if(factory.getMapMacrosParam().get(macros)!=null)
    						 factory.getMapMacrosParam().put(word.trim().substring(0,word.trim().length()-2).trim(),factory.getMapMacrosParam().get(macros));
    					 else if(factory.getEquivalentInstruction(macros)!=null)
    						 factory.getMapMacrosParam().put(word.trim().substring(0,word.trim().length()-2).trim(),factory.getEquivalentInstruction(macros));
    					 else factory.getMapMacrosParam().put(word.trim().substring(0,word.trim().length()-2).trim(),rewriteMul(macros));
    				 }
    				 if(factory.getEquivalentInstruction(macros)==null)
    					 factory.put(word.trim(),rewriteMul(macros));
    				 else{ factory.put(word.trim(), rewriteMul(factory.getEquivalentInstruction(macros)));

    				 }
   				}
    			bool[0]=c;
        		bool[1]=1;
    		 }
    		c=buffer.read();
    	
    		}
    	 return bool;
    }
    /**
     * rewrite the equivalent of to_digit and multi_decr in the string
     * @param word
     * @return String
     */
    private String rewriteMul(String word){
    	String tab[] = word.split(" "); 
    	
    		if(tab[0].trim().equals("MULTI_DECR")){
    			if(tab.length==1) return "-";
    			int i = Integer.parseInt(tab[1]);
    			String S="";
    			for(int j=0; j<i; j++){
    				S+="-";
    			}
    			return S;
    		}
    		else if(word.trim().equals("TO_DIGIT")){
    			String S="";
    			for(int j=0; j<48; j++){
    				S+="-";
    			}
    			return S;
    		}
    		
    	return word;
    }
    /**
     * verify if a string is an integer
     * @param chaine
     * @return boolean
     */
    private static boolean isInt(String chaine){
    	boolean valeur = true;
    	char[] tab = chaine.toCharArray();

    	for(char carac : tab){
    	if(!Character.isDigit(carac) && valeur){ valeur = false; }
    	}

    	return valeur;
    	} 
}

