package item;

import unit.Unit;

public abstract class Item {
    // target에게 피해를 주거나 체력을 회복시킨다.
    public abstract void effect(Unit target);
    private String name;
    public String getName() {
        return name;
    }
}
