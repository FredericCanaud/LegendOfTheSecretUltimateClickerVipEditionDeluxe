package models.personnages;

public class Guerrier extends Personnage {

    public Guerrier(String nom) {
        super(nom, Type.GUERRIER);
    }

    public Guerrier(int id, String nom, int exp, int gold){
        super(id,nom,exp,Type.GUERRIER,gold);
    }
}
