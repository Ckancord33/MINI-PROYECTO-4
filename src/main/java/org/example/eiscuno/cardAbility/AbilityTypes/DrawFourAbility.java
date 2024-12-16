package org.example.eiscuno.cardAbility.AbilityTypes;

import javafx.application.Platform;
import org.example.eiscuno.cardAbility.CardAbility;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;
import org.example.eiscuno.model.player.Player;

public class DrawFourAbility extends CardAbility {

    public DrawFourAbility(GameUnoController gameUnoController, GameUno gameUno) {
        super(gameUnoController, gameUno);
    }

    @Override
    public void execute() {
        gameUno.eatCard(gameUno.getVictimPlayer(), 4);
        gameUno.setIsPlayerSelectingColor(true);
        Player player = gameUno.getActualPlayer();
        if(player.getTypePlayer().equals("HUMAN_PLAYER")) {
            gameUnoController.showColorButtons();
        }
        Platform.runLater(() -> {
            gameUnoController.printCardsMachinePlayer();
            gameUnoController.printCardsHumanPlayer();}
        );
    }
}
