package classes.arme;

import classes.arme.Arme;

public class Arc extends Arme {
	private int multiplicateur;
	
	public Arc(int multiplicateur){
		super("arc");
		this.multiplicateur = multiplicateur;
	}
	
	public int getMultiplicateur() {
		return this.multiplicateur;
	}	
	
}
