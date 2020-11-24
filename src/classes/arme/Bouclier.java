package classes.arme;

public class Bouclier extends Arme {
	private int resistance;
	
	public Bouclier(int resistance){
		super("bouclier");
		this.resistance = resistance;
	}
	
	public int getResistance() {
		return this.resistance;
	}
}
