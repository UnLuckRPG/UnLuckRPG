package region;

import unit.Player;

import java.util.Scanner;

public class Shop extends Region {
    @Override
    public void enter(Player player) {
        Scanner sc = new Scanner(System.in);

        System.out.println("💰💰💰💰💰 Lucky Shop에 오신걸 환영합니다 💰💰💰💰💰");

        while (true) {
            System.out.print("1. 주사위🎲/물약💊 구매하기");
            System.out.print("2. 직원 협박하기🎭");
            int no =  sc.nextInt();

            switch (no) {
                case 1 : return;
                case 2 : return;
                default:
                    System.out.println("잘 못 된 번호를 입력하셨습니다."); break;
            }
        }
    }
    
}
