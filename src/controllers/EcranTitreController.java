package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

/**
 * Classe de controller FXML pour l'écran titre
 * Scène suivante : Charger une sauvegarde
 *
 * L'utilisateur peut choisir entre 3 fichiers de sauvegardes (vide ou existant)
 * Si existant -> Redirection vers scène d'entracte
 * Si vide -> Redirection vers scène de création de perso
 *
 * L'utilisateur peut supprimer une sauvegarde, après avoir cliqué sur un bouton,
 * puis après avoir confirmé la suppression par l'intermédiaire d'un pop-up.
 * @author Frédéric CANAUD / Thomas CAMPREDON
 */

public class EcranTitreController {
    @FXML
    private AnchorPane startPane;

    @FXML
    void startButtonEvent() throws IOException {
        URL url;
        url = this.getClass().getClassLoader().getResource("views/ChoixSauvegarde.fxml");
        if (url == null) {
            System.out.println("NULL");
            return;
        }
        AnchorPane pane = FXMLLoader.load(url);
        this.startPane.getChildren().setAll(pane);
    }
}
