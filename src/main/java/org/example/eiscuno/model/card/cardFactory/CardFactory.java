package org.example.eiscuno.model.card.cardFactory;

import org.example.eiscuno.cardAbility.AbilityTypes.*;
import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.card.Card;
import org.example.eiscuno.model.card.cardTypes.NumberCard;
import org.example.eiscuno.model.card.cardTypes.SpecialCard;
import org.example.eiscuno.model.card.cardTypes.SpecialColorCard;
import org.example.eiscuno.model.game.GameUno;

public class CardFactory {
    public static Card createCard(String url, String value, String color, GameUno gameUno, GameUnoController gameUnoController) {
        Card card = switch (value) {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> new NumberCard(url, value, color, new NumberCardAbility(gameUnoController, gameUno));
            case "SKIP" -> new SpecialColorCard(url, value, color, new SkipAbility(gameUnoController, gameUno));
            case "RESERVE" -> new SpecialColorCard(url, value, color, new SkipAbility(gameUnoController, gameUno));
            case "TWO_WILD_DRAW" -> new SpecialColorCard(url, value, color, new DrawTwoAbility(gameUnoController, gameUno));
            case "FOUR_WILD_DRAW" -> new SpecialCard(url, value, color,new DrawFourAbility(gameUnoController, gameUno));
            case "WILD" -> new SpecialCard(url, value, color,new WildAbility(gameUnoController, gameUno));
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
        return card;
    }
}
