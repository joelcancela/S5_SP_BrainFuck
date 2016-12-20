package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

import java.io.FileWriter;

/**
 * Class used to specify the Out operator behaviour
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Out implements Operator {

    private String filename;

    /**
     * Out constructor
     *
     * @param filename is where out is supposed to write, or null if the operator
     *                 is supposed to write on the standard output.
     */
    public Out(String filename) {
        this.filename = filename;
    }

    /**
     * Out constructor without filename, uses standard output instead (console)
     */
    public Out() {
        this(null);
    }

    /**
     * Write the current cell memory content as an ASCII character, either on the standard output or in a file.
     *
     * @param interpreter is the current instance of interpreter
     */
    @Override
    public void execute(Interpreter interpreter) throws Exception {

        if (filename == null) {
            System.out.print((char) (interpreter.getMemory().getCells()[interpreter.getMemory().getP()] & 0xFF));
        } else {
            FileWriter outputFile = new FileWriter(filename, true);
            outputFile.write(((char) (interpreter.getMemory().getCells()[interpreter.getMemory().getP()] & 0xFF)));
            outputFile.close();
        }
        interpreter.getMetrics().incrementDataRead();
    }

    @Override
    public String toString() {
        return ".";
    }
}