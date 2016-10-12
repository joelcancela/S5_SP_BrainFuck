package unice.polytech.polystirN.brainfuck.interpreter;

import java.io.IOException;

public abstract class Reader {
	
	public abstract boolean hasNext() throws Exception;
	public abstract String next() throws Exception;
	public abstract Object getBuffer();
}
