package unice.polytech.polystirN.brainfuck.language;

/**
 * Created by Joel CANCELA VAZ on 25/09/2016.
 */
public class Decrement implements Operator {
    public boolean doOperation(int p, byte[] M) throws Exception {

        M[p]--;
        /**
         * Test overflow
         */
        if (((float) M[p]) < 0) {
            throw new Exception("Memory underflow error");//TODO
        }
        return true;
    }
}

