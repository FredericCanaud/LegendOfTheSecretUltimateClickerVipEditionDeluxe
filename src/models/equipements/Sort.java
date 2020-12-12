package models.equipements;

public abstract class Sort extends Equipement{

    private int coutMana;

    public Sort(String nom, int coutMana) {
        super(nom);
    }

    public int getCoutMana() {
        return coutMana;
    }
}
