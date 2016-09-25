package unice.polytech.polystirN.brainfuck.language;

/**
 * Class Increment
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Increment implements Operator {
    /**
     * methode doOperation increment the current case of memory
     * and @return boolean if the case is incremented else return an exception
     */
    public boolean doOperation(int p, byte[] M) throws Exception {
        /**
         * overflow or not
         */
        if ((((float) ((M[p]) & 0x00FF)) / 255) == 255){
            throw new Exception("Memory overflow error");
        }
        M[p]++;
        return true;
    }
}

	
