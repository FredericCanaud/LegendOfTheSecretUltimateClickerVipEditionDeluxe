package models.equipements;

public class Arme extends Equipement{

    private float degats;
    private int durabilite;
    public Arme(String nom, float degats, int durabilite) {
        super(nom);
        this.degats = degats;
        this.durabilite = durabilite;
    }

    public float getDegats() {
        return degats;
    }

    public int getDurabilite() {
        return durabilite;
    }

    public void setDurabilite(int durabilite){
        this.durabilite = durabilite;
    }
}
