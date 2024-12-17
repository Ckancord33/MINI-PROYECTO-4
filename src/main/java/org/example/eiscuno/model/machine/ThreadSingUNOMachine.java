package org.example.eiscuno.model.machine;

import javafx.application.Platform;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.card.Card;

import java.util.ArrayList;

public class ThreadSingUNOMachine implements Runnable{
    private ArrayList<Card> cardsPlayer;
    private ArrayList<Card> cardsMachine;
    private boolean machineRealizedPlayerCards;
    private boolean unoAnnounced;
    private volatile boolean running;
    private GameUnoController controller;
    private boolean machineRealizedItsCards;
    private boolean isMachineProtected;

    public ThreadSingUNOMachine(ArrayList<Card> cardsPlayer,ArrayList<Card> cardsMachine, GameUnoController controller) {
        this.cardsPlayer = cardsPlayer;
        this.machineRealizedPlayerCards = false;
        this.machineRealizedItsCards = false;
        this.isMachineProtected = false;
        this.running = true; // Variable para controlar la ejecución del hilo
        this.controller = controller;
        this.cardsMachine = cardsMachine;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep((long) (Math.random() * 2000)+2000
                );
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
                break;
            }
            hasOneCardTheHumanPlayer();
            hasOneCardTheMachinePlayer();
        }
    }

    private void hasOneCardTheMachinePlayer() {
        if (cardsMachine.size() != 1 && machineRealizedItsCards) {
            machineRealizedItsCards = false;
            isMachineProtected = false;
            System.out.println("LA MAQUINA YA NO ESTA PROTEGIDA");
        }else if(cardsMachine.size() == 1 && machineRealizedItsCards){
            isMachineProtected = true;
            System.out.println("LA MAQUINA ESTA PROTEGIDA");
        }else if (cardsMachine.size() == 1 && !machineRealizedItsCards) {
            System.out.println("LA MAQUINA SE DIO CUENTA QUE LE QUEDA UNA CARTA");
            machineRealizedItsCards = true;
        }

    }

    private void hasOneCardTheHumanPlayer() {
        if (cardsPlayer.size() != 1 && machineRealizedPlayerCards) {
            machineRealizedPlayerCards = false;
            unoAnnounced = false;
        }else if( cardsPlayer.size() == 1 && machineRealizedPlayerCards && !unoAnnounced){
            Platform.runLater(() -> controller.penalizeForNotSingingUno());
            unoAnnounced = true;
        }else if (cardsPlayer.size() == 1 && !machineRealizedPlayerCards) {
            System.out.println("LA MAQUINA SE DIO CUENTA QUE EL JUGADOR TIENE UNA CARTA");
            machineRealizedPlayerCards = true;
        }
    }
    public void setUnoAnnounced(boolean unoAnnounced) {
        this.unoAnnounced = unoAnnounced;
    }

    public boolean getIsMachineProtected() {
        return isMachineProtected;
    }

    public boolean setIsMachineProtected(boolean isMachineProtected) {
        return this.isMachineProtected = isMachineProtected;
    }


    public void stop() {
        running = false; // Detener la ejecución del hilo
    }
}