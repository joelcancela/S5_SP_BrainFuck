package unice.polytech.polystirN.brainfuck.language;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.interpreter.InstructionFactory;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

public class Procedure implements Operator{
	private List<String> array = new ArrayList();
	private String corp;
	private List<String> param = new ArrayList();
	private int nbParam;
	private  InstructionFactory factory;
	private int i=0; //pour parcourire le corp de la procedure
	/**
	 * constructor
	 * @param corp
	 * @param param2
	 * @param factory
	 */
	public Procedure(String corp, String[] param2, InstructionFactory factory) {
		param.addAll(Arrays.asList(param2));
		this.corp = corp;
		this.factory = factory;
	}
	/**
	 * method execute
	 */
	@Override
	public void execute(Interpreter interpreter) throws Exception {
		Interpreter inter = new Interpreter(interpreter);//clone the instance of interpreter
		execute1(inter);
	}
	/**
	 * Method which execute the call of the procedure
	 * @param interpreter
	 * @throws Exception
	 */
	private void execute1(Interpreter interpreter) throws Exception{
		if(corp.trim().length()==0)//si la procedure est vide on execute rien meme si elle a des paramettres
			return ;
		MacroWithParam left= (MacroWithParam) factory.getMapInstruction().get("MULTI_LEFT");
		MacroWithParam right = (MacroWithParam) factory.getMapInstruction().get("MULTI_RIGHT");
		for(i=0;i<corp.length();i++){
			int p = interpreter.getMemory().getP();
			String keyword = readInstruction();
			String[] macro = keyword.split(" ");
	        if(macro.length==2)
	        if(isInt(macro[1].trim())){
	        	if(factory.getInstruction(macro[0].trim())!=null && factory.getInstruction(macro[0].trim()).getClass().equals(MacroWithParam.class)){
	        		if(factory.getInstruction(macro[0].trim()).getClass().equals(MacroWithParam.class))
		        		if(Integer.parseInt(macro[1].trim())>=0){
		        			keyword = macro[0].trim();
		        			((MacroWithParam) factory.getInstruction(keyword)).setParam(Integer.parseInt(macro[1].trim()));
		        			((MacroWithParam) factory.getInstruction(keyword)).execute(interpreter);
		        		}
	        	}
	        }//executer l'instruction
	        if(factory.getInstruction(keyword.trim())!=null){
	    			if(factory.getInstruction(keyword.trim()) instanceof Jump){	    				 
	    				new Jump(LoopSeparate()).execute(interpreter);
	    			}
	    			else
	        		factory.getInstruction(keyword.trim()).execute(interpreter);
	        	 }
	        else //if the character or the word are an parameter 
	        	if(param.contains(keyword.trim()) && !param.isEmpty() && !array.isEmpty()){
						for(int j=0;j<param.size();j++){
							if(keyword.trim().equals(param.get(j).trim())){
								if(isInt(array.get(j).trim())){
								if(Integer.parseInt(array.get(j).trim())-p > 0){
									right.setParam(Integer.parseInt(array.get(j))-p);
									right.execute(interpreter);
									break;
								}else{
									left.setParam(p - Integer.parseInt(array.get(j).trim()));
									left.execute(interpreter);
									break;
								}
							}//thrw an exception if the parameter are not an integer 
							else throw new SyntaxErrorException("the param should be Integer");
						}
						}
					}else{
						if(factory.getInstruction(keyword)!=null){
							 throw new SyntaxErrorException("Incorrect word operator");
						}
				
			}
		}
	}
	/**
	 * Method which read the body of the procedure 
	 * the job of this method  are the of the job of next in TextReader
	 * @return
	 * @throws Exception 
	 */
	public String readInstruction() throws Exception{
		String keyword = " ";
		int c ;
		
		while(i<corp.length()){
			c = corp.charAt(i);
			//Ignore the commentary 
	        if(c=='#'){
	        	while(c!='\n' && c!='\r' && i<corp.length()){
	        		i++;
	        		c = corp.charAt(i); 
	        	}
	        	i++;
	        	c = corp.charAt(i);
	        }
	        if (('A' <= c && 'Z' >= c) || ('a' <= c && 'z' >= c)) {
	            while ((char) c != '\r' && (char) c != '\n' && i<corp.length()) {//c!=1 required because we read in the buffer
	            	//Ignore the commentary  
	            	if(c=='#'){
	     	        	while(c!='\n' && c!='\r' && i<corp.length()){
	     	        		i++;
	     	        		c = corp.charAt(i);
	     	        	}
	     	        	i++;
	     	        	c = corp.charAt(i);
	     	        }
	            	keyword += ((char) c);
	                i++;
	                c = corp.charAt(i);
	                
	            }
	        } else {
	            keyword = Character.toString((char) c);
	        }
	        if(keyword.split("\\(").length ==2 && keyword.split("\\)").length ==2 && keyword.trim().split(";").length==1){
	        	if(factory.getInstruction(keyword.split("\\(")[0].trim())!=null && factory.getInstruction(keyword.split("\\(")[0].trim()) instanceof Procedure){
	        		String[] paramettre = keyword.split("\\(")[1].split("\\)")[0].split(",");
	            	keyword = keyword.split("\\(")[0].trim();
	        		((Procedure)factory.getInstruction(keyword.split("\\(")[0].trim())).setParam(paramettre);
	        	}
	        }
        return keyword;
		}
		return keyword;
	}
	/**
	 * 
	 * @return
	 */
	public int getNbParam() {
		return nbParam;
	}
	/**
	 * 
	 * @param nbParam
	 */
	public void setNbParam(int nbParam) {
		this.nbParam = nbParam;
	}
	/**
	 * setter of parameter of the procedure which will be execute
	 * @param parametre
	 * @return
	 * @throws Exception
	 */
	public void setParam(String[] parametre) throws Exception{
		if(parametre.length!=param.size())
			throw new SyntaxErrorException("It is necessary to have the same number as in the declaration");
		for(int j=0;j<parametre.length;j++){
			if(!parametre[j].trim().isEmpty())
				array.add(parametre[j].trim());
		}
	}
	/**
	 * 
	 * @param chaine
	 * @return
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
	 /**
	  * 
	  * @return
	  * @throws Exception
	  */
	  public List<Operator> LoopSeparate() throws Exception{
			List<Operator> list = new ArrayList<Operator>();
			int nb=1;
			for(i=i+1;i<corp.length() && nb!=0;i++){
				String keyword= readInstruction().trim();
				String[] macro = keyword.split(" ");
		        if(macro.length==2)
		        if(isInt(macro[1].trim())){
		        	if(factory.getInstruction(macro[0].trim())!=null && factory.getInstruction(macro[0].trim()).getClass().equals(MacroWithParam.class)){
		        		if(factory.getInstruction(macro[0].trim()).getClass().equals(MacroWithParam.class))
			        		if(Integer.parseInt(macro[1].trim())>=0){
			        			keyword = macro[0].trim();
			        			((MacroWithParam) factory.getInstruction(keyword)).setParam(Integer.parseInt(macro[1].trim()));
			  
			        		}
		        	}
		        }
				if(!keyword.isEmpty()){
					Operator op= factory.getInstruction(keyword);
					if(op==null)
						 throw new SyntaxErrorException("Incorrect word operator");
					if(op instanceof Jump){
						nb++;
					}
					else
						if(op instanceof Back){
						nb--;
						}
					if(op instanceof Macro){
						list.addAll(((Macro) op).transform());
					}else
					list.add(op);
			   }
			}
			i--;
			return list;
		}
}