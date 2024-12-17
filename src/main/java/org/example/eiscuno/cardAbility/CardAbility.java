package org.example.eiscuno.cardAbility;

import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;

/**
 * Represents the base class for card abilities in the game
 * Defines common functionality and attributes shared by all card abilities
 */
public class CardAbility implements ICardAbility{
    protected GameUnoController gameUnoController;
    protected final GameUno gameUno;

    /**
     * Constructs a CardAbility with the specified game controller and game instance
     * @param gameUnoController the controller managing the game logic
     * @param gameUno           the current game instance
     */
    public CardAbility(GameUnoController gameUnoController, GameUno gameUno) {
        this.gameUnoController = gameUnoController;
        this.gameUno = gameUno;
    }

    /**
     * Executes the ability associated with the card.
     */
    @Override
    public void execute() {

    }
}
