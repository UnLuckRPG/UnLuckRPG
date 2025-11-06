package region;

import item.*;
import unit.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Shop extends Region {
    @Override
    public void enter(Player player) {
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("💰💰💰💰💰 Lucky Shop에 오신걸 환영합니다 💰💰💰💰💰");
        System.out.println();

        while (true) {
            System.out.println("1. 주사위🎲/물약💊 구매하기");
            System.out.println("2. 직원 협박하기🎭");
            System.out.print("숫자를 입력하세요 :");
            int no =  sc.nextInt();
            System.out.println();

            switch (no) {
                // 아이템 구매 선택시
                case 1 :

                    System.out.println("주사위🎲와 물약💊 중 어떤걸 구매할지 정하고 있습니다.");

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    double num1 = Math.random();

                    // 보유 골드 확인
                    if (player.getGold() >= 50) {

                        // 물약 / 주사위 랜덤
                        if (num1 < 0.5) {
                            System.out.println("물약💊을 구매 하셨습니다");
                            player.setGold(player.getGold() - 50);
                            player.addItem(new NormalPotion());

                            System.out.println();
                            System.out.println("🧰 현재 보유 중인 아이템 🧰");
                            System.out.println("===================");


                            Dice dice = player.getDice();
                            if (dice != null) {
                                System.out.println("🎲 " +  dice.getName());
                            }

                            // 포션 출력
                            List<Potion> potions = player.getPotions();
                            if (potions != null && !potions.isEmpty()) {
                                System.out.println("💊 " + potions.get(0).getName() + ": " + potions.size() + "개");
                            }

                            // 아무것도 없으면
                            if (dice == null && (potions == null || potions.isEmpty())) {
                                System.out.println("보유한 아이템이 없습니다.");
                            }

//                            List<Item> itemList = player.getItems();
//                            for (int i = 0; i < player.getItems().size(); i++) {
//                                System.out.println((i+1) + ". " + itemList.get(i).getClass().getSimpleName());
//                            }
                            System.out.println("===================");
                            System.out.println();
                            return;

                        } else {
                            System.out.println("주사위🎲를 구매 하셨습니다.");
                            player.setGold(player.getGold() - 50);
                            Dice oldDice = player.getDice();
                            if (oldDice != null) {
                                player.removeItem(oldDice);
                            }

                            Dice[] diceArray = {
                                    new NormalDice(),
                                    new StableDice(),
                                    new SuperDice(),
                                    new ExtremeDice()
                            };
                            Random random = new Random();
                            int randomIndex = random.nextInt(diceArray.length);
                            Dice selectedDice = diceArray[randomIndex];

                            player.addItem(selectedDice);

                            System.out.println();
                            System.out.println("🧰 현재 보유 중인 아이템 🧰");
                            System.out.println("===================");
                            Dice dice = player.getDice();
                            if (dice != null) {
                                System.out.println("🎲 " +  dice.getName());
                            }

                            // 포션 출력
                            List<Potion> potions = player.getPotions();
                            if (potions != null && !potions.isEmpty()) {
                                System.out.println("💊 " +  potions.get(0).getName() + ": " + potions.size() + "개");
                            }

                            // 아무것도 없으면
                            if (dice == null && (potions == null || potions.isEmpty())) {
                                System.out.println("보유한 아이템이 없습니다.");
                            }
                            System.out.println("===================");
                            System.out.println();
                            return;
                        }

                    // 보유 골드 부족시
                    } else {
                        System.out.println("골드가 부족합니다.");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println();
                        System.out.println("상점에서 쫓겨납니다.");
                        return;
                    }

                // 협박을 선택시
                case 2 :

                    System.out.println("상점 주인을 협박합니다🎭");
                    System.out.println();

                    double num2 = Math.random();

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 협박 성공시
                    if (num2 < 0.5) {
                        System.out.println("🎉🎉🎉 협박 성공!! 🎉🎉🎉");
                        System.out.println();
                        System.out.println("겁먹은 상점 주인🧔이 아이템을 줍니다.");

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        double num3 = Math.random();

                        // 아이템 랜덤 획득
                        if (num3 < 0.5) {
                            System.out.println("물약💊을 받았습니다.");
                            player.addItem(new NormalPotion());

                            System.out.println();
                            System.out.println("🧰 현재 보유 중인 아이템 🧰");
                            System.out.println("===================");
                            Dice dice = player.getDice();
                            if (dice != null) {
                                System.out.println("🎲 " +  dice.getName());
                            }

                            // 포션 출력
                            List<Potion> potions = player.getPotions();
                            if (potions != null && !potions.isEmpty()) {
                                System.out.println("💊 " + potions.get(0).getName() + ": " + potions.size() + "개");
                            }

                            // 아무것도 없으면
                            if (dice == null && (potions == null || potions.isEmpty())) {
                                System.out.println("보유한 아이템이 없습니다.");
                            }
                            System.out.println("===================");
                            System.out.println();
                        } else {
                            System.out.println("주사위🎲를 받았습니다.");
                            Dice oldDice = player.getDice();
                            if (oldDice != null) {
                                player.removeItem(oldDice);
                            }
                            Dice[] diceArray = {
                                    new NormalDice(),
                                    new StableDice(),
                                    new SuperDice(),
                                    new ExtremeDice()
                            };
                            Random random = new Random();
                            int randomIndex = random.nextInt(diceArray.length);
                            Dice selectedDice = diceArray[randomIndex];

                            player.addItem(selectedDice);

                            System.out.println();
                            System.out.println("🧰 현재 보유 중인 아이템 🧰");
                            System.out.println("===================");
                            Dice dice = player.getDice();
                            if (dice != null) {
                                System.out.println("🎲 " +  dice.getName());
                            }

                            // 포션 출력
                            List<Potion> potions = player.getPotions();
                            if (potions != null && !potions.isEmpty()) {
                                System.out.println("💊 " + potions.get(0).getName() + ": " + potions.size() + "개");
                            }

                            // 아무것도 없으면
                            if (dice == null && (potions == null || potions.isEmpty())) {
                                System.out.println("보유한 아이템이 없습니다.");
                            }
                            System.out.println("===================");
                            System.out.println();
                        }

                        return;

                    // 협박 실패시
                    }else {
                        System.out.println("👺🔥 화가난 상점 주인 🔥👺이 플레이어에게 응징을 합니다.");
                        int currentHp = player.getHp();
                        int newHp = Math.max(1, currentHp / 2);
                        player.setHp(newHp);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("플레이어의 체력이 50% 감소합니다. 🥶");
                        System.out.println();

                        System.out.println("===================");
                        System.out.println("♥ 현재 플레이어의 HP : " + player.getHp() + "입니다.");
                        System.out.println("===================");
                        System.out.println();
                        return;
                    }
                default:
                    System.out.println("잘 못 된 번호를 입력하셨습니다."); break;
            }
        }
    }

}
