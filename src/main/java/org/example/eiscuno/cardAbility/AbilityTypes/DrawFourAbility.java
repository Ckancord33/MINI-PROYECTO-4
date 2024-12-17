package org.example.eiscuno.cardAbility.AbilityTypes;

import javafx.application.Platform;
import org.example.eiscuno.cardAbility.CardAbility;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;
import org.example.eiscuno.model.player.Player;

/**
 * Represents the "Draw Four" ability in the game
 * This ability forces the victim player to draw four cards and then allows the current player to select a color
 */
public class DrawFourAbility extends CardAbility {

    public DrawFourAbility(GameUnoController gameUnoController, GameUno gameUno) {
        super(gameUnoController, gameUno);
    }

    /**
     * Executes the "Draw Four" ability
     * This method makes the victim player draw four cards and shows an animation
     * If the current player is human, it displays the color selection buttons
     */
    @Override
    public void execute() {
        gameUno.eatCard(gameUno.getVictimPlayer(), 4);
        gameUno.setIsPlayerSelectingColor(true);
        Player player = gameUno.getActualPlayer();
        Player victimPlayer = gameUno.getVictimPlayer();
        Platform.runLater(()->{
            gameUnoController.eatCardAnimation(victimPlayer.getTypePlayer(), 4);
                });
        if(player.getTypePlayer().equals("HUMAN_PLAYER")) {
            gameUnoController.showColorButtons();
        }
    }
}
