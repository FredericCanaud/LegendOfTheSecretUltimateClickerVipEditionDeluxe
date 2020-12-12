package controllers;

import models.personnages.Personnage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

import static controllers.HelperController.*;

/**
 * Controller FXML du choix de l'ennemi
 *
 * @author Frédéric CANAUD / Thomas CAMPREDON
 */
public class ChoixEnnemiController {
    private Personnage[] ennemis;

    @FXML
    private AnchorPane choixEnnemiPane;

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
    private Label nomSlot1;

    @FXML
    private Label nomSlot2;

    @FXML
    private Label nomSlot3;

    @FXML
    private Label typeSlot1;

    @FXML
    private Label typeSlot2;

    @FXML
    private Label typeSlot3;

    @FXML
    private Label orSlot1;

    @FXML
    private Label orSlot2;

    @FXML
    private Label orSlot3;

    @FXML
    private Label niveauSlot1;

    @FXML
    private Label niveauSlot2;

    @FXML
    private Label niveauSlot3;

    @FXML
    void initialize() {
        ennemis = game.getEnnemi(joueur.getNiveau());

        nomSlot1.setText(ennemis[0].getNom());
        typeSlot1.setText(String.valueOf(ennemis[0].getType()));
        orSlot1.setText("Or : " + ennemis[0].getOr());
        niveauSlot1.setText("Lvl : " + ennemis[0].getNiveau());
        imageSlot1.setImage(ennemis[0].getImage(false));

        nomSlot2.setText(ennemis[1].getNom());
        typeSlot2.setText(String.valueOf(ennemis[1].getType()));
        orSlot2.setText("Or : " + ennemis[1].getOr());
        niveauSlot2.setText("Lvl : " + ennemis[1].getNiveau());
        imageSlot2.setImage(ennemis[1].getImage(false));

        nomSlot3.setText(ennemis[2].getNom());
        typeSlot3.setText(String.valueOf(ennemis[2].getType()));
        orSlot3.setText("Or : " + ennemis[2].getOr());
        niveauSlot3.setText("Lvl : " + ennemis[2].getNiveau());
        imageSlot3.setImage(ennemis[2].getImage(false));
    }

    @FXML
    void slotButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(buttonSlot1)) {
            ennemi = ennemis[0];
        } else if (event.getSource().equals(buttonSlot2)) {
            ennemi = ennemis[1];
        } else if (event.getSource().equals(buttonSlot3)) {
            ennemi = ennemis[2];
        }
        URL url = this.getClass().getClassLoader().getResource("views/Combat.fxml");
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        choixEnnemiPane.getChildren().setAll(pane);
    }

    @FXML
    void retourButtonEvent() throws IOException {
        URL url = this.getClass().getClassLoader().getResource("views/Entracte.fxml");
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        choixEnnemiPane.getChildren().setAll(pane);
    }

}
