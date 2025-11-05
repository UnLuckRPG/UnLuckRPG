package region;

import unit.Player;

import java.util.Random;

public class Inn extends Region {
    @Override
    public void enter(Player player) {

        System.out.println(" 🏪 Luck Inn에 오신걸 환영합니다.");
        Random random = new Random();
        double randomProbability = random.nextDouble();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Luck Inn에 방문합니다.");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (randomProbability < 0.5) {
            System.out.println("안에서 들리는 따뜻한 웃음소리와 맛있는 음식 냄새...");
            System.out.println("주인: \"어서오시게 손님은 언제나 환영하네~\"");
            System.out.println("✨✨✨  체력이 회복되었습니다  ✨✨✨");
            player.setHp(player.getMaxHp());
        } else {
            System.out.println("...하지만 사람들로 가득 차 있습니다.");
            System.out.println("주인: \"미안하지만 자리가 없네요. 다음 기회에~\"");
            System.out.println("회복하지 못하고 발걸음을 돌렸습니다.");
        }
    }
}
