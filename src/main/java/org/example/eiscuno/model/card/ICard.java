package org.example.eiscuno.model.card;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.eiscuno.cardAbility.ICardAbility;

/**
 * Interface representing the common behaviors of a card in the UNO card game.
 * <p>
 * This interface defines methods for retrieving a card's visual representation, its properties (color, value),
 * checking if the card is playable, and managing its abilities.
 * </p>
 *
 * @author Nicolás Córdoba
 * @author Miguel Castillo
 * @author Camilo Pinzón
 * @version 1.0
 */
public interface ICard {

    /**
     * Creates and configures the {@link ImageView} for the card's image.
     * <p>
     * This method is responsible for creating the visual representation of the card that can be displayed in the UI.
     * </p>
     *
     * @return the configured {@link ImageView} for the card
     */
    ImageView createCardImageView();

    /**
     * Gets the {@link ImageView} representing the card.
     * <p>
     * This method returns the visual component of the card that is displayed in the game's user interface.
     * </p>
     *
     * @return the {@link ImageView} of the card
     */
    ImageView getCard();

    /**
     * Gets the {@link Image} object of the card.
     * <p>
     * This method retrieves the image representation of the card, which can be used for various purposes
     * like rendering the card in the UI or performing image-based operations.
     * </p>
     *
     * @return the {@link Image} of the card
     */
    Image getImage();

    /**
     * Gets the color of the card.
     * <p>
     * The color defines the card's category and can be one of the standard UNO colors:
     * "RED", "BLUE", "GREEN", or "YELLOW".
     * </p>
     *
     * @return the color of the card
     */
    String getColor();

    /**
     * Gets the value of the card.
     * <p>
     * The value could represent a number (e.g., "0" to "9") or a special card keyword (e.g., "SKIP", "WILD").
     * </p>
     *
     * @return the value of the card
     */
    String getValue();

    /**
     * Determines if the current card can be played based on the specified card.
     * <p>
     * This method checks the rules for whether a card can be placed on the current game stack
     * in comparison to another card.
     * </p>
     *
     * @param card the {@link ICard} to compare with
     * @return {@code true} if the card can be played, {@code false} otherwise
     */
    boolean isPlayable(ICard card);

    /**
     * Gets the ability associated with the card.
     * <p>
     * This method retrieves the specific behavior or action that the card can trigger during the game,
     * such as drawing cards or skipping turns.
     * </p>
     *
     * @return the {@link ICardAbility} representing the card's special ability
     */
    ICardAbility getAbility();

    /**
     * Sets the color of the card.
     * <p>
     * This method allows updating the color of the card, commonly used for wild cards
     * that can change the active color in the game.
     * </p>
     *
     * @param color the new color to set for the card
     */
    void setColor(String color);
}
