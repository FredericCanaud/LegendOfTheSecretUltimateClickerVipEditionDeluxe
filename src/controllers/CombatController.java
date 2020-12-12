package controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.equipements.Arme;
import models.equipements.SortOffensif;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static controllers.HelperController.*;

/**
 * Controller FXML du combat
 *
 * @author Frédéric CANAUD / Thomas CAMPREDON
 */
public class CombatController {

    @FXML
    private AnchorPane battlePane;

    //////////////////////////////////////     JOUEUR       /////////////////////////////////////////

    @FXML
    private Text nomJoueur;

    @FXML
    private Text pointsDeVieJoueur;

    @FXML
    private Text pointsDeManaJoueur;

    @FXML
    private Text niveauJoueur;

    @FXML
    private Text typeJoueur;

    @FXML
    private ProgressBar progressBarPvJoueur;

    @FXML
    private ProgressBar progressBarManaJoueur;

    @FXML
    private ImageView imageJoueur;

    @FXML
    private Label nombreSurJoueur;

    //////////////////////////////////////     ENNEMI       /////////////////////////////////////////

    @FXML
    private Text nomEnnemi;

    @FXML
    private Text pointsDeVieEnnemi;

    @FXML
    private Text niveauEnnemi;

    @FXML
    private Text typeEnnemi;

    @FXML
    private ProgressBar progressBarPVEnnemi;

    @FXML
    private ImageView imageEnnemi;

    @FXML
    private Label nombreSurEnnemi;

    //////////////////////////////////////     ACTION       /////////////////////////////////////////

    @FXML
    private Button boutonCompetence1;

    @FXML
    private Button boutonCompetence2;

    @FXML
    private Button boutonPasser;

    @FXML
    private Button boutonFuite;

    private boolean oppFirst;

    @FXML
    void initialize() {
        nomJoueur.setText(joueur.getNom());
        imageJoueur.setImage(joueur.getImage(false));
        typeJoueur.setText(String.valueOf(joueur.getType()));
        niveauJoueur.setText(String.valueOf(joueur.getNiveau()));
        progressBarPvJoueur.setProgress(joueur.getStatus().getPointsDeVieRestants() / joueur.getStatus().getPointsDeVieMax());
        progressBarManaJoueur.setProgress(joueur.getStatus().getPointsDeManaRestants() / joueur.getStatus().getPointsDeManaMax());
        pointsDeVieJoueur.setText(String.valueOf(joueur.getStatus().getPointsDeVieRestants()));
        pointsDeManaJoueur.setText(String.valueOf(joueur.getStatus().getPointsDeManaRestants()));

        nomEnnemi.setText(ennemi.getNom());
        imageEnnemi.setImage(ennemi.getImage(true));
        typeEnnemi.setText(String.valueOf(ennemi.getType()));
        niveauEnnemi.setText(String.valueOf(ennemi.getNiveau()));
        progressBarPVEnnemi.setProgress(ennemi.getStatus().getPointsDeVieRestants() / ennemi.getStatus().getPointsDeVieMax());
        pointsDeVieEnnemi.setText(String.valueOf(ennemi.getStatus().getPointsDeVieRestants()));

        boutonCompetence1.setText(joueur.getEquipement()[0].getNom());
        if (joueur.getNiveau() < 3) {
            boutonCompetence2.setDisable(true);
            boutonCompetence2.setText("Indisponible");
        } else {
            boutonCompetence2.setText(joueur.getEquipement()[1].getNom());
        }

        if (ennemi.getStatus().getVitesse() > joueur.getStatus().getVitesse()) {
            oppFirst = true;
            boutonCompetence1.fire();
        }
        setMusic("src/views/assets/audio/combat.mp3",0.2, true);
    }

