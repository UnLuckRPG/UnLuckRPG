package item;

import unit.Unit;

public class StableDice extends Dice {
//    private String name = "안정적인 주사위";

    public StableDice() {
        super("안정적인 주사위");
        this.min = 3;
        this.max = 4;
    }
}