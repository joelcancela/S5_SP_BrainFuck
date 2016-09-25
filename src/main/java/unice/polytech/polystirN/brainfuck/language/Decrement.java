package unice.polytech.polystirN.brainfuck.language;

/**
 * Created by Joel CANCELA VAZ on 25/09/2016.
 */
public class Decrement implements Operator{
    public boolean doOperation(int p,byte[] M) throws Exception{

        M[p]--;
        /**
         * overflow or not
         */
        if((((float)((M[p]) & 0x00FF))/255)==0) {
            throw new Exception("Memory overflow error");//TODO
        }
        return true;
    }
}

