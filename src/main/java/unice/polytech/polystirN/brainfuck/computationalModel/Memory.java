package unice.polytech.polystirN.brainfuck.computationalModel;

/**
 * Class that represents the memory
 * It also use the pointer which is the index
 * of the current memory cell used
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Memory {
    private byte[] cells = {-128};
    private int p;

    public Memory() {
        p = 0;
        cells = new byte[30000];
    }

    /**
     * Get the current memory.
     *
     * @return The current state of the memory.
     */
    public byte[] getCells() {
        return cells;
    }

    /**
     * Get the current memory cell index
     *
     * @return Which memory cell is selected.
     */
    public int getP() {
        return p;
    }

    /**
     * Set the current memory cell index.
     *
     * @param nP New value of p.
     */
    public void setP(int nP) {
        p = nP;
    }

}
