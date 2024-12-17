package org.example.eiscuno.cardAbility;

/**
 * Invokes the execution of a card ability in the game
 */
public class AbilityInvoker {
    private ICardAbility ability;

    /**
     * Constructs an AbilityInvoker with no predefined ability.
     */
    public AbilityInvoker() {

    }

    /**
     * Sets the card ability to be executed
     * @param ability the card ability to assign to this invoker
     */
    public void setAbility(ICardAbility ability) {
        this.ability = ability;
    }

    /**
     * Executes the currently assigned card ability.
     */
    public void execute() {
        ability.execute();
    }
}
