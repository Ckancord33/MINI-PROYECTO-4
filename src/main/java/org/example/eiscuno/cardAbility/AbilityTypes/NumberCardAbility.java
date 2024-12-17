package org.example.eiscuno.cardAbility.AbilityTypes;

import org.example.eiscuno.cardAbility.ACardAbility;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;

/**
 * Represents the ability associated with Number Cards in the game
 * This ability changes the turn to the next player when executed.
 * @author Nicolás Córdoba
 * @author Miguel Castillo
 * @author Camilo Pinzon
 * @version 1.0
 */
public class NumberCardAbility extends ACardAbility {


    public NumberCardAbility(GameUnoController gameUnoController, GameUno gameUno) {
        super(gameUnoController, gameUno);
    }

    /**
     * Executes the "Number Card" ability, which changes the turn to the next player
     */
    @Override
    public void execute() {
        gameUno.changeTurn();
    }
}
