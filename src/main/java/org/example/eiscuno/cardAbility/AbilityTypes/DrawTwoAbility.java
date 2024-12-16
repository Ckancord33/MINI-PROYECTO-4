package org.example.eiscuno.cardAbility.AbilityTypes;

import javafx.application.Platform;
import org.example.eiscuno.cardAbility.CardAbility;
import org.example.eiscuno.cardAbility.ICardAbility;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;
import org.example.eiscuno.model.player.Player;

public class DrawTwoAbility extends CardAbility {

    public DrawTwoAbility(GameUnoController gameUnoController, GameUno gameUno) {
        super(gameUnoController, gameUno);
    }

    @Override
    public void execute() {
        gameUno.eatCard(gameUno.getVictimPlayer(), 2);
        Player player2 = gameUno.getVictimPlayer();
        Platform.runLater(()->{
            gameUnoController.eatCardAnimation(player2.getTypePlayer(), 2);
        });
        Platform.runLater(() -> {
            gameUnoController.printCardsMachinePlayer();
            gameUnoController.printCardsHumanPlayer();}
        );
    }

}
