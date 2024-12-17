package org.example.eiscuno.model.game;

import org.example.eiscuno.cardAbility.AbilityInvoker;
import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.deck.Deck;
import org.example.eiscuno.model.player.Player;
import org.example.eiscuno.model.table.Table;

/**
 * Represents a game of Uno.
 * This class manages the game logic and interactions between players, deck, and the table.
 */
public class GameUno implements IGameUno {

    private Player humanPlayer;
    private Player machinePlayer;
    private Player victimPlayer;
    private Player actualPlayer;
    private String winner;
    private Deck deck;
    private Table table;
    private boolean isMachineTurn;
    private AbilityInvoker abilityInvoker;
    private Card cardPlayed;
    private boolean isPlayerSelectingColor;

    /**
     * Constructs a new GameUno instance.
     *
     * @param humanPlayer   The human player participating in the game.
     * @param machinePlayer The machine player participating in the game.
     * @param deck          The deck of cards used in the game.
     * @param table         The table where cards are placed during the game.
     */
    public GameUno(Player humanPlayer, Player machinePlayer, Deck deck, Table table) {
        this.humanPlayer = humanPlayer;
        this.machinePlayer = machinePlayer;
        this.deck = deck;
        this.table = table;
        this.isMachineTurn = false;
        this.abilityInvoker = new AbilityInvoker();
        this.isPlayerSelectingColor = false;
    }

    /**
     * Starts the Uno game by distributing cards to players.
     * The human player and the machine player each receive 10 cards from the deck.
     */
    @Override
    public void startGame() {
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                humanPlayer.addCard(this.deck.takeCard());
            } else {
                machinePlayer.addCard(this.deck.takeCard());
            }
        }

    }

    @Override
    public void chooseFirstCard() {
        Card card = this.deck.takeCard();
        while (card.getValue().equals("WILD") || card.getValue().equals("WILD_DRAW_FOUR")) {
            this.deck.takeCard();
            card = this.deck.takeCard();
        }
        this.table.addCardOnTheTable(card);
    }

    /**
     * Allows a player to draw a specified number of cards from the deck.
     *
     * @param player        The player who will draw cards.
     * @param numberOfCards The number of cards to draw.
     */
    @Override
    public void eatCard(Player player, int numberOfCards) {
        for (int i = 0; i < numberOfCards; i++) {
            player.addCard(this.deck.takeCard());

        }
    }

    /**
     * Places a card on the table during the game.
     *
     * @param card The card to be placed on the table.
     */
    @Override
    public Card playCard(Card card) {
        try {
            if(isPlayerSelectingColor){
                return null;
            }
            else if(!card.isPlayable(this.table.getCurrentCardOnTheTable())){
                return null;
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        cardPlayed = card;
        updateActualPlayer();
        actualPlayer.removeCard(findPosCardsHumanPlayer(cardPlayed));
        abilityInvoker.setAbility(cardPlayed.getAbility());
        abilityInvoker.execute();
        addCardOnTheTable(cardPlayed);
        return cardPlayed;

    }

    @Override
    public void setColorToCardPlayed(String color){
        cardPlayed.setColor(color);
        isPlayerSelectingColor = false;
    }

    @Override
    public void setIsPlayerSelectingColor(boolean isPlayerSelectingColor){
        this.isPlayerSelectingColor = isPlayerSelectingColor;
    }

    @Override
    public void changeTurn(){
        isMachineTurn = !isMachineTurn;
    }

    @Override
    public Integer findPosCardsHumanPlayer(Card card) {
        for (int i = 0; i < this.actualPlayer.getCardsPlayer().size(); i++) {
            if (this.actualPlayer.getCardsPlayer().get(i).equals(card)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void addCardOnTheTable(Card card){
        this.table.addCardOnTheTable(card);
    }

    /**
     * Handles the scenario when a player shouts "Uno", forcing the other player to draw a card.
     *
     * @param playerWhoSang The player who shouted "Uno".
     */
    @Override
    public void haveSungOne(String playerWhoSang) {
        if (playerWhoSang.equals("HUMAN_PLAYER")) {
            machinePlayer.addCard(this.deck.takeCard());
        } else {
            humanPlayer.addCard(this.deck.takeCard());
        }
    }

    /**
     * Retrieves the current visible cards of the human player starting from a specific position.
     *
     * @param posInitCardToShow The initial position of the cards to show.
     * @return An array of cards visible to the human player.
     */
    @Override
    public Card[] getCurrentVisibleCards(int posInitCardToShow, Player player) {
        int totalCards = player.getCardsPlayer().size();
        int numVisibleCards = Math.min(4, totalCards - posInitCardToShow);
        Card[] cards = new Card[numVisibleCards];

        for (int i = 0; i < numVisibleCards; i++) {
            cards[i] = player.getCard(posInitCardToShow + i);
        }

        return cards;
    }

    /**
     * Checks if the game is over.
     *
     * @return True if the deck is empty, indicating the game is over; otherwise, false.
     */
    @Override
    public Boolean isGameOver() {
        return null;
    }

    public Player getVictimPlayer(){
        if(isMachineTurn){
            this.victimPlayer = humanPlayer;
        }else{
            this.victimPlayer = machinePlayer;
        }
        return victimPlayer;
    }

    @Override
    public boolean getIsMachineTurn(){
        return isMachineTurn;
    }

    @Override
    public void setIsMachineTurn(boolean isMachineTurn){
        this.isMachineTurn = isMachineTurn;
    }

    public void updateActualPlayer(){
        if(isMachineTurn){
            this.actualPlayer = machinePlayer;
        }else{
            this.actualPlayer = humanPlayer;
        }
    }

    @Override
    public Player getActualPlayer(){
        return actualPlayer;
    }

    @Override
    public boolean getIsPlayerSelectingColor(){
        return isPlayerSelectingColor;
    }

    @Override
    public boolean checkIsGameOver(){
        if(humanPlayer.getCardsPlayer().isEmpty()){
            System.out.println("Human player wins");
            winner = "HUMAN_PLAYER";
            return true;
        }else if(machinePlayer.getCardsPlayer().isEmpty()){
            System.out.println("Machine player wins");
            winner = "MACHINE_PLAYER";
            return true;
        }
        return false;
    }
    @Override
    public String getWinner(){
        return winner;
    }

}
