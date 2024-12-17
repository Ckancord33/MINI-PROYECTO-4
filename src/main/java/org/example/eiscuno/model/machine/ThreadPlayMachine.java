package org.example.eiscuno.model.machine;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.card.ACard;
import org.example.eiscuno.model.game.GameUno;
import org.example.eiscuno.model.player.Player;
import org.example.eiscuno.model.table.Table;

import java.io.IOException;

/**
 * Class ThreadPlayMachine
 * This class represents a thread that automates the machine turn in the Uno game
 * It handles card selection, game state updates, and interactions.
 * @author Nicolás Córdoba
 * @author Miguel Castillo
 * @author Camilo Pinzón
 * @author Fabian Valencia
 * @version 1.0
 */
public class ThreadPlayMachine extends Thread {
    private Table table;
    private Player machinePlayer;
    private ImageView tableImageView;
    private volatile GameUnoController controller;
    private volatile GameUno gameUno;
    private final String[] colors = {"RED", "BLUE", "GREEN", "YELLOW"};

    /**
     * Constructor of ThreadPlayMachine object with the game elements.
     *
     * @param table          The game table where cards are played
     * @param machinePlayer  The machine player instance
     * @param tableImageView The ImageView to display the current card on the table
     * @param controller     The GameUnoController to update the game
     * @param gameUno        The GameUno instance managing the game state
     * @throws IOException   If an input/output error occurs during initialization
     */
    public ThreadPlayMachine(Table table, Player machinePlayer, ImageView tableImageView, GameUnoController controller, GameUno gameUno)throws IOException {
        this.table = table;
        this.machinePlayer = machinePlayer;
        this.tableImageView = tableImageView;
        this.controller = controller;
        this.gameUno = gameUno;
    }

    /**
     * Runs the thread to manage the machine turn.
     */
    public void run() {
        while (true){
            if(gameUno.checkIsGameOver()){
                Platform.runLater(() -> controller.handleGameOver());
                break;
            }
            if(gameUno.getIsMachineTurn() && !gameUno.getIsPlayerSelectingColor()){
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                putCardOnTheTable();
                Platform.runLater(() ->{
                    controller.updateTurnLabel();
                    controller.handleGameOver();
                });
            }
        }
    }

    /**
     * Handles the logic for the machine player to play a card on the table
     * If no valid card is found, the machine player draws a card
     */
    private void putCardOnTheTable(){
        for (ACard card : machinePlayer.getCardsPlayer()) {
            if(gameUno.playCard(card) != null){
                table.addCardOnTheTable(card);
                tableImageView.setImage(card.getImage());
                if(gameUno.getIsPlayerSelectingColor()){
                    String color = colors[(int)(Math.random() * 4)];
                    gameUno.setColorToCardPlayed(color);
                }
                Platform.runLater(() ->{
                    controller.tableEffect(card.getColor());
                    controller.printCardsMachinePlayer();
                });
                return;
            }
        }
        gameUno.eatCard(machinePlayer, 1);
        controller.eatCardAnimation("MACHINE_PLAYER", 1);
        gameUno.setIsMachineTurn(false);

    }
}