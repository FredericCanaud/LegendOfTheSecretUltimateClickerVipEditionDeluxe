package classes;

import java.util.ArrayList;
import java.util.HashMap;


public class Chasseur extends Personnage implements ManipulateurDArme{

	protected ArrayList<Arme> armeUtilisable;
	protected HashMap<String, Object[]> listeFleches;
	
	protected Arc arc;
	protected String fleche;
	
	public Chasseur(int pv, int pm, int lvl) {
		super(pv, pm, lvl);
		this.armeUtilisable = new ArrayList<>();
		this.listeFleches = new HashMap<>();
	}

	public void addArc(Arc arc) {
		this.armeUtilisable.add(arc);
	}
	
	public void utiliseArc(int i) {
		this.arc = (Arc)this.armeUtilisable.get(i);
	}
	
	public void utiliseFleche(String nomFleche) {
		if (this.hasFleche(nomFleche)) {
			this.fleche = nomFleche;
		}
		
	}
	
	public void addFleches(String nomFleche, int quantite) {
		int quantite2 = quantite;
		if (quantite <=0) {
			quantite2 = 0;
		}
		if (this.hasFleche(nomFleche)) {
			this.augmenterFleche(nomFleche, quantite2);
		}else {
			this.listeFleches.put(nomFleche, new Object[] {Fleche.getNewFleche(nomFleche), quantite2});
		}
	}
	
	
	public boolean hasFleche(String nomFleche) {
		if (this.listeFleches.containsKey(nomFleche)) {
			return true;
		}
		return false;
	}
	
	private void diminuerFleche(int nbFleches) {
		if (((Integer)this.listeFleches.get(fleche)[1])-nbFleches <= 0){
			this.listeFleches.remove(this.fleche);
		}else {
			this.listeFleches.get(fleche)[1] = (Integer)this.listeFleches.get(fleche)[1]-nbFleches;
		}
		
	}
	
	private void augmenterFleche(String nomFleche, int quantite) {
		this.listeFleches.get(nomFleche)[1] = (Integer)this.listeFleches.get(nomFleche)[1]+quantite;
	}
	
	
	
	
	@Override
	public void infligerDegats(Personnage adversaire) {
		if (this.hasFleche(this.fleche)) {
			int value = this.arc.getMultiplicateur() * ((Fleche)this.listeFleches.get(fleche)[0]).getDegats();
			diminuerFleche(1);
			adversaire.recevoirDegats(value);
		}
	}

	

	@Override
	public void recevoirDegats(int value) {
		this.diminuerPv(value);
	}
}
