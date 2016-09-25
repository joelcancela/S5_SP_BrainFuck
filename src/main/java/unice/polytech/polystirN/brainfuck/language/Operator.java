package unice.polytech.polystirN.brainfuck.language;

public interface Operator {
    public boolean doOperation(int p, byte[] M) throws Exception;
}