    @FXML
    void actionButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(boutonCompetence1)) {
            if(joueur.getEquipement()[0] instanceof Arme || joueur.getEquipement()[0] instanceof SortOffensif){
                attaquer(joueur.attaquePerform(ennemi,0));
            } else {
                action(joueur.competencePerform(0));
            }
        } else if (event.getSource().equals(boutonCompetence2)) {
            if(joueur.getEquipement()[1] instanceof Arme || joueur.getEquipement()[1] instanceof SortOffensif){
                attaquer(joueur.attaquePerform(ennemi,1));
            } else {
                action(joueur.competencePerform(1));
            }
        } else if (event.getSource().equals(boutonPasser)) {
            passe(joueur.passer());
            System.out.println("Oui");
        } else if (event.getSource().equals(boutonFuite)) {
            URL url = this.getClass().getClassLoader().getResource("views/FinCombat.fxml");
            if (url == null) return;
            AnchorPane pane = FXMLLoader.load(url);
            battlePane.getChildren().setAll(pane);
        }
    }

    private KeyFrame[] joueurAttaque(int damage) {
        if (joueur.estKO()) return new KeyFrame[0];
        // all the keyframe stuff
        KeyValue PVEnnemiBar = new KeyValue(progressBarPVEnnemi.progressProperty(), ennemi.getStatus().getPointsDeVieRestants() / ennemi.getStatus().getPointsDeVieMax());
        KeyValue PVEnnemi = new KeyValue(pointsDeVieEnnemi.textProperty(), String.valueOf(ennemi.getStatus().getPointsDeVieRestants()));
        KeyValue ManaJoueurBar = new KeyValue(progressBarManaJoueur.progressProperty(), joueur.getStatus().getPointsDeManaRestants() / joueur.getStatus().getPointsDeManaMax());
        KeyValue ManaJoueur = new KeyValue(pointsDeManaJoueur.textProperty(), String.valueOf(joueur.getStatus().getPointsDeManaRestants()));
        KeyValue competence1Bouton = new KeyValue(boutonCompetence1.disableProperty(), true);
        KeyValue competence2Bouton = new KeyValue(boutonCompetence2.disableProperty(), true);
        KeyValue passerBouton = new KeyValue(boutonPasser.disableProperty(), true);
        KeyValue fuiteBouton = new KeyValue(boutonFuite.disableProperty(), true);

        KeyFrame framedot0s = new KeyFrame(new Duration(0), competence1Bouton, competence2Bouton, passerBouton, fuiteBouton, PVEnnemiBar, PVEnnemi, ManaJoueurBar, ManaJoueur);

        KeyValue positionXJoueur = new KeyValue(imageJoueur.yProperty(), imageJoueur.getY());
        KeyValue positionYJoueur = new KeyValue(imageJoueur.xProperty(), imageJoueur.getX());

        KeyFrame framedot100s = new KeyFrame(new Duration(1000), positionYJoueur, positionXJoueur);

        positionYJoueur = new KeyValue(imageJoueur.xProperty(), imageJoueur.getX() + 10);
        positionXJoueur = new KeyValue(imageJoueur.yProperty(), imageJoueur.getY() - 10);

        KeyFrame framedot125s = new KeyFrame(new Duration(1250), positionYJoueur, positionXJoueur);

        KeyValue degatsEnnemi = new KeyValue(nombreSurEnnemi.textProperty(), String.valueOf(damage));
        KeyValue positionYEnnemi = new KeyValue(imageEnnemi.yProperty(), imageEnnemi.getY());
        KeyValue positionXEnnemi = new KeyValue(imageEnnemi.xProperty(), imageEnnemi.getX());
        positionYJoueur = new KeyValue(imageJoueur.xProperty(), imageJoueur.getX());
        positionXJoueur = new KeyValue(imageJoueur.yProperty(), imageJoueur.getY());

        KeyFrame framedot15s = new KeyFrame(new Duration(1350), e -> nombreSurEnnemi.setStyle("-fx-text-fill: red;"), degatsEnnemi, positionXEnnemi, positionYEnnemi, positionYJoueur, positionXJoueur);

        positionXEnnemi = new KeyValue(imageEnnemi.xProperty(), imageEnnemi.getX() + 10);
        positionYEnnemi = new KeyValue(imageEnnemi.yProperty(), imageEnnemi.getY() - 10);

        KeyFrame framedot175s = new KeyFrame(new Duration(1550), positionXEnnemi, positionYEnnemi, PVEnnemiBar, degatsEnnemi);

        ennemi.getStatus().deHp(damage);
        PVEnnemiBar = new KeyValue(progressBarPVEnnemi.progressProperty(), ennemi.getStatus().getPointsDeVieRestants() / ennemi.getStatus().getPointsDeVieMax());
        ManaJoueurBar = new KeyValue(progressBarManaJoueur.progressProperty(), joueur.getStatus().getPointsDeManaRestants() / joueur.getStatus().getPointsDeManaMax());
        positionXEnnemi = new KeyValue(imageEnnemi.xProperty(), imageEnnemi.getX());
        positionYEnnemi = new KeyValue(imageEnnemi.yProperty(), imageEnnemi.getY());
        degatsEnnemi = new KeyValue(nombreSurEnnemi.textProperty(), null);
        competence1Bouton = new KeyValue(boutonCompetence1.disableProperty(), false);
        if (joueur.getNiveau() >= 3) competence2Bouton = new KeyValue(boutonCompetence2.disableProperty(), false);
        passerBouton = new KeyValue(boutonPasser.disableProperty(), false);
        fuiteBouton = new KeyValue(boutonFuite.disableProperty(), false);
        PVEnnemi = new KeyValue(pointsDeVieEnnemi.textProperty(), String.valueOf(ennemi.getStatus().getPointsDeVieRestants()));
        ManaJoueur = new KeyValue(pointsDeManaJoueur.textProperty(), String.valueOf(joueur.getStatus().getPointsDeManaRestants()));

        KeyFrame framedot200s = new KeyFrame(new Duration(1800), PVEnnemiBar, ManaJoueurBar, positionXEnnemi, positionYEnnemi, degatsEnnemi, competence1Bouton, competence2Bouton, passerBouton, fuiteBouton, PVEnnemi, ManaJoueur);
        return new KeyFrame[]{framedot0s, framedot15s, framedot100s, framedot125s, framedot175s, framedot200s};
    }

    private KeyFrame[] ennemiAttaque(int time) {

        if (ennemi.estKO()) return new KeyFrame[0];
        // all the keyframe stuff
        KeyValue PVJoueurBar = new KeyValue(progressBarPvJoueur.progressProperty(), joueur.getStatus().getPointsDeVieRestants() / joueur.getStatus().getPointsDeVieMax());
        KeyValue PVJoueur = new KeyValue(pointsDeVieJoueur.textProperty(), String.valueOf(joueur.getStatus().getPointsDeVieRestants()));
        KeyValue competence1Bouton = new KeyValue(boutonCompetence1.disableProperty(), true);
        KeyValue competence2Bouton = new KeyValue(boutonCompetence2.disableProperty(), true);
        KeyValue passerBouton = new KeyValue(boutonPasser.disableProperty(), true);
        KeyValue fuiteBouton = new KeyValue(boutonFuite.disableProperty(), true);

        KeyFrame framedot0s = new KeyFrame(new Duration(time), competence1Bouton, competence2Bouton, passerBouton, fuiteBouton, PVJoueurBar, PVJoueur);

        KeyValue positionXEnnemi = new KeyValue(imageEnnemi.xProperty(), imageEnnemi.getX());
        KeyValue positionYEnnemi = new KeyValue(imageEnnemi.yProperty(), imageEnnemi.getY());

        KeyFrame framedot1000s = new KeyFrame(new Duration(time + 1000), positionXEnnemi, positionYEnnemi);

        positionXEnnemi = new KeyValue(imageEnnemi.xProperty(), imageEnnemi.getX() - 10);
        positionYEnnemi = new KeyValue(imageEnnemi.yProperty(), imageEnnemi.getY() - 10);

        KeyFrame framedot1250s = new KeyFrame(new Duration(time + 1250), positionXEnnemi, positionYEnnemi);

        int degats = ennemi.botPerform(joueur);

        joueur.getStatus().deHp(degats);
        KeyValue degatsEnnemi = new KeyValue(this.nombreSurJoueur.textProperty(), String.valueOf(degats));
        KeyValue positionYJoueur = new KeyValue(imageJoueur.yProperty(), imageJoueur.getY());
        KeyValue positionXJoueur = new KeyValue(imageJoueur.xProperty(), imageJoueur.getX());
        positionXEnnemi = new KeyValue(imageEnnemi.xProperty(), imageEnnemi.getX());
        positionYEnnemi = new KeyValue(imageEnnemi.yProperty(), imageEnnemi.getY());

        KeyFrame framedot1350s = new KeyFrame(new Duration(time + 1350), e -> nombreSurJoueur.setStyle("-fx-text-fill: red;"), positionXEnnemi, positionYEnnemi, positionYJoueur, positionXJoueur, degatsEnnemi);

        positionXJoueur = new KeyValue(imageJoueur.xProperty(), imageJoueur.getX() - 10);
        positionYJoueur = new KeyValue(imageJoueur.yProperty(), imageJoueur.getY() + 10);

        KeyFrame framedot1550s = new KeyFrame(new Duration(time + 1550), positionYJoueur, positionXJoueur, PVJoueurBar, degatsEnnemi);

        PVJoueurBar = new KeyValue(progressBarPvJoueur.progressProperty(), joueur.getStatus().getPointsDeVieRestants() / joueur.getStatus().getPointsDeVieMax());
        positionXJoueur = new KeyValue(imageJoueur.xProperty(), imageJoueur.getX());
        positionYJoueur = new KeyValue(imageJoueur.yProperty(), imageJoueur.getY());
        degatsEnnemi = new KeyValue(this.nombreSurJoueur.textProperty(), null);
        competence1Bouton = new KeyValue(boutonCompetence1.disableProperty(), false);
        if (joueur.getNiveau() >= 3) competence2Bouton = new KeyValue(boutonCompetence2.disableProperty(), false);
        passerBouton = new KeyValue(boutonPasser.disableProperty(), false);
        fuiteBouton = new KeyValue(boutonFuite.disableProperty(), false);
        PVJoueur = new KeyValue(pointsDeVieJoueur.textProperty(), String.valueOf(joueur.getStatus().getPointsDeVieRestants()));

        KeyFrame framedot1800s = new KeyFrame(new Duration(time + 1800), positionXJoueur, positionYJoueur, degatsEnnemi, PVJoueurBar, competence1Bouton, competence2Bouton, passerBouton, fuiteBouton, PVJoueur);
        return new KeyFrame[]{framedot0s, framedot1000s, framedot1250s, framedot1350s, framedot1550s, framedot1800s};
    }

    private KeyFrame[] joueurSoigne(int soin) {
        if (joueur.estKO()) return new KeyFrame[0];

        KeyValue PVJoueurBar = new KeyValue(progressBarPvJoueur.progressProperty(), joueur.getStatus().getPointsDeVieRestants() / joueur.getStatus().getPointsDeVieMax());
        KeyValue PVJoueur = new KeyValue(pointsDeVieJoueur.textProperty(), String.valueOf(joueur.getStatus().getPointsDeVieRestants()));
        KeyValue ManaJoueurBar = new KeyValue(progressBarManaJoueur.progressProperty(), joueur.getStatus().getPointsDeManaRestants() / joueur.getStatus().getPointsDeManaMax());
        KeyValue ManaJoueur = new KeyValue(pointsDeManaJoueur.textProperty(), String.valueOf(joueur.getStatus().getPointsDeManaRestants()));
        KeyValue competence1Bouton = new KeyValue(boutonCompetence1.disableProperty(), true);
        KeyValue competence2Bouton = new KeyValue(boutonCompetence2.disableProperty(), true);
        KeyValue passerBouton = new KeyValue(boutonPasser.disableProperty(), true);
        KeyValue fuiteBouton = new KeyValue(boutonFuite.disableProperty(), true);

        KeyFrame framedot0s = new KeyFrame(new Duration(0), competence1Bouton, competence2Bouton, passerBouton, fuiteBouton);

        KeyValue soinJoueur = new KeyValue(nombreSurJoueur.textProperty(), String.valueOf(soin));

        KeyFrame framedot75s = new KeyFrame(new Duration(750), e -> nombreSurJoueur.setStyle("-fx-text-fill: green;"), soinJoueur);
        KeyFrame framedot100s = new KeyFrame(new Duration(100), PVJoueurBar, soinJoueur, PVJoueurBar, PVJoueurBar, ManaJoueurBar, ManaJoueur);

        soinJoueur = new KeyValue(nombreSurJoueur.textProperty(), null);

        PVJoueurBar = new KeyValue(progressBarPvJoueur.progressProperty(), joueur.getStatus().getPointsDeVieRestants() / joueur.getStatus().getPointsDeVieMax());
        ManaJoueurBar = new KeyValue(progressBarManaJoueur.progressProperty(), joueur.getStatus().getPointsDeManaRestants() / joueur.getStatus().getPointsDeManaMax());
        competence1Bouton = new KeyValue(boutonCompetence1.disableProperty(), false);
        if (joueur.getNiveau() >= 3) competence2Bouton = new KeyValue(boutonCompetence2.disableProperty(), false);
        passerBouton = new KeyValue(boutonPasser.disableProperty(), false);
        fuiteBouton = new KeyValue(boutonFuite.disableProperty(), false);
        PVJoueur = new KeyValue(pointsDeVieJoueur.textProperty(), String.valueOf(joueur.getStatus().getPointsDeVieRestants()));
        ManaJoueur = new KeyValue(pointsDeManaJoueur.textProperty(), String.valueOf(joueur.getStatus().getPointsDeManaRestants()));

        KeyFrame framedot150s = new KeyFrame(new Duration(1500), PVJoueurBar, PVJoueur, ManaJoueurBar, ManaJoueur, soinJoueur, competence1Bouton, competence2Bouton, passerBouton, fuiteBouton);
        return new KeyFrame[]{framedot0s, framedot75s, framedot100s, framedot150s};
    }

    private KeyFrame[] joueurPasse(int mana) {
        if (joueur.estKO()) return new KeyFrame[0];

        KeyValue ManaJoueurBar = new KeyValue(progressBarManaJoueur.progressProperty(), joueur.getStatus().getPointsDeManaRestants() / joueur.getStatus().getPointsDeManaMax());
        KeyValue ManaJoueur = new KeyValue(pointsDeManaJoueur.textProperty(), String.valueOf(joueur.getStatus().getPointsDeManaRestants()));
        KeyValue competence1Bouton = new KeyValue(boutonCompetence1.disableProperty(), true);
        KeyValue competence2Bouton = new KeyValue(boutonCompetence2.disableProperty(), true);
        KeyValue passerBouton = new KeyValue(boutonPasser.disableProperty(), true);
        KeyValue fuiteBouton = new KeyValue(boutonFuite.disableProperty(), true);

        KeyFrame framedot0s = new KeyFrame(new Duration(0), competence1Bouton, competence2Bouton, passerBouton, fuiteBouton, ManaJoueurBar, ManaJoueurBar);

        KeyValue manaAjoute = new KeyValue(nombreSurJoueur.textProperty(), String.valueOf(mana));

        KeyFrame framedot15s = new KeyFrame(new Duration(1350), e -> nombreSurJoueur.setStyle("-fx-text-fill: green;"), manaAjoute);

        KeyFrame framedot175s = new KeyFrame(new Duration(1550), ManaJoueurBar, manaAjoute);

        ManaJoueurBar = new KeyValue(progressBarManaJoueur.progressProperty(), joueur.getStatus().getPointsDeManaRestants() / joueur.getStatus().getPointsDeManaMax());

        manaAjoute = new KeyValue(nombreSurJoueur.textProperty(), null);
        competence1Bouton = new KeyValue(boutonCompetence1.disableProperty(), false);
        if (joueur.getNiveau() >= 3) competence2Bouton = new KeyValue(boutonCompetence2.disableProperty(), false);
        passerBouton = new KeyValue(boutonPasser.disableProperty(), false);
        fuiteBouton = new KeyValue(boutonFuite.disableProperty(), false);
        ManaJoueur = new KeyValue(pointsDeManaJoueur.textProperty(), String.valueOf(joueur.getStatus().getPointsDeManaRestants()));

        KeyFrame framedot200s = new KeyFrame(new Duration(1800), ManaJoueurBar,  manaAjoute, competence1Bouton, competence2Bouton, passerBouton, fuiteBouton, ManaJoueur);
        return new KeyFrame[]{framedot0s, framedot15s, framedot175s, framedot200s};
    }

    private void attaquer(int degats) {
        Timeline timeline = new Timeline();
        List<KeyFrame> keyFrames = new ArrayList<>();
        int time = 0;
        if (!oppFirst) {
            time = 1800;
            Collections.addAll(keyFrames, joueurAttaque(degats));
        }
        Collections.addAll(keyFrames, ennemiAttaque(time));
        timeline.getKeyFrames().addAll(keyFrames);
        oppFirst = false;
        timeline.play();

        timeline.setOnFinished(e -> {
            if (joueur.estKO() || ennemi.estKO()) {
                boutonFuite.fire();
            }
        });
    }

    private void action(int degats) {
        Timeline timeline = new Timeline();
        List<KeyFrame> keyFrames = new ArrayList<>();
        int time = 0;
        if (!oppFirst) {
            time = 1800;
            Collections.addAll(keyFrames, joueurSoigne(degats));
        }
        Collections.addAll(keyFrames, ennemiAttaque(time));
        timeline.getKeyFrames().addAll(keyFrames);
        oppFirst = false;
        timeline.play();

        timeline.setOnFinished(e -> {
            if (joueur.estKO() || ennemi.estKO()) {
                boutonFuite.fire();
            }
        });
    }

    private void passe(int mana) {
        Timeline timeline = new Timeline();
        List<KeyFrame> keyFrames = new ArrayList<>();
        int time = 0;
        if (!oppFirst) {
            time = 1800;
            Collections.addAll(keyFrames, joueurPasse(mana));
        }
        Collections.addAll(keyFrames, ennemiAttaque(time));
        timeline.getKeyFrames().addAll(keyFrames);
        oppFirst = false;
        timeline.play();

        timeline.setOnFinished(e -> {
            if (joueur.estKO() || ennemi.estKO()) {
                boutonFuite.fire();
            }
        });
    }
}
