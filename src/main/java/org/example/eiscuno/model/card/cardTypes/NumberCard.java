package org.example.eiscuno.model.card.cardTypes;

import org.example.eiscuno.cardAbility.ICardAbility;
import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.card.ICard;

public class NumberCard extends Card {

    public NumberCard(String url, String value, String color, ICardAbility ability) {
        super(url, value, color, ability);
    }

    @Override
    public boolean isPlayable(ICard card) {
        return card.getColor().equals(this.getColor()) || card.getValue().equals(this.getValue());
    }
}
