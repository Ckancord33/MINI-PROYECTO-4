package org.example.eiscuno.model.game;

import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.deck.Deck;
import org.example.eiscuno.model.game.GameUno;
import org.example.eiscuno.model.player.Player;
import org.example.eiscuno.model.table.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GameUnoTest {

    @BeforeAll
    public static void setUp() {
        Card.setTestMode(true);
    }

    /**
     * Test to ensure that the number of cards dealt to both players is equal.
     */
    @Test
    public void testEqualCardsDistribution() {

        Player humanPlayer = new Player("HUMAN_PLAYER");
        Player machinePlayer = new Player("MACHINE_PLAYER");
        Deck deck = new Deck();
        Table table = new Table();
        GameUno myGameUno = new GameUno(humanPlayer, machinePlayer, deck, table);
        deck.initializeDeck(myGameUno, new GameUnoController());
        myGameUno.startGame();

        int playerInitialCards = humanPlayer.getCardsPlayer().size();
        int machineInitialCards = machinePlayer.getCardsPlayer().size();

        Assertions.assertEquals(playerInitialCards, machineInitialCards);
    }


    /**
     *
     */
    @Test
    public void testGameOverReturnsNullAtStart() {

        Player humanPlayer = new Player("HUMAN_PLAYER");
        Player machinePlayer = new Player("MACHINE_PLAYER");
        Deck deck = new Deck();
        Table table = new Table();
        GameUno myGameUno = new GameUno(humanPlayer, machinePlayer, deck, table);
        deck.initializeDeck(myGameUno, new GameUnoController());
        myGameUno.startGame();

        Assertions.assertNull(myGameUno.isGameOver());
    }

    @Test
    public void testChangeTurnAtStartChangesToMachineTurn() {
        Player humanPlayer = new Player("HUMAN_PLAYER");
        Player machinePlayer = new Player("MACHINE_PLAYER");
        Deck deck = new Deck();
        Table table = new Table();
        GameUno myGameUno = new GameUno(humanPlayer, machinePlayer, deck, table);
        deck.initializeDeck(myGameUno, new GameUnoController());
        myGameUno.startGame();

        // Change turn starting the game
        myGameUno.changeTurn();

        Assertions.assertTrue(myGameUno.getIsMachineTurn());
    }
}
