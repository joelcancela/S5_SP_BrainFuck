package unice.polytech.polystirN.brainfuck.computationalModel;

/**TODO Maybe transform into an ArrayList
 * Class that represents the memory
 * It also uses the pointer which is the index
 * of the current memory cell used
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Memory {
    private byte[] cells = {-128}; //Cells are all initialized to 0 (-128 in unsigned byte)
    private int p; //Pointer

    /**
     * Constructor of Memory
     */
    public Memory() {
        p = 0;
        cells = new byte[30000];
    }

    /**
     * Gets the current memory state
     *
     * @return cells being
     */
    public byte[] getCells() {
        return cells;
    }

    /**
     * Gets the current memory cell index
     *
     * @return Which memory cell is selected.
     */
    public int getP() {
        return p;
    }

    /**
     * Sets a new value to the memory cell index
     *
     * @param nP New value of p.
     */
    public void setP(int nP) {
        p = nP;
    }

}
