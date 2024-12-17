package org.example.eiscuno.model.game;

import javafx.application.Platform;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.deck.Deck;
import org.example.eiscuno.model.player.Player;
import org.example.eiscuno.model.table.Table;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the GameUno class to verify the game logic and ensure the
 * game behaves as expected under different conditions.
 */
class GameUnoTest {

    private Player humanPlayer;
    private Player machinePlayer;
    private Deck deck;
    private Table table;
    private GameUno myGameUno;

    @BeforeAll
    public static void setupJavaFX() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    public void setupGame() {
        humanPlayer = new Player("HUMAN_PLAYER");
        machinePlayer = new Player("MACHINE_PLAYER");
        deck = new Deck();
        table = new Table();
        myGameUno = new GameUno(humanPlayer, machinePlayer, deck, table);
        deck.initializeDeck(myGameUno, new GameUnoController());
        myGameUno.startGame();
    }

    /**
     * Test to ensure that the number of cards dealt to both players are equal.
     */
    @Test
    public void testEqualCardsDistribution() {
        int playerInitialCards = humanPlayer.getCardsPlayer().size();
        int machineInitialCards = machinePlayer.getCardsPlayer().size();

        Assertions.assertEquals(playerInitialCards, machineInitialCards);
    }

    /**
     * Test to check that the game over status returns null at the start of the game.
     */
    @Test
    public void testGameOverReturnsNullAtStart() {
        Assertions.assertNull(myGameUno.isGameOver());
    }

    /**
     * Test to check that the turn changes to the machine correctly at the start of the game.
     */
    @Test
    public void testChangeTurnAtStartChangesToMachineTurn() {
        myGameUno.changeTurn();
        Assertions.assertTrue(myGameUno.getIsMachineTurn());
    }
}
