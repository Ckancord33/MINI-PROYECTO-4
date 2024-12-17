package org.example.eiscuno.exceptions;
/**
 * Exception thrown when a special card is improperly used or encountered
 * at the start of the game in the UNO card game logic.
 * <p>
 * This custom exception extends {@link Exception} to represent specific
 * error conditions related to the initialization of special cards.
 * </p>
 *
 * @author Nicolás Córdoba
 * @author Miguel Castillo
 * @author Camilo Pinzón
 * @version 1.0
 */
public class StarterSpecialCard extends Exception {

    /**
     * Constructs a new {@code StarterSpecialCard} exception with the specified detail message.
     *
     * @param message the detail message that explains the cause of the exception.
     */
    public StarterSpecialCard(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code StarterSpecialCard} exception with the specified detail message
     * and cause.
     *
     * @param message the detail message that explains the cause of the exception.
     * @param cause   the cause of the exception (a {@link Throwable} instance).
     */
    public  StarterSpecialCard(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code StarterSpecialCard} exception with the specified cause.
     *
     * @param cause the cause of the exception (a {@link Throwable} instance).
     */
    public StarterSpecialCard(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new {@code StarterSpecialCard} exception with no detail message or cause.
     */
    public StarterSpecialCard() {
        super();
    }
}
