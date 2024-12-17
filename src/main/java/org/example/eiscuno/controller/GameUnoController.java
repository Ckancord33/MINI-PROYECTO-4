package org.example.eiscuno.controller;

import javafx.animation.*;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.example.eiscuno.fileManager.FileManager;
import javafx.util.Duration;
import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.deck.Deck;
import org.example.eiscuno.model.game.GameUno;
import org.example.eiscuno.model.machine.ThreadPlayMachine;
import org.example.eiscuno.model.machine.ThreadSingUNOMachine;
import org.example.eiscuno.model.player.Player;
import org.example.eiscuno.model.table.Table;
import org.example.eiscuno.model.unoenum.EISCUnoEnum;
import org.example.eiscuno.threads.MusicPlayer;
import org.example.eiscuno.view.GameUnoStage;
import org.example.eiscuno.view.WelcomeUnoStage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;

/**
 * Controller class for the Uno game.
 */
public class GameUnoController {

    @FXML
    private ImageView blueButton;

    @FXML
    private Button deckButton;

    @FXML
    private AnchorPane gamePane;

    @FXML
    private ImageView greenButton;

    @FXML
    private GridPane gridPaneCardsMachine;

    @FXML
    private GridPane gridPaneCardsPlayer;

    @FXML
    private ImageView redButton;

    @FXML
    private ImageView tableImageView;


    @FXML
    private ImageView yelowButton;

    @FXML
    private Label winLabel;

    @FXML
    private Label turnLabel;
    @FXML
    private Circle playerCircle;
    @FXML
    private Circle machineCircle;
    @FXML
    private ImageView bg;
    @FXML
    private ImageView deckCard;
    @FXML
    private ImageView deckCard2;
    @FXML
    private ImageView deckCard21;
    @FXML
    private ImageView deckCard1;

    @FXML
    private Button ButtonUno;

    @FXML
    private Circle c;
    @FXML
    private Group group;
    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;
    @FXML
    private ImageView deckCard12;
    @FXML
    private Button exitButton;
    @FXML
    private Rectangle rect;
    @FXML
    private Label turnLabel1;
    @FXML
    private Label winLabel1;
    @FXML
    private Button exitButton1;
    @FXML
    private ImageView playerCreeper;
    @FXML
    private ImageView machineCreeper;



    int indice = 0;
    private Player humanPlayer;
    private Player machinePlayer;
    private Deck deck;
    private Table table;
    private GameUno gameUno;
    private int posInitCardToShow;
    private ThreadSingUNOMachine threadSingUNOMachine;
    private ThreadPlayMachine threadPlayMachine;
    ImageView playerImageView = new ImageView();
    ImageView machineImageView = new ImageView();

    Image c1 = new Image(getClass().getResource("/org/example/eiscuno/images/steve.png").toExternalForm());
    Image c2 = new Image(getClass().getResource("/org/example/eiscuno/images/chicken.png").toExternalForm());
    Image c3 = new Image(getClass().getResource("/org/example/eiscuno/images/enderman.png").toExternalForm());
    Image c4 = new Image(getClass().getResource("/org/example/eiscuno/images/piglin.png").toExternalForm());

    private final String[] imagenesNether = {
            "/org/example/eiscuno/images/nether1.jpg",
            "/org/example/eiscuno/images/nether2.jpg",
            "/org/example/eiscuno/images/nether3.jpg",
            "/org/example/eiscuno/images/nether4.jpg",
            "/org/example/eiscuno/images/nether5.jpg",
            "/org/example/eiscuno/images/nether6.jpg",

    };

    private final String[] imagenesEnd = {
            "/org/example/eiscuno/images/end1.jpg",
            "/org/example/eiscuno/images/end2.jpg",
            "/org/example/eiscuno/images/end3.jpg",
            "/org/example/eiscuno/images/end4.jpg",
    };

