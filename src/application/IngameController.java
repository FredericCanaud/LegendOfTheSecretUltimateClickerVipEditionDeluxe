package application;

import classes.arme.Arc;
import classes.arme.Bouclier;
import classes.arme.Epee;
import classes.personnages.Chasseur;
import classes.personnages.Guerrier;
import classes.personnages.Personnage;
import javafx.scene.control.Label;

public class IngameController {

    public Label nomHero;
    public Label nomEnnemi;
    public Label niveauEnnemi;
    public Label pointsDeVieEnnemi;

    public Label niveauHero;
    public Label pointsDeVieHero;
    public Label pointsDeVieMana;
    public Label armeEquipee;

    Personnage hero;
    
    void initData(String classeChoisie, String nomHero) {
        System.out.println(classeChoisie);

        switch(classeChoisie){
            case "creerGuerrier":
                hero = new Guerrier(50,10,1);
                this.armeEquipee.setText(( (Guerrier) hero).getEpee().getClass().getSimpleName());
                break;

            case "creerArcher":
                hero = new Chasseur(35,15,1);
                this.armeEquipee.setText(((Chasseur) hero).getArc().getClass().getSimpleName());
                break;

        }
        this.nomHero.setText(nomHero);
        this.niveauHero.setText("Niveau " + String.valueOf(hero.getNiveau()));
        this.pointsDeVieHero.setText("PV : " + hero.getPointsDeVieRestants() + "/" + hero.getPointsDeVieMax());
        this.pointsDeVieMana.setText("PM : " + hero.getPointsDeManaRestants() + "/" + hero.getPointsDeManaMax());
    }

}
