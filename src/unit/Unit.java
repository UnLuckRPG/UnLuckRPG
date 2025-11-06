package unit;

public class Unit {
    private final int maxHp;
    private int hp;

    protected Unit(int maxHp) {
        this.maxHp = maxHp;
        this.hp = maxHp; // 처음 생성될떄는 체력이 꽉찬상태
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
