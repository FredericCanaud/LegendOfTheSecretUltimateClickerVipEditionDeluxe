package classes.arme;

public abstract class Fleche extends Arme implements InfligeDegats {
	protected int degats;
	
	public Fleche (int degats){
		super("flèche");
		this.degats = degats;
	}
	
	public int getDegats() {
		return this.degats;
	}
	
	public static Fleche setNewFleche(String nomFleche) {
		return switch (nomFleche) {
			case "FlecheNormale" -> new FlecheNormale();
			case "FlecheDeFeu" -> new FlecheDeFeu();
			case "FlecheDeGlace" -> new FlecheDeGlace();
			default -> new FlecheBizarre();
		};
	}

	public String getType(){
		return this.getClass().getSimpleName();
	}
}
