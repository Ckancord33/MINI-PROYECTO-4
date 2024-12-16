package org.example.eiscuno.model.card.cardFactory;

import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.card.ICard;
import org.example.eiscuno.model.card.cardTypes.NumberCard;
import org.example.eiscuno.model.card.cardTypes.SpecialCard;
import org.example.eiscuno.model.card.cardTypes.SpecialColorCard;

public class CardFactory {
    public static Card createCard(String url, String value, String color) {
        Card card = switch (value) {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> new NumberCard(url, value, color);
            case "SKIP" -> new SpecialColorCard(url, value, color);
            case "RESERVE" -> new SpecialColorCard(url, value, color);
            case "TWO_WILD_DRAW" -> new SpecialColorCard(url, value, color);
            case "FOUR_WILD_DRAW" -> new SpecialCard(url, value, color);
            case "WILD" -> new SpecialCard(url, value, color);
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
        return card;
    }
}
