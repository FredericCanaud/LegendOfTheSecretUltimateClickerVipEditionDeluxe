package models.personnages;

public class Lancier extends Personnage {

    public Lancier(String nom) {
        super(nom, Type.LANCIER);
    }

    public Lancier(int id, String nom, int exp, int gold){
        super(id,nom,exp,Type.LANCIER,gold);
    }
}
