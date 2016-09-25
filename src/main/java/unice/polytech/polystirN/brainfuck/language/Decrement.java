package unice.polytech.polystirN.brainfuck.language;

/**
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Decrement implements Operator {
    public boolean doOperation(int p, byte[] M) throws Exception {

        /**
         * Test overflow
         */
        if (((int) M[p]) == 0) {
            throw new Exception("Memory underflow error");//TODO
        }
        M[p]--;
        return true;
    }
}

