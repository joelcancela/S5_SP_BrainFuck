package unice.polytech.polystirN.brainfuck.language;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import unice.polytech.polystirN.brainfuck.exceptions.PointerPositionOutOfBoundsException;
import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * Class used to specify the Jump operator behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */

public class Jump implements Operator {
	private int nbOuvert;
	private List<String> file;

	/**
	 * This method checks the content of the current memory cell. 
	 * This operator does nothing if the value is different of 0.
	 * Else, this operator will read the next instruction until he meet the BACK operator. All operators encountered are executed if the memory cell is different to 0.
	 *
	 * @param interpreter memory (M and P) of the current program and all of the following operations.
	 * @return true if the loop is over.
	 * @throws PointerPositionOutOfBoundsException if the pointer value is inferior to 0.
	 */
	@Override
	public boolean doOperation(Interpreter interpreter) throws PointerPositionOutOfBoundsException, Exception {
		int dp = interpreter.getMemory().getCells()[interpreter.getMemory().getP()];
		int i = 0;
		boolean premierParcours = true;
		String instruction = "";

		nbOuvert = 1;
		file = new ArrayList<String>();

		//Anomaly case :
		if (dp < 0)
			throw new PointerPositionOutOfBoundsException("current memory have illegal value (inferior to 0)");

		switch(interpreter.getClass().getSimpleName()) {
		case "InterpreterText": //Dans le cas d'un fichier texte (.bf)
			BufferedReader buffer = (BufferedReader) interpreter.getBuffer(); //Récupère le buffer adapté

			//Special case : (value of initiale memory case is already equals to 0)
			if(interpreter.getMemory().getCells()[interpreter.getMemory().getP()] == 0){
				char c;
				while(nbOuvert != 0) {
					instruction = buffer.readLine();
					
					if(!('A'<=instruction.trim().charAt(0) && 'Z'>=instruction.trim().charAt(0))){
						for (int z=0;z<instruction.replaceAll("\\s", "").length();z++){
							c = instruction.replaceAll("\\s", "").charAt(z);
							switch(c) {
								case '[': nbOuvert++; break;
								case ']': nbOuvert--; break;
							}
						}
					}else{
						switch(instruction) {
							case "JUMP": nbOuvert++; break;
							case "BACK": nbOuvert--; break;
						}
					}
				}
				return false;
			}

			//Nominal case :
			while(interpreter.getMemory().getCells()[interpreter.getMemory().getP()] != 0) { //Tant que dp ne vaut pas 0 (sort immédiatement si ma case vaut 0)
				String chara;
				while(nbOuvert != 0) { //Tant qu'il reste des boucles à parcourir
					if (premierParcours == true) { //Renseigne la liste d'instructions (file) composant la boucle la plus englobante
						instruction = buffer.readLine();
						
						if(!('A'<=instruction.trim().charAt(0) && 'Z'>=instruction.trim().charAt(0))){
							for (int z=0;z<instruction.replaceAll("\\s", "").length();z++){
								chara = instruction.replaceAll("\\s", "").substring(z,z+1);
								file.add(chara);
								System.out.println(chara);
							}
						}else{
							file.add(instruction);
						}

						iteration(instruction, interpreter, false, 0);
					} 
					else { //Exécute les instructions de la boucle englobante
						instruction = file.get(i);

						i = iteration(instruction, interpreter, true, i);
						i++;
					}
				}
				System.out.println("--------fin de boucle--------");
				//Iteration suivante dans la boucle brainfuck :
				premierParcours = false; //la première itération du while permet de récupérer toutes les instructions sans poser de problèmes aux itérations suivantes
				nbOuvert = 1;
				i = 0;
			}
			break;

		case "InterpreterImage": //Dans le cas d'une image
			break;
		}
		return true;
	}

	/**
	 * Analyze an instruction of loop.
	 * 
	 * @param instruction the operation to analyze.
	 * @param interpreter memory (M and P) of the current program and all of the following operations.
	 * @param execute true for execute the operation, false otherwise.
	 * @param index position in the operations list's (file).
	 * @return the current index in the operations list's.
	 * @throws Exception
	 */
	public int iteration(String instruction, Interpreter interpreter, boolean execute, int index) throws Exception{
		//Incrémente/décrémente le nombre de boucles à parcourir
		switch(instruction) {
		case "JUMP": case "[": nbOuvert++; break;
		case "BACK": case "]": nbOuvert--; break;
		}
		//------------------------------------------------------

		//Si on est pas dans la première itération du while et qu'on exécute :
		if(execute == true) {
			if(file.get(index).equals("JUMP") || file.get(index).equals("[")) {
				index = internalLoop(index,interpreter); //Crée une boucle interne
			}
			else {
				execute(instruction, interpreter); //Exécute l'opération
			}
			return index;
		}

		return index;
	}

	/**
	 * Make an other loop in a loop (support object).
	 * 
	 * @param index position in the operations list's (file)
	 * @param interpreter memory (M and P) of the current program and all of the following operations.
	 * @return the current index in the operations list's.
	 * @throws Exception
	 */
	public int internalLoop(int index, Interpreter interpreter) throws Exception{
		int originalIndex = index; //Index à l'entrée de la boucle
		int internalIndex; //Index à la sortie, utile si et seulement si on a une autre boucle dans cette boucle et ainsi desuite

		//DEBUT BOUBLE INTERNE
		while (interpreter.getMemory().getCells()[interpreter.getMemory().getP()] != 0) { //Tant que cette boucle n'est pas terminée
			index = originalIndex;
			while (file.get(index).equals("BACK")!=true && file.get(index).equals("]")!=true) {
				index++;
				if (file.get(index).equals("JUMP") || file.get(index).equals("[")) { //Si on a une boucle dans cette boucle
					nbOuvert++;
					internalIndex = internalLoop(index,interpreter); //Lance récursivement des boucles internes et récupère l'index dans la liste des instructions 
					index = internalIndex+1;
				}
				execute(file.get(index), interpreter); //Les "JUMP" dans un JUMP ne sont donc jamais exécutés pour ne pas perdre les instructions stockées dans "file"
			}
			//FIN BOUCLE INTERNE

		}
		while (file.get(index).equals("BACK")!=true && file.get(index).equals("]")!=true) {//Permet de ne pas repasser par les instructions de la boucle si la case mémoire vaut 0
			index++;
		}
		nbOuvert--;
		return index;
	}

	/**
	 * Execute an instruction of loop.
	 * 
	 * @param instruction the operation to execute.
	 * @param interpreter memory (M and P) of the current program and all of the following operations.
	 * @throws Exception
	 */
	public boolean execute(String instruction, Interpreter interpreter) throws Exception{
		if (interpreter.getOperatorsKeywords().get(instruction.trim()) == null) {
			throw new SyntaxErrorException("Invalid keyword operator");
		}
		return interpreter.getOperatorsKeywords().get(instruction.trim()).doOperation(interpreter);
	}

}
