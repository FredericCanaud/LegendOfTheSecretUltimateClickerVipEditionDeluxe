package classes.personnages;

import classes.sorts.Sort;

import java.util.ArrayList;

public abstract class Personnage {
	protected int pv;
	protected int pm;
	protected int lvl;
	protected ArrayList<Sort> listeSorts;
	
	public Personnage(int pv, int pm, int lvl) {
		this.pv = pv;
		this.pm = pm;
		this.lvl = lvl;
		this.listeSorts = new ArrayList<>();
	}
	
	
	public abstract void infligerDegats(Personnage adversaire);
	public abstract void recevoirDegats(int value);
	
	
	//========= GESTION DE LA VIE =============
	public void diminuerPv(int value) {
		this.pv -= value;
		if (this.pv <= 0) {
			this.pv = 0;
		}
	}
	
	public void augmenterPv(int value) {
		this.pv += value;
	}
	
	
	
	
	//=========== GETTERS AND SETTERS ============
	
	
	public int getPv() {
		return pv;
	}
	public void setPv(int pv) {
		this.pv = pv;
	}
	public int getPm() {
		return pm;
	}
	public void setPm(int pm) {
		this.pm = pm;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
}
