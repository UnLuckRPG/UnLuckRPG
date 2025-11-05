package item;

import unit.Unit;

import java.util.Random;

public abstract class Dice extends Item {
    protected int min;
    protected int max;
    protected Random random = new Random();

    public int roll() {
        return random.nextInt((max - min) + 1) + min;
    }

    @Override
    public void effect(Unit target) {
        int damage = roll();
        int targetHp = target.getHp() - damage;
        if (targetHp < 0) {
            targetHp = 0;
        }
        target.setHp(targetHp);
    }
}
