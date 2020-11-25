package classes.personnages;

import classes.sorts.Sort;

import java.util.ArrayList;

public abstract class Personnage {
	protected int pointsDeVieRestants;
	protected int pointsDeVieMax;
	protected int pointsDeMana;
	protected int niveau;

	
	public Personnage(int pointsDeVieMax, int pointsDeMana, int niveau) {
		this.pointsDeVieMax = pointsDeVieMax;
		this.pointsDeVieRestants = pointsDeVieMax;
		this.pointsDeMana = pointsDeMana;
		this.niveau = niveau;

	}
	
	
	public abstract void infligerDegats(Personnage adversaire);
	public void recevoirDegats(int value){
		this.diminuerPv(value);
	}
	
	
	//========= GESTION DE LA VIE =============
	public void diminuerPv(int value) {
		this.pointsDeVieRestants -= value;
		if (this.pointsDeVieRestants <= 0) {
			this.pointsDeVieRestants = 0;
		}
	}
	
	public void augmenterPv(int value) {
		this.pointsDeVieRestants += value;
	}
	
	
	
	
	//=========== GETTERS AND SETTERS ============
	
	
	public int getPointsDeVieRestants() {
		return pointsDeVieRestants;
	}
	public int getPointsDeVieMax() { return pointsDeVieMax; }
	public void setPointsDeVieRestants(int pointsDeVieRestants) {
		this.pointsDeVieRestants = pointsDeVieRestants;
	}
	public int getPointsDeMana() {
		return pointsDeMana;
	}
	public void setPointsDeMana(int pointsDeMana) {
		this.pointsDeMana = pointsDeMana;
	}
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	public boolean isKo(){ return (this.pv <= 0); }
}
