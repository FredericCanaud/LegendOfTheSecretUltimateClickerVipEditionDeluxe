package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;

import static controllers.HelperController.joueur;

/**
 * Classe de controller FXML pour l'écran de pause d'un personnage
 * Scène précédente : Fin d'un combat / Choix d'une sauvegarde
 * Scène suivante : Choix d'un ennemi / Choix d'une sauvegarde
 *
 * Le joueur peut consulter les stats de son personnage avant d'aller
 * combattre un ennemi.
 *
 * @author Frédéric CANAUD / Thomas CAMPREDON
 */

public class EntracteController {

    @FXML
    private AnchorPane entractePane;

    @FXML
    private ProgressBar barExp;

    @FXML
    private ProgressBar barPV;

    @FXML
    private ProgressBar barAttaque;

    @FXML
    private ProgressBar barMana;

    @FXML
    private ProgressBar barDefense;

    @FXML
    private ProgressBar barVitesse;

    @FXML
    private Text nomText;

    @FXML
    private Text niveauText;

    @FXML
    private Text orText;

    @FXML
    private Text expMaxText;

    @FXML
    private Text expCurrentText;

    @FXML
    private Text PVText;

    @FXML
    private Text attaqueText;

    @FXML
    private Text manaText;

    @FXML
    private Text defenseText;

    @FXML
    private Text vitesseText;

    @FXML
    private Button boutonCombat;

    @FXML
    private Button boutonBoutique;

    @FXML
    private Button boutonRetour;

    @FXML
    private ImageView imageView;

    @FXML
    void initialize() {
        imageView.setImage(joueur.getImage(false));
        nomText.setText(joueur.getNom());
        niveauText.setText(String.valueOf(joueur.getNiveau()));
        orText.setText(String.valueOf(joueur.getOr()));
        expCurrentText.setText(String.format("%d", joueur.getExperience()));
        expMaxText.setText(String.format("/ %d", joueur.getCriteria()));
        barExp.setProgress(joueur.getExperience() / (double) joueur.getCriteria());
        PVText.setText(String.format("%.0f", joueur.getStatus().getPointsDeVieRestants()));
        barPV.setProgress(joueur.getStatus().getPointsDeVieRestants() / 1000);
        attaqueText.setText(String.format("%.0f", joueur.getStatus().getAttaque()));
        barAttaque.setProgress(joueur.getStatus().getAttaque() / 1000);
        manaText.setText(String.format("%.0f", joueur.getStatus().getPointsDeManaMax()));
        barMana.setProgress(joueur.getStatus().getPointsDeManaMax() / 1000);
        defenseText.setText(String.format("%.0f", joueur.getStatus().getDefense()));
        barDefense.setProgress(joueur.getStatus().getDefense() / 500);
        vitesseText.setText(String.format("%.0f", joueur.getStatus().getVitesse()));
        barVitesse.setProgress(joueur.getStatus().getVitesse() / 250);
    }

    @FXML
    void buttonEvent(ActionEvent event) throws IOException {
        URL url = null;
        if (event.getSource().equals(boutonCombat)) {
            url = this.getClass().getClassLoader().getResource("views/ChoixEnnemi.fxml");
        } else if (event.getSource().equals(boutonBoutique)) {
            url = this.getClass().getClassLoader().getResource("views/Boutique.fxml");
        } else if (event.getSource().equals(boutonRetour)) {
            url = this.getClass().getClassLoader().getResource("views/ChoixSauvegarde.fxml");
            joueur = null;
        }
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        entractePane.getChildren().setAll(pane);
    }
}
