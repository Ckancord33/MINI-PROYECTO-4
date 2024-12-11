package org.example.eiscuno.cardAbility;

import org.example.eiscuno.model.deck.Deck;
import org.example.eiscuno.model.player.Player;

public class DrawTwoAbility implements ICardAbility {

    @Override
    public void execute(Player player, Deck deck) {
        player.draw(amount);

    }
}
