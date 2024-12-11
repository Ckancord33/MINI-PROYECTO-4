package org.example.eiscuno.cardAbility.AbilityTypes;

import org.example.eiscuno.cardAbility.CardAbility;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;

public class SkipAbility extends CardAbility {

    public SkipAbility(GameUnoController gameUnoController, GameUno gameUno) {
        super(gameUnoController, gameUno);
    }

    @Override
    public void execute() {
    }
}
