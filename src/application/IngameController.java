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
    public Label armeEquipee;

    Personnage hero;
    
    void initData(String classeChoisie, String nomHero) {
        switch(classeChoisie){
            case "guerrier":
                hero = new Guerrier();
                Epee epeeClassique = new Epee(10);

                //this.armeEquipee.setText((Guerrier) hero.;
            case "archer":
                hero = new Chasseur();

        }
        this.nomHero.setText(nomHero);
        this.niveauHero.setText(String.valueOf(hero.getNiveau()));
        this.pointsDeVieHero.setText(hero.getPointsDeVieRestants() + ":" + hero.getPointsDeVieMax());
    }

}
