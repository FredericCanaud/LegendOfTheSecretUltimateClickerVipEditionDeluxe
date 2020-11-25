package classes.personnages;

public abstract class Personnage {
	protected int pointsDeVieRestants;
	protected int pointsDeVieMax;
	protected int pointsDeManaMax;
	protected int pointsDeManaRestants;
	protected int niveau;
	
	public Personnage(int pointsDeVieMax, int pointsDeMana, int niveau) {
		this.pointsDeVieMax = pointsDeVieMax;
		this.pointsDeVieRestants = pointsDeVieMax;
		this.pointsDeManaMax = pointsDeMana;
		this.pointsDeManaRestants = pointsDeMana;
		this.niveau = niveau;
	}
	
	public abstract void infligerDegats(Personnage adversaire);
	public void recevoirDegats(int value){
		this.diminuerPv(value);
	}
	
	
	//========= GESTION DE LA VIE =============
	public void diminuerPv(int value) {
		if(value > 0){
			this.pointsDeVieRestants = Math.max(this.pointsDeVieRestants - value, 0);
		}
	}

	public void augmenterPv(int value) {
		if(value > 0){
			this.pointsDeVieRestants = Math.min(this.pointsDeVieRestants + value, this.pointsDeVieRestants);
		}
	}
	
	//=========== GETTERS AND SETTERS ============

	public int getPointsDeVieRestants() {
		return pointsDeVieRestants;
	}

	public int getPointsDeVieMax() { return pointsDeVieMax; }

	public void setPointsDeVieRestants(int pointsDeVieRestants) {
		this.pointsDeVieRestants = pointsDeVieRestants;
	}

	public int getPointsDeManaMax() {
		return pointsDeManaMax;
	}

	public void setPointsDeManaMax(int pointsDeManaMax) {
		this.pointsDeManaMax = pointsDeManaMax;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	public boolean isKo(){ return (this.pv <= 0); }
    
	public int getPointsDeManaRestants() {
		return pointsDeManaRestants;
	}
}
