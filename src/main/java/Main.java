import joptsimple.OptionParser;
import joptsimple.OptionSet;
import unice.polytech.polystirN.brainfuck.exceptions.IncorrectFileTypeException;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * The main class of the project handles the different flags and options of our program.
 * Using joptsimple ( https://pholser.github.io/jopt-simple/ ). To see a detailed list
 * of the options supported by the program, launch the executable without any flags.
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Main {

    public static void main(String[] args) throws Exception {

        //First, we got to configure the parser.
        OptionParser parser = new OptionParser("p:i:o:"); //: after a character means that an argument is mandatory for this flag.
        parser.accepts("check");
        parser.accepts("rewrite"); //here we add the longs options.

        OptionSet options = parser.parse(args); //Handle the args of the command line with the options.

        if (args.length == 0) {
            print_empty_message(); //Show a man-like message if no options have been given.
        }
        if (options.has("p")) { //Is there a file ?
            try {
                if (check_file_type((String) options.valueOf("p"))) { //Is the type of the file valid ?
                    if (options.has("check")) { //Do we need to check it ?
                        Interpreter inte = new Interpreter((String) options.valueOf("p"));
                        inte.check();
                    } else if (options.has("rewrite")) { //Do we need to rewrite it ?
                        Interpreter inte = new Interpreter((String) options.valueOf("p"));
                        inte.rewriteFile();
                    } else { //This else condition execute the file, with the proper input/output files
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
                } else { //Error being thrown if the file type is invalid
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

    /**
     * This static method is called in the main when no flags are written.
     * It shows a man-like message, allowing the user to know how to use
     * the program.
     */
    private static void print_empty_message() {
        System.out.println("usage: bfck [-p <filename>] [-i <filename>] [-o <filename>]\n"
                + "            [--rewrite] [--translate] [--check]\n"
                + "BRAINFUCK [Mül93] is a programming language created in 1993 by Urban Müller,"
                + " and notable for its extreme minimalism.\nThis is the BrainFuck interpreter made by the group PolyStirN,"
                + " composed of Joël CANCELA VAZ, Pierre RAINERO, Aghiles DZIRI and Tanguy INVERNIZZI.");
    }

    /**
     * This static method is called to check the type of the file.
     * Our program only handle .bf and .bmp files.
     */
    private static boolean check_file_type(String filename) {
        return (filename.matches("(.*).bf") || filename.matches("(.*).bmp"));
    }
}
