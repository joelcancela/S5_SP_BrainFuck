import joptsimple.OptionParser;
import joptsimple.OptionSet;
import unice.polytech.polystirN.brainfuck.exceptions.IncorrectFileTypeException;
import unice.polytech.polystirN.brainfuck.interpreter.ImageWriter;
import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;
import unice.polytech.polystriN.brainfuck.debug.Trace;

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
        Trace trace = null;

        //First, we got to configure the parser.
        OptionParser parser = new OptionParser("p:i:o:"); //: after a character means that an argument is mandatory for this flag.
        parser.accepts("check");
        parser.accepts("rewrite");
        parser.accepts("translate");
        parser.accepts("trace");
        //here we add the longs options.

        OptionSet options = parser.parse(args); //Handle the args of the command line with the options.

        if (args.length == 0) {
            printEmptyMessage(); //Show a man-like message if no options have been given.
        }
        if (options.has("p")) { //Is there a file ?
            Interpreter intrptr = null;
            try {
                if (checkFileType((String) options.valueOf("p"))) { //Is the type of the file valid ?
                    if (options.has("check")) { //Do we need to check it ?
                        intrptr = new Interpreter((String) options.valueOf("p"));
                        intrptr.check();
                    } else if (options.has("rewrite")) { //Do we need to rewrite it ?
                        intrptr = new Interpreter((String) options.valueOf("p"));
                        intrptr.rewriteFile();
                    } else if (options.has("translate")) {
                        ImageWriter iw = new ImageWriter((String) options.valueOf("p"));
                        iw.translate();
                    } else if (options.has("trace")) {
                        trace = new Trace();
                        intrptr = new Interpreter((String) options.valueOf("p"), trace);
                        intrptr.interpretFile();
                    } else { //This else condition execute the file, with the proper input/output files
                        if (options.has("i") && options.has("o")) {
                            intrptr = new Interpreter((String) options.valueOf("p"), (String) options.valueOf("i"), (String) options.valueOf("o"));
                            intrptr.interpretFile();
                        } else if (options.has("i")) {
                            intrptr = new Interpreter((String) options.valueOf("p"), (String) options.valueOf("i"), null);
                            intrptr.interpretFile();
                        } else if (options.has("o")) {
                            intrptr = new Interpreter((String) options.valueOf("p"), null, (String) options.valueOf("o"));
                            intrptr.interpretFile();
                        } else {
                            intrptr = new Interpreter((String) options.valueOf("p"));
                            intrptr.interpretFile();
                        }
                        System.out.println(intrptr.getMetrics().toString());
                    }
                } else { //Error being thrown if the file type is invalid
                    throw new IncorrectFileTypeException(options.valueOf("p") + " must have .bf or .bmp extension");
                }
            } catch (Exception e) {
                if (intrptr != null && intrptr.isTrace())
                    if (trace != null && trace.isOpen()) {
                        e.printStackTrace(trace.getPrintWriter());
                        trace.closePrint();
                        trace.close();
                    }
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
                        break;
                    case "IncorrectFileTypeException":
                        System.exit(3);
                        break;
                    case "FileNotFoundException":
                        System.exit(3);
                        break;
                    case "BadLoopException":
                        System.exit(4);
                        break;
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
    private static void printEmptyMessage() {
        System.out.println("usage: bfck [-p <filename>] [-i <filename>] [-o <filename>]\n"
                + "            [--rewrite] [--translate] [--check]\n"
                + "BRAINFUCK [M\u00fcl93] is a programming language created in 1993 by Urban M\u00fcller,"
                + " and notable for its extreme minimalism.\nThis is the BrainFuck interpreter made by the group PolyStirN,"
                + " composed of Jo\u00ebl CANCELA VAZ, Pierre RAINERO, Aghiles DZIRI and Tanguy INVERNIZZI.");
    }

    /**
     * This static method is called to check the type of the file.
     * Our program only handle .bf and .bmp files.
     */
    private static boolean checkFileType(String filename) {
        return (filename.matches("(.*).bf") || filename.matches("(.*).bmp"));
    }
}
