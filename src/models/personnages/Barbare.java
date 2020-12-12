package models.personnages;

public class Barbare extends Personnage {

    public Barbare(String nom) {
        super(nom, Type.BARBARE);
    }

    public Barbare(int id, String nom, int exp, int gold){
        super(id,nom,exp,Type.BARBARE,gold);
    }
}
