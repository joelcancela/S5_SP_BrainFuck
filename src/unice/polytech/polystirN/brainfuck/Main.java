package unice.polytech.polystirN.brainfuck;

import java.io.FileNotFoundException;

import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * The main class of the project.
 * 
 * @author Joël CANCELA VAZ and Pierre Rainero
 * @author Tanguy Invernizzi and Aghiles Dziri
 */
public class Main {
	
    public static void main(String[] args) throws FileNotFoundException,Throwable {
		if (args.length != 2) {
			return ;
		}
		if (args[0].equals("-p"))
		{
			Interpreter bfckInterpreter = new Interpreter(args[1]);
			bfckInterpreter.readfile();
		}
    }
}