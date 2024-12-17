package org.example.eiscuno.model.card;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.eiscuno.cardAbility.ICardAbility;

/**
 * Represents a card in the Uno game.
 * @author Nicolás Córdoba
 * @author Miguel Castillo
 * @author Camilo Pinzon
 * @author Fabian Valencia
 * @version 1.0
 */
public class ACard implements ICard {
    protected String url;
    protected String value;
    protected String color;
    protected Image image;
    protected ImageView cardImageView;
    protected ICardAbility ability;

    private static boolean testMode = false;

    /**
     * Sets the test mode flag for the card class.
     * <p>
     * This method allows enabling or disabling test mode, which can alter certain behaviors
     * during testing scenarios.
     * </p>
     *
     * @param testMode {@code true} to enable test mode; {@code false} to disable it.
     */
    public static void setTestMode(boolean testMode) {
        ACard.testMode = testMode;
    }

    /**
     * Constructs a Card with the specified image URL and name.
     *
     * @param url the URL of the card image
     * @param value of the card
     */
    public ACard(String url, String value, String color, ICardAbility ability) {
        if (!testMode) {
            this.url = url;
            this.value = value;
            this.color = color;
            this.image = new Image(String.valueOf(getClass().getResource(url)));
            this.cardImageView = createCardImageView();
            this.ability = ability;
        }
    }

    /**
     * Creates and configures the ImageView for the card.
     *
     * @return the configured ImageView of the card
     */
    @Override
    public ImageView createCardImageView() {
        ImageView card = new ImageView(this.image);
        card.setY(16);
        card.setFitHeight(90*2);
        card.setFitWidth(70*2);
        return card;
    }

    /**
     * Gets the ImageView representation of the card.
     *
     * @return the ImageView of the card
     */
    @Override
    public ImageView getCard() {
        return cardImageView;
    }

    /**
     * Gets the image of the card.
     *
     * @return the Image of the card
     */
    @Override
    public Image getImage() {
        return image;
    }

    /**
     * Gets the value of the card
     * @return The value of the card
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * Checks if the current card is playable based on the specified card
     * @param card the card to compare
     * @return True if the card is playable, false otherwise
     */
    @Override
    public boolean isPlayable(ICard card) {
        return false;
    }

    /**
     * Gets the color of the card
     * @return The color of the card
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * Gets the ability of the card
     * @return The ability of the card
     */
    @Override
    public ICardAbility getAbility() {
        return ability;
    }

    /**
     * Sets the color of the card
     * @param color The new color of the card
     */
    @Override
    public void setColor(String color) {
    }
}
