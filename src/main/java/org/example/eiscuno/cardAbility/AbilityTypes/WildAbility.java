package org.example.eiscuno.cardAbility.AbilityTypes;

import org.example.eiscuno.cardAbility.ACardAbility;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;
import org.example.eiscuno.model.player.Player;

/**
 * Represents the "Wild" ability in the game
 * This ability allows the player to choose a color when executed, and then changes the turn to the next player
 * @author Nicolás Córdoba
 * @author Miguel Castillo
 * @author Camilo Pinzon
 * @version 1.0
 */
public class WildAbility extends ACardAbility {

    public WildAbility(GameUnoController gameUnoController, GameUno gameUno) {
        super(gameUnoController, gameUno);
    }

    /**
     * Executes the "Wild" ability, allowing the player to select a color and then changes the turn
     * If the current player is human, color selection buttons are displayed
     */
    @Override
    public void execute() {
        gameUno.setIsPlayerSelectingColor(true);
        Player player = gameUno.getActualPlayer();
        if(player.getTypePlayer().equals("HUMAN_PLAYER")) {
            gameUnoController.showColorButtons();
        }
        gameUno.changeTurn();
    }
}
