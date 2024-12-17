package org.example.eiscuno.model.card.cardFactory;

import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.card.ACard;
import org.example.eiscuno.model.card.cardTypes.NumberCard;
import org.example.eiscuno.model.card.cardTypes.SpecialCard;
import org.example.eiscuno.model.card.cardTypes.SpecialColorCard;
import org.example.eiscuno.model.cardAbility.AbilityTypes.*;
import org.example.eiscuno.model.game.GameUno;

/**
 * Factory class for creating card instances in the game
 * Determines the type of card to create based on its value and assigns the appropriate ability
 * @author Nicolás Córdoba
 * @author Miguel Castillo
 * @author Camilo Pinzon
 * @version 1.0
 */
public class CardFactory {

    /**
     * Creates a specific type of {@link ACard} based on the provided value, color, and abilities.
     * <p>
     * This method uses a switch statement to determine the type of card to create:
     * <ul>
     *     <li>Number cards (values "0" to "9") are created as {@link NumberCard} with a {@link NumberCardAbility}.</li>
     *     <li>Special cards such as "SKIP", "RESERVE", and "TWO_WILD_DRAW" are created as {@link SpecialColorCard} with respective abilities.</li>
     *     <li>Special cards like "FOUR_WILD_DRAW" and "WILD" are created as {@link SpecialCard} with associated abilities.</li>
     * </ul>
     * If an unexpected value is passed, an {@link IllegalStateException} is thrown.
     * </p>
     *
     * @param url                the image URL representing the card.
     * @param value              the value of the card (e.g., numbers, "SKIP", "WILD").
     * @param color              the color of the card (e.g., "RED", "BLUE").
     * @param gameUno            the current {@link GameUno} instance for game logic.
     * @param gameUnoController  the {@link GameUnoController} instance for managing game state.
     * @return a specific {@link ACard} instance based on the provided parameters.
     * @throws IllegalStateException if the card value is unexpected or not recognized.
     */
    public static ACard createCard(String url, String value, String color, GameUno gameUno, GameUnoController gameUnoController) {
        ACard card = switch (value) {
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