    private final String[] imagenesOver = {
            "/org/example/eiscuno/images/o1.jpg",
            "/org/example/eiscuno/images/o2.jpg",
            "/org/example/eiscuno/images/o3.jpg",
            "/org/example/eiscuno/images/o4.jpg",
            "/org/example/eiscuno/images/o5.jpg"

    };



    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() throws IOException{

        exitButton1.setDisable(true);
        exitButton1.setVisible(false);
        rect.setVisible(false);
        deckButton.setEffect(new ImageInput(new Image(getClass().getResource("/org/example/eiscuno/cards-uno/card_uno.png").toExternalForm())));
        deckCard1.setImage(new Image(getClass().getResource("/org/example/eiscuno/cards-uno/card_uno.png").toExternalForm()));
        deckCard12.setImage(new Image(getClass().getResource("/org/example/eiscuno/cards-uno/card_uno.png").toExternalForm()));
        bgChange();
        deckCard.setImage(new Image(getClass().getResource("/org/example/eiscuno/cards-uno/card_uno.png").toExternalForm()));
        initVariables();
        gameUno.chooseFirstCard();
        tableImageView.setImage(table.getCurrentCardOnTheTable().getImage());

        this.gameUno.startGame();
        printCardsHumanPlayer();
        printCardsMachinePlayer();
        startColorVBoxButtons();
        updateTurnLabel();

        threadSingUNOMachine = new ThreadSingUNOMachine(this.humanPlayer.getCardsPlayer(),this.machinePlayer.getCardsPlayer(), this);
        Thread t = new Thread(threadSingUNOMachine, "ThreadSingUNO");
        t.start();

        threadPlayMachine = new ThreadPlayMachine(this.table, this.machinePlayer, this.tableImageView, this, this.gameUno);
        threadPlayMachine.start();

        Card card = deck.takeCard();

        setPlayerImageView(FileManager.loadCharacter());
        machineChange();

        // Timeline para cambiar imágenes
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(10), event -> bgChange())
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // Repetir para siempre
        timeline.play();

    }

    /**
     * Initializes the variables for the game.
     */
    private void initVariables() {
        this.humanPlayer = new Player("HUMAN_PLAYER");
        this.machinePlayer = new Player("MACHINE_PLAYER");
        this.table = new Table();
        this.deck = new Deck();
        this.gameUno = new GameUno(this.humanPlayer, this.machinePlayer, this.deck, this.table);
        deck.initializeDeck(gameUno, this);
        this.posInitCardToShow = 0;
        this.winLabel.setVisible(false);
    }


    public void setPlayerImageView(int character) {
        if(character == 1) {
            playerChange(c1);
        } else if (character == 2) {
            playerChange(c2);
        } else if (character == 3) {
            playerChange(c3);
        } else if (character == 4) {
            playerChange(c4);
        }
        System.out.println("peneeeeeeeeee");
        System.out.println(character);
    }


    public void bgChange() {

        if(FileManager.loadBiome() == 2){

            bg.setImage(new Image(getClass().getResource(imagenesNether[indice]).toExternalForm()));
            // Crear animación de desplazamiento horizontal
            TranslateTransition mover = new TranslateTransition(Duration.seconds(10), bg);
            mover.setFromX(0);  // Comienza fuera de la pantalla
            mover.setToX(-1600/5);  // Termina centrado


            // Crear animación de zoom
            ScaleTransition zoom = new ScaleTransition(Duration.seconds(10), bg);
            zoom.setFromX(1.5);  // Comienza con zoom
            zoom.setFromY(1.5);
            zoom.setToX(1.5);  // Termina en tamaño normal
            zoom.setToY(1.5);

            // Combinar ambas animaciones
            ParallelTransition animacion = new ParallelTransition(mover, zoom);
            animacion.play();

            // Cambiar al siguiente índice
            indice = (indice + 1) % imagenesNether.length;
        } else if (FileManager.loadBiome() == 3) {
            bg.setImage(new Image(getClass().getResource(imagenesEnd[indice]).toExternalForm()));
            // Crear animación de desplazamiento horizontal
            TranslateTransition mover = new TranslateTransition(Duration.seconds(10), bg);
            mover.setFromX(0);  // Comienza fuera de la pantalla
            mover.setToX(-1600/5);  // Termina centrado


            // Crear animación de zoom
            ScaleTransition zoom = new ScaleTransition(Duration.seconds(10), bg);
            zoom.setFromX(1.5);  // Comienza con zoom
            zoom.setFromY(1.5);
            zoom.setToX(1.5);  // Termina en tamaño normal
            zoom.setToY(1.5);

            // Combinar ambas animaciones
            ParallelTransition animacion = new ParallelTransition(mover, zoom);
            animacion.play();

            // Cambiar al siguiente índice
            indice = (indice + 1) % imagenesEnd.length;
            System.out.println(indice);
        } else if (FileManager.loadBiome() == 1) {
            bg.setImage(new Image(getClass().getResource(imagenesOver[indice]).toExternalForm()));
            // Crear animación de desplazamiento horizontal
            TranslateTransition mover = new TranslateTransition(Duration.seconds(10), bg);
            mover.setFromX(0);  // Comienza fuera de la pantalla
            mover.setToX(-1600/5);  // Termina centrado


            // Crear animación de zoom
            ScaleTransition zoom = new ScaleTransition(Duration.seconds(10), bg);
            zoom.setFromX(1.5);  // Comienza con zoom
            zoom.setFromY(1.5);
            zoom.setToX(1.5);  // Termina en tamaño normal
            zoom.setToY(1.5);

            // Combinar ambas animaciones
            ParallelTransition animacion = new ParallelTransition(mover, zoom);
            animacion.play();

            // Cambiar al siguiente índice
            indice = (indice + 1) % imagenesOver.length;
        }
    }


    public void playerChange(Image image){

        playerImageView.setImage(image);

        // Crear un ImageView y ajustar el tamaño
        double radius = playerCircle.getRadius();
        playerImageView.setFitWidth(radius * 2);
        playerImageView.setFitHeight(radius * 2);
        playerImageView.setTranslateX(-100);

        // Crear un círculo para recortar
        Circle clip = new Circle(radius);
        clip.setCenterX(radius);
        clip.setCenterY(radius);

        // Aplicar el clip
        playerImageView.setClip(clip);

        // Centrar la imagen en el contenedor
        playerImageView.setLayoutX(playerCircle.getLayoutX() - radius);
        playerImageView.setLayoutY(playerCircle.getLayoutY() - radius);

        // Agregar al contenedor
        Pane parent = (Pane) playerCircle.getParent();
        int circleIndex = parent.getChildren().indexOf(playerCircle);
        if(!parent.getChildren().isEmpty()){
            parent.getChildren().remove(playerImageView);
        }
        parent.getChildren().add(circleIndex + 1, playerImageView);

    }
    public void machineChange(){

        machineImageView.setImage(new Image(getClass().getResource("/org/example/eiscuno/images/herobrine.jpg").toExternalForm()));

            // Crear un ImageView y ajustar el tamaño
            double radius = machineCircle.getRadius();
        machineImageView.setFitWidth(radius * 2);
        machineImageView.setFitHeight(radius * 2);
        machineImageView.setTranslateX(-100);

            // Crear un círculo para recortar
            Circle clip = new Circle(radius);
            clip.setCenterX(radius);
            clip.setCenterY(radius);

            // Aplicar el clip
        machineImageView.setClip(clip);

            // Centrar la imagen en el contenedor
        machineImageView.setLayoutX(machineCircle.getLayoutX() - radius);
        machineImageView.setLayoutY(machineCircle.getLayoutY() - radius);

            // Agregar al contenedor
            Pane parent = (Pane) machineCircle.getParent();
            int circleIndex = parent.getChildren().indexOf(machineCircle);
            if(!parent.getChildren().isEmpty()){
                parent.getChildren().remove(machineImageView);
            }
            parent.getChildren().add(circleIndex + 1, machineImageView);

        }


    /**
     * Prints the human player's cards on the grid pane.
     */
    public void printCardsHumanPlayer() {
        this.gridPaneCardsPlayer.getChildren().clear();
        Card[] currentVisibleCardsHumanPlayer = this.gameUno.getCurrentVisibleCards(this.posInitCardToShow, humanPlayer);

        for (int i = 0; i < currentVisibleCardsHumanPlayer.length; i++) {
            Card card = currentVisibleCardsHumanPlayer[i];
            ImageView cardImageView = card.getCard();

            cardImageView.setOnMouseClicked((MouseEvent event) -> onHandlePlayCard(card));

            this.gridPaneCardsPlayer.add(cardImageView, i, 0);
        }

    }

    /**
     * Adds a penalty card to the human player if they do not say "UNO" in time.
     */
    public void penalizeForNotSingingUno() {

        machineAttackPlayer();
    }


    /**
     * Checks if the human player has one card and starts the UNO timer if necessary.
     */



    private void createExperience(Pane pane, double startX, double startY) {
        for(int i = 0; i < 500; i++) {
            Random random = new Random();
            Circle experience = new Circle(random.nextInt(5) + 2);

            experience.setLayoutX(startX + random.nextInt(1600)); // Posición inicial cercana al TextField
            experience.setLayoutY(startY);
            experience.setStroke(Color.GREENYELLOW);
            experience.setStrokeWidth(2);
            experience.setFill(Color.LIMEGREEN);

            pane.getChildren().add(experience);

            double paneHeight = pane.getHeight();

            TranslateTransition transition = new TranslateTransition(Duration.seconds(random.nextDouble(7) + 2), experience);
            transition.setByY(paneHeight - startY); // Caída hasta el final de la pantalla
            transition.setByX(random.nextInt(200) - 25); // Movimiento lateral aleatorio
            transition.setInterpolator(Interpolator.LINEAR); // Movimiento suave y constante
            //transition.setOnFinished(e -> pane.getChildren().remove(experience)); // Eliminar confeti cuando caiga
            transition.play();
        }
    }



    public void onHandlePlayCard(Card card){
        handleGameOver();
        if(gameUno.getIsMachineTurn() || gameUno.checkIsGameOver()){
            return;
        }
        card = gameUno.playCard(card);
        if(card != null) {
            tableImageView.setImage(card.getImage());
            printCardsHumanPlayer();
            tableEffect(card.getColor());
            handleGameOver();
            updateTurnLabel();
        }
    }



    public void printCardsMachinePlayer() {
        this.gridPaneCardsMachine.getChildren().clear();

        Card[] currentVisibleCardsMachinePlayer = this.gameUno.getCurrentVisibleCards(0, machinePlayer);

        for (int i = 0; i < currentVisibleCardsMachinePlayer.length; i++) {
            Card card = currentVisibleCardsMachinePlayer[i];
            EISCUnoEnum cardUno = EISCUnoEnum.CARD_UNO;
            ImageView cardImageView = new ImageView(String.valueOf(getClass().getResource(cardUno.getFilePath())));
            cardImageView.setY(16);
            cardImageView.setFitHeight(90*2);
            cardImageView.setFitWidth(70*2);

            this.gridPaneCardsMachine.add(cardImageView, i, 0);
        }
    }

    public void colorButtonsVisible(boolean visible){
        group.setVisible(visible);
        c.setVisible(false);
    }


    void startColorVBoxButtons(){
        redButton.setOnMousePressed(event -> chooseColor("RED"));
        redButton.setOnMouseEntered(mouseEvent -> redButton.toFront());
        blueButton.setOnMousePressed(event -> chooseColor("BLUE"));
        blueButton.setOnMouseEntered(mouseEvent -> blueButton.toFront());
        greenButton.setOnMousePressed(event -> chooseColor("GREEN"));
        greenButton.setOnMouseEntered(mouseEvent -> greenButton.toFront());
        yelowButton.setOnMousePressed(event -> chooseColor("YELLOW"));
        yelowButton.setOnMouseEntered(mouseEvent -> yelowButton.toFront());
        colorButtonsVisible(false);

    }

    public void tableEffect(String color){
        if(color.equals("RED")){

            tableImageView.setStyle(" -fx-effect: dropshadow(gaussian, rgba(225,0,0,0.5), 15, 0.7, 0, 0);");
        } else if(color.equals("BLUE")){

            tableImageView.setStyle(" -fx-effect: dropshadow(gaussian, rgba(0,30,225,0.5), 15, 0.7, 0, 0);");
        } else if(color.equals("GREEN")){

            tableImageView.setStyle(" -fx-effect: dropshadow(gaussian, rgba(41,225,0,0.5), 15, 0.7, 0, 0);");
        } else if(color.equals("YELLOW")){

            tableImageView.setStyle(" -fx-effect: dropshadow(gaussian, rgba(225,180,0,0.5), 15, 0.7, 0, 0);");
        } else if (color.equals("TRANSPARENT")){

            tableImageView.setStyle(" -fx-effect: dropshadow(gaussian, rgba(225,180,0,0), 15, 0.7, 0, 0);");
        }
    }

    public void chooseColor(String color){
        gameUno.setColorToCardPlayed(color);
        colorButtonsVisible(false);
        if(color.equals("RED")){
            tableImageView.setStyle(" -fx-effect: dropshadow(gaussian, rgba(225,0,0,0.5), 15, 0.7, 0, 0);");
        } else if(color.equals("BLUE")){
            tableImageView.setStyle(" -fx-effect: dropshadow(gaussian, rgba(0,30,225,0.5), 15, 0.7, 0, 0);");
        } else if(color.equals("GREEN")){
            tableImageView.setStyle(" -fx-effect: dropshadow(gaussian, rgba(41,225,0,0.5), 15, 0.7, 0, 0);");
        } else if(color.equals("YELLOW")){
            tableImageView.setStyle(" -fx-effect: dropshadow(gaussian, rgba(225,180,0,0.5), 15, 0.7, 0, 0);");
        }
        printCardsHumanPlayer();
        printCardsMachinePlayer();
    }

    /**
     * Handles the "Back" button action to show the previous set of cards.
     *
     * @param event the action event
     */
    @FXML
    void onHandleBack(ActionEvent event) {
        if (this.posInitCardToShow > 0) {
            TranslateTransition back = new TranslateTransition(Duration.millis(250),deckCard2);
            backButton.setDisable(true);
            deckCard2.setImage(new Image(getClass().getResource("/org/example/eiscuno/cards-uno/card_uno.png").toExternalForm()));
            back.setToX(0);
            back.setFromX(-400);
            back.setOnFinished(actionEvent -> {
                backButton.setDisable(false);
                deckCard2.setImage(new Image(getClass().getResource("").toExternalForm()));
                this.posInitCardToShow--;
                printCardsHumanPlayer();
            });
            back.play();
        }
    }

    public void updateTurnLabel(){
        if(gameUno.getIsMachineTurn()){
            turnLabel.setText("Turno de la maquina");
            turnLabel1.setText("Turno de la maquina");
        }else{
            turnLabel.setText("Tu turno");
            turnLabel1.setText("Tu turno");
        }
    }

    /**
     * Handles the "Next" button action to show the next set of cards.
     *
     * @param event the action event
     */
    @FXML
    void onHandleNext(ActionEvent event) {
        if (this.posInitCardToShow < this.humanPlayer.getCardsPlayer().size() - 4) {
            if(posInitCardToShow !=humanPlayer.getCardsPlayer().size() || posInitCardToShow != humanPlayer.getCardsPlayer().size()-1|| posInitCardToShow != humanPlayer.getCardsPlayer().size()-2|| posInitCardToShow != humanPlayer.getCardsPlayer().size()-3){
                TranslateTransition next = new TranslateTransition(Duration.millis(250),deckCard2);
                nextButton.setDisable(true);
                deckCard2.setImage(new Image(getClass().getResource("/org/example/eiscuno/cards-uno/card_uno.png").toExternalForm()));
                next.setFromX(0);
                next.setToX(-400);
                next.setOnFinished(actionEvent -> {
                    nextButton.setDisable(false);
                    deckCard2.setImage(new Image(getClass().getResource("").toExternalForm()));
                    this.posInitCardToShow++;
                    printCardsHumanPlayer();
                });
                next.play();
            }

        }
    }

    /**
     * Handles the action of taking a card.
     *
     * @param event the action event
     */
    @FXML
    void onHandleTakeCard(ActionEvent event) {
        if(gameUno.getIsMachineTurn() || gameUno.getIsPlayerSelectingColor() || gameUno.checkIsGameOver()){
            return;
        }
        gameUno.setIsMachineTurn(true);
        gameUno.eatCard(humanPlayer, 1);
        updateTurnLabel();
        TranslateTransition deckMove = new TranslateTransition(Duration.millis(250), deckCard);

        deckMove.setFromX(0);
        deckMove.setFromY(0);

        deckMove.setToX(1241);
        deckMove.setToY(300);
        RotateTransition rotation = new RotateTransition(Duration.millis(250), deckCard);
        rotation.setFromAngle(0);
        rotation.setToAngle(720);

        ParallelTransition parallel = new ParallelTransition(deckMove,rotation);
        parallel.play();
        deckMove.setOnFinished(actionEvent -> {
            printCardsHumanPlayer();
        deckCard1.setImage(new Image(getClass().getResource("/org/example/eiscuno/cards-uno/card_uno.png").toExternalForm()));
        });

    }

    public void showColorButtons(){
        colorButtonsVisible(true);
        tableEffect("TRANSPARENT");
        RotateTransition rotation = new RotateTransition(Duration.seconds(1),group);
        rotation.setFromAngle(0);
        rotation.setToAngle(360);
        ScaleTransition scale = new ScaleTransition(Duration.seconds(1),group);
        scale.setFromX(0);
        scale.setFromY(0);
        scale.setToX(1);
        scale.setToY(1);
        ParallelTransition transition = new ParallelTransition(rotation,scale);
        transition.play();
        transition.setOnFinished(actionEvent -> {
        });

    }


    /**
     * Handles the action of saying "Uno".
     *
     * @param event el evento de acción
     */
    @FXML
    void onHandleUno(ActionEvent event) {
//        proteccion de la persona
        if (humanPlayer.getCardsPlayer().size() == 1) {
            threadSingUNOMachine.setUnoAnnounced(true);

        }

        if(machinePlayer.getCardsPlayer().size() == 1){
            if(!threadSingUNOMachine.getIsMachineProtected()){
//                ataque a la maquina
                playerAttackMachine();
            }
        }
    }

    public void machineAttackPlayer(){
        TranslateTransition pum = new TranslateTransition(Duration.seconds(1),machineCreeper);
        pum.setFromX(0);
        pum.setFromY(0);
        pum.setToX(-1209);
        pum.setToY(660);
        RotateTransition room = new RotateTransition(Duration.seconds(1),machineCreeper);
        room.setFromAngle(0);
        room.setToAngle(720);
        ParallelTransition pam = new ParallelTransition(pum,room);
        pam.play();
        pum.setOnFinished(actionEvent -> {
            playSound("src/main/resources/org/example/eiscuno/sounds/explosionSound.wav",0);
            createExplosion(91,703,gamePane);
            System.out.println("El jugador no cantó UNO a tiempo. Comiendo una carta...");
            gameUno.eatCard(humanPlayer, 1);
            eatCardAnimation("HUMAN_PLAYER", 1);
            printCardsHumanPlayer();
        });
    }

    public void playerAttackMachine(){
        TranslateTransition pum = new TranslateTransition(Duration.seconds(1),playerCreeper);
        pum.setFromX(0);
        pum.setFromY(0);
        pum.setToX(1209);
        pum.setToY(-660);
        RotateTransition room = new RotateTransition(Duration.seconds(1),playerCreeper);
        room.setFromAngle(0);
        room.setToAngle(720);
        ParallelTransition pam = new ParallelTransition(pum,room);
        pam.play();
        pum.setOnFinished(actionEvent -> {
            playSound("src/main/resources/org/example/eiscuno/sounds/explosionSound.wav",0);
            gameUno.eatCard(machinePlayer, 1);
            threadSingUNOMachine.setIsMachineProtected(true);
            eatCardAnimation("MACHINE_PLAYER", 1);
            createExplosion(1289,108,gamePane);
        });
    }

    public static void createExplosion(double x, double y, Pane pane) {
        Random random = new Random();
        int numSquares = 500; // Número de cuadrados en la explosión

        for (int i = 0; i < numSquares; i++) {
            // Crear un cuadrado con tamaño aleatorio
            double size = random.nextDouble() * 20 + 10; // Tamaño entre 10 y 30
            Rectangle square = new Rectangle(size, size);
            square.setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
            square.setX(x - size / 2);
            square.setY(y - size / 2);

            // Añadir el cuadrado al Pane
            pane.getChildren().add(square);

            // Crear una transición de escala
            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), square);
            scaleTransition.setToX(10 + random.nextDouble() * 2); // Escalar entre 2 y 4 veces
            scaleTransition.setToY(10 + random.nextDouble() * 2);

            // Crear una transición de desvanecimiento
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), square);
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);

            // Ejecutar ambas transiciones simultáneamente
            scaleTransition.setOnFinished(e -> pane.getChildren().remove(square)); // Eliminar el cuadrado después de la animación
            scaleTransition.play();
            fadeTransition.play();
        }
    }


    @FXML
    void onHandleExitButton(ActionEvent event)throws IOException {
        WelcomeUnoStage.getInstance();
        GameUnoStage.deleteInstance();
        threadPlayMachine.interrupt();
        threadSingUNOMachine.stop();
    }

    public void handleGameOver(){
        if(gameUno.checkIsGameOver()) {
            winLabel.setVisible(true);
            if(gameUno.getWinner().equals("HUMAN_PLAYER")){
                rect.setVisible(true);
                rect.setFill(Color.ALICEBLUE);
                rect.setOpacity(0.3);
                winLabel.setText("HEROBRINE WAS SLAYED BY PLAYER");
                winLabel1.setText("HEROBRINE WAS SLAYED BY PLAYER");
                playSound("src/main/resources/org/example/eiscuno/sounds/experienceSound.wav",0);
                exitButton1.setDisable(false);
                exitButton1.setVisible(true);
                createExperience(gamePane,gamePane.getLayoutX(),gamePane.getLayoutY());
            }else {
                winLabel.setText("PLAYER WAS SLAYED BY HEROBRINE");
                winLabel1.setText("PLAYER WAS SLAYED BY HEROBRINE");
                rect.setVisible(true);
                rect.setMouseTransparent(false);
                exitButton1.setDisable(false);
                exitButton1.setVisible(true);
            }
            turnLabel.setText("");
            threadPlayMachine.interrupt();
            threadSingUNOMachine.stop();
        }

    }

    public void eatCardAnimation(String typePlayer, int cardsNumber) {
        if(typePlayer.equals("HUMAN_PLAYER")) {
            TranslateTransition deckMove = new TranslateTransition(Duration.millis(250), deckCard);

            deckMove.setFromX(0);
            deckMove.setFromY(0);

            deckMove.setToX(1241);
            deckMove.setToY(300);
            rotateCardAnimation(cardsNumber, deckMove);
        } else if (typePlayer.equals("MACHINE_PLAYER")) {
            TranslateTransition deckMove = new TranslateTransition(Duration.millis(250), deckCard);

            deckMove.setFromX(0);
            deckMove.setFromY(0);

            deckMove.setToX(-125);
            deckMove.setToY(-310);
            rotateCardAnimation(cardsNumber, deckMove);
        }
    }

    private void rotateCardAnimation(int cardsNumber, TranslateTransition deckMove) {
        RotateTransition rotation = new RotateTransition(Duration.millis(250), deckCard);
        rotation.setFromAngle(0);
        rotation.setToAngle(720);

        ParallelTransition parallel = new ParallelTransition(deckMove, rotation);
        parallel.setCycleCount(cardsNumber);
        parallel.setOnFinished(actionEvent -> {
            printCardsHumanPlayer();
            printCardsMachinePlayer();
        });
        parallel.play();
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
}
