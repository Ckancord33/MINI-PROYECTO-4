package org.example.eiscuno.model.card;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a card in the Uno game.
 */
public class Card implements ICard {
    private String url;
    private String value;
    private String color;
    private Image image;
    private ImageView cardImageView;

    /**
     * Constructs a Card with the specified image URL and name.
     *
     * @param url the URL of the card image
     * @param value of the card
     */
    public Card(String url, String value, String color) {
        this.url = url;
        this.value = value;
        this.color = color;
        this.image = new Image(String.valueOf(getClass().getResource(url)));
        this.cardImageView = createCardImageView();
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
}
