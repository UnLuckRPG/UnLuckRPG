package unit;

import item.Item;
import item.Potion;

public class Player extends Unit {
    private Item[] items;
    private static final int MAX_ITEM = 5; // 최대 소유 가능한 아이템의 개수
    private int currentItemNum; // 플레이어가 현재 소유한 아이템의 개수

    public Player(int maxHp) {
        super(maxHp);
        items = new Item[MAX_ITEM];
        // TODO : 기본으로 일반 주사위 하나를 받음
        currentItemNum = 1;
    }

    // TODO
    public boolean addItem() {
        return false;
    }

    // TODO
    public void consumePotion(Potion potion) {
        return;
    }

    // TODO
    public Potion[] getPotions() {
        return null;
    }
}
