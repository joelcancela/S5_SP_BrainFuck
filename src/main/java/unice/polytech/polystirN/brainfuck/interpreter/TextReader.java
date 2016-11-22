package unice.polytech.polystirN.brainfuck.interpreter;

import unice.polytech.polystirN.brainfuck.exceptions.SyntaxErrorException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class used to read texts and translate them into instructions
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
class TextReader extends Reader {
    private BufferedReader buffer;
    private InstructionFactory factory = new InstructionFactory();

    /**
     * TextReader constructor
     *
     * @param filename is the name of the file to be read
     */

    TextReader(String filename) throws Exception {
        buffer = new BufferedReader(new FileReader(filename));
        buffer = new BufferedReader(new FileReader(macrosRead()));
    }

    /**
     * Checks if there is another character to read
     *
     * @return true if the current character is not the last, else false
     */
    @Override
    public boolean hasNext() throws Exception {
        boolean returnValue = false;

        buffer.mark(1);
        if (buffer.read() != -1)
            returnValue = true;
        buffer.reset();

        return returnValue;
    }

    /**
     * Reads the next character or keyword and translates it into an instruction
     *
     * @return String being the instruction
     */
    @Override
    public String next() throws Exception {
        int c;
        String keyword = "";


        c = buffer.read();

        if (('A' <= c && 'Z' >= c)) {
            while ((char) c != '\r' && (char) c != '\n' && c != -1) {//c!=1 required because we read in the buffer
                keyword += ((char) c);
                c = buffer.read();
            }
        } else {
            keyword = Character.toString((char) c);
        }

        return keyword;

    }

    /**
     * Replace the macros by their equivalent in the file which will be executed
     *
     * @return name of file which will be executed
     */
    private String macrosRead() throws Exception {
        int c = buffer.read();
        c = readDefineMacros(c);
        return MacroTransform(c);
    }

    /**
     * @throws IOException
     * @throws SyntaxErrorException
     */
    private String MacroTransform(int c) throws IOException, SyntaxErrorException {
        String keyword = "", fichier = "./fichierTampon.bf", keyword1 = "";
        FileWriter fichierTampon = new FileWriter(fichier);
        while (c != -1) {
            keyword = "";
            keyword1 = "";
            if (('A' <= c && 'Z' >= c) || ('a' <= c && 'z' >= c)) {
                while ((char) c != '\r' && (char) c != '\n' && c != -1) {//c!=1 required because we read in the buffer
                    keyword += ((char) c);
                    c = buffer.read();
                }
                String tab[] = keyword.split(" ");
                if (tab.length == 2) {
                    if (isInt(tab[1])) {
                        keyword1 = tab[1];
                        keyword = tab[0];
                    }
                }
            }
            if (c == '#') {
                keyword = Character.toString((char) c) + buffer.readLine();
            }
            if (c == '$') {
                keyword = Character.toString((char) c) + buffer.readLine();
            }

            if (factory.getEquivalentInstruction(Character.toString((char) c)) != null) {
                fichierTampon.write(System.getProperty("line.separator") + factory.getEquivalentInstruction(Character.toString((char) c)).trim());
            } else {
                if ((short) c != -1 && keyword.equals(""))
                    fichierTampon.write(System.getProperty("line.separator") + Character.toString((char) c).trim());
            }
            if (!keyword1.trim().equals("")) {
                if (isInt(keyword1.trim())) {
                    int att = Integer.parseInt(keyword1.trim());
                    if (factory.getMapMacrosParam().get(keyword.trim()) == null)
                        fichierTampon.write(System.getProperty("line.separator") + keyword.trim() + " " + keyword1);
                    else for (int j = 0; j < att; j++) {
                        fichierTampon.write(System.getProperty("line.separator") + factory.getMapMacrosParam().get(keyword.trim()).trim());
                    }
                }
            } else {
                if (factory.getEquivalentInstruction(keyword.trim()) != null)
                    fichierTampon.write(System.getProperty("line.separator") + factory.getEquivalentInstruction(keyword.trim()).trim());
                else fichierTampon.write(System.getProperty("line.separator") + rewriteMul(keyword.trim()));
            }
            c = buffer.read();
        }
        fichierTampon.close();
        return fichier;
    }

