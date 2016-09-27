import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * The main class of the project.
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Main {

    public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("usage: \"bfck -p <filename>");
			System.out.println("BRAINFUCK [Mül93] is a programming language created in 1993 by Urban Müller," +
                    " and notable for its extreme minimalism.\nThis is the BrainFuck interpreter made by the group PolyStirN," +
                    " composed of Joël CANCELA VAZ, Pierre RAINERO, Aghiles DZIRI and Tanguy INVERNIZZI.");
		}
        if (args.length != 2) {
            return;
        }
        if (args[0].equals("-p")) {
            try {
                Interpreter.init(args[1]);
                Interpreter.readfile();
            } catch (Exception e) {
                if(e.getMessage().equals("MemoryOverflowError") || e.getMessage().equals("MemoryUnderflowError")){
                    System.exit(1);
                }
                if(e.getMessage().equals("PointerMinimumValueError") || e.getMessage().equals("PointerMaximumValueError")){
                    System.exit(2);
                }
            }
        }
    }
}
