package classes;

public abstract class SortA2 extends SortNV2 implements InfligeDegats{
	protected int degats;
		
	public SortA2(int degats, int manaConsomme){
		super(manaConsomme);
		this.degats = degats;
	}

	public int getDegats() {
		return this.degats;
	}
}
