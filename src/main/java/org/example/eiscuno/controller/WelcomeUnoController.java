package org.example.eiscuno.controller;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.example.eiscuno.fileManager.FileManager;
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
    @FXML
    private Label optionsLabel;
    @FXML
    private Label optionsLabel2;
    @FXML
    private Button musicChange;
    @FXML
    private Button increase;
    @FXML
    private Button playMusic;
    @FXML
    private Button pauseMusic;
    @FXML
    private Button decrease;
    @FXML
    private ImageView optionsImageView;
    @FXML
    private Label musicLabel;
    @FXML
    private Circle biomeCircle;
    @FXML
    private Circle characterCircle;
    @FXML
    private HBox hBox;
    int controlSonido = 0;
    int character = 1;
    int biome = 1;
    ImageView characterImageView = new ImageView();
    ImageView biomeImageView = new ImageView();



    MusicPlayer musicPlayer = MusicPlayer.getInstance();



    private int indiceImagen = 1;
    private int indiceMensaje = 0;
    Image bg1 = new Image(getClass().getResource("/org/example/eiscuno/images/bg1.jpg").toExternalForm());
    Image bg2 = new Image(getClass().getResource("/org/example/eiscuno/images/bg2.jpg").toExternalForm());
    Image bg3 = new Image(getClass().getResource("/org/example/eiscuno/images/bg3.jpg").toExternalForm());
    Image bg4 = new Image(getClass().getResource("/org/example/eiscuno/images/bg4.jpg").toExternalForm());
    Image bgOpt = new Image(getClass().getResource("/org/example/eiscuno/images/bgOpt.jpg").toExternalForm());
    Image c1 = new Image(getClass().getResource("/org/example/eiscuno/images/steve.png").toExternalForm());
    Image c2 = new Image(getClass().getResource("/org/example/eiscuno/images/chicken.png").toExternalForm());
    Image c3 = new Image(getClass().getResource("/org/example/eiscuno/images/enderman.png").toExternalForm());
    Image c4 = new Image(getClass().getResource("/org/example/eiscuno/images/piglin.png").toExternalForm());
    Image b1 = new Image(getClass().getResource("/org/example/eiscuno/images/overworld.png").toExternalForm());
    Image b2 = new Image(getClass().getResource("/org/example/eiscuno/images/nether.png").toExternalForm());
    Image b3 = new Image(getClass().getResource("/org/example/eiscuno/images/end.png").toExternalForm());

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

        if (FileManager.loadCharacter()==1){
            characterChange(c1);
        }else if (FileManager.loadCharacter()==2){
            characterChange(c2);
        } else if (FileManager.loadCharacter()==3) {
            characterChange(c3);
        } else if (FileManager.loadCharacter()==4) {
            characterChange(c4);
        }

        if(FileManager.loadBiome() == 1){
            biomeChange(b1);
        } else if (FileManager.loadBiome() == 2) {
            biomeChange(b2);
        }else if (FileManager.loadBiome() == 3) {
            biomeChange(b3);
        }


        hBox.setMouseTransparent(true);
        hBox.setVisible(false);



        handleMusicRev();
        optionsImageView.setImage(bgOpt);

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

    public int getBiome(){
        return biome;
    }
    public int getCharacter(){
        return character;
    }

    public void characterChange(Image image){

        characterImageView.setImage(image);

        // Crear un ImageView y ajustar el tamaño
        double radius = characterCircle.getRadius();
        characterImageView.setFitWidth(radius * 2);
        characterImageView.setFitHeight(radius * 2);

        // Crear un círculo para recortar
        Circle clip = new Circle(radius);
        clip.setCenterX(radius);
        clip.setCenterY(radius);

        // Aplicar el clip
        characterImageView.setClip(clip);

        // Centrar la imagen en el contenedor
        characterImageView.setLayoutX(characterCircle.getLayoutX() - radius);
        characterImageView.setLayoutY(characterCircle.getLayoutY() - radius);

        // Agregar al contenedor
        Pane parent = (Pane) characterCircle.getParent();
        int circleIndex = parent.getChildren().indexOf(characterCircle);
        if(!parent.getChildren().isEmpty()){
        parent.getChildren().remove(characterImageView);
        }
        parent.getChildren().add(circleIndex + 1, characterImageView);

    }
    public void biomeChange(Image image){

        biomeImageView.setImage(image);

        // Crear un ImageView y ajustar el tamaño
        double radius = biomeCircle.getRadius();
        biomeImageView.setFitWidth(radius * 2);
        biomeImageView.setFitHeight(radius * 2);

        // Crear un círculo para recortar
        Circle clip = new Circle(radius);
        clip.setCenterX(radius);
        clip.setCenterY(radius);

            // Aplicar el clip
        biomeImageView.setClip(clip);

        // Centrar la imagen en el contenedor
        biomeImageView.setLayoutX(biomeCircle.getLayoutX() - radius);
        biomeImageView.setLayoutY(biomeCircle.getLayoutY() - radius);

        // Agregar al contenedor
        Pane parent = (Pane) biomeCircle.getParent();
        int circleIndex = parent.getChildren().indexOf(biomeCircle);
        if(!parent.getChildren().isEmpty()){
        parent.getChildren().remove(biomeImageView);
        }
        parent.getChildren().add(circleIndex + 1, biomeImageView);

    }

    public void hBoxTransition(){
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1),hBox);
        if(hBox.isVisible()) {
            transition.setFromY(0);
            transition.setToY(-optionsImageView.getFitHeight());
            transition.setOnFinished(actionEvent -> {
                hBox.setVisible(false);
                hBox.setMouseTransparent(true);
                optionsImageView.setMouseTransparent(true);
                optionsImageView.setVisible(false);
                optionsLabel.setVisible(false);
                optionsLabel2.setVisible(false);
                hBox.getChildren().clear();
            });
        } else if (!hBox.isVisible()) {
            transition.setFromY(-optionsImageView.getFitHeight());
            transition.setToY(0);

        }
        transition.play();
    }

    public void handleCharacter(){
        hBoxTransition();

        ImageView CI1 = new ImageView(c1);
        CI1.setFitHeight(hBox.getHeight());
        CI1.setFitWidth(hBox.getWidth()/4);
        CI1.setOnMousePressed(mouseEvent -> {
            characterChange(c1);
            hBoxTransition();
            character = 1;
            FileManager.updateLine(0, String.valueOf(character));
            System.out.println(character);
            System.out.println(getCharacter());
        });
        CI1.getStyleClass().add("imageView1");

        ImageView CI2 = new ImageView(c2);
        CI2.setFitHeight(hBox.getHeight());
        CI2.setFitWidth(hBox.getWidth()/4);
        CI2.setOnMousePressed(mouseEvent -> {
            characterChange(c2);
            hBoxTransition();
            character = 2;
            System.out.println(character);
            FileManager.updateLine(0, String.valueOf(character));
            System.out.println(getCharacter());
        });
        CI2.getStyleClass().add("imageView1");


        ImageView CI3 = new ImageView(c3);
        CI3.setFitHeight(hBox.getHeight());
        CI3.setFitWidth(hBox.getWidth()/4);
        CI3.setOnMousePressed(mouseEvent -> {
            characterChange(c3);
            hBoxTransition();
            character = 3;
            System.out.println(character);
            FileManager.updateLine(0, String.valueOf(character));
            System.out.println(getCharacter());
        });
        CI3.getStyleClass().add("imageView1");


        ImageView CI4 = new ImageView(c4);
        CI4.setFitHeight(hBox.getHeight());
        CI4.setFitWidth(hBox.getWidth()/4);
        CI4.setOnMousePressed(mouseEvent -> {
            characterChange(c4);
            hBoxTransition();
            character = 4;
            System.out.println(character);
            FileManager.updateLine(0, String.valueOf(character));
            System.out.println(getCharacter());
        });
        CI4.getStyleClass().add("imageView1");



        hBox.getChildren().addAll(CI1,CI2,CI3,CI4);

        optionsLabel.setText("SELECCIONA TU PERSONAJE");
        optionsLabel2.setText("SELECCIONA TU PERSONAJE");
        optionsLabel.setVisible(true);
        optionsLabel2.setVisible(true);

        hBox.setVisible(true);
        hBox.setMouseTransparent(false);
        optionsImageView.setMouseTransparent(false);
        optionsImageView.setVisible(true);

    }

