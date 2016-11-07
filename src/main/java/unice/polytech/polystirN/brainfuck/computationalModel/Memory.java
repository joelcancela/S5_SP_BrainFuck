package unice.polytech.polystirN.brainfuck.computationalModel;

/**
 * TODO maybe transform into an ArrayList - see diff with metrics
 * Class that represents the memory
 * It also uses the pointer which is the index
 * of the current memory cell used
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Memory {
    private byte[] cells; //Cells are all initialized to 0 (-128 in unsigned byte)
    private int p; //Pointer to the current memory cell used
    public final static int size = 30000;

    /**
     * Memory constructor
     */
    public Memory() {
        p = 0;
        cells = new byte[size];
    }

    /**
     * Gets the current memory state
     *
     * @return cells being all the data stored in memory
     */
    public byte[] getCells() {
        return cells;
    }

    /**
     * Gets the current memory cell index
     *
     * @return p being the pointer
     */
    public int getP() {
        return p;
    }

    /**
     * Sets a new value to the memory cell index
     *
     * @param nP new value for the pointer p
     */
    public void setP(int nP) {
        p = nP;
    }

}
