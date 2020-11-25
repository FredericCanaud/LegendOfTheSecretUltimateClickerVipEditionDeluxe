package classes.personnages;


import classes.sorts.*;

import java.util.ArrayList;

public class Mage extends Personnage {
	protected SortNV1 sortNV1;
	protected SortNV2 sortNV2;

	protected ArrayList<Sort> sortUtilisable;

	public Mage(int pv, int pm, int lvl) {
		super(pv, pm, lvl);
		//TODO
	}

	public void addSort(Sort sort) {
		this.sortUtilisable.add(sort);
	}

	public void utiliser(SortNV1 sortNV1){
		if (null != this.sortNV2){
			if (this.sortNV2 instanceof SortDeDegats){
				this.sortNV1 = sortNV1;
			}else{
				if (!(sortNV1 instanceof SortDeDegats)){
					throw new IllegalArgumentException("Il faut au moins un sort d'attaque !");
				}
			}
		}else{
			this.sortNV1 = sortNV1;
		}
	}

	public void utiliser(SortNV2 sortNV2){
		if (null != this.sortNV1){
			if (this.sortNV1 instanceof SortDeDegats){
				this.sortNV2 = sortNV2;
			}else{
				if (!(sortNV2 instanceof SortDeDegats)){
					throw new IllegalArgumentException("Il faut au moins un sort d'attaque !");
				}
			}
		}else{
			this.sortNV2 = sortNV2;
		}
	}


	@Override
	public void infligerDegats(Personnage adversaire) {
		// TODO Auto-generated method stub
	}

}
