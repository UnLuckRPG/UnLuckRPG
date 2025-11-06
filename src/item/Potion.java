package item;

import unit.Unit;

public abstract class Potion extends Item {

    public Potion(String name) {
        super(name);
    }
    @Override
    public abstract void effect(Unit target);
}
