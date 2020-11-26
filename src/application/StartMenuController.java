package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class StartMenuController {

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
    private ToggleButton mage;
    @FXML
    private ToggleButton chasseur;
    @FXML
    private ToggleButton guerrier;
    @FXML
    public TextField pseudo;
    public Label inputPseudo;
    public Button validerClasse;

    @FXML
    public void choisirClasse(MouseEvent mouseEvent) {
        creerPerso.setVisible(false);
        groupCreationClasse.setVisible(true);

        ImageView iv1 = new ImageView();
        ImageView iv2 = new ImageView();
        ImageView iv3 = new ImageView();

        iv1.setImage(new Image("img/warrior.jpg"));
        guerrier.setGraphic(iv1);

        iv2.setImage(new Image("img/archer.jpg"));
        chasseur.setGraphic(iv2);

        iv3.setImage(new Image("img/mage.png"));
        mage.setGraphic(iv3);
    }

    public void commencerPartie(MouseEvent mouseEvent) throws IOException {

        Stage stage = (Stage) validerClasse.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ingame.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add("css/style.css");
        stage.setScene(scene);
        stage.show();
        Main.setMusic("src/audio/combat2.mp3", 0.07);
        String classeChoisie = ((ToggleButton) toggleGroup.getSelectedToggle()).getId();
        String nomHero = pseudo.getText();
        IngameController ingameController = loader.getController();
        ingameController.initData(classeChoisie, nomHero);
        ingameController.startFight();
    }
}
