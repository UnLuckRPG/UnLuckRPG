package region;

import unit.EasyEnemy;
import unit.Enemy;
import unit.HardEnemy;
import unit.NormalEnemy;
import unit.Player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import item.Dice;
import item.Potion;
import run.Game;

public class Dungeon extends Region {
    @Override
    public void enter(Player player) {
      // 랜덤으로 쉬운 적, 보통 적, 어려운 적을 선택
      Enemy[] enemyPool = new Enemy[3];
      enemyPool[0] = new EasyEnemy();
      enemyPool[1] = new NormalEnemy();
      enemyPool[2] = new HardEnemy();
      Random random = new Random();
      Enemy enemy = enemyPool[random.nextInt(enemyPool.length)];

      System.out.println();
      System.out.println("🚨🚨🚨🚨🚨 " + enemy.getName() + "의 출현!! 🚨🚨🚨🚨🚨");
      System.out.println();
      Game.delayOutput(1000);
      
      // 플레이어 선공 결정
      boolean playerFirst = true;
      if (Math.random() < 0.5) {
        playerFirst = false;
      }

      if (playerFirst) { // 플레이어 선공
        System.out.println();
        System.out.println("🍀🍀🍀🍀🍀 LUCKY! 플레이어가 먼저 공격합니다. 🍀🍀🍀🍀🍀");
        System.out.println();
      } else {
        System.out.println();
        System.out.println("😡😡😡😡😡 UNLUCKY... 적이 먼저 공격합니다. 😡😡😡😡😡");
        System.out.println();
      }
      Game.delayOutput(1000);

      Scanner sc = new Scanner(System.in);
      while (true) {
        if (playerFirst) { // 플레이어 선공
          takeTurn(player, enemy, sc);
          if (checkBattleEnd(player, enemy, sc)) break;
          takeTurn(enemy, player, sc);
          if (checkBattleEnd(player, enemy, sc)) break;
        } else {
          takeTurn(enemy, player, sc);
          if (checkBattleEnd(player, enemy, sc)) break;
          takeTurn(player, enemy, sc);
          if (checkBattleEnd(player, enemy, sc)) break;
        }
      }
    }

    private boolean checkBattleEnd(Player player, Enemy enemy, Scanner sc) {
      if (player.getHp() <= 0) {
        System.out.println("😵‍💫 플레이어는 hp가 0이 되어 쓰려졌습니다...");
        Game.setGameOVer();
        return true;
      } else if (enemy.getHp() <= 0) {
        System.out.println("😇 플레이어가 적을 쓰려트렸습니다!");
        return true;
      }
      return false;
    }

    // 플레이어 입력을 받는다.
    private int getPlayerInput(Scanner sc, String prompt, String errorMsg) {
      int input;
      System.out.print(prompt);
      while (true) {
        try {
          input = Integer.parseInt(sc.nextLine());
          break;
        } catch (Exception e) {
          System.out.println("올바른 숫자를 입력해주세요.");
          System.out.print(prompt);
        }
      }
      return input;
    }

    private void displayPlayerStatus(Player player) {
        System.out.println("============================");
        System.out.println("플레이어의 hp : " + player.getHp() + "/" + player.getMaxHp());
        System.out.println("============================");
    }

    private void displayPlayerStatus(Enemy enemy) {
        System.out.println("============================");
        System.out.println(enemy.getName() + "의 hp : " + enemy.getHp() + "/" + enemy.getMaxHp());
        System.out.println("============================");
    }


    // 플레이어의 공격턴
    private void takeTurn(Player player, Enemy enemy, Scanner sc) {
      boolean turnEnd = false;

      while (turnEnd == false) {
        // 유저의 입력 받기
        System.out.println();
        System.out.println("행동을 선택하세요.");
        System.out.println("----- 1. 주사위를 굴려 적을 공격하기");
        System.out.println("----- 2. 사용가능한 포션 확인하기");
        System.out.println("----- 3. 현재 주사위 확인하기");
        System.out.println("----- 4. 플레이어와 적의 현재 상태 확인하기");
        System.out.println();

        int input;
        while (true) {
          input = getPlayerInput(sc, "1 ~ 4 중 하나를 입력 : ", "올바른 숫자를 입력하세요");
          if (input < 1 || input > 4) {
            System.out.println("1 ~ 4 사이의 숫자를 입력하세요.");
          }
          break;
        }

        Dice dice;
        switch (input) {
          case 1: // 주사위를 굴려 적을 공격하기
            dice = player.getDice();
            System.out.println();
            System.out.println("⚔️⚔️⚔️⚔️⚔️ 플레이어가 주사위를 굴려 공격합니다. ⚔️⚔️⚔️⚔️⚔️");
            System.out.println();
            Game.delayOutput(1000);

            dice.effect(enemy);
            System.out.println("⚔️플레이어가 적에게 " + dice.getRecentEye() + "의 피해를 입혔습니다!⚔️");
            Game.delayOutput(1000);
            displayPlayerStatus(enemy);
            Game.delayOutput(1000);
            turnEnd = true;
            break;
          case 2: // 사용가능한 포션 확인하기
            List<Potion> potions = player.getPotions();
            if (potions != null) {
              for (int i = 0; i < potions.size(); i++) {
                System.out.println("💊" + (i+1) +". " + potions.get(i).getName());
              }
              int itemInput;
              while (true) {
                itemInput = getPlayerInput(sc, "사용할 포션의 번호를 선택해주세요 : ", "올바른 숫자를 입력하세요.");
                if ((input - 1) < 0 || (input - 1) >= potions.size()) {
                  System.out.println("올바른 숫자를 입력하세요.");
                }
                break;
              }
              // 포션 사용
              Potion potionUsed = potions.get(itemInput - 1);
              potionUsed.effect(player);
              System.out.println("플레이어의 hp가 완전히 회복되었습니다.");
              displayPlayerStatus(player);
              player.removeItem(potionUsed);
            } else {
              System.out.println();
              System.out.println("사용가능한 포션이 없습니다.");
              System.out.println();
            }
            break;
          case 3: // 현재 주사위 확인하기
            dice = player.getDice();
            if (dice != null) {
              System.out.println("플레이어의 주사위 : " + dice.getName());
              System.out.println("주사위 효과 : ");
              System.out.println(dice.getMin() + "부터 " + dice.getMax() + "까지의 숫자가 무작위로 나옵니다.");
              System.out.println("나온 숫자만큼 적에게 피해를 줍니다.");
            }
            break;
          case 4: // 플레이어와 적의 현재 상태 확인하기
            displayPlayerStatus(player);
            displayPlayerStatus(enemy);
          default:
            break;
        } // switch END
      } // while (turnEnd == false) END
    }

    // 적의 공격턴
    private void takeTurn(Enemy enemy, Player player, Scanner sc) {
        System.out.println();
      System.out.println("👺" +  enemy.getName() + "이/가 공격합니다!");
      Game.delayOutput(1000);
      int damage = enemy.attack(player);
        System.out.println();
      System.out.println("🔪적이 플레이어에게 " + damage + "의 피해를 입혔습니다!");
      displayPlayerStatus(player);
      Game.delayOutput(1000);
    }
}
