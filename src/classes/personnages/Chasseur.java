package classes.personnages;

import classes.arme.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Chasseur extends Personnage implements ManipulateurDArme {

	protected ArrayList<Arme> armeUtilisable;
	protected HashMap<String, Integer> listeFleches;

	protected Arc arc;
	protected Fleche fleche;

	public Chasseur(int pv, int pm, int lvl) {
		super(pv, pm, lvl);
		this.armeUtilisable = new ArrayList<>();
		this.listeFleches = new HashMap<>();
		this.listeFleches.put(FlecheNormale.class.getSimpleName(),20);
		this.listeFleches.put(FlecheBizarre.class.getSimpleName(),0);
		this.listeFleches.put(FlecheDeFeu.class.getSimpleName(),0);
		this.listeFleches.put(FlecheDeGlace.class.getSimpleName(),0);

		Arc arcClassique = new Arc(2);
		this.addArc(arcClassique);
		this.addFleches("FlecheNormale",10);
		this.utiliseArc(0);
		this.fleche = new FlecheNormale();
		this.arc = null;
		this.fleche = null;
	}

	public Chasseur(){
		super(35, 20, 1);
		this.armeUtilisable = new ArrayList<>();
		this.listeFleches = new HashMap<>();
		this.listeFleches.put(FlecheNormale.class.getSimpleName(),20);
		this.listeFleches.put(FlecheBizarre.class.getSimpleName(),0);
		this.listeFleches.put(FlecheDeFeu.class.getSimpleName(),0);
		this.listeFleches.put(FlecheDeGlace.class.getSimpleName(),0);
		this.fleche = new FlecheNormale();
		this.arc = new Arc(2);;
		this.utiliseFleche("FlecheNormale");
	}

	public void addArc(Arc arc) {
		this.armeUtilisable.add(arc);
	}

	public void utiliseArc(int i) {
		this.arc = (Arc)this.armeUtilisable.get(i);
	}

	public void utiliseFleche(String nomFleche) {

		if (this.getFlecheType(nomFleche) != null) {
			this.fleche = Fleche.setNewFleche(nomFleche);
		}

	}
	public String getFlecheType(String nomFleche) {

		for(String fleche : this.listeFleches.keySet()){
			if(fleche.equals(nomFleche)){
				return fleche;
			}
		}
		return null;
	}

	private void diminuerFleche(int nbFleches) {
		this.listeFleches.put(this.fleche.getType(), Math.max(this.listeFleches.get(this.fleche.getType()) - nbFleches, 0));
	}

	public void addFleches(String nomFleche, int quantite) {
		if (quantite > 0 && this.getFlecheType(nomFleche) != null) {
			this.listeFleches.put(this.getFlecheType(nomFleche), quantite);
		} else {
			this.listeFleches.put(fleche.getType(),this.listeFleches.get(fleche.getType())+quantite);
		}
	}

	private void augmenterFleche(Fleche fleche, int quantite) {
		this.listeFleches.put(fleche.getType(),this.listeFleches.get(fleche.getType())+quantite);
	}

	@Override
	public void infligerDegats(Personnage adversaire) {
		if(this.listeFleches.get(fleche.getType()) > 0){
			adversaire.recevoirDegats(this.arc.getMultiplicateur() * fleche.getDegats());
			diminuerFleche(1);
		}
	}


	public HashMap<String, Integer> getListeFleches() {
		return listeFleches;
	}

	public Fleche getFleche() {
		return fleche;
	}
	public Arc getArc() {
		return arc;
	}
}
