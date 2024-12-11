package org.example.eiscuno.model.card.cardTypes;

import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.card.ICard;

public class NumberCard extends Card {

    public NumberCard(String url, String value, String color) {
        super(url, value, color);
    }

    @Override
    public boolean isPlayable(ICard card) {
        return card.getColor().equals(this.getColor()) || card.getValue().equals(this.getValue());
    }
}
