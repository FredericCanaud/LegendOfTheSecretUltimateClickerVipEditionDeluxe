package application;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    public Group group;

    @FXML
    public Label classe;
    @FXML
    private Button creerPerso;

    @FXML
    public Group groupCreationClasse;
    @FXML
    public ToggleGroup toggleGroup;
    @FXML
    private ToggleButton creerMage;
    @FXML
    private ToggleButton creerArcher;
    @FXML
    private ToggleButton creerGuerrier;
    @FXML
    public TextField pseudo;
    public Label inputPseudo;
    public Button validerClasse;

    @FXML
    public void choisirClasse(MouseEvent mouseEvent) {
        creerPerso.setVisible(false);
        classe.setVisible(true);
        pseudo.setVisible(true);
        inputPseudo.setVisible(true);

        ImageView iv1 = new ImageView();
        ImageView iv2 = new ImageView();
        ImageView iv3 = new ImageView();

        Image archer = new Image("img/archer.jpg");
        iv1.setImage(archer);
        creerArcher.setGraphic(iv1);
        creerArcher.setVisible(true);

        Image warrior = new Image("img/warrior.jpg");
        iv2.setImage(warrior);
        creerGuerrier.setGraphic(iv2);
        creerGuerrier.setVisible(true);

        Image sorciere = new Image("img/sorciere.png");
        iv3.setImage(sorciere);
        creerMage.setGraphic(iv3);
        creerMage.setVisible(true);


    }

    public void commencerPartie(MouseEvent mouseEvent) {
        creerMage.setSelected(true);
        creerMage.setVisible(true);
        creerMage.setVisible(true);
    }
}
