package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.language.Operator;

import java.io.BufferedReader;
import java.io.FileReader;

public class InterpreterText extends Interpreter {
    /**
     * buffer is the BufferedReader used to read files
     **/
    private BufferedReader buffer;

    public InterpreterText(String filename, String inputFile, String outputFile) throws Exception {
    	super(inputFile, outputFile);
        buffer = new BufferedReader(new FileReader(filename));
    }

    public InterpreterText(String filename) throws Exception{
        buffer = new BufferedReader(new FileReader(filename));
    }


    @Override
    public boolean executeFile() throws Exception {
        String keyword = "";

        int c;
        while ((c = buffer.read()) != -1) {
            if ('A' <= c && 'Z' >= c) {
                while ((char) c != '\r' && (char) c != '\n' && c!=-1) {
                	//c!=1 required because we read in the buffer
                    keyword += ((char) c);
                    c = buffer.read();
                }

                Operator op = getOperatorsKeywords().get(keyword);
                if (op == null) {
                    throw new SyntaxErrorException("Incorrect word operator");
                }
                op.doOperation(this);
                keyword = "";
            } else {
                if (((char) c != '\r') && ((char) c != '\n') &&((char) c != '\t')&&((char) c != ' ') ) {
                    if (getOperatorsKeywords().get(Character.toString((char) c)) == null) {
                        throw new SyntaxErrorException("Incorrect short expression");
                    }
                    getOperatorsKeywords().get(Character.toString((char) c)).doOperation(this);
                }
            }
        }
        return true;
    }

    /**
     * This method is invoked if the parameter --rewrite is present at the execution
     * It will print on the standard output the shortened representation of the program given as input.
     *
     * @return true if the operation ended well, else false
     * @throws SyntaxErrorException if an incorrect character is in the file that is being read
     */
    @Override
    public boolean rewriteFile() throws Exception {
        String keyword;

        while ((keyword = buffer.readLine()) != null) {
            if (getOperatorsKeywords().get(keyword.trim()) == null) {
                for (int i = 0; i < keyword.trim().length(); i++) {
                    if (getOperatorsKeywords().get(keyword.trim().substring(i, i + 1)) != null) {
                        System.out.print(keyword.trim().substring(i, i + 1));
                    } else {
                        System.out.println();
                        throw new SyntaxErrorException("Invalid keyword operator");
                    }
                }
            } else {
                if (keyword.trim().equals("INCR")) {
                    System.out.print("+");
                } else if (keyword.trim().equals("DECR")) {
                    System.out.print("-");
                } else if (keyword.trim().equals("RIGHT")) {
                    System.out.print(">");
                } else if (keyword.trim().equals("LEFT")) {
                    System.out.print("<");
                } else if (keyword.trim().equals("JUMP")) {
                    System.out.print("[");
                } else if (keyword.trim().equals("BACK")) {
                    System.out.print("]");
                } else {
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
