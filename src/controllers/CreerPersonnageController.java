package controllers;

import models.personnages.Guerrier;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import static controllers.HelperController.game;
import static controllers.HelperController.joueur;

/**
 * Classe de controller FXML pour l'écran de création d'un personnage
 * Scène précédente : Choix d'une Sauvegarde
 * Scène suivante : Choix d'une Sauvegarde (Avec personnage créé ou non)
 *
 * L'utilisateur peut choisir son personnage suivant 5 classes : Guerrier, Lancier, Barbare, Chasseur ou Mage
 * L'utilisateur donne ensuite un nom à son personnage, avant de pouvoir le sauvegarder
 *
 * @author Frédéric CANAUD / Thomas CAMPREDON
 */
public class CreerPersonnageController {


    @FXML
    private AnchorPane choixPersonnagePane;

    @FXML
    private ProgressBar barAttaque;

    @FXML
    private ProgressBar barMana;

    @FXML
    private ProgressBar barPV;

    @FXML
    private ProgressBar barDef;

    @FXML
    private ProgressBar barVitesse;

    @FXML
    private Button boutonCreerPerso;

    @FXML
    private Button boutonAnnuler;

    @FXML
    private Button nextType;

    @FXML
    private Button prevType;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField nameField;

    @FXML
    private Label labelAttaque;

    @FXML
    private Label labelMana;

    @FXML
    private Label labelPV;

    @FXML
    private Label labelVitesse;

    @FXML
    private Label labelType;

    @FXML
    private Label labelDefense;

    @FXML
    void initialize() {
        joueur = new Guerrier("temp");
        boutonCreerPerso.setDisable(true);
        update();
    }

    @FXML
    void nameInputEvent() {
        if (nameField.getText().equals("") || nameField.getText().contains(" "))
            boutonCreerPerso.setDisable(true);
        else
            boutonCreerPerso.setDisable(false);
    }

    private int type = 1;
    @FXML
    void typeSelectEvent(ActionEvent event) {
        if (event.getSource().equals(prevType)) type--;
        else type++;
        if (type > 5) type = 1;
        else if (type < 1) type = 5;
        joueur.setType(type);
        update();
    }

    @FXML
    void createButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(boutonAnnuler)) joueur = null;
        else {
            joueur.setNom(nameField.getText());
            joueur.setEquipements();
            game.addSave(joueur);
        }
        URL url = this.getClass().getClassLoader().getResource("views/ChoixSauvegarde.fxml");
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        choixPersonnagePane.getChildren().setAll(pane);
    }

    private void update() {
        imageView.setImage(joueur.getImage(false));
        labelType.setText(String.valueOf(joueur.getType()));
        Timeline timeline = new Timeline();

        KeyValue hpValue = new KeyValue(barPV.progressProperty(), joueur.getStatus().getPointsDeVieRestants() / 1000);
        KeyValue atkValue = new KeyValue(barAttaque.progressProperty(), joueur.getStatus().getAttaque() / 250);
        KeyValue manaValue = new KeyValue(barMana.progressProperty(), joueur.getStatus().getPointsDeManaMax() / 250);
        KeyValue defValue = new KeyValue(barDef.progressProperty(), joueur.getStatus().getDefense() / 150);
        KeyValue spdValue = new KeyValue(barVitesse.progressProperty(), joueur.getStatus().getVitesse() / 120);

        KeyFrame frame = new KeyFrame(new Duration(1000), hpValue, atkValue, manaValue, defValue, spdValue);
        timeline.getKeyFrames().add(frame);
        timeline.play();
        labelPV.setText(String.format("%.0f", joueur.getStatus().getPointsDeVieRestants()));
        labelAttaque.setText(String.format("%.0f", joueur.getStatus().getAttaque()));
        labelMana.setText(String.format("%.0f", joueur.getStatus().getPointsDeManaMax()));
        labelDefense.setText(String.format("%.0f", joueur.getStatus().getDefense()));
        labelVitesse.setText(String.format("%.0f", joueur.getStatus().getVitesse()));
    }
}
