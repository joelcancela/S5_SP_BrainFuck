import joptsimple.OptionParser;
import joptsimple.OptionSet;
import unice.polytech.polystirN.brainfuck.exceptions.IncorrectFileTypeException;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

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
        parser.accepts("check");
        parser.accepts("rewrite");

        //Handle the args with the options
        OptionSet options = parser.parse(args);

        if (args.length == 0) {
            print_empty_message();
        }
        if (options.has("p")) {
            try {
                if (check_file_type((String) options.valueOf("p"))) {
                    if (options.has("check")) {
                        Interpreter inte = new Interpreter((String) options.valueOf("p"));
                        inte.check();
                    } else if (options.has("rewrite")) {
                        Interpreter inte = new Interpreter((String) options.valueOf("p"));
                        inte.rewriteFile();
                    } else {
                        if (options.has("i") && options.has("o")) {
                            Interpreter inte = new Interpreter((String) options.valueOf("p"), (String) options.valueOf("i"), (String) options.valueOf("o"));
                            inte.interpretFile();
                        } else if (options.has("i")) {
                            Interpreter inte = new Interpreter((String) options.valueOf("p"), (String) options.valueOf("i"), null);
                            inte.interpretFile();
                        } else if (options.has("o")) {
                            Interpreter inte = new Interpreter((String) options.valueOf("p"), null, (String) options.valueOf("o"));
                            inte.interpretFile();
                        } else {
                            Interpreter inte = new Interpreter((String) options.valueOf("p"));
                            inte.interpretFile();
                        }
                    }
                } else {
                    throw new IncorrectFileTypeException(options.valueOf("p") + " must have .bf or .bmp extension");
                }
            } catch (Exception e) {
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
                    case "FileNotFoundException":
                        System.exit(3);
                    default:
                        break;

                }
            }
        }
    }

    private static void print_empty_message() {
        System.out.println("usage: bfck [-p <filename>] [-i <filename>] [-o <filename>]\n"
                + "            [--rewrite] [--translate] [--check]\n"
                + "BRAINFUCK [Mül93] is a programming language created in 1993 by Urban Müller,"
                + " and notable for its extreme minimalism.\nThis is the BrainFuck interpreter made by the group PolyStirN,"
                + " composed of Joël CANCELA VAZ, Pierre RAINERO, Aghiles DZIRI and Tanguy INVERNIZZI.");
    }

    private static boolean check_file_type(String filename) {
        return (filename.matches("(.*).bf") || filename.matches("(.*).bmp"));
    }
}
