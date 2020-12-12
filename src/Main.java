import java.io.*;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * Classe principale
 *
 * @author Frédéric CANAUD / Thomas CAMPREDON
 */

public class Main extends Application {
    final private static String START_SCENE = "views/Start.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = this.getClass().getClassLoader().getResource(START_SCENE);
        Parent root = FXMLLoader.load(url);

        Media media = new Media(new File("src/views/assets/video/intro.mp4").toURI().toString());
        MediaPlayer player = new MediaPlayer(media);

        primaryStage.setScene(new Scene(new Group(new MediaView(player)), 800, 600));

        player.play();

        primaryStage.setResizable(false);
        primaryStage.setTitle("Legend of the Secret : Ultimate Clicker Vip Edition Deluxe 4.2 Free");
        primaryStage.getIcons().add(new Image("views/assets/img/favicon.png"));
        primaryStage.show();

        player.setOnEndOfMedia(() -> {
            player.stop();
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
