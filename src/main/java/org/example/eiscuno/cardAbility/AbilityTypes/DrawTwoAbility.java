package org.example.eiscuno.cardAbility.AbilityTypes;

import javafx.application.Platform;
import org.example.eiscuno.cardAbility.ACardAbility;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;
import org.example.eiscuno.model.player.Player;

/**
 * Represents the "Draw Two" ability in the game
 * This ability forces the victim player to draw two cards
 * @author Nicolás Córdoba
 * @author Miguel Castillo
 * @author Camilo Pinzon
 * @version 1.0
 */
public class DrawTwoAbility extends ACardAbility {

    public DrawTwoAbility(GameUnoController gameUnoController, GameUno gameUno) {
        super(gameUnoController, gameUno);
    }

    /**
     * Executes the "Draw Two" ability in the game
     * This method forces the victim player to draw two cards and triggers the associated animation
     */
    @Override
    public void execute() {
        gameUno.eatCard(gameUno.getVictimPlayer(), 2);
        Player player2 = gameUno.getVictimPlayer();
        Platform.runLater(()->{
            gameUnoController.eatCardAnimation(player2.getTypePlayer(), 2);
        });
    }

}
