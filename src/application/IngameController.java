package application;

import classes.arme.Arc;
import classes.arme.Bouclier;
import classes.arme.Epee;
import classes.personnages.Chasseur;
import classes.personnages.Guerrier;
import classes.personnages.Personnage;

public class IngameController {

    Personnage hero;
    
    void initData(String classeChoisie) {
        switch(classeChoisie){
            case "guerrier":
                hero = new Guerrier(50,10,1);
                Epee epeeClassique = new Epee(10);
                ((Guerrier) hero).addEpee(epeeClassique);
                ((Guerrier) hero).addBouclier(new Bouclier(7));
                ((Guerrier) hero).utiliser(epeeClassique);
            /* case "archer":
                hero = new Chasseur(35,15,1);
                Arc arcClassique = new Arc(2);
                ((Chasseur) hero).addArc(arcClassique);
                ((Chasseur) hero).augmenterFleche(10);
                ((Chasseur) hero).utiliseArc(0); */
        }
    }

}
