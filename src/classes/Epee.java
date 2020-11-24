package classes;

public class Epee extends Arme implements InfligeDegats{
	private int degats;
	
	public Epee(int degats){
		super("épée");
		this.degats = degats;
	}

	@Override
	public int getDegats() {
		return this.degats;
	}
}
