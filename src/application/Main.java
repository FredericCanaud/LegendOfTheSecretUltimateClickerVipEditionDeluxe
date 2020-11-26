package application;

import classes.arme.Arc;
import classes.personnages.Chasseur;
import classes.personnages.Guerrier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;

public class Main extends Application {

    private static MediaPlayer player;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("startMenu.fxml"));
        primaryStage.setTitle("Super RPG Game");
        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add("css/style.css");
        setMusic("src/audio/menu.mp3", 0.25);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        Chasseur chasseur = new Chasseur();
		Guerrier guerrier = new Guerrier(30, 4, 1);

		chasseur.addArc(new Arc(2));
		chasseur.addFleches("FlecheDeGlace", 2);

		chasseur.addFleches("FlecheNormale", 3);
		chasseur.utiliseArc(0);

		chasseur.utiliseFleche("FlecheDeGlace");
		chasseur.infligerDegats(guerrier);
		chasseur.infligerDegats(guerrier);
		chasseur.utiliseFleche("FlecheNormale");

		chasseur.infligerDegats(guerrier);
		chasseur.infligerDegats(guerrier);
		chasseur.infligerDegats(guerrier);

		System.out.println(guerrier.getPointsDeVieRestants());
		launch(args);
    }

    public static void setMusic(String musicURL, double volume){
        Media music = new Media(new File(musicURL).toURI().toString());
        if(null != player){
            player.stop();
        }
        player = new MediaPlayer(music);
        player.setAutoPlay(true);
        player.setVolume(volume);
    }
}
