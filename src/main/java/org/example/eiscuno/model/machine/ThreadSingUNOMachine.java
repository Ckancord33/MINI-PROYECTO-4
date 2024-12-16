package org.example.eiscuno.model.machine;

import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.card.Card;

import java.util.ArrayList;

public class ThreadSingUNOMachine implements Runnable{
    private ArrayList<Card> cardsPlayer;
    private volatile boolean unoAnnounced;

    private volatile boolean running;
    private GameUnoController controller;

    public ThreadSingUNOMachine(ArrayList<Card> cardsPlayer){
        this.cardsPlayer = cardsPlayer;
        this.unoAnnounced = false;
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
        }
    }

    private void hasOneCardTheHumanPlayer() {
        if (cardsPlayer.size() == 1 && !unoAnnounced) {
            System.out.println("UNO PARA USUARIO");
            unoAnnounced = true;
            controller.penalizeForNotSingingUno();
        } else if (cardsPlayer.size() != 1 && unoAnnounced) {
            unoAnnounced = false;
        }
    }
    public void stop() {
        running = false; // Detener la ejecución del hilo
    }
}