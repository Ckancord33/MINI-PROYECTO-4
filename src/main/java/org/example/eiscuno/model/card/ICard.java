package org.example.eiscuno.model.card;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.eiscuno.cardAbility.ICardAbility;

/**
 * Interface representing the behavior of a card in the game
 * Defines the methods required for cards
 */
public interface ICard {
    ImageView createCardImageView();
    ImageView getCard();
    Image getImage();
    String getColor();
    String getValue();
    boolean isPlayable(ICard card);
    ICardAbility getAbility();
    void setColor(String color);




}
