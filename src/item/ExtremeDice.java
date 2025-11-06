package item;

import unit.Unit;

public class ExtremeDice extends Dice {
//    private String name = "극단적인 주사위";

    public ExtremeDice() {
        super("극단적인 주사위");
        this.min = 1;
        this.max = 10;
    }
}