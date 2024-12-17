package org.example.eiscuno.model.player;

import org.example.eiscuno.model.card.ACard;

import java.util.ArrayList;

/**
 * Interface representing a player in the Uno game.
 * Provides methods to interact with the player's hand of cards.
 * @author Fabian Valencia
 * @version 1.0
 */
public interface IPlayer {

    /**
     * Adds a card to the player's hand.
     *
     * @param card The card to be added to the player's hand.
     */
    void addCard(ACard card);

    /**
     * Retrieves a card from the player's hand based on its index.
     *
     * @param index The index of the card to retrieve.
     * @return The card at the specified index in the player's hand.
     */
    ACard getCard(int index);

    /**
     * Retrieves all cards currently held by the player.
     *
     * @return An ArrayList containing all cards in the player's hand.
     */
    ArrayList<ACard> getCardsPlayer();

    /**
     * Removes a card from the player's hand based on its index.
     *
     * @param index The index of the card to remove.
     */
    void removeCard(int index);
}
