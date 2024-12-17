package org.example.eiscuno.model.player;

import org.example.eiscuno.model.card.ACard;

import java.util.ArrayList;

/**
 * Represents a player in the Uno game.
 * @author Fabian Valencia
 * @version 1.0
 */
public class Player implements IPlayer {
    private ArrayList<ACard> cardsPlayer;
    private String typePlayer;

    /**
     * Constructs a new Player object with an empty hand of cards.
     */
    public Player(String typePlayer){
        this.cardsPlayer = new ArrayList<ACard>();
        this.typePlayer = typePlayer;
    };

    /**
     * Adds a card to the player's hand.
     *
     * @param card The card to be added to the player's hand.
     */
    @Override
    public void addCard(ACard card){
        cardsPlayer.add(card);
    }

    /**
     * Retrieves all cards currently held by the player.
     *
     * @return An ArrayList containing all cards in the player's hand.
     */
    @Override
    public ArrayList<ACard> getCardsPlayer() {
        return cardsPlayer;
    }

    /**
     * Removes a card from the player's hand based on its index.
     *
     * @param index The index of the card to remove.
     */
    @Override
    public void removeCard(int index) {
        cardsPlayer.remove(index);
    }

    /**
     * Retrieves a card from the player's hand based on its index.
     *
     * @param index The index of the card to retrieve.
     * @return The card at the specified index in the player's hand.
     */
    @Override
    public ACard getCard(int index){
        return cardsPlayer.get(index);
    }

    /**
     * Retrieves the type of player.
     *
     * @return A string representing the type of player.
     */
    public String getTypePlayer() {
        return typePlayer;
    }
}