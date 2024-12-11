package org.example.eiscuno.cardAbility.AbilityTypes;

import org.example.eiscuno.cardAbility.CardAbility;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;

public class DrawFourAbility extends CardAbility {

    public DrawFourAbility(GameUnoController gameUnoController, GameUno gameUno) {
        super(gameUnoController, gameUno);
    }

    @Override
    public void execute() {
        gameUno.eatCard(gameUno.getVictimPlayer(), 4);
        gameUno.setColorToCardPlayed("RED");
        gameUnoController.printCardsMachinePlayer();
        gameUnoController.printCardsMachinePlayer();
        gameUno.changeTurn();
    }
}
