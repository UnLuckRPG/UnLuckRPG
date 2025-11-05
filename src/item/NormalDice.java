package item;

import unit.Unit;

public class NormalDice extends Dice {
    private String name = "기본 주사위";

    public NormalDice() {
        this.min = 1;
        this.max = 6;
    }
}
