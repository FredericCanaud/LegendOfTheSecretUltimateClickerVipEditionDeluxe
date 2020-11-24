package classes.arme.sorts;

import classes.arme.InfligeDegats;

public abstract class SortA1 extends SortNV1 implements InfligeDegats {
	protected int degats;
	
	public SortA1(int degats, int manaConsomme){
		super(manaConsomme);
		this.degats = degats;
	}
	
	public int getDegats() {
		return this.degats;
	}
}
