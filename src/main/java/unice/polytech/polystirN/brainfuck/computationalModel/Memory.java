package unice.polytech.polystirN.brainfuck.computationalModel;

import java.util.ArrayList;
import java.util.List;

/**
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
    private int lastInstancedCell;
    //private List<Byte> cells;

    /**
     * Memory constructor
     */
    public Memory() {
        p = 0;
        cells = new byte[size];
        //cells = new ArrayList<Byte>();
        //cells.add((byte)-127);
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
    /*public List<Byte> getCells(){
    	return cells;
    }*/

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
    	lastInstancedCell=Math.max(lastInstancedCell, nP);
    	/*if(lastInstancedCell<nP)
    		cells.add((byte)-127);*/
        p = nP;
    }
    
    public String toString(){
    	String returnValue = "[";
    	for(int i=0;i<=lastInstancedCell;i++){
    		if(i==lastInstancedCell)
    			returnValue+=(cells[i]& 0xFF);
    		else
    			returnValue+=(cells[i]& 0xFF) + ", ";
    	}
    	returnValue+="]\n";
    	return returnValue;
    }

}
