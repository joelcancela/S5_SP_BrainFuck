package unice.polytech.polystirN.brainfuck.computationalModel;

/**
 * Class that represents the memory
 * It also uses the pointer which is the index
 * of the current memory cell used
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Memory {
    private byte[] cells; //Cells of the memory
    private int p; //Pointer to the current memory cell used
    public final static int size = 30000; //Size of the memory
    private int lastInstancedCell; //Index of the last instanced cell

    /**
     * Memory constructor
     */
    public Memory() {
        p = 0;
        cells = new byte[size];
        lastInstancedCell = 0;
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
        lastInstancedCell = Math.max(lastInstancedCell, nP);
        p = nP;
    }

    /**
     * Prints a snapshot of the memory
     *
     * @return a string being the display of the cells of the memory and their content
     */
    public String toString() {
        String returnValue = "[";
        for (int i = 0; i <= lastInstancedCell; i++) {
            if (i == lastInstancedCell)
                returnValue += (cells[i] & 0xFF);
            else
                returnValue += (cells[i] & 0xFF) + ", ";
        }
        returnValue += "]\n";
        return returnValue;
    }

}
