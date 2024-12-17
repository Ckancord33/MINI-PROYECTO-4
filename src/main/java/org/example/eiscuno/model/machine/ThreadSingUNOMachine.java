package org.example.eiscuno.model.machine;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.card.Card;

import java.util.ArrayList;

public class ThreadSingUNOMachine implements Runnable{
    private ArrayList<Card> cardsPlayer;
    private boolean machineRealized;
    private boolean unoAnnounced;
    private volatile boolean running;
    private GameUnoController controller;

    public ThreadSingUNOMachine(ArrayList<Card> cardsPlayer, GameUnoController controller) {
        this.cardsPlayer = cardsPlayer;
        this.machineRealized = false;
        this.running = true; // Variable para controlar la ejecución del hilo
        this.controller = controller;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep((long) (Math.random() * 4000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
                break;
            }
            hasOneCardTheHumanPlayer();
            hasOneCardTheMachinePlayer();
        }
    }

    private void hasOneCardTheMachinePlayer() {

    }

    private void hasOneCardTheHumanPlayer() {
        if (cardsPlayer.size() != 1 && machineRealized) {
            machineRealized = false;
            unoAnnounced = false;
        }else if( cardsPlayer.size() == 1 && machineRealized && !unoAnnounced){
            Platform.runLater(() -> controller.penalizeForNotSingingUno());
            unoAnnounced = true;
        }else if (cardsPlayer.size() == 1 && !machineRealized) {
            System.out.println("LA MAQUINA SE DIO CUENTA QUE EL JUGADOR TIENE UNA CARTA");
            machineRealized = true;
        }
    }
    public void setUnoAnnounced(boolean unoAnnounced) {
        this.unoAnnounced = unoAnnounced;
    }


    public void stop() {
        running = false; // Detener la ejecución del hilo
    }
}