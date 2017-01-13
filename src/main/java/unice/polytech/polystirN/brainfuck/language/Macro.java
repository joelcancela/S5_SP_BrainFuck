package unice.polytech.polystirN.brainfuck.language;


import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.interpreter.InstructionFactory;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Macro
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Macro implements Operator {
	private List<Operator> instructions;//for save the instruction of macros
	private InstructionFactory factory;
	private int j;

	/**
	 * constructor
	 *
	 * @param ins
	 * @param factory
	 * @throws Exception
	 */
	public Macro(String ins, InstructionFactory factory) throws Exception {
		instructions = new ArrayList<Operator>();
		this.factory = factory;
		madeInstruction(ins);
	}

	/**
	 * Verifies if a string is an integer
	 *
	 * @param chaine is a string to check
	 * @return boolean if the string is an integer else false
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

	private static boolean isShort(String chaine) {
        return chaine.equals("+") || chaine.equals("-") || chaine.equals("<") || chaine.equals(">")
                || chaine.equals("[") || chaine.equals("]") || chaine.equals(",") || chaine.equals(".");
    }

	/**
	 * Method execute
	 */
	@Override
	public void execute(Interpreter interpreter) throws Exception {
		List<Operator> temp;
		for (j = 0; j < instructions.size(); j++) {
			if (instructions.get(j) instanceof Jump) {
				temp = LoopSeparate();
				new Jump(temp).execute(interpreter);
			} else {
				instructions.get(j).execute(interpreter);
			}
		}
	}

	/**
	 * @param macrosEquivalent
	 * @return list of the instruction of a macros
	 * @throws Exception
	 */
	public List<Operator> madeInstruction(String macrosEquivalent) throws Exception {
		String instruction[] = macrosEquivalent.split("/");
        String inst[] = instruction;
		if (macrosEquivalent.equals("")) return instructions;
		int i = 0;
		while (i < instruction.length) {
			int j = 0;
			if (instruction[i].trim().split(" ").length == 2)
				if (isInt(instruction[i].split(" ")[1])) {
					if (factory.getInstruction(instruction[i].split(" ")[0]) instanceof MacroWithParam) {
						((MacroWithParam) factory.getInstruction(instruction[i].split(" ")[0].trim())).setParam(Integer.parseInt(instruction[i].split(" ")[1].trim()));
						instruction[i] = ((MacroWithParam) factory.getInstruction(instruction[i].split(" ")[0].trim())).toString().replace(" ", "");

					} else throw new SyntaxErrorException("Incorrect word operator");
				}
			char car = instruction[i].charAt(0);
			if (('A' <= car && 'Z' >= car) || ('a' <= car && 'z' >= car)) {
				Operator op = factory.getInstruction(instruction[i].trim());
				if (op == null)
					throw new SyntaxErrorException("Incorrect word operator");
				instructions.add(op);
			} else {
				while (j < instruction[i].length()) {
					Operator op = null;
					String shortInstruction = instruction[i].substring(j, j + 1);
					if (shortInstruction != " ") {
						if (isShort(shortInstruction))
							op = factory.getInstruction(shortInstruction);
						else
							throw new SyntaxErrorException("Incorrect word operator");
						instructions.add(op);
					}
					j++;
				}


			}
			i++;
		}
		return instructions;

	}

	/**
	 * get arrayList of the instructions of a macros
	 *
	 * @return
	 */
	public List<Operator> getInstructions() {
		return instructions;
	}

	/**
	 * get all instruction of a macros
	 *
	 * @return String
	 * @throws SyntaxErrorException
	 */
	public String toString() {
		String S = "";
		if (instructions.size() == 0)
			return " ";
		for (int i = 0; i < instructions.size(); i++) {
			S += instructions.get(i).toString();
		}
		return S;
	}

	@Override
	public String generateC(int indentLevel, int consecutive) {
		String cCode = "";
		for (int i = 0; i < consecutive; i++) {
			for (int j = 0; j < instructions.size(); j++) {
				cCode = cCode + instructions.get(j).generateC(indentLevel, consecutive);
			}
		}
		return cCode;
	}

	/**
	 * number of instruction in a macro
	 *
	 * @return
	 */
	public int getNumberOfinstruction() {
		return instructions.size();
	}

	/**
     * Isolate the operators in the selected loop
	 * @return The list of operators in the loop
	 */
	public List<Operator> LoopSeparate() {
		List<Operator> list = new ArrayList<Operator>();
		int nb = 1;
		for (j = j + 1; j < instructions.size() && nb != 0; j++) {
			if (instructions.get(j) instanceof Jump) {
				nb++;
			} else if (instructions.get(j) instanceof Back) {
				nb--;
			}

			if (instructions.get(j) instanceof MacroWithParam) {
				list.addAll(((MacroWithParam) instructions.get(j)).transform());
			} else if (instructions.get(j) instanceof Macro) {
				list.addAll(((Macro) instructions.get(j)).transform());
			} else
				list.add(instructions.get(j));
		}
		j--;
		return list;
	}

	public List<Operator> transform() {
		String string;
		List<Operator> array = new ArrayList<Operator>();
		string = toString().replaceAll(" ", "");
		for (int i = 0; i < string.length(); i++) {
			array.add(factory.getInstruction(string.substring(i, i + 1)));
		}

		return array;
	}


}