    /**
     * Stock the equivalent of the macros to be able to use them after
     *
     * @throws Exception
     */
    private int readDefineMacros(int c) throws Exception {
        String word = "";
        char charOfM = '\0';
        String macros;
        String define = "DEFINE";
        int i;

        while (c != -1 && (c == '$' || c == ' ' || c == '\n' || c == '\r')) {
            word = "";
            charOfM = '\0';
            if ((char) c == '$') {
                macros = buffer.readLine();
                if (macros.length() <= define.length())
                    throw new SyntaxErrorException("$DEFINE <your word> <instructions>");
                if (!macros.substring(0, define.length()).equals(define))
                    throw new SyntaxErrorException("$DEFINE <your word> <instructions>");
                else {
                    if (macros.trim().split(" ").length < 2)
                        throw new SyntaxErrorException("$DEFINE <your word> <instructions>");
                    macros = macros.substring(define.length(), macros.trim().length()).trim();
                    for (i = 0; i < macros.trim().length() && charOfM != ' '; i++) {
                        word += macros.trim().charAt(i);
                        if (i < macros.trim().length() - 1)
                            charOfM = macros.trim().charAt(i + 1);

                    }
                    if (macros.trim().substring(word.trim().length(), macros.trim().length()).contains("\\")) {
                        String tableau[] = macros.trim().substring(word.trim().length(), macros.trim().length()).trim().replace("\\", "\"").split("\"");
                        macros = "";
                        for (int j = 0; j < tableau.length; j++) {
                            if (factory.getEquivalentInstruction(tableau[j].trim()) != null)
                                macros += System.lineSeparator() + rewriteMul(factory.getEquivalentInstruction(tableau[j].trim()));
                            else if (factory.getMapMacrosParam().get(rewriteMul(tableau[j].trim())) != null)
                                macros += System.lineSeparator() + rewriteMul(factory.getMapMacrosParam().get(rewriteMul(tableau[j].trim())));
                            else macros += System.lineSeparator() + rewriteMul(tableau[j].trim());


                        }

                    } else {
                        macros = macros.trim().substring(word.trim().length(), macros.trim().length()).trim();
                    }
                    if (word.trim().length() > 2) {
                        if (word.trim().substring(word.trim().length() - 2, word.trim().length()).equals("()"))
                            if (factory.getMapMacrosParam().get(macros) != null)
                                factory.getMapMacrosParam().put(word.trim().substring(0, word.trim().length() - 2).trim(), rewriteMul(factory.getMapMacrosParam().get(macros)));
                            else if (factory.getEquivalentInstruction(macros) != null)
                                factory.getMapMacrosParam().put(word.trim().substring(0, word.trim().length() - 2).trim(), rewriteMul(factory.getEquivalentInstruction(macros)));
                            else
                                factory.getMapMacrosParam().put(word.trim().substring(0, word.trim().length() - 2).trim(), rewriteMul(macros));
                    }
                    if (factory.getEquivalentInstruction(macros) == null)
                        factory.put(word.trim(), rewriteMul(macros));
                    else {
                        factory.put(word.trim(), rewriteMul(factory.getEquivalentInstruction(macros)));

                    }
                }
            }
            c = buffer.read();

        }
        return c;
    }

    /**
     * rewrite the equivalent of to_digit and multi_decr in the string
     *
     * @param word
     * @return String
     */
    private String rewriteMul(String word) {
        String tab[] = word.split(" ");

        if (tab[0].trim().equals("MULTI_DECR")) {
            if (tab.length == 1) return word;
            int i = Integer.parseInt(tab[1]);
            String S = "";
            for (int j = 0; j < i; j++) {
                S += "-";
            }
            return S;
        } else if (word.trim().equals("TO_DIGIT")) {
            String S = "";
            for (int j = 0; j < 48; j++) {
                S += "-";
            }
            return S;
        }

        return word;
    }

    /**
     * verify if a string is an integer
     *
     * @param chaine
     * @return boolean
     */
    private static boolean isInt(String chaine) {
        boolean valeur = true;
        char[] tab = chaine.toCharArray();

        for (char carac : tab) {
            if (!Character.isDigit(carac) && valeur) {
                valeur = false;
            }
        }

        return valeur;
    }
}

