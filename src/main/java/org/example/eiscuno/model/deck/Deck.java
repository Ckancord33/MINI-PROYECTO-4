package org.example.eiscuno.model.deck;

import org.example.eiscuno.controller.GameUnoController;
import org.example.eiscuno.model.card.cardFactory.CardFactory;
import org.example.eiscuno.model.game.GameUno;
import org.example.eiscuno.model.unoenum.EISCUnoEnum;
import org.example.eiscuno.model.card.Card;

import java.util.Collections;
import java.util.Stack;

/**
 * Represents a deck of Uno cards.
 */
public class Deck {
    private Stack<Card> deckOfCards;

    /**
     * Constructs a new deck of Uno cards and initializes it.
     */
    public Deck() {
        deckOfCards = new Stack<>();
    }

    /**
     * Initializes the deck with cards based on the EISCUnoEnum values.
     */
    public void initializeDeck(GameUno gameUno, GameUnoController gameUnoController) {
        for(int j = 0;j < 2;j++ ) {
            for (EISCUnoEnum cardEnum : EISCUnoEnum.values()) {
                if (cardEnum.name().startsWith("GREEN_") ||
                        cardEnum.name().startsWith("YELLOW_") ||
                        cardEnum.name().startsWith("BLUE_") ||
                        cardEnum.name().startsWith("RED_") ||
                        cardEnum.name().startsWith("SKIP_") ||
                        cardEnum.name().startsWith("RESERVE_") ||
                        cardEnum.name().startsWith("TWO_WILD_DRAW_") ||
                        cardEnum.name().equals("FOUR_WILD_DRAW") ||
                        cardEnum.name().equals("WILD")) {
                    if (cardEnum.name().equals("WILD") || cardEnum.name().equals("FOUR_WILD_DRAW")) {
                        for (int i = 0; i < 30; i++) {
                            Card card = CardFactory.createCard(cardEnum.getFilePath(), getCardValue(cardEnum.name()), getCardColor(cardEnum.name()), gameUno, gameUnoController);
                            deckOfCards.push(card);
                        }
                    }
                    Card card = CardFactory.createCard(cardEnum.getFilePath(), getCardValue(cardEnum.name()), getCardColor(cardEnum.name()), gameUno, gameUnoController);
                    deckOfCards.push(card);
                }
            }
        }
        Collections.shuffle(deckOfCards);
    }

    private String getCardValue(String name) {
        if (name.endsWith("0")){
            return "0";
        } else if (name.endsWith("1")){
            return "1";
        } else if (name.endsWith("2")){
            return "2";
        } else if (name.endsWith("3")){
            return "3";
        } else if (name.endsWith("4")){
            return "4";
        } else if (name.endsWith("5")){
            return "5";
        } else if (name.endsWith("6")){
            return "6";
        } else if (name.endsWith("7")){
            return "7";
        } else if (name.endsWith("8")){
            return "8";
        } else if (name.endsWith("9")){
            return "9";
        } else if (name.endsWith("SKIP")){
            return "SKIP";
        } else if(name.endsWith("RESERVE")){
            return "RESERVE";
        } else if(name.endsWith("TWO_WILD_DRAW")){
            return "TWO_WILD_DRAW";
        } else if(name.equals("FOUR_WILD_DRAW")){
            return "FOUR_WILD_DRAW";
        } else if(name.equals("WILD")){
            return "WILD";
        } else {
            return null;
        }

    }

    private String getCardColor(String name){
        if(name.startsWith("GREEN")){
            return "GREEN";
        } else if(name.startsWith("YELLOW")){
            return "YELLOW";
        } else if(name.startsWith("BLUE")){
            return "BLUE";
        } else if(name.startsWith("RED")){
            return "RED";
        } else {
            return "ESPECIAL";
        }
    }

    /**
     * Takes a card from the top of the deck.
     *
     * @return the card from the top of the deck
     * @throws IllegalStateException if the deck is empty
     */
    public Card takeCard() {
        if (deckOfCards.isEmpty()) {
            throw new IllegalStateException("No hay más cartas en el mazo.");
        }
        return deckOfCards.pop();
    }

    /**
     * Checks if the deck is empty.
     *
     * @return true if the deck is empty, false otherwise
     */
    public boolean isEmpty() {
        return deckOfCards.isEmpty();
    }
}
