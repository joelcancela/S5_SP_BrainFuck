package unice.polytech.polystirN.brainfuck.language;

import java.io.FileNotFoundException;

import unice.polytech.polystirN.brainfuck.interpreter.Interpreter;

/**
 * test of the virtual machine and increment methode
 * 
 * @author Joël CANCELA VAZ and Pierre Rainero
 * @author Tanguy Invernizzi and Aghiles Dziri
 */
public class TestIncrement {

	public static void main(String[] args) throws FileNotFoundException, Throwable {
		//cas 1 ou ca marche ==0
			Interpreter a=new Interpreter("./examples/empty.bf");
			a.readfile();
			a.affiche(0);
		//cas 2 ou ca marche ==255
			a=new Interpreter("./examples/empty2.bf");
			a.readfile();
			a.affiche(0);
		//cas 1 ou ca marche pas==256
			a=new Interpreter("./examples/empty1.bf");
			a.readfile();
			a.affiche(0);
		//cas 2 ou ca marche pas index==-1
			a=new Interpreter("./examples/empty1.bf");
			a.setP(-1);
			a.readfile();
	}
}






