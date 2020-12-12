package models.personnages;

import javafx.scene.image.Image;
import models.equipements.*;

public abstract class Personnage {

    private int id;
    private String nom;
    private int niveau;
    private int experience;
    private Status status;
    private Type type;
    private int or;
    private Image[] image;
    private Equipement[] equipement = new Equipement[2];

    public Personnage(String nom, int experience, Type type, int or) {
        this.id = hashCode();
        this.nom = nom;
        this.niveau = 1;
        this.experience = experience;
        this.type = type;
        this.or = or;
        setNiveau();
        setStatus();
        setImage();
        setEquipements();
    }

    public Personnage(String nom, Type type) {
        this(nom, 0, type, 0);
    }

    public Personnage(String nom, int experience, Type type) {
        this(nom, experience, type, 0);
        this.setType(type);
    }

    public Personnage(int id, String nom, int experience, Type type, int or) {
        this(nom, experience, type, or);
        this.id = id;
    }

    private boolean setNiveau() {
        setEquipements();
        if (experience < getCriteria() || this.niveau >= 10) return false;
        while (experience >= getCriteria()) {
            experience -= getCriteria();
            niveau++;
        }
        return true;
    }

    public void setEquipements() {
        switch (type) {
            case GUERRIER -> {
                equipement[0] = new Arme("Epée",10,30);
                equipement[1] = new Arme("Katana",15,20);
            }
            case LANCIER -> {
                equipement[0] = new Arme("Lance",10,30);
                equipement[1] = new Arme("Epieu",15,20);
            }
            case BARBARE -> {
                equipement[0] = new Arme("Hache",10,30);
                equipement[1] = new Arme("Tomawahk",15,20);
            }
            case CHASSEUR -> {
                equipement[0] = new Arc("Arc",10,30);
                equipement[1] = new SortSoin("Soin",10,80);
            }
            case MAGE -> {
                equipement[0] = new SortOffensif("Flamme Ardente", 10,30);
                equipement[1] = new SortOffensif("Chaos", 20,50);
            }
        }
    }

    private void setStatus() {
        this.status = new Status(this.niveau, this.type);
    }

    private void setImage() {
        this.image = new Image[2];
        String path = "";
        String pathReverse = "";
        switch (type) {
            case GUERRIER -> {
                path = "views/assets/img/personnage/guerrier.png";
                pathReverse = "views/assets/img/personnage/guerrierReverse.png";
            }
            case LANCIER -> {
                path = "views/assets/img/personnage/lancier.png";
                pathReverse = "views/assets/img/personnage/lancierReverse.png";
            }
            case BARBARE -> {
                path = "views/assets/img/personnage/barbare.png";
                pathReverse = "views/assets/img/personnage/barbareReverse.png";
            }
            case CHASSEUR -> {
                path = "views/assets/img/personnage/chasseur.png";
                pathReverse = "views/assets/img/personnage/chasseurReverse.png";
            }
            case MAGE -> {
                path = "views/assets/img/personnage/mage.png";
                pathReverse = "views/assets/img/personnage/mageReverse.png";
            }
        }
        this.image[0] = new Image(path);
        this.image[1] = new Image(pathReverse);
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
        setStatus();
    }

    public void reset() {
        this.status = new Status(this.niveau, this.type);
    }

