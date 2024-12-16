package org.example.eiscuno.cardAbility;

import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;

public class CardAbility implements ICardAbility{
    protected GameUnoController gameUnoController;
    protected final GameUno gameUno;

    public CardAbility(GameUnoController gameUnoController, GameUno gameUno) {
        this.gameUnoController = gameUnoController;
        this.gameUno = gameUno;
    }


    @Override
    public void execute() {

    }
}
