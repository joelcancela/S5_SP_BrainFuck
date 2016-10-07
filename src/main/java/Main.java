import joptsimple.OptionParser;
import joptsimple.OptionSet;
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
			if (options.has("check")) {
				InterpreterText inte = new InterpreterText((String)options.valueOf("p"));
				inte.check();
			}
			else if (options.has("rewrite")) {
				InterpreterText inte = new InterpreterText((String)options.valueOf("p"));
				inte.rewriteFile();
			}
			else {
				Interpreter inte = new InterpreterText((String)options.valueOf("p"));
				inte.executeFile();
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
}
