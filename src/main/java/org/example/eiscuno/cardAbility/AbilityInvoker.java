package org.example.eiscuno.cardAbility;

public class AbilityInvoker {
    private ICardAbility ability;

    public AbilityInvoker() {

    }

    public void setAbility(ICardAbility ability) {
        this.ability = ability;
    }

    public void execute() {
        ability.execute();
    }
}
