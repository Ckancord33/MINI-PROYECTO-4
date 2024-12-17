package org.example.eiscuno.exceptions;

public class StarterSpecialCard extends Exception {
    public StarterSpecialCard(String message) {
        super(message);
    }

    public  StarterSpecialCard(String message, Throwable cause) {
        super(message, cause);
    }

    public StarterSpecialCard(Throwable cause) {
        super(cause);
    }

    public StarterSpecialCard() {
        super();
    }
}
