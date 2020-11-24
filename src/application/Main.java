package application;

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
        setMusic("src/audio/menu.mp3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setMusic(String musicURL){
        Media music = new Media(new File(musicURL).toURI().toString());
        if(null != player){
            player.stop();
        }
        player = new MediaPlayer(music);
        player.setAutoPlay(true);
    }
}
