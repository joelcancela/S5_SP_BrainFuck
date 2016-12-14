package unice.polytech.polystirN.brainfuck.codeGenerator;

import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.interpreter.ImageReader;
import unice.polytech.polystirN.brainfuck.interpreter.InstructionFactory;
import unice.polytech.polystirN.brainfuck.interpreter.Reader;
import unice.polytech.polystirN.brainfuck.interpreter.TextReader;
import unice.polytech.polystirN.brainfuck.language.Operator;

import java.io.FileWriter;

public class CGenerator {

    private InstructionFactory factory;
    private Reader reader;
    private String programName;

    public CGenerator(String filename) throws Exception {
        factory = new InstructionFactory();
        if (filename.matches("(.*).bf")) {
            programName = filename.substring(0, filename.length() - 3);
            reader = new TextReader(filename,this);
        } else if (filename.matches("(.*).bmp")) {
            programName = filename.substring(0, filename.length() - 4);
            reader = new ImageReader(filename);
        }
    }
    /**
     * Generate a C file
     */
    public void generateFile() throws Exception {
        FileWriter  outputFile = new FileWriter(programName + ".c", false);
        int         indentLevel = 1;
        String      indentation = "";
        String      keyword;

        outputFile.write("#include <stdio.h>\n\n" +
                "int\t\tmain(void)\n{\n" +
                "\tint\t\tc[30000] = {};\n" +
                "\tint\t\tp = 0;\n\n");

        while (reader.hasNext()) {
            keyword = reader.next();
            if (!(keyword.equals("\n") || keyword.equals("\r") || keyword.equals("\t") || keyword.equals(" ") || keyword.equals("#") || keyword.equals(""))) {
                Operator op = getFactory().getInstruction(keyword.trim());
                if (op == null) {
                    throw new SyntaxErrorException("Incorrect word operator");
                }
                else if (op.toString().equals("]"))
                    indentLevel--;
                outputFile.write(op.generateC(indentLevel));
                if (op.toString().equals("["))
                    indentLevel++;
            } else if (keyword.equals("#")) {
                keyword = reader.next();
                while (reader.hasNext() && (!(keyword.equals("\n")) || (keyword.equals("\r")))) {
                    keyword = reader.next();
                }
            }
        }

        outputFile.write("\treturn (0);\n}");
        outputFile.close();
    }


    public InstructionFactory getFactory() {
        return factory;
    }
}
