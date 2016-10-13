package unice.polytech.polystirN.brainfuck.interpreter;

/**
 * TODO Maybe implement Iterable
 * Abstract class for the input reader
 */
public abstract class Reader {

    public abstract boolean hasNext() throws Exception;

    public abstract String next() throws Exception;

    public abstract Object getBuffer();
}
