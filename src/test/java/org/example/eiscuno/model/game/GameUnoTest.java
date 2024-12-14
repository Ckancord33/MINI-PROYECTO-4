package org.example.eiscuno.model.game;

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

    @Test
    public void testEqualCardsDistribution() {

        Player humanPlayer = new Player("HUMAN_PLAYER");
        Player machinePlayer = new Player("MACHINE_PLAYER");
        Deck deck = new Deck();
        Table table = new Table();
        GameUno myGameUno = new GameUno(humanPlayer, machinePlayer, deck, table);

        myGameUno.eatCard(humanPlayer, 4);
        myGameUno.eatCard(machinePlayer, 4);

        int playerInitialCards = humanPlayer.getCardsPlayer().size();
        int machineInitialCards = machinePlayer.getCardsPlayer().size();
        Assertions.assertEquals(playerInitialCards, machineInitialCards);
    }

    @Test
    public void testGameOverReturnsNullAtStart() {

        Player humanPlayer = new Player("HUMAN_PLAYER");
        Player machinePlayer = new Player("MACHINE_PLAYER");
        Deck deck = new Deck();
        Table table = new Table();
        GameUno myGameUno = new GameUno(humanPlayer, machinePlayer, deck, table);

        Assertions.assertNull(myGameUno.isGameOver());
    }

    @Test
    public void testHaveSungOne() {

        Player humanPlayer = new Player("HUMAN_PLAYER");
        Player machinePlayer = new Player("MACHINE_PLAYER");
        Deck deck = new Deck();
        Table table = new Table();
        GameUno myGameUno = new GameUno(humanPlayer, machinePlayer, deck, table);

        // Obtiene la cantidad inicial de cartas de la máquina
        int initialMachineCards = machinePlayer.getCardsPlayer().size();

        // Simula que el jugador humano canta "Uno"
        myGameUno.haveSungOne("HUMAN_PLAYER");

        // Verifica que las cartas de la máquina hayan incrementado en 1
        Assertions.assertTrue(machinePlayer.getCardsPlayer().size() == initialMachineCards+1);
    }


}
