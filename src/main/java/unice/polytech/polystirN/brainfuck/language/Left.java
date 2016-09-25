package unice.polytech.polystirN.brainfuck.language;

import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * Class Left
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
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
        //Anomaly case :
        if (p == 0)
            throw new Exception("p = 0 -1 | Can't move to the left");
        if (p > 29999)
            throw new Exception("p > 30000 | Wrong memory");

        //Nominal case :
        Interpreter.setP(Interpreter.getP()-1);
        return true;
    }

}
