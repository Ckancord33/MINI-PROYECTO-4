package org.example.eiscuno.cardAbility.AbilityTypes;

import org.example.eiscuno.cardAbility.CardAbility;
import org.example.eiscuno.cardAbility.ICardAbility;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;

/**
 * Represents the ability associated with Number Cards in the game
 * This ability changes the turn to the next player when executed.
 */
public class NumberCardAbility extends CardAbility {


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
