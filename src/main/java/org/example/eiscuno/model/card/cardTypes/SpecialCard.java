package org.example.eiscuno.model.card.cardTypes;

import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.card.ICard;

public class SpecialCard extends Card {

    public SpecialCard(String url, String value, String color) {
        super(url, value, color);
    }

    @Override
    public boolean isPlayable(ICard card) {
        return true;
    }
}
