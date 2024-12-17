package org.example.eiscuno.model.cardAbility;

import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;

/**
 * Represents the base class for card abilities in the game
 * Defines common functionality and attributes shared by all card abilities
 * @author Nicolás Córdoba
 * @author Miguel Castillo
 * @author Camilo Pinzon
 * @version 1.0
 */
public class ACardAbility implements ICardAbility{
    protected GameUnoController gameUnoController;
    protected final GameUno gameUno;

    /**
     * Constructs a CardAbility with the specified game controller and game instance
     * @param gameUnoController the controller managing the game logic
     * @param gameUno           the current game instance
     */
    public ACardAbility(GameUnoController gameUnoController, GameUno gameUno) {
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
