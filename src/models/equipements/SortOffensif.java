package models.equipements;

public class SortOffensif extends Sort{

    private float degats;

    public SortOffensif(String nom, int coutMana, float degats) {
        super(nom, coutMana);
        this.degats = degats;
    }

    public float getDegats() {
        return degats;
    }
}
