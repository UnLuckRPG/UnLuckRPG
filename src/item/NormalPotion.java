package item;

import unit.Unit;

public class NormalPotion extends Potion {
    private String name = "기본 포션";

    @Override
    public void effect(Unit target) {
        target.setHp(target.getMaxHp());
    }
}
