package org.example.eiscuno.model.card;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.eiscuno.cardAbility.ICardAbility;

/**
 * Represents a card in the Uno game.
 */
public class Card implements ICard {
    protected String url;
    protected String value;
    protected String color;
    protected Image image;
    protected ImageView cardImageView;
    protected ICardAbility ability;

    /**
     * Constructs a Card with the specified image URL and name.
     *
     * @param url the URL of the card image
     * @param value of the card
     */
    public Card(String url, String value, String color, ICardAbility ability) {
        this.url = url;
        this.value = value;
        this.color = color;
        this.image = new Image(String.valueOf(getClass().getResource(url)));
        this.cardImageView = createCardImageView();
        this.ability = ability;
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

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean isPlayable(ICard card) {
        return false;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public ICardAbility getAbility() {
        return ability;
    }

    @Override
    public void setColor(String color) {
    }
}
