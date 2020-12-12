package models.personnages;

public class Mage extends Personnage {

    public Mage(String nom) {
        super(nom, Type.MAGE);
    }

    public Mage(int id, String nom, int exp, int gold){
        super(id,nom,exp,Type.MAGE,gold);
    }
}
