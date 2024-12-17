package org.example.eiscuno.model.game;

import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.player.Player;

/**
 * Interface representing the Uno game functionality.
 */
public interface IGameUno {

    /**
     * Starts the Uno game.
     */
    void startGame();

    void chooseFirstCard();

    /**
     * Makes a player draw a specified number of cards from the deck.
     *
     * @param player the player who will draw the cards
     * @param numberOfCards the number of cards to be drawn
     */
    void eatCard(Player player, int numberOfCards);

    /**
     * Plays a card in the game, adding it to the table.
     *
     * @param card the card to be played
     */
    Card playCard(Card card);

    /**
     * Handles the action when a player shouts "Uno".
     *
     * @param playerWhoSang the identifier of the player who shouted "Uno"
     */
    void haveSungOne(String playerWhoSang);

    /**
     * Retrieves the current visible cards of the human player starting from a specific position.
     *
     * @param posInitCardToShow the starting position of the cards to be shown
     * @return an array of cards that are currently visible to the human player
     */
    Card[] getCurrentVisibleCards(int posInitCardToShow, Player player);

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise
     */
    Boolean isGameOver();

    /**
     * Determines when is the machine's turn to play.
     * @return true if it is the machine's turn, false otherwise.
     */
    boolean getIsMachineTurn();

    /**
     * Sets when is the machine's turn to play.
     * @param isMachineTurn true if it is the machine's turn, false otherwise.
     */
    void setIsMachineTurn(boolean isMachineTurn);

    /**
     * Updates the current active player in the game.
     */
    void updateActualPlayer();

    /**
     * Retrieves the player who is currently taking their turn.
     * @return the current player.
     */
    Player getActualPlayer();

    /**
     * Checks if the player is in the process of selecting a color
     * @return true if the player is selecting a color, false otherwise
     */
    boolean getIsPlayerSelectingColor();

    /**
     * Verifies if the game has ended
     * @return true if the game is over, false otherwise.
     */
    boolean checkIsGameOver();;

    /**
     * Retrieves the winner of the game.
     * @return a string representing the winner.
     */
    String getWinner();

    /**
     * Sets the color for the last card played
     * @param color The color to set for the card
     */
    void setColorToCardPlayed(String color);

    /**
     * Updates when the player is selecting a color
     * @param isPlayerSelectingColor true if the player is selecting a color, false otherwise.
     */
    void setIsPlayerSelectingColor(boolean isPlayerSelectingColor);

    /**
     * Changes the turn to the next player in the game.
     */
    void changeTurn();

    /**
     * Finds the position of a specific card in the human player's hand.
     * @param card The card to find.
     * @return The index position of the card in the player's hand
     */
    Integer findPosCardsHumanPlayer(Card card);

    /**
     * Adds a specified card to the table.
     * @param card the card to add to the table.
     */
    void addCardOnTheTable(Card card);
}
