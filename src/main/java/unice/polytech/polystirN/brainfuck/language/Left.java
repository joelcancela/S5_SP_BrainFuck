package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * Class Left
 *
 * @author Joël CANCELA VAZ and Pierre Rainero
 * @author Tanguy Invernizzi and Aghiles Dziri
 */

public class Left implements Operator {

    /**
     * Default constructor
     */
    public Left() {

    }

    /**
     * Move the pointer of the current memory cell by one to the left
     *
     * @param p pointer of the current memory cell
     * @param M all memory cells
     * @return true if the current memory cell was shifted by one to the left
     */
    public boolean doOperation(int p, byte[] M) throws Exception {
        //Cas d'anomalie :
        if (p == 0)
            throw new Exception("p = 0 -1 | Can't move to the left");
        if (p > 29999)
            throw new Exception("p > 30000 | Wrong memory");

        //Cas nominal :
        Interpreter.getInstance().p--;
        return true;
    }

}
