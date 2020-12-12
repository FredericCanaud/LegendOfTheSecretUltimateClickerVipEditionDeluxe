package models.equipements;

public class SortSoin extends Sort{

    private float soin;

    public SortSoin(String nom, int coutMana, float soin) {
        super(nom, coutMana);
        this.soin = soin;
    }

    public float getSoin() {
        return soin;
    }
}
