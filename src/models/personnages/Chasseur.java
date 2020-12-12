package models.personnages;

public class Chasseur extends Personnage {

    private int nombreFleches;
    public Chasseur(String nom) {
        super(nom, Type.CHASSEUR);
        this.nombreFleches = 30;
    }

    public Chasseur(int id, String nom, int exp, int gold){
        super(id,nom,exp,Type.CHASSEUR,gold);
        this.nombreFleches = 30;
    }

    public int getNombreFleches() {
        return nombreFleches;
    }

    public void addFleches(int nombreFleches){
        this.nombreFleches += nombreFleches;
    }
}
