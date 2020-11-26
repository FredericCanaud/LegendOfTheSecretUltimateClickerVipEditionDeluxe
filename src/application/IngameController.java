package application;

import classes.personnages.Chasseur;
import classes.personnages.Guerrier;
import classes.personnages.Personnage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class IngameController {

    public Label nomHero;
    public Label nomEnnemi;
    public Label niveauEnnemi;
    public Label pointsDeVieEnnemi;

    public Label niveauHero;
    public Label pointsDeVieHero;
    public Label pointsDeVieMana;
    public Label armeEquipee;
    public Button action1;
    public Button action2;
    public Button passer;
    public Button sauvegarder;
    public Button quitter;
    public ImageView ivHero;
    public ImageView ivEnnemi;
    public Label dialogue;

    Personnage hero;
    Personnage ennemi;

    void initData(String classeChoisie, String nomHero) {
        System.out.println(classeChoisie);

        switch (classeChoisie) {
            case "guerrier" -> {
                hero = new Guerrier();
                this.armeEquipee.setText(((Guerrier) hero).getEpee().getClass().getSimpleName());
                this.action1.setText("Action 1 \n " + ((Guerrier) hero).getEpee().getClass().getSimpleName());
                this.action2.setText("Action 2 \n " + ((Guerrier) hero).getBouclier().getClass().getSimpleName());
            }
            case "chasseur" -> {
                hero = new Chasseur();
                this.armeEquipee.setText(((Chasseur) hero).getArc().getClass().getSimpleName());
                this.action1.setText("Attaque \n " + ((Chasseur) hero).getArc().getClass().getSimpleName());
                this.action2.setText("Attaque \n " + ((Chasseur) hero).getSort().getClass().getSimpleName());
            }
        }
        this.nomHero.setText(nomHero);
        this.niveauHero.setText("Niveau " + hero.getNiveau());
        this.pointsDeVieHero.setText("PV : " + hero.getPointsDeVieRestants() + "/" + hero.getPointsDeVieMax());
        this.pointsDeVieMana.setText("PM : " + hero.getPointsDeManaRestants() + "/" + hero.getPointsDeManaMax());
    }

    public void startFight(){
        switch(this.hero.getClass().getSimpleName()){
            case "Guerrier" -> {
                ivHero.setImage(new Image("img/warrior2.png"));
            }
            case "Chasseur" -> {
                ivHero.setImage(new Image("img/archer2.png"));
            }
            case "Mage" -> {
                ivHero.setImage(new Image("img/mage2.png"));
            }
        }
        ivEnnemi.setImage(new Image("img/enemy1.png"));
        ennemi = new Guerrier(20,5,1);
        nomEnnemi.setText("Goblin");
        niveauEnnemi.setText("Niveau " + ennemi.getNiveau());
        pointsDeVieEnnemi.setText("PV : " + ennemi.getPointsDeVieRestants() + "/" + ennemi.getPointsDeVieMax());
        dialogue.setText("Le combat commence !");
    }
    public void action1(MouseEvent actionEvent) {
        switch(this.hero.getClass().getSimpleName()){
            case "Guerrier" -> {
                hero.infligerDegats(ennemi);
                pointsDeVieEnnemi.setText("PV : " + ennemi.getPointsDeVieRestants() + "/" + ennemi.getPointsDeVieMax());
            }
            case "Chasseur" -> {
                ivHero.setImage(new Image("img/archer2.png"));
            }
            case "Mage" -> {
                ivHero.setImage(new Image("img/mage2.png"));
            }
        }
    }

    public void action2(MouseEvent actionEvent) {

    }

    public void passer(MouseEvent actionEvent) {

    }
}
