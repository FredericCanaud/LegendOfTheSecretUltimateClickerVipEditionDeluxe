package controllers;

import models.personnages.Personnage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import static controllers.HelperController.*;

/**
 * Classe de controller FXML pour l'écran de séléction d'une sauvegarde
 * Scène précédente : Ecran titre
 * Scène suivante : Creer un personnage, Entracte (Après choix du perso)
 *
 * L'utilisateur peut choisir entre 3 fichiers de sauvegardes (vide ou existant)
 * Si existant -> Redirection vers scène d'entracte
 * Si vide -> Redirection vers scène de création de perso
 *
 * L'utilisateur peut supprimer une sauvegarde, après avoir cliqué sur un bouton,
 * puis après avoir confirmé la suppression par l'intermédiaire d'un pop-up.
 *
 * @author Frédéric CANAUD / Thomas CAMPREDON
 */

public class ChoixSauvegardeController {

    private Personnage[] personnages;

    @FXML
    private AnchorPane loadGamePane;

    @FXML
    private ImageView imageSlot1;

    @FXML
    private ImageView imageSlot2;

    @FXML
    private ImageView imageSlot3;

    @FXML
    private Button buttonSlot1;

    @FXML
    private Button buttonSlot2;

    @FXML
    private Button buttonSlot3;

    @FXML
    private Label nameSlot1;

    @FXML
    private Label nameSlot2;

    @FXML
    private Label nameSlot3;

    @FXML
    private Label typeSlot1;

    @FXML
    private Label typeSlot2;

    @FXML
    private Label typeSlot3;

    @FXML
    private Label goldSlot1;

    @FXML
    private Label goldSlot2;

    @FXML
    private Label goldSlot3;

    @FXML
    private Label levelSlot1;

    @FXML
    private Label levelSlot2;

    @FXML
    private Label levelSlot3;

    @FXML
    private Button deleteSlot1;

    @FXML
    private Button deleteSlot2;

    @FXML
    private Button deleteSlot3;

    @FXML
    private Label labelSlot1;

    @FXML
    private Label labelSlot2;

    @FXML
    private Label labelSlot3;

    @FXML
    private void initialize() {
        setMusic("src/views/assets/audio/menu.mp3",0.2, true);
        update();
    }

    @FXML
    void slotButtonEvent(ActionEvent event) throws IOException {
        int slot = 0;
        if (event.getSource().equals(buttonSlot1)) slot = 1;
        else if (event.getSource().equals(buttonSlot2)) slot = 2;
        else if (event.getSource().equals(buttonSlot3)) slot = 3;

        URL url;
        if (personnages.length >= slot) {
            joueur = personnages[slot - 1];
            url = this.getClass().getClassLoader().getResource("views/Entracte.fxml");
        } else {
            url = this.getClass().getClassLoader().getResource("views/CreerPersonnage.fxml");
        }
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        loadGamePane.getChildren().setAll(pane);
    }

    @FXML
    void deleteButtonEvent(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression de la sauvegarde ?");
        alert.setHeaderText("Suppression de la sauvegarde");
        alert.setContentText("Vous ne pourrez plus revoir votre personnage ! Etes-vous sûr ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.CANCEL) return;

        if (event.getSource().equals(deleteSlot1)) game.deleteSave(personnages[0]);
        else if (event.getSource().equals(deleteSlot2)) game.deleteSave(personnages[1]);
        else game.deleteSave(personnages[2]);
        update();
    }

    @FXML
    void backButtonEvent() throws IOException {
        URL url = this.getClass().getClassLoader().getResource("views/Start.fxml");
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        loadGamePane.getChildren().setAll(pane);
    }

    private void update() {
        clear();
        game.loadSave();
        personnages = game.getPersonnages();
        if (personnages.length > 0) {
            imageSlot1.setImage(personnages[0].getImage(false));
            nameSlot1.setText(personnages[0].getNom());
            typeSlot1.setText(personnages[0].getType().toString());
            goldSlot1.setText("OR " + personnages[0].getOr());
            levelSlot1.setText(String.valueOf(personnages[0].getNiveau()));
            labelSlot1.setText("NIV");
            deleteSlot1.setDisable(false);
        }
        if (personnages.length > 1) {
            imageSlot2.setImage(personnages[1].getImage(false));
            nameSlot2.setText(personnages[1].getNom());
            typeSlot2.setText(personnages[1].getType().toString());
            goldSlot2.setText("OR " + personnages[1].getOr());
            levelSlot2.setText(String.valueOf(personnages[1].getNiveau()));
            labelSlot2.setText("NIV");
            deleteSlot2.setDisable(false);
        }
        if (personnages.length > 2) {
            imageSlot3.setImage(personnages[2].getImage(false));
            nameSlot3.setText(personnages[2].getNom());
            typeSlot3.setText(personnages[2].getType().toString());
            goldSlot3.setText("OR " + personnages[2].getOr());
            levelSlot3.setText(String.valueOf(personnages[2].getNiveau()));
            labelSlot3.setText("NIV");
            deleteSlot3.setDisable(false);
        }
    }

    private void clear() {
        imageSlot1.setImage(null);
        nameSlot1.setText("VIDE");
        typeSlot1.setText("Nouveau");
        goldSlot1.setText(null);
        levelSlot1.setText(null);
        deleteSlot1.setDisable(true);
        labelSlot1.setText(null);

        imageSlot2.setImage(null);
        nameSlot2.setText("VIDE");
        typeSlot2.setText("Nouveau");
        goldSlot2.setText(null);
        levelSlot2.setText(null);
        deleteSlot2.setDisable(true);
        labelSlot2.setText(null);

        imageSlot3.setImage(null);
        nameSlot3.setText("VIDE");
        typeSlot3.setText("Nouveau");
        goldSlot3.setText(null);
        levelSlot3.setText(null);
        deleteSlot3.setDisable(true);
        labelSlot3.setText(null);
    }
}
