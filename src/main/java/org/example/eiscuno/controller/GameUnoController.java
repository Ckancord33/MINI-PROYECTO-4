package org.example.eiscuno.controller;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
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
    private Button ButtonUno;

    @FXML
    private Rectangle rectangleColor;

    @FXML
    private Label winLabel;

    @FXML
    private Label turnLabel;


    private Player humanPlayer;
    private Player machinePlayer;
    private Deck deck;
    private Table table;
    private GameUno gameUno;
    private int posInitCardToShow;
    private ThreadSingUNOMachine threadSingUNOMachine;
    private ThreadPlayMachine threadPlayMachine;

    private boolean unoButtonPressed = false;
    private PauseTransition unoTimer;


    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() throws IOException{
        initVariables();

        // Inicializar el temporizador dinámico para "UNO"
        unoTimer = new PauseTransition();
        unoTimer.setOnFinished(event -> {
            if (!unoButtonPressed) {
                penalizeForNotSingingUno();
            }
        });

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

        // Comprobar si se debe iniciar el temporizador "UNO"
        checkStartUnoTimer();
    }

    /**
     * Adds to cards to the human player when this does not click the UNO button on time
     */
    /**
     * Adds a penalty card to the human player if they do not say "UNO" in time.
     */
    public void penalizeForNotSingingUno() {
        System.out.println("El jugador no cantó UNO a tiempo. Comiendo una carta...");
        gameUno.eatCard(humanPlayer, 1);
        printCardsHumanPlayer();
    }

    /**
     * Checks if the human player has one card and starts the UNO timer if necessary.
     */
    private void checkStartUnoTimer() {
        if (unoTimer == null) {
            System.out.println("Error: el temporizador UNO no está inicializado.");
            return;
        }

        if (humanPlayer.getCardsPlayer().size() == 1 && !unoButtonPressed) {
            int delay = 2 + (int) (Math.random() * 3); // Generar # aleatorio entre 2 y 4 segundos
            System.out.println("Iniciando temporizador UNO: " + delay + " segundos.");
            unoTimer.setDuration(Duration.seconds(delay));
            unoTimer.playFromStart();
        } else {
            unoTimer.stop();
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
            this.posInitCardToShow++;
            printCardsHumanPlayer();
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
        gameUno.eatCard(humanPlayer, 1);
        printCardsHumanPlayer();
        gameUno.setIsMachineTurn(true);
        updateTurnLabel();
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
    /**
     * Handles the action of saying "Uno".
     *
     * @param event el evento de acción
     */
    @FXML
    void onHandleUno(ActionEvent event) {
        if (humanPlayer.getCardsPlayer().size() == 1) {
            unoButtonPressed = true;
            System.out.println("UNO presionado a tiempo.");
            unoTimer.stop();
        }
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
