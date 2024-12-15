package org.example.eiscuno.cardAbility.AbilityTypes;

import org.example.eiscuno.cardAbility.CardAbility;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.game.GameUno;
import org.example.eiscuno.model.player.Player;

public class WildAbility extends CardAbility {

    public WildAbility(GameUnoController gameUnoController, GameUno gameUno) {
        super(gameUnoController, gameUno);
    }

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
