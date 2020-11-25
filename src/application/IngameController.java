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
                hero = new Guerrier(50,10,1);
                Epee epeeClassique = new Epee(10);
                ((Guerrier) hero).addEpee(epeeClassique);
                ((Guerrier) hero).addBouclier(new Bouclier(7));
                ((Guerrier) hero).utiliser(epeeClassique);
                this.armeEquipee.setText((Guerrier) hero.;
            case "archer":
                hero = new Chasseur(35,15,1);
                Arc arcClassique = new Arc(2);
                ((Chasseur) hero).addArc(arcClassique);
                ((Chasseur) hero).addFleches("FlecheNormale",10);
                ((Chasseur) hero).utiliseArc(0);
        }
        this.nomHero.setText(nomHero);
        this.niveauHero.setText(String.valueOf(hero.getNiveau()));
        this.pointsDeVieHero.setText(hero.getPointsDeVieRestants() + ":" + hero.getPointsDeVieMax());
    }

}
