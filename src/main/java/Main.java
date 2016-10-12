import joptsimple.OptionParser;
import joptsimple.OptionSet;
import unice.polytech.polystirN.brainfuck.exceptions.IncorrectFileTypeException;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;
import unice.polytech.polystirN.brainfuck.interpreter.InterpreterText;

/**
 * The main class of the project.
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Main {

	public static void main(String[] args) throws Exception {

		//Configure the parser
		OptionParser parser = new OptionParser("p:i:o:");
		parser.accepts( "check" );
		parser.accepts( "rewrite" );

		//Handle the args with the options
		OptionSet options = parser.parse(args);

		if (args.length == 0) {
			print_empty_message();
		}
		if (options.has("p")) {
			try {
				switch (check_file_type((String)options.valueOf("p"))) {
				case 0: //fichier invalide
					throw new IncorrectFileTypeException(options.valueOf("p") + " must have .bf or .bmp extension");
				case 1: //fichier texte
					if (options.has("check")) {
						InterpreterText inte = new InterpreterText((String)options.valueOf("p"), null, null);
						inte.check();
					}
					else if (options.has("rewrite")) {
						InterpreterText inte = new InterpreterText((String)options.valueOf("p"), null, null);
						inte.rewriteFile();
					}
					else {
						if (options.has("i") && options.has("o")) {
							Interpreter inte = new InterpreterText((String)options.valueOf("p"), (String)options.valueOf("i"), (String)options.valueOf("o"));
							inte.executeFile();
						}
						else if (options.has("i")) {
							Interpreter inte = new InterpreterText((String)options.valueOf("p"), (String)options.valueOf("i"), null);
							inte.executeFile();
						}
						else if (options.has("o")) {
							Interpreter inte = new InterpreterText((String)options.valueOf("p"), null, (String)options.valueOf("o"));
							inte.executeFile();
						}
						else {
							Interpreter inte = new InterpreterText((String)options.valueOf("p"), null, null);
							inte.executeFile();
						}
					}
					break;
				case 2: //fichier image
					//TODO
					break;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				switch (e.getClass().getSimpleName()) {
				case "MemoryOverflowException":
					System.exit(1);
					break;
				case "MemoryUnderflowException":
					System.exit(1);
					break;
				case "PointerPositionOutOfBoundsException":
					System.exit(2);
				case "IncorrectFileTypeException":
					System.exit(3);
				default:
					break;

				}
			}
		}
	}

	private static void print_empty_message() {
		System.out.println("usage: bfck [-p <filename>] [-i <filename>] [-o <filename>]\n"
				+   "            [--rewrite] [--translate] [--check]\n"
				+   "BRAINFUCK [Mül93] is a programming language created in 1993 by Urban Müller,"
				+   " and notable for its extreme minimalism.\nThis is the BrainFuck interpreter made by the group PolyStirN," 
				+   " composed of Joël CANCELA VAZ, Pierre RAINERO, Aghiles DZIRI and Tanguy INVERNIZZI.");
	}

	private static int check_file_type(String filename) {
		if (filename.matches("(.*).bf")) {
			return 1;
		}
		if (filename.matches("(.*).bmp")) {
			return 2;
		}
		return 0;      
	}
}