public void handleBiome(){
        hBoxTransition();

    ImageView BI1 = new ImageView(b1);
    BI1.setFitHeight(hBox.getHeight());
    BI1.setFitWidth(hBox.getWidth()/3);
    BI1.setOnMousePressed(mouseEvent -> {
            biomeChange(b1);
            hBoxTransition();
            biome = 1;
        FileManager.updateLine(1, String.valueOf(biome));
        });
    BI1.getStyleClass().add("imageView1");

    ImageView BI2 = new ImageView(b2);
    BI2.setFitHeight(hBox.getHeight());
    BI2.setFitWidth(hBox.getWidth()/3);
    BI2.setOnMousePressed(mouseEvent -> {
        biomeChange(b2);
        hBoxTransition();
        biome = 2;
        FileManager.updateLine(1, String.valueOf(biome));
    });
    BI2.getStyleClass().add("imageView1");

    ImageView BI3 = new ImageView(b3);
    BI3.setFitHeight(hBox.getHeight());
    BI3.setFitWidth(hBox.getWidth()/3);
    BI3.setOnMousePressed(mouseEvent -> {
        biomeChange(b3);
        hBoxTransition();
        biome = 3;
        FileManager.updateLine(1, String.valueOf(biome));
        System.out.println(String.valueOf(biome));
    });
    BI3.getStyleClass().add("imageView1");


    hBox.getChildren().addAll(BI1,BI2,BI3);

        optionsLabel.setText("SELECCIONA TU PERSONAJE");
        optionsLabel2.setText("SELECCIONA TU PERSONAJE");
        optionsLabel.setVisible(true);
        optionsLabel2.setVisible(true);

        hBox.setVisible(true);
        hBox.setMouseTransparent(false);
        optionsImageView.setMouseTransparent(false);
        optionsImageView.setVisible(true);
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

    public void handleMusic(){
        optionsLabel.setVisible(true);

        optionsLabel2.setVisible(true);

        musicChange.setVisible(true);
        musicChange.setDisable(false);

        increase.setVisible(true);
        increase.setDisable(false);

        playMusic.setVisible(true);
        playMusic.setDisable(false);

        pauseMusic.setVisible(true);
        pauseMusic.setDisable(false);

        decrease.setVisible(true);
        decrease.setDisable(false);

        optionsImageView.setVisible(true);
        optionsImageView.setMouseTransparent(false);

        musicLabel.setVisible(true);

        optionsLabel.setText("MUSIC OPTIONS");
        optionsLabel2.setText("MUSIC OPTIONS");
    }

    public void handleMusicRev(){
        if(controlSonido > 0) {
            clickSound();
        }
        controlSonido++;
        optionsLabel.setVisible(false);

        optionsLabel2.setVisible(false);

        musicChange.setVisible(false);
        musicChange.setDisable(true);

        increase.setVisible(false);
        increase.setDisable(true);

        playMusic.setVisible(false);
        playMusic.setDisable(true);

        pauseMusic.setVisible(false);
        pauseMusic.setDisable(true);

        decrease.setVisible(false);
        decrease.setDisable(true);

        optionsImageView.setVisible(false);
        optionsImageView.setMouseTransparent(true);

        musicLabel.setVisible(false);
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
