package unit;

public class Unit {
    private final int maxHp;
    private int hp;
    Unit(int maxHp) {
        this.maxHp = maxHp;
        this.hp = maxHp; // 처음 생성될떄는 체력이 꽉찬상태
    }

    public void dealDamage(Unit opponent, int value) {
        // hp를 0 이하로 만들 수 없음
        opponent.setHp(Math.max(0, opponent.getHp() - value));
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
