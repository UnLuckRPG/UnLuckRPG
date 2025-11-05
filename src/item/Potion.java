package item;

import unit.Unit;

public abstract class Potion extends Item {
    @Override
    public abstract void effect(Unit target);
}
