package org.example.eiscuno.controller;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.example.eiscuno.fileManager.FileManager;
import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.deck.Deck;
import org.example.eiscuno.model.game.GameUno;
import org.example.eiscuno.model.machine.ThreadPlayMachine;
import org.example.eiscuno.model.machine.ThreadSingUNOMachine;
import org.example.eiscuno.model.player.Player;
import org.example.eiscuno.model.table.Table;
import org.example.eiscuno.model.unoenum.EISCUnoEnum;
import org.example.eiscuno.view.GameUnoStage;
import org.example.eiscuno.view.WelcomeUnoStage;

import java.io.IOException;

/**
 * Controller class for the Uno game.
 */
public class GameUnoController {

    @FXML
    private Button blueButton;

    @FXML
    private Button deckButton;

    @FXML
    private Pane gamePane;

    @FXML
    private Button greenButton;

    @FXML
    private GridPane gridPaneCardsMachine;

    @FXML
    private GridPane gridPaneCardsPlayer;

    @FXML
    private Button redButton;

    @FXML
    private ImageView tableImageView;

    @FXML
    private VBox vBoxColorButtons;

    @FXML
    private Button yelowButton;

    @FXML
    private Rectangle rectangleColor;

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
        deckButton.setEffect(new ImageInput(new Image(getClass().getResource("/org/example/eiscuno/cards-uno/card_uno.png").toExternalForm())));
        deckCard1.setImage(new Image(getClass().getResource("/org/example/eiscuno/cards-uno/card_uno.png").toExternalForm()));
        bgChange();
        deckCard.setImage(new Image(getClass().getResource("/org/example/eiscuno/cards-uno/card_uno.png").toExternalForm()));
        initVariables();
        this.gameUno.startGame();
        printCardsHumanPlayer();
        printCardsMachinePlayer();
        startColorVBoxButtons();
        updateTurnLabel();

        threadSingUNOMachine = new ThreadSingUNOMachine(this.humanPlayer.getCardsPlayer());
        Thread t = new Thread(threadSingUNOMachine, "ThreadSingUNO");
        t.start();

        threadPlayMachine = new ThreadPlayMachine(this.table, this.machinePlayer, this.tableImageView, this, this.gameUno);
        threadPlayMachine.start();

        Card card = deck.takeCard();
        table.addCardOnTheTable(card);
        tableImageView.setImage(card.getImage());


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

    public void deckDrawTransition(){


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

    public void setVisibleRectangleColor(String color){
        this.rectangleColor.setVisible(true);
        if(color.equals("RED")){
            this.rectangleColor.setFill(javafx.scene.paint.Color.RED);
        } else if(color.equals("BLUE")){
            this.rectangleColor.setFill(javafx.scene.paint.Color.BLUE);
        } else if(color.equals("GREEN")){
            this.rectangleColor.setFill(javafx.scene.paint.Color.GREEN);
        } else if(color.equals("YELLOW")){
            this.rectangleColor.setFill(javafx.scene.paint.Color.YELLOW);
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
            setRectangleColorVisibility(card);
            handleGameOver();
            updateTurnLabel();
        }
    }

    public void setRectangleColorVisibility(Card card){
        if(!(card.getValue().equals("WILD") || card.getValue().equals("FOUR_WILD_DRAW"))){
            this.rectangleColor.setVisible(false);
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


    void startColorVBoxButtons(){
        redButton.setOnAction(event -> chooseColor("RED"));
        blueButton.setOnAction(event -> chooseColor("BLUE"));
        greenButton.setOnAction(event -> chooseColor("GREEN"));
        yelowButton.setOnAction(event -> chooseColor("YELLOW"));
        this.vBoxColorButtons.setVisible(false);
        this.rectangleColor.setVisible(false);
    }

    public void chooseColor(String color){
        gameUno.setColorToCardPlayed(color);
        this.vBoxColorButtons.setVisible(false);
        if(color.equals("RED")){
            this.rectangleColor.setFill(javafx.scene.paint.Color.RED);
        } else if(color.equals("BLUE")){
            this.rectangleColor.setFill(javafx.scene.paint.Color.BLUE);
        } else if(color.equals("GREEN")){
            this.rectangleColor.setFill(javafx.scene.paint.Color.GREEN);
        } else if(color.equals("YELLOW")){
            this.rectangleColor.setFill(javafx.scene.paint.Color.YELLOW);
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
            this.posInitCardToShow--;
            printCardsHumanPlayer();
        }
    }

    public void updateTurnLabel(){
        if(gameUno.getIsMachineTurn()){
            turnLabel.setText("Turno de la maquina");
        }else{
            turnLabel.setText("Tu turno");
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
                TranslateTransition next = new TranslateTransition(Duration.seconds(2),deckCard2);
                deckCard2.setImage(new Image(getClass().getResource("/org/example/eiscuno/cards-uno/card_uno.png").toExternalForm()));
                next.setFromX(0);
                next.setToX(-400);
                next.setOnFinished(actionEvent -> {
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
        TranslateTransition deckMove = new TranslateTransition(Duration.seconds(2), deckCard);

        deckMove.setFromX(0);
        deckMove.setFromY(0);

//        deckMove.setByY(20);

        deckMove.setToX(1241);
        deckMove.setToY(300);
        deckMove.play();
        deckMove.setOnFinished(actionEvent -> {
            gameUno.eatCard(humanPlayer, 1);
            printCardsHumanPlayer();
            gameUno.setIsMachineTurn(true);
            updateTurnLabel();
        deckCard1.setImage(new Image(getClass().getResource("/org/example/eiscuno/cards-uno/card_uno.png").toExternalForm()));
        });

    }

    public void showColorButtons(){
        this.vBoxColorButtons.setVisible(true);
        this.rectangleColor.setVisible(true);
        this.rectangleColor.setFill(Color.TRANSPARENT);
    }

    /**
     * Handles the action of saying "Uno".
     *
     * @param event the action event
     */
    @FXML
    void onHandleUno(ActionEvent event) {
        // Implement logic to handle Uno event here
    }

    @FXML
    void onHandleExitButton(ActionEvent event)throws IOException {
        WelcomeUnoStage.getInstance();
        GameUnoStage.deleteInstance();
    }

    public void handleGameOver(){
        if(gameUno.checkIsGameOver()) {
            winLabel.setVisible(true);
            if(gameUno.getWinner().equals("HUMAN_PLAYER")){
                winLabel.setText("GANASTE FIERA!");
            }else {
                winLabel.setText("PERDISTE JAJAJA");
            }
            turnLabel.setText("");
            threadPlayMachine.interrupt();
        }

    }

}
