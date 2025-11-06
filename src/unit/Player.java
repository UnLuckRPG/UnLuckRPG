package unit;

import item.Dice;
import item.Item;
import item.NormalDice;
import item.Potion;

import java.util.ArrayList;
import java.util.List;

public class Player extends Unit {
    private int gold; // 플레이어의 소지금
    private List<Item> items; // 소유하고 있는 아이템들
    private static final int MAX_ITEM = 5; // 최대 소유 가능한 아이템의 개수

    public Player(int maxHp, int gold) {
        super(maxHp);
        this.gold = gold;
        items = new ArrayList<>();
        addItem(new NormalDice()); // 기본으로 기본 주사위 하나 소유
    }

    // 아이템 추가 실패시 false 반환 (예 : 최대 소유 가능한 아이템 수를 초과했을 떄)
    public boolean addItem(Item item) {
        if (items.size() >= MAX_ITEM) {
            return false; // 더 넣을 수 없다.
        }
        items.add(item);
        return true;
    }

    public boolean removeItem(Item targetItem) {
        return items.remove(targetItem);
    }

    public void usePotion(Potion potion) {
        potion.effect(this); // 포션은 플레이어 자신에게만 사용가능
        removeItem(potion); // 포션 소모
    }

    public void useDice(Dice dice, Unit target) {
        dice.effect(target);
    }

    // 가지고 있는 포션 목록을 반환함, 없으면 null 반환
    public List<Potion> getPotions() {
        List<Potion> result = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Potion) result.add((Potion) item);
        }
        if (result.isEmpty()) return null;
        return result;
    }

    // 가지고 있는 주사위를 반환함, 없으면 null 반환
    public Dice getDice() {
        Dice dice = null;
        for (Item item : items) {
            if (item instanceof Dice) dice = (Dice) item;
        }
        return dice;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public List<Item> getItems() {
        return items;
    }

}
