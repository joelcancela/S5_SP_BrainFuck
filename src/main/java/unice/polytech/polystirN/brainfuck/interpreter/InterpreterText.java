package unice.polytech.polystirN.brainfuck.interpreter;

import java.io.BufferedReader;
import java.io.FileReader;

import unice.polytech.polystirN.brainfuck.exceptions.IncorrectFileTypeException;
import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;

public class InterpreterText extends Interpreter {
	/**
	 * 	buffer is the BufferedReader used to read files
	 **/
	private BufferedReader buffer;

	public InterpreterText(String filename) throws Exception {
		super();
		if (!filename.matches("(.*).bf")) {
			throw new IncorrectFileTypeException(filename + " must have .bf extension");
		}
		buffer = new BufferedReader(new FileReader(filename));
	}


	@Override
	public boolean executeFile() throws Exception {
		String keyword;
		
		while ((keyword = buffer.readLine()) != null) {
			if (!keyword.trim().equals("")) {
				if ('A'<=keyword.trim().charAt(0) && 'Z'>=keyword.trim().charAt(0)) {
					if (getOperatorsKeywords().get(keyword.trim()) == null) {
						throw new SyntaxErrorException("Invalid keyword operator");
					}
					getOperatorsKeywords().get(keyword.trim()).doOperation(this);
				}
				else {
					for (int i=0;i<keyword.replaceAll("\\s", "").length();i++) {
						getOperatorsKeywords().get(keyword.replaceAll("\\s", "").substring(i,i+1)).doOperation(this);
					}
				}
			}
		}
		return false;
	}

	/**
	 * This method is invoked if the parameter --rewrite is present at the execution
	 * It will print on the standard output the shortened representation of the program given as input.
	 *
	 * @return true if the operation ended well, else false
	 * @throws SyntaxErrorException             if an incorrect character is in the file that is being read
	 */
	@Override
	public boolean rewriteFile() throws Exception {
		String keyword;
		
		while ((keyword = buffer.readLine()) != null) {
			if (getOperatorsKeywords().get(keyword.trim()) == null) {
				for (int i = 0; i < keyword.trim().length(); i++)
				{
					if (getOperatorsKeywords().get(keyword.trim().substring(i, i+1)) != null)
					{
						System.out.print(keyword.trim().substring(i,i+1));
					}
					else {
						System.out.println();
						throw new SyntaxErrorException("Invalid keyword operator");
					}
				}
			}
			else {
				if (keyword.trim().equals("INCR"))
				{
					System.out.print("+");
				}
				else if (keyword.trim().equals("DECR"))
				{
					System.out.print("-");
				}
				else if (keyword.trim().equals("RIGHT"))
				{
					System.out.print(">");
				}
				else if (keyword.trim().equals("LEFT"))
				{
					System.out.print("<");
				}
				else
				{
					System.out.println();
					throw new SyntaxErrorException("Invalid keyword operator");
				}
			}
		}
		return true;
	}

	public void check() {
		System.out.println("I checked the file !");
	}

	@Override
	public Object getBuffer() {
		return buffer;
	}
}
