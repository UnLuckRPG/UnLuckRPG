package item;

import unit.Unit;

public abstract class Item {
    // target에게 피해를 주거나 체력을 회복시킨다.
    public abstract void effect(Unit target);
    private final String name;
    protected Item(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }



}
