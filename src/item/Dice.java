package item;

import unit.Unit;

import java.util.Random;

public abstract class Dice extends Item {
    protected int min;
    protected int max;
    private int recentEye; // 가장 최근에 굴려서 나온 눈

    public int getRecentEye() {
      return recentEye;
    }

    public int getMin() {
      return this.min;
    }

    public int getMax() {
      return this.max;
    }

    public Dice(String name) {
        super(name);
    }
    protected Random random = new Random();
    public int roll() {
        return random.nextInt((max - min) + 1) + min;
    }

    @Override
    public void effect(Unit target) {
        int damage = roll();
        recentEye = damage;
        int targetHp = target.getHp() - damage;
        if (targetHp < 0) {
            targetHp = 0;
        }
        target.setHp(targetHp);
    }
}
