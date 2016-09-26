import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * The main class of the project.
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            return;
        }
        if (args[0].equals("-p")) {
            Interpreter.init(args[1]);
            Interpreter.readfile();
        }
    }
}