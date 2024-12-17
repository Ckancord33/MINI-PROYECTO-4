package org.example.eiscuno.cardAbility.AbilityTypes;

import org.example.eiscuno.cardAbility.CardAbility;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;

/**
 * Represents the "Skip" ability in the game
 * This ability skips the next player's turn when executed
 */
public class SkipAbility extends CardAbility {

    public SkipAbility(GameUnoController gameUnoController, GameUno gameUno) {
        super(gameUnoController, gameUno);
    }

    /**
     * Executes the "Skip" ability, which skips the next player's turn
     */
    @Override
    public void execute() {
    }
}
