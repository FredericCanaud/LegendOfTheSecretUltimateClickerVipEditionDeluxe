package models.personnages;

/**
 * Status class is a class for status object
 * Contain all the status for monster and helper method.
 *
 * @author Pakanon Pantisawat
 */
public class Status {

    private double pointsDeVieMax;
    private double pointsDeVieRestants;
    private double pointsDeManaMax;
    private double pointsDeManaRestants;
    private double attaque;
    private double defense;
    private double vitesse;

    public Status(int level, Type type) {
        pointsDeVieMax = 10 * (level + 29);
        pointsDeVieRestants = pointsDeVieMax;
        pointsDeManaMax = 10 * (level + 3);
        pointsDeManaRestants = pointsDeManaMax;
        attaque = 15 * (level + 5);
        defense = 10 * (level + 3);
        vitesse = 10 * level;

        switch (type) {
            case GUERRIER, BARBARE -> {
                pointsDeVieMax = 10 * (level + 31);
                pointsDeVieRestants = pointsDeVieMax;
                attaque += 15;
                defense += 10;
            }
            case LANCIER ->{
                attaque += 10;
                defense += 15;
            }
            case CHASSEUR -> {
                pointsDeVieMax = 10 * (level + 25);
                pointsDeVieRestants = pointsDeVieMax;
                defense = 10 * (level + 2);
                vitesse = 10 * (level + 4);
                pointsDeManaMax = 10 * (level + 12);
                pointsDeManaRestants = pointsDeManaMax;
            }
            case MAGE -> {
                pointsDeVieMax = 10 * (level + 22.5);
                pointsDeVieRestants = pointsDeVieMax;
                defense = 10 * (level + 2);
                vitesse = 10 * (level + 5);
                pointsDeManaMax = 10 * (level + 18);
                pointsDeManaRestants = pointsDeManaMax;
            }
        }
    }

    public void deHp(int nbPv) {
        this.pointsDeVieRestants -= nbPv;
    }

    public void soin(int soin) {
        this.pointsDeVieRestants = Math.min(soin + this.pointsDeVieRestants,this.pointsDeVieMax);
    }

    public double getPointsDeVieRestants() {
        return pointsDeVieRestants;
    }

    public double getPointsDeVieMax() {
        return pointsDeVieMax;
    }

    public double getPointsDeManaMax() {
        return pointsDeManaMax;
    }

    public double getPointsDeManaRestants() {
        return pointsDeManaRestants;
    }

    public double getAttaque() {
        return attaque;
    }

    public double getDefense() {
        return defense;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setPointsDeVieRestants(double pointsDeVieRestants) {
        this.pointsDeVieRestants = pointsDeVieRestants;
    }

    public void setPointsDeManaRestants(double pointsDeManaRestants) {
        this.pointsDeManaRestants = pointsDeManaRestants;
    }
}

