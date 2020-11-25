package classes.arme;

public class Bouclier extends Arme {
	private int resistance;
	private boolean isHold;
	
	public Bouclier(int resistance){
		super("bouclier");
		this.resistance = resistance;
		this.isHold = false;
	}
	
	public int getResistance() {
		if (this.isHold){
			return this.resistance*2;
		}
		return this.resistance;
	}

	public void setHold(boolean bool){
		this.isHold = bool;
	}
}
