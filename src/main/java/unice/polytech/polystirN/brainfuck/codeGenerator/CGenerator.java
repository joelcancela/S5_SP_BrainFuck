package unice.polytech.polystirN.brainfuck.codeGenerator;

import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;
import unice.polytech.polystirN.brainfuck.interpreter.ImageReader;
import unice.polytech.polystirN.brainfuck.interpreter.InstructionFactory;
import unice.polytech.polystirN.brainfuck.interpreter.Reader;
import unice.polytech.polystirN.brainfuck.interpreter.TextReader;
import unice.polytech.polystirN.brainfuck.language.Operator;
import unice.polytech.polystirN.brainfuck.language.Procedure;

import java.io.FileWriter;

public class CGenerator {

    private InstructionFactory factory;
    private Reader reader;
    private String programName;
    private String filename;

    public CGenerator(String filename) throws Exception {
        factory = new InstructionFactory();
        this.filename = filename;
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
        int         consecutive = 1;
        String      keyword;
        Operator    lastOp = null;
        String      header = "#include <stdio.h>\n\n";
        String      main = "int\t\tmain(void)\n{\n" +
                "\tint\t\tc[30000] = {};\n" +
                "\tint\t\tp = 0;\n\n";

        while (reader.hasNext()) {
            keyword = reader.next();
            if (keyword.charAt(0) == '?') {
                keyword = keyword.substring(1, keyword.length());
                header = header + CreateProcedure(((Procedure)factory.getInstruction(keyword)));
                keyword = " ";
            }
            if (!(keyword.equals("\n") || keyword.equals("\r") || keyword.equals("\t") || keyword.equals(" ") || keyword.equals("#") || keyword.equals(""))) {
                Operator op = getFactory().getInstruction(keyword.trim());
                if (op == null) {
                    throw new SyntaxErrorException("Incorrect word operator");
                }

                if (lastOp != null) {
                    if (lastOp.toString().equals("]")) {
                        indentLevel--;
                    }
                    if (!(op.toString().equals(lastOp.toString()))) {
                        main = main + lastOp.generateC(indentLevel, consecutive);
                        consecutive = 1;
                    } else
                        consecutive++;
                    if (lastOp.toString().equals("["))
                        indentLevel++;
                }
                lastOp = op;

        } else if (keyword.equals("#")) {
            keyword = reader.next();
            while (reader.hasNext() && (!(keyword.equals("\n")) || (keyword.equals("\r")))) {
                keyword = reader.next();
            }
        }
    }
        outputFile.write(header + main);
        if (lastOp != null) {
            outputFile.write(lastOp.generateC(indentLevel, consecutive));
        }

        outputFile.write("\treturn (0);\n}");
        outputFile.close();
}

    private String CreateProcedure(Procedure instructions) throws Exception {
        String procedure = "void\t";

        procedure = procedure + instructions.getName() + "(int *s) \n" +
                "{\n" +
                "\ts = calloc(s);\n" +
                "\tfor (int i = 0; i < 3000; i++)\n" +
                "\t\tc[i] = s[i];\n";

        String instruction;
        Operator lastOp = null;
        int indentLevel = 1;
        int consecutive = 1;

        instruction = instructions.readInstruction();
        while(!instruction.equals("")) {
            if (!(instruction.equals("\n") || instruction.equals("\r") || instruction.equals("\t") || instruction.equals(" ") || instruction.equals("#") || instruction.equals(""))) {
                Operator op = getFactory().getInstruction(instruction.trim());
                if (op == null) {
                    procedure = procedure + instruction + ";\n";
                }
                else if (lastOp != null) {
                    if (lastOp.toString().equals("]")) {
                        indentLevel--;
                    }
                    if (!(op.toString().equals(lastOp.toString()))) {
                        procedure = procedure + lastOp.generateC(indentLevel, consecutive);
                        consecutive = 1;
                    } else
                        consecutive++;
                    if (lastOp.toString().equals("["))
                        indentLevel++;
                }
                lastOp = op;

            } else if (instruction.equals("#")) {
                instruction = reader.next();
                while (reader.hasNext() && (!(instruction.equals("\n")) || (instruction.equals("\r")))) {
                    instruction = reader.next();
                }
            }

            instructions.incI();
            instruction = instructions.readInstruction();
        }

        procedure = procedure + "\n" + "}\n\n";
        return procedure;
    }

    public InstructionFactory getFactory() {
        return factory;
    }
}
