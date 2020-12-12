package controllers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import models.GameHelper;
import models.personnages.Personnage;

import java.io.File;

/**
 * Classe de controller d'aide
 *
 * Permet d'accéder dans les controllers aux variables du jeu,
 * du joueur ou de l'ennemi en combat
 *
 * @author Frédéric CANAUD / Thomas CAMPREDON
 */

class HelperController {
    public static GameHelper game = new GameHelper();
    public static Personnage joueur;
    public static Personnage ennemi;
    static MediaPlayer player;

    public static void setMusic(String musicURL, double volume, boolean repeter){
        Media music = new Media(new File(musicURL).toURI().toString());
        if(null != player && !player.getMedia().getSource().endsWith(musicURL)){
            player.stop();
        }
        if(null == player || !player.getMedia().getSource().endsWith(musicURL)) {
            player = new MediaPlayer(music);
            if(repeter){
                player.setCycleCount(MediaPlayer.INDEFINITE);
            } else{
                player.setCycleCount(1);
            }
            player.setAutoPlay(true);
            player.setVolume(volume);
        }
    }
}
