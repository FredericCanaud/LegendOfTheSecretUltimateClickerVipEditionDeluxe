package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;

import static controllers.HelperController.*;

/**
 * Classe de controller FXML pour l'écran de fin de Combat
 * Scène précédente : Combat
 * Scène suivante : Entracte
 *
 * L'utilisateur peut sortir de trois façons d'un combat :
 *    - En ayant gagné le combat : Il remporte l'or de l'ennemi et de l'expérience
 *    - En ayant perdu le combat : Il ne remporte pas d'or mais de l'expérience
 *    - En ayant fuit le combat : Il ne remporte rien
 *
 * Une sauvegarde de l'état actuel du personnage est faite à la fin du combat
 * @author Frédéric CANAUD / Thomas CAMPREDON
 */

public class FinCombatController {

    @FXML
    private AnchorPane finCombatPane;

    @FXML
    private Text resultatText;

    @FXML
    private Text orText;

    @FXML
    private Text experienceText;

    @FXML
    private Text niveauSuperieurText;

    @FXML
    void initialize() {
        int or = 0;
        int experience = 0;

        if (joueur.estKO()) {
            resultatText.setText("DEFAITE !");
            experience = ennemi.getNiveau() * 10;
            setMusic("src/views/assets/audio/defaite.mp3",0.2, false);
        } else if (ennemi.estKO()) {
            resultatText.setText("VICTOIRE !");
            or = ennemi.getOr();
            experience = ennemi.getNiveau() * 50;
            setMusic("src/views/assets/audio/victoire.mp3",0.2, false);
        } else {
            resultatText.setText("Vous avez pris la fuite !");
            setMusic("src/views/assets/audio/fuite.mp3",0.2, false);
        }

        if (joueur.plusExp(experience) && joueur.getNiveau() > 2) {
            niveauSuperieurText.setText("Niveau supérieur !!! Compétence dévérouillée !");
        } else if (joueur.plusExp(experience)) {
            niveauSuperieurText.setText("Niveau supérieur !!!");
        } else niveauSuperieurText.setText(null);
        joueur.ajouterOr(or);

        orText.setText(String.valueOf(or));
        experienceText.setText(String.valueOf(experience * 2));
    }

    @FXML
    void continueButtonEvent() throws IOException {
        joueur.reset();
        game.addSave(joueur);
        setMusic("src/views/assets/audio/menu.mp3",0.2, true);
        URL url = this.getClass().getClassLoader().getResource("views/Entracte.fxml");
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        finCombatPane.getChildren().setAll(pane);
    }
}
