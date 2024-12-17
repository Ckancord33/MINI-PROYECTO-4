package org.example.eiscuno.model.machine;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.game.GameUno;
import org.example.eiscuno.model.player.Player;
import org.example.eiscuno.model.table.Table;

import java.io.IOException;

public class ThreadPlayMachine extends Thread {
    private Table table;
    private Player machinePlayer;
    private ImageView tableImageView;
    private volatile GameUnoController controller;
    private volatile GameUno gameUno;
    private final String[] colors = {"RED", "BLUE", "GREEN", "YELLOW"};

    public ThreadPlayMachine(Table table, Player machinePlayer, ImageView tableImageView, GameUnoController controller, GameUno gameUno)throws IOException {
        this.table = table;
        this.machinePlayer = machinePlayer;
        this.tableImageView = tableImageView;
        this.controller = controller;
        this.gameUno = gameUno;
    }

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

    private void putCardOnTheTable(){
        for (Card card : machinePlayer.getCardsPlayer()) {
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