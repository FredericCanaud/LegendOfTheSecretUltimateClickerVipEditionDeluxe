package classes.personnages;

import classes.arme.Arme;
import classes.arme.Bouclier;
import classes.arme.Epee;

import java.util.ArrayList;

public class Guerrier extends Personnage implements ManipulateurDArme {

	protected ArrayList<Arme> armeUtilisable;
	protected Epee epee;
	protected Bouclier bouclier;

	public Guerrier(int pv, int pm, int lvl) {
		super(pv, pm, lvl);
		this.armeUtilisable = new ArrayList<>();
		this.bouclier = null;
		this.epee = null;
	}

	public Guerrier(){
		super(50, 10, 1);
		this.armeUtilisable = new ArrayList<>();
		this.bouclier = null;
		this.epee = new Epee(10);
		this.armeUtilisable.add(epee);
	}

	public void addEpee(Epee epee) {
		this.armeUtilisable.add(epee);
	}

	public void addBouclier(Bouclier bouclier) {
		this.armeUtilisable.add(bouclier);
	}

	public void utiliser(Bouclier bouclier) {
		this.bouclier = bouclier;
	}

	public void utiliser(Epee epee) {
		this.epee = epee;
	}


	public void leverBouclier(){
		this.bouclier.setHold(true);
	}

	@Override
	public void infligerDegats(Personnage adversaire) {
		adversaire.recevoirDegats(this.epee.getDegats());
	}

	@Override
	public void recevoirDegats(int value) {
		if (null != this.bouclier) {
			int degatsSubits = value - this.bouclier.getResistance();
			this.diminuerPv(degatsSubits);
		}else {
			this.diminuerPv(value);
		}
	}
}
