package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.computationalModel.Memory;
import unice.polytech.polystirN.brainfuck.debug.Metrics;
import unice.polytech.polystirN.brainfuck.debug.Trace;
import unice.polytech.polystirN.brainfuck.exceptions.BadLoopException;
import unice.polytech.polystirN.brainfuck.exceptions.IncorrectFileTypeException;
import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.language.Jump;
import unice.polytech.polystirN.brainfuck.language.Operator;
import unice.polytech.polystirN.brainfuck.language.Procedure;

/**
 * Models the virtual machine interpreting the
 * brainfuck language.
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Interpreter {

	private Memory memory;
	private InstructionFactory factory;
	private Reader reader;
	private boolean inALoop;
	private String programName;
	private Metrics metrics;
	private Trace trace;

	/**
	 * Constructor for Interpreter
	 *
	 * @param filename is the name of the file to be interpreted
	 * @throws IncorrectFileTypeException if the filename has an invalid extension
	 */
	public Interpreter(String filename) throws Exception {
		factory = new InstructionFactory();
		memory = new Memory();
		metrics = new Metrics();

		if (filename.matches("(.*).bf")) {
			programName = filename.substring(0, filename.length() - 3);
			reader = new TextReader(filename, this);
		} else if (filename.matches("(.*).bmp")) {
			programName = filename.substring(0, filename.length() - 4);
			reader = new ImageReader(filename);
		} else {
			throw new IncorrectFileTypeException("Incorrect file's extension, expected .bf or .bmp");
		}


		inALoop = false;
	}

	public Interpreter(Interpreter interpreter) {
		this.factory = interpreter.getFactory();
		this.memory = new Memory(interpreter.getMemory());
		this.metrics = interpreter.metrics;
	}

	/**
	 * Sets the correct operatorsKeywords to interpret as operators
	 *
	 * @param filename   is the name of the file to be interpreted
	 * @param inputFile  is the name of the file to replace the input (if null, keyboard by default)
	 * @param outputFile is the name of the file to replace the output (if null, console by default)
	 */
	public Interpreter(String filename, String inputFile, String outputFile) throws Exception {
		this(filename);
		factory = new InstructionFactory(inputFile, outputFile);
		if (filename.matches("(.*).bf")) {
			programName = filename.substring(0, filename.length() - 3);
			reader = new TextReader(filename, this);
		} else if (filename.matches("(.*).bmp")) {
			programName = filename.substring(0, filename.length() - 4);
			reader = new ImageReader(filename);
		}
	}

	/**
	 * Constructor for Interpreter with a trace
	 *
	 * @param filename is the name of the file to be interpreted
	 * @param trace    object trace responsible for write the trace file (.log)
	 */
	public Interpreter(String filename, Trace trace) throws Exception {
		this(filename);
		this.trace = trace;
	}

	/**
	 * Interprets a file
	 *
	 * @throws SyntaxErrorException if an invalid symbol or keyword is encountered
	 */
	public void interpretFile() throws Exception {
		String keyword;

		if (isTrace()) {
			trace.start(programName);
		}
		while (reader.hasNext()) {
			keyword = reader.next();
			if (keyword.length() > 0 && keyword.charAt(0) == '?')
				keyword = " ";
			if (!(keyword.equals("\n") || keyword.equals("\r") || keyword.equals("\t") || keyword.equals(" ") || keyword.equals("#") || keyword.equals(""))) {
				Operator op = getFactory().getInstruction(keyword.trim());
				metrics.incrementProgSize();
				metrics.incrementExecMove();
				if (isTrace()) {
					trace.write(metrics.getExecMove() + " : " + op.toString() + "\n");
					trace.write("pointer : " + memory.getP());
				}
				if (op == null) {
					throw new SyntaxErrorException("Incorrect word operator");
				}
				op.execute(this);
				if ((op instanceof Jump) && isTrace())
					trace.write(((Jump) op).getTrace());
				else if (isTrace()) {
					trace.write("\npointer after : " + memory.getP() + "\n");
					trace.write(memory.toString());
					trace.write("----------------------------\n");
				}
			} else if (keyword.equals("#")) {
				keyword = reader.next();
				while (reader.hasNext() && (!(keyword.equals("\n")) || (keyword.equals("\r")))) {
					keyword = reader.next();
				}
			}
		}
		if (isTrace())
			trace.close();
		System.out.println("\nC" + memory.getP() + ": " + (memory.getCells()[memory.getP()] & 0xFF));
	}

	/**
	 * Called when a file has to be read.
	 * Throws an error when a character in the file isn't supported
	 * by the brainfuck language.
	 *
	 * @return true if the file was successfully read, false if not.
	 * @throws SyntaxErrorException if the keyword isn't recognized as an operator
	 */
	public boolean rewriteFile() throws Exception {
		String keyword;

		while (reader.hasNext()) {
			keyword = reader.next();
			if (!keyword.isEmpty() && keyword.charAt(0) == '?')
				if (keyword.length() > 1)
					keyword = keyword.substring(1, keyword.length());
			if (getFactory().getInstruction(keyword.trim()) == null) {
				for (int i = 0; i < keyword.trim().length(); i++) {
					if (getFactory().getInstruction(keyword.trim().substring(i, i + 1)) != null) {
						System.out.print(keyword.trim().substring(i, i + 1));
					} else {
						throw new SyntaxErrorException("Incorrect word operator");
					}
				}
			} else {
				if (keyword.trim().equals("INCR") || keyword.trim().equals("#FFFFFF")) {
					System.out.print("+");
				} else if (keyword.trim().equals("DECR") || keyword.trim().equals("#4B0082")) {
					System.out.print("-");
				} else if (keyword.trim().equals("RIGHT") || keyword.trim().equals("#0000FF")) {
					System.out.print(">");
				} else if (keyword.trim().equals("LEFT") || keyword.trim().equals("#9400D3")) {
					System.out.print("<");
				} else if (keyword.trim().equals("JUMP") || keyword.trim().equals("#FF7F00")) {
					System.out.print("[");
				} else if (keyword.trim().equals("BACK") || keyword.trim().equals("#FF0000")) {
					System.out.print("]");
				} else if (keyword.trim().equals("OUT") || keyword.trim().equals("#00FF00")) {
					System.out.print(".");
				} else if (keyword.trim().equals("IN") || keyword.trim().equals("#FFFF00")) {
					System.out.print(",");
				} else if (factory.getInstruction(keyword.trim()) != null) {

					if (factory.getInstruction(keyword.trim()) instanceof Procedure) {
						if (!((Procedure) factory.getInstruction(keyword.trim())).wasDefined())
							System.out.println(((Procedure) factory.getInstruction(keyword.trim())).defineP());
						else
							System.out.print(System.lineSeparator() + keyword.trim() + "(" + ((Procedure) factory.getInstruction(keyword.trim())).getParam() + ");");
					} else System.out.print(factory.getInstruction(keyword.trim()).toString());
				}
			}
		}
		return true;
	}


	/**
	 * Checks if a program is well formed
	 */
	public void check() throws Exception {
		String keyword;
		int nbOuvert = 0;

		while (reader.hasNext()) {
			keyword = reader.next();
			if (!(keyword.equals("\n") || keyword.equals("\r") || keyword.equals("#") || keyword.equals("\t") || keyword.equals(" ") || keyword.isEmpty())) {
				Operator op = getFactory().getInstruction(keyword);
				if (op == null)
					throw new SyntaxErrorException("Incorrect word operator");
				if (keyword.equals("JUMP") || keyword.equals("[") || keyword.equals("#FF7F00"))
					nbOuvert++;
				if (keyword.equals("BACK") || keyword.equals("]") || keyword.equals("#FF0000"))
					nbOuvert--;
				if (nbOuvert < 0)
					throw new BadLoopException("Loop without start : Missing JUMP operator");
			} else if (keyword.equals("#")) {
				keyword = reader.next();
				while (reader.hasNext() && (!(keyword.equals("\n")) || (keyword.equals("\r")))) {
					keyword = reader.next();
				}
			}
		}
		if (nbOuvert > 0)
			throw new BadLoopException("Loop without end : Missing BACK operator");
		System.out.println("The program is well formed");
	}

	/**
	 * Getter for the reader attribute
	 *
	 * @return reader being the input reader of the interpreter
	 */
	public Reader getReader() {
		return reader;
	}

	/**
	 * Getter for the memory attribute
	 *
	 * @return memory
	 */
	public Memory getMemory() {
		return memory;
	}

	/**
	 * Getter for the OperatorsKeywords HashMap
	 *
	 * @return operatorKeywords
	 */
	public InstructionFactory getFactory() {
		return factory;
	}

	/**
	 * Return true if the execution thread is in a loop
	 *
	 * @return inALoop
	 */
	public boolean isInALoop() {
		return inALoop;
	}

	/**
	 * Change the status of inALoop to true
	 */
	public void startALoop() {
		inALoop = true;
	}

	/**
	 * Change the status of inALoop to false
	 */
	public void endALoop() {
		inALoop = false;
	}

	/**
	 * Getter for the metrics
	 *
	 * @return the metrics instance
	 */
	public Metrics getMetrics() {
		return metrics;
	}

	/**
	 * Setter for the metrics
	 *
	 * @return the metrics instance
	 */
	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}

	/**
	 * Checks if the trace is initialized
	 *
	 * @return true if the trace is initialized else false
	 */
	public boolean isTrace() {
		return !(trace == null);
	}
}
