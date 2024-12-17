package org.example.eiscuno.model.table;

import org.example.eiscuno.model.card.ACard;

import java.util.ArrayList;

/**
 * Represents the table in the Uno game where cards are played.
 * @author Fabian Valencia
 * @version 1.0
 */
public class Table {
    private ArrayList<ACard> cardsTable;

    /**
     * Constructs a new Table object with no cards on it.
     */
    public Table(){
        this.cardsTable = new ArrayList<ACard>();
    }

    /**
     * Adds a card to the table.
     *
     * @param card The card to be added to the table.
     */
    public void addCardOnTheTable(ACard card){
        this.cardsTable.add(card);
    }

    /**
     * Retrieves the current card on the table.
     *
     * @return The card currently on the table.
     * @throws IndexOutOfBoundsException if there are no cards on the table.
     */
    public ACard getCurrentCardOnTheTable() throws IndexOutOfBoundsException {
        if (cardsTable.isEmpty()) {
            throw new IndexOutOfBoundsException("There are no cards on the table.");
        }
        return this.cardsTable.get(this.cardsTable.size()-1);
    }
}
