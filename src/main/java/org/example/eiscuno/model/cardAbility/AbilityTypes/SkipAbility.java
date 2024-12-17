package org.example.eiscuno.model.cardAbility.AbilityTypes;

import org.example.eiscuno.model.cardAbility.ACardAbility;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;

/**
 * Represents the "Skip" ability in the game
 * This ability skips the next player's turn when executed
 * @author Nicolás Córdoba
 * @author Miguel Castillo
 * @author Camilo Pinzon
 * @version 1.0
 */
public class SkipAbility extends ACardAbility {

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
