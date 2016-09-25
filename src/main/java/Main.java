import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

import java.io.FileNotFoundException;

/**
 * The main class of the project.
 *
 * @author Joël CANCELA VAZ and Pierre Rainero
 * @author Tanguy Invernizzi and Aghiles Dziri
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, Throwable {
        if (args.length != 2) {
            return;
        }
        if (args[0].equals("-p")) {
            Interpreter bfckInterpreter = Interpreter.getInstance();
            bfckInterpreter.init(args[1]);
            bfckInterpreter.readfile();
        }
    }
}