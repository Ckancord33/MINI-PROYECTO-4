package org.example.eiscuno.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;
import org.example.eiscuno.view.GameUnoStage;
import org.example.eiscuno.view.WelcomeUnoStage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class WelcomeUnoController {

    @FXML
    private ImageView bgImageView;
    @FXML
    private Button playButton;



    private int indiceImagen = 0;
    Image bg1 = new Image(getClass().getResource("/org/example/eiscuno/images/bg1.jpg").toExternalForm());
    Image bg2 = new Image(getClass().getResource("/org/example/eiscuno/images/bg2.jpg").toExternalForm());
    Image bg3 = new Image(getClass().getResource("/org/example/eiscuno/images/bg3.jpg").toExternalForm());
    private final String[] imagenes = {
            "/org/example/eiscuno/images/bg1.jpg",
            "/org/example/eiscuno/images/bg2.jpg",
            "/org/example/eiscuno/images/bg3.jpg"
    };

    public void initialize(){
        cambiarImagen(bgImageView);

        // Timeline para cambiar imágenes
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(10), event -> cambiarImagen(bgImageView))
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // Repetir para siempre
        timeline.play();
    }


    @FXML
    public void onHandlePlayButton(ActionEvent event)throws IOException {
        startSound();
        GameUnoStage.getInstance();
        WelcomeUnoStage.deleteInstance();
    }

    @FXML
    public void onHandleEnteredPlayButton() {
        bSound();
        playButton.setStyle(
                "-fx-background-image: url('../resources/org/example/eiscuno/playButton.png');"+
                "-fx-cursor: hand;\n" +
                "    -fx-scale-x: 1.1;\n" +
                "    -fx-scale-y: 1.1;\n" );
    }

    @FXML
    void onHandleExitedPlayButton(MouseEvent event) {
        playButton.setStyle(
                "-fx-background-image: url('../resources/org/example/eiscuno/playButton.png');"+
                "-fx-cursor: pointer;"+
                "-fx-scale-x: 1.0;\n" +
                "-fx-scale-y: 1.0;\n"
        );
    }

    @FXML
    void onHandleExitButton(ActionEvent event) {
        WelcomeUnoStage.deleteInstance();
    }


    public void playSound(String nombreSonido, float volumen){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            if (volume != null) {
                volume.setValue(volumen);
            }

        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

    public void bSound(){
        playSound("src/main/resources/org/example/eiscuno/sounds/minecraft-drop-block-sound-effect.wav",0);
    }

    public void startSound(){
        playSound("src/main/resources/org/example/eiscuno/sounds/minecraft_click.wav",0);
    }


    private void cambiarImagen(ImageView imageView) {
        if(indiceImagen == 0){
            Image nuevaImagen = bg1;
            imageView.setImage(nuevaImagen);
        } else if (indiceImagen == 1) {
            Image nuevaImagen = bg2;
            imageView.setImage(nuevaImagen);
        }else if (indiceImagen == 2) {
            Image nuevaImagen = bg3;
            imageView.setImage(nuevaImagen);
        }


        // Cambiar al siguiente índice
        indiceImagen = (indiceImagen + 1) % imagenes.length;
    }

}
