package unit;

import java.util.Random;

public class Enemy extends Unit {
    protected String name;

    protected Enemy(int maxHp) {
        super(maxHp);
    }

    public String getName() {
      return name;
    }

    public int attack(Player player) {
      Random random = new Random();
      // 1 ~ 6 사이에서 랜덤 숫자
      int damage = random.nextInt((6 - 1) + 1) + 1;
      int playerHp = player.getHp() - damage;
      if (playerHp < 0) {
          playerHp = 0;
      }
      player.setHp(playerHp);
      return damage; // 입힌 피해 반환
    }
}
