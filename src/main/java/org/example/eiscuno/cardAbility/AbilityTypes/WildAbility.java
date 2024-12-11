package org.example.eiscuno.cardAbility.AbilityTypes;

import org.example.eiscuno.cardAbility.CardAbility;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;

public class WildAbility extends CardAbility {

    public WildAbility(GameUnoController gameUnoController, GameUno gameUno) {
        super(gameUnoController, gameUno);
    }

    @Override
    public void execute() {
        gameUno.setColorToCardPlayed("RED");
        gameUno.changeTurn();
    }
}
