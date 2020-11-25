package classes.sorts;

public abstract class Sort {
	protected int manaConsomme;
	
	public Sort(int manaConsomme){
		this.manaConsomme = manaConsomme;
	}
	
	public int getManaConsomme() {
		return this.manaConsomme;
	}
}
