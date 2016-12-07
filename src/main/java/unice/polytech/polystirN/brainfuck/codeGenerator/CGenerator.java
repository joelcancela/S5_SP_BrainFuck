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
     *
     * @throws SyntaxErrorException if an invalid symbol or keyword is encountered
     */
    public void generateFile() throws Exception {
        FileWriter outputFile = new FileWriter("bfToC.c", false);
        int p = 0;
        String keyword;

        outputFile.write("#include <stdio.h>\n\n" +
                "int\t\tmain(void) {\n" +
                "\tint\t\tc[30000] = {};\n" +
                "\tint\t\tp = 0;\n\n");

        while (reader.hasNext()) {
            keyword = reader.next();
            if (!(keyword.equals("\n") || keyword.equals("\r") || keyword.equals("#") || keyword.equals("\t") || keyword.equals(" "))) {
                Operator op = getFactory().getInstruction(keyword);
                if (op == null)
                    throw new SyntaxErrorException("Incorrect word operator");
                if (keyword.equals("INCR") || keyword.equals("+") || keyword.equals("#QQCHOSE"))
                    outputFile.write("\tc[p] = c[p] + 1;\n");
                if (keyword.equals("DECR") || keyword.equals("-") || keyword.equals("#QQCHOSE"))
                    outputFile.write("\tc[p] = c[p] - 1;\n");
                if (keyword.equals("RIGHT") || keyword.equals(">") || keyword.equals("#QQCHOSE")) {
                    outputFile.write("\tp = p + 1;\n");
                    p++;
                }
                if (keyword.equals("LEFT") || keyword.equals("<") || keyword.equals("#QQCHOSE")) {
                    outputFile.write("\tp = p - 1;\n");
                    p--;
                }
                if (keyword.equals("IN") || keyword.equals(",") || keyword.equals("#QQCHOSE"))
                    outputFile.write("\tc["+ p + "] = getchar();\n");
                if (keyword.equals("OUT") || keyword.equals(".") || keyword.equals("#QQCHOSE"))
                    outputFile.write("\tprintf(\"%c\", c[" + p + "]);\n");
                if (keyword.equals("JUMP") || keyword.equals("[") || keyword.equals("#FF7F00"))
                    outputFile.write("\t#to implement\n");
                if (keyword.equals("BACK") || keyword.equals("]") || keyword.equals("#FF0000"))
                    outputFile.write("\t#to implement\n");
            }
            else if (keyword.equals("#")) {
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
