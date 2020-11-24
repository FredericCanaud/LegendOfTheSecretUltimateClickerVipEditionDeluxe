package classes;

public abstract class Fleche extends Arme implements InfligeDegats{
	protected int degats;
	
	public Fleche (int degats){
		super("flèche");
		this.degats = degats;
	}
	
	public int getDegats() {
		return this.degats;
	}
	
	public String getClassName() {
		return this.getClass().getName();
	}
	
	public static Fleche getNewFleche(String nomFleche) {
		switch(nomFleche) {
			case "FlecheNormale":
				return new FlecheNormale();
			case "FlecheDeFeu":
				return new FlecheDeFeu();
			case "FlecheDeGlace":
				return new FlecheDeGlace();
			default:
				return new FlecheBizarre();
		}
			
	}
}
