package unice.polytech.polystriN.brainfuck.debug;

/**
 * Measure the different performances associated to the
 * implementation of the brainfuck program
 *
 * @author Joël CANCELA VAZ and Pierre RAINERO
 * @author Tanguy INVERNIZZI and Aghiles DZIRI
 */
public class Metrics {

    //metrics
    private long startTime = 0; // get the current system time, in milliseconds
    private long programSize = 0; // the number of instructions in the program
    private long execMove = 0; // the number of times the execution pointer was moved to execute this program
    private long dataMove = 0; // the number of time the data pointer was moved to execute this program
    private long dataWrite = 0; // the number of time the memory was accessed to change its contents while executing this program
    private long dataRead = 0; // the number of times the memory was accessed to read its contents

    public Metrics() {
        startTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return startTime;
    }

    public long getProgramSize() {
        return programSize;
    }

    public long getExecMove() {
        return execMove;
    }

    public long getDataMove() {
        return dataMove;
    }

    public long getDataWrite() {
        return dataWrite;
    }

    public long getDataRead() {
        return dataRead;
    }

    public void incrementDataMove() {
        dataMove++;
    }

    public void incrementExecMove() {
        execMove++;
    }

    public void incrementDataWrite() {
        dataWrite++;
    }

    public void incrementProgSize() {
        programSize++;
    }

    public void incrementDataRead() { dataRead++;}

    public String toString() {
        String str;

        return( "PROG_SIZE = " + programSize +
                "\nEXEC_TIME = " + (System.currentTimeMillis()- startTime) + " ms" +
                "\nEXEC_MOVE = " + execMove +
                "\nDATA_MOVE = " + dataMove +
                "\nDATA_WRITE = " + dataWrite +
                "\nDATA_READ = " + dataRead );
    }
}
