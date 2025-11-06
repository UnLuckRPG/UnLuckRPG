package item;

import unit.Unit;

public class NormalDice extends Dice {
//    private String name = "기본 주사위";

    public NormalDice() {
        super("기본 주사위");
        this.min = 1;
        this.max = 6;
    }
}