    public String getNom() {
        return nom;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getExperience() {
        return experience;
    }

    public Status getStatus() {
        return status;
    }

    public Type getType() {
        return type;
    }

    public Image getImage(boolean isFlip) {
        if (isFlip) return image[1];
        else return image[0];
    }

    public Equipement[] getEquipement() {
        return equipement;
    }

    public int getCriteria() {
        return (int)Math.round(Math.pow(3 * niveau + 70, 2) / 9);
    }

    public int getId() {
        return id;
    }

    public int getOr() {
        return or;
    }

    public void setType(int type) {
        switch (type) {
            case 1 -> setType(Type.GUERRIER);
            case 2 -> setType(Type.LANCIER);
            case 3 -> setType(Type.BARBARE);
            case 4 -> setType(Type.CHASSEUR);
            case 5 -> setType(Type.MAGE);
        }
    }

    private void setType(Type type) {
        this.type = type;
        setStatus();
        setImage();
    }

    public void setEquipement(Equipement[] equipement) {
        this.equipement = equipement;
    }

    public boolean plusExp(int exp) {
        this.experience += exp;
        return setNiveau();
    }

    public void ajouterOr(int factor) {
        this.or += factor;
    }

    public void setOr(int or) {
        this.or = or;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int attaquePerform(Personnage ennemi, int equipement) {

        int lancerSur100 = new java.util.Random().nextInt(99) + 1;

        if (lancerSur100 > 90){
            return 0;
        }
        double multiplicateur = 1.0;
        if (ennemi.getType().getFaiblesse().contains(this.type)) multiplicateur = 2.0;

        if (lancerSur100 <= 10 || (lancerSur100 <= 30) && (this.type == Type.CHASSEUR || this.type == Type.MAGE) ){
            multiplicateur *= 2.0;
        }
        double degats = Math.floor((this.status.getAttaque() - ennemi.getStatus().getDefense()) * multiplicateur);
        if (ennemi.getStatus().getPointsDeVieRestants() - degats <= 0) degats = ennemi.getStatus().getPointsDeVieRestants();
        
        return (int) degats;
    }

    public int competencePerform(int equipement){
        double soin = 0.0;
        if(this.getEquipement()[equipement] instanceof SortSoin){
            if( (this.getStatus().getPointsDeVieRestants() + ((SortSoin) this.getEquipement()[equipement]).getSoin() + 5 * this.getNiveau() > this.getStatus().getPointsDeVieMax()) ){
                soin = this.getStatus().getPointsDeVieMax() - this.getStatus().getPointsDeVieRestants();
                this.getStatus().setPointsDeVieRestants(this.getStatus().getPointsDeVieMax());
            }
            else{
                soin = ((SortSoin) this.getEquipement()[equipement]).getSoin() + 5 * this.getNiveau();
                this.getStatus().setPointsDeVieRestants(this.getStatus().getPointsDeVieRestants() + soin);
            }
        }
        this.getStatus().setPointsDeManaRestants(Math.max(this.getStatus().getPointsDeManaRestants() - ((SortSoin) this.getEquipement()[equipement]).getCoutMana(),0));
        System.out.println(this.getStatus().getPointsDeManaRestants());
        return (int) soin;
    }

    public int passer() {
        int manaApres = 0;
        int manaAvant = (int) this.getStatus().getPointsDeManaRestants();
        switch (this.type) {
            case GUERRIER, LANCIER, BARBARE, CHASSEUR -> {
                manaApres = (int) Math.min(this.getStatus().getPointsDeManaRestants() + 0.15 * this.getStatus().getPointsDeManaMax(),
                        this.getStatus().getPointsDeManaMax());
                this.getStatus().setPointsDeManaRestants(manaApres);
            }
            case MAGE -> {
                manaApres = (int) Math.min(this.getStatus().getPointsDeManaRestants() + 0.3 * this.getStatus().getPointsDeManaMax(),
                        this.getStatus().getPointsDeManaMax());
                this.getStatus().setPointsDeManaRestants(manaApres);
            }
        }
        return manaApres - manaAvant;
    }

    public boolean estKO() {
        return this.status.getPointsDeVieRestants() <= 0;
    }

    public int botPerform(Personnage player) {
        int perform = new java.util.Random().nextInt(9) + 1;
        double damage;
        if (perform < 5) damage = attaquePerform(player,0);
        else {
            if (this.niveau < 2) damage = attaquePerform(player, 1);
            else damage = attaquePerform(player, perform % 2);
        }
        return (int) damage;
    }
}