package classes.personnages;

import classes.sorts.Sort;

import java.util.ArrayList;

public abstract class Personnage {
	protected int pointsDeVieRestants;
	protected int pointsDeVieMax;
	protected int pointsDeMana;
	protected int niveau;
	protected ArrayList<Sort> listeSorts;
	
	public Personnage(int pointsDeVieMax, int pointsDeMana, int niveau) {
		this.pointsDeVieMax = pointsDeVieMax;
		this.pointsDeVieRestants = pointsDeVieMax;
		this.pointsDeMana = pointsDeMana;
		this.niveau = niveau;
		this.listeSorts = new ArrayList<>();
	}
	
	
	public abstract void infligerDegats(Personnage adversaire);
	public abstract void recevoirDegats(int value);
	
	
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
}
