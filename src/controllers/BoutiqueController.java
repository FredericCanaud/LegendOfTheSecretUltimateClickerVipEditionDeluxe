package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import models.equipements.*;
import models.personnages.Chasseur;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import static controllers.HelperController.*;

/**
 * Controller FXML de la boutique
 *
 * @author Frédéric CANAUD / Thomas CAMPREDON
 */
public class BoutiqueController {

    private Equipement[] equipementsBoutique;
    private int prix1;
    private int prix2;

    @FXML
    private AnchorPane boutiquePane;

    @FXML
    private ImageView imageView;

    @FXML
    public Button buttonSlotAchat1;

    @FXML
    public Button buttonSlotAchat2;

    @FXML
    public Label nomArmeSlot1;

    @FXML
    public Label pointsArmeSlot1;

    @FXML
    public Label orArmeSlot1;

    @FXML
    public Label nomArmeSlot2;

    @FXML
    public Label pointsArmeSlot2;

    @FXML
    public Label orArmeSlot2;

    @FXML
    public Button retourButton;

    @FXML
    public Label erreurOr;

    @FXML
    void initialize() throws FileNotFoundException {
        equipementsBoutique = game.getEquipementsBoutique(joueur);
        if(equipementsBoutique[0] instanceof Arme || equipementsBoutique[0] instanceof SortOffensif){
            nomArmeSlot1.setText(equipementsBoutique[0].getNom());
            if(equipementsBoutique[0] instanceof  SortOffensif){
                pointsArmeSlot1.setText("Dégats : " + ((SortOffensif) equipementsBoutique[0]).getDegats() + "\n Cout Mana : " + ((SortOffensif) equipementsBoutique[0]).getCoutMana());
            } else{
                pointsArmeSlot1.setText("Dégats : " + ((Arme) equipementsBoutique[0]).getDegats());
            }
        } else if(equipementsBoutique[0] instanceof SortSoin){
            nomArmeSlot1.setText(equipementsBoutique[0].getNom());
            pointsArmeSlot1.setText("Soin : " + ((SortSoin) equipementsBoutique[0]).getSoin() + "\n Cout Mana : " + ((SortSoin) equipementsBoutique[0]).getCoutMana());
        } else {
            nomArmeSlot1.setText("Boost Arc");
            pointsArmeSlot1.setText("+ 20 flèches");
        }
        orArmeSlot1.setText("Cout Or : " + joueur.getNiveau() * 10);
        prix1 = joueur.getNiveau() * 10;

        if (joueur.getNiveau() >= 3) {
            nomArmeSlot2.setText(equipementsBoutique[1].getNom());
            if(equipementsBoutique[1] instanceof Arme || equipementsBoutique[1] instanceof  SortOffensif){
                if(equipementsBoutique[1] instanceof  SortOffensif){
                    pointsArmeSlot2.setText("Dégats : " + ((SortOffensif) equipementsBoutique[1]).getDegats() + "\n Cout Mana : " + ((SortOffensif) equipementsBoutique[1]).getCoutMana());
                } else{
                    pointsArmeSlot2.setText("Dégats : " + ((Arme) equipementsBoutique[1]).getDegats());
                }
            } else {
                pointsArmeSlot2.setText("Soin : " + ((SortSoin) equipementsBoutique[1]).getSoin() + "\n Cout Mana : " + ((SortSoin) equipementsBoutique[1]).getCoutMana());
            }
            orArmeSlot2.setText("Cout Or : " + joueur.getNiveau() * 10);
            prix2 = joueur.getNiveau() * 10;
        }
    }
    @FXML
    void retourButtonEvent() throws IOException {
        URL url = this.getClass().getClassLoader().getResource("views/Entracte.fxml");
        if (url == null) return;
        AnchorPane pane = FXMLLoader.load(url);
        boutiquePane.getChildren().setAll(pane);
    }

    public void choisirEquipement(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource().equals(buttonSlotAchat1)) {
            if (prix1 <= joueur.getOr()){
                if(joueur instanceof Chasseur){
                    ((Arc) joueur.getEquipement()[0]).setDurabilite(30);
                    ((Chasseur) joueur).addFleches(20);
                } else {
                    joueur.setEquipement(new Equipement[]{equipementsBoutique[0],joueur.getEquipement()[1]});
                }
                joueur.setOr(joueur.getOr() - prix1);
                URL url = this.getClass().getClassLoader().getResource("views/Entracte.fxml");
                if (url == null) return;
                AnchorPane pane = FXMLLoader.load(url);
                boutiquePane.getChildren().setAll(pane);
            } else {
                erreurOr.setVisible(true);
            }
        } else if (actionEvent.getSource().equals(buttonSlotAchat2)) {
            if(joueur.getNiveau() >= 3){
                if (prix2 <= joueur.getOr()){
                    joueur.setOr(joueur.getOr() - prix2);
                    joueur.setEquipement(new Equipement[]{joueur.getEquipement()[0],equipementsBoutique[1]});
                    URL url = this.getClass().getClassLoader().getResource("views/Entracte.fxml");
                    if (url == null) return;
                    AnchorPane pane = FXMLLoader.load(url);
                    boutiquePane.getChildren().setAll(pane);
                } else {
                    erreurOr.setVisible(true);
                }
            }
        }

    }
}
