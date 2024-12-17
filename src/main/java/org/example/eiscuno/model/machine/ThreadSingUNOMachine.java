package org.example.eiscuno.model.machine;

import javafx.application.Platform;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.card.Card;

import java.util.ArrayList;

/**
 * Class ThreadSingUNOMachine
 * This class implements a runnable thread that monitors when the players have only
 * one card left. Handles penalties and "protection" states
 */
public class ThreadSingUNOMachine implements Runnable{
    private ArrayList<Card> cardsPlayer;
    private ArrayList<Card> cardsMachine;
    private boolean machineRealizedPlayerCards;
    private boolean unoAnnounced;
    private volatile boolean running;
    private GameUnoController controller;
    private boolean machineRealizedItsCards;
    private boolean isMachineProtected;

    /**
     * Constructs a ThreadSingUNOMachine instance
     * @param cardsPlayer   The list of cards by the human player.
     * @param cardsMachine  The list of cards by the machine player.
     * @param controller    The GameUnoController to manage game actions and penalties
     */
    public ThreadSingUNOMachine(ArrayList<Card> cardsPlayer,ArrayList<Card> cardsMachine, GameUnoController controller) {
        this.cardsPlayer = cardsPlayer;
        this.machineRealizedPlayerCards = false;
        this.machineRealizedItsCards = false;
        this.isMachineProtected = false;
        this.running = true; // Variable para controlar la ejecución del hilo
        this.controller = controller;
        this.cardsMachine = cardsMachine;
    }

    /**
     * Runs the thread to continuously check the cards of both players
     * It determines if penalties or protective states need to be applied
     */
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

    /**
     * Checks if the machine player has only one card remaining.
     * Updates the protection state
     */
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

    /**
     * Checks if the human player has only one card remaining.
     * Penalizes the player if they fail to announce "UNO" in time
     */
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

    /**
     * Set when the "UNO" announcement has been made
     * @param unoAnnounced True if "UNO" has been announced, false otherwise
     */
    public void setUnoAnnounced(boolean unoAnnounced) {
        this.unoAnnounced = unoAnnounced;
    }

    /**
     * Retrieves when the machine player is protected after announcing "UNO"
     * @return True if the machine player is protected, false otherwise.
     */
    public boolean getIsMachineProtected() {
        return isMachineProtected;
    }

    /**
     * Sets the protection state of the machine
     * @param isMachineProtected True to protect the machine, false otherwise.
     * @return The updated protection state of the machine
     */
    public boolean setIsMachineProtected(boolean isMachineProtected) {
        return this.isMachineProtected = isMachineProtected;
    }

    /**
     * Stops the execution of the thread
     */
    public void stop() {
        running = false;
    }
}