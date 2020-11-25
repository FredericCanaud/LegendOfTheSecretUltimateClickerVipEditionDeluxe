package classes.personnages;


import classes.sorts.Sort;

public class Mage extends Personnage {
	
	public Mage(int pv, int pm, int lvl) {
		super(pv, pm, lvl);
	}

	public void addSort(Sort sort) {
		this.listeSorts.add(sort);
	}



	@Override
	public void infligerDegats(Personnage adversaire) {
		// TODO Auto-generated method stub
	}

	@Override
	public void recevoirDegats(int value) {
		// TODO Auto-generated method stub
		
	}
}
