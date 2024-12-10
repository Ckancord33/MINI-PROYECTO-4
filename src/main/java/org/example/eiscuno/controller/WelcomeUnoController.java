package org.example.eiscuno.controller;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.example.eiscuno.threads.MusicPlayer;
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
    @FXML
    private ImageView subtitle;
    @FXML
    private ImageView title;
    @FXML
    private Label message;

    MusicPlayer musicPlayer = MusicPlayer.getInstance();



    private int indiceImagen = 1;
    private int indiceMensaje = 0;
    Image bg1 = new Image(getClass().getResource("/org/example/eiscuno/images/bg1.jpg").toExternalForm());
    Image bg2 = new Image(getClass().getResource("/org/example/eiscuno/images/bg2.jpg").toExternalForm());
    Image bg3 = new Image(getClass().getResource("/org/example/eiscuno/images/bg3.jpg").toExternalForm());
    Image bg4 = new Image(getClass().getResource("/org/example/eiscuno/images/bg4.jpg").toExternalForm());
    private final String[] imagenes = {
            "",
            "",
            "",
            ""
    };
    private final String[] mensajes = {
            "ALSO TRY MINECRAFT",
            "TERRARIA'S GOOD",
            "LA MALA PA JUAN MANUEL",
            "XDDDDDDDDD",
            "NO SÉ QUE PONEEEERRRRR",
            "LOS +2 SI SE ACUMULAN"
    };

    public void initialize(){
        musicPlayer.startMusic("src/main/resources/org/example/eiscuno/sounds/1-08.-Minecraft.wav");

        imageChange(bgImageView);
        messageChange(message);
        labelTransition();

        // Timeline para cambiar imágenes
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(10), event -> imageChange(bgImageView))
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // Repetir para siempre
        timeline.play();

        title.setImage(new Image(getClass().getResource("/org/example/eiscuno/title.png").toExternalForm()));
        subtitle.setImage(new Image(getClass().getResource("/org/example/eiscuno/subtitle.png").toExternalForm()));

        // Timeline para cambiar mensajes
        Timeline messageTimeline = new Timeline(
                new KeyFrame(Duration.seconds(15), event -> messageChange(message))
        );
        messageTimeline.setCycleCount(Timeline.INDEFINITE); // Repetir para siempre
        messageTimeline.play();


    }

    private void messageChange(Label message) {
            message.setText(mensajes[indiceMensaje]);
            indiceMensaje = (indiceMensaje + 1) % mensajes.length;
    }
    private void labelTransition(){
        ScaleTransition zoomIn = new ScaleTransition(Duration.seconds(3), message);
        zoomIn.setFromX(1.0);  // Comienza con zoom
        zoomIn.setFromY(1.0);
        zoomIn.setToX(1.5);  // Termina en tamaño normal
        zoomIn.setToY(1.5);
        zoomIn.setAutoReverse(true);
        zoomIn.setCycleCount(Timeline.INDEFINITE);
        zoomIn.play();
    }


    @FXML
    public void onHandlePlayButton(ActionEvent event)throws IOException {
        GameUnoStage.getInstance();
        WelcomeUnoStage.deleteInstance();
    }


    @FXML
    void onHandleExitButton(ActionEvent event) {
        WelcomeUnoStage.deleteInstance();
        MusicPlayer.deleteInstance();
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

    public void hoverSound(){
        playSound("src/main/resources/org/example/eiscuno/sounds/minecraft-drop-block-sound-effect.wav",0);
    }

    public void clickSound(){
        playSound("src/main/resources/org/example/eiscuno/sounds/minecraft_click.wav",0);
    }


    private void imageChange(ImageView imageView) {
        if(indiceImagen == 0){
            Image nuevaImagen = bg1;
            imageView.setImage(nuevaImagen);
        } else if (indiceImagen == 1) {
            Image nuevaImagen = bg2;
            imageView.setImage(nuevaImagen);
        }else if (indiceImagen == 2) {
            Image nuevaImagen = bg3;
            imageView.setImage(nuevaImagen);
        }else if (indiceImagen == 3) {
            Image nuevaImagen = bg4;
            imageView.setImage(nuevaImagen);
        }


        // Crear animación de desplazamiento horizontal
        TranslateTransition mover = new TranslateTransition(Duration.seconds(10), imageView);
        mover.setFromX(0);  // Comienza fuera de la pantalla
        mover.setToX(-imageView.getFitWidth()/5);  // Termina centrado


        // Crear animación de zoom
        ScaleTransition zoom = new ScaleTransition(Duration.seconds(10), imageView);
        zoom.setFromX(1.5);  // Comienza con zoom
        zoom.setFromY(1.5);
        zoom.setToX(1.5);  // Termina en tamaño normal
        zoom.setToY(1.5);

        // Combinar ambas animaciones
        ParallelTransition animacion = new ParallelTransition(mover, zoom);
        animacion.play();

        // Cambiar al siguiente índice
        indiceImagen = (indiceImagen + 1) % imagenes.length;
    }

    public void handleMusicCange(){
        musicPlayer.changeTrack(musicPlayer.getTrack());
        musicPlayer.setTrack((musicPlayer.getTrack()+1)%4);
    }
    public void increaseVolume(){
        musicPlayer.increaseVolume();
    }
    public void decreaseVolume(){
        musicPlayer.decreaseVolume();
    }
    public void playMusic(){
       musicPlayer.resumeMusic();
    }
    public void pauseMusic(){
        musicPlayer.pauseMusic();
    }

}
