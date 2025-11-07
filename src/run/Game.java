package run;
import region.Dungeon;
import region.Inn;
import region.Region;
import region.Shop;
import unit.Player;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
    // 총 스테이지 수
    private static final int MAX_STAGE_NUM = 5;
    private static int currentStageNum = 1;
    private static boolean isGameOver;
    public static void setGameOVer() {
        isGameOver = true;
    }
    /*
    * isWinner이 true일 경우 게임이 끝날떄 플레이어의 승리
    * false일 경우 게임이 끝날떄 적의 승리
    * */
    private static boolean isWinner;
    // 현재 게임 스테이지 번호를 읽는다.
    public static int currentStageNum() {
        return currentStageNum;
    }
    private static StringBuilder sb = new StringBuilder();

    public void play() throws Exception {
        // 1번째 줄
        for (int i = 0; i < 29; i++) sb.append("ㅁ");
        sb.append("\n");
        // 2번째 줄
        sb.append("ㅁ");
        for (int i = 0; i < 29; i++) sb.append("　");
        sb.append("ㅁ\n");
        // 3번째 줄
        sb.append("ㅁ");
        for (int i = 0; i < 10; i++) sb.append("　");
        sb.append("운　빨　Ｒ　Ｐ　Ｇ");
        for (int i = 0; i < 10; i++) sb.append("　");
        sb.append("ㅁ\n");
        // 4번째 줄
        sb.append("ㅁ");
        for (int i = 0; i < 29; i++) sb.append("　");
        sb.append("ㅁ\n");
        // 5번째 줄
        for (int i = 0; i < 29; i++) sb.append("ㅁ");
        System.out.println(sb);

        playStartAnimation();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("게임을 시작합니다.(y/n) : ");
            String userInput = sc.nextLine();
            if (userInput.equals("y")) {
                break;
            } else if (userInput.equals("n")) {
                System.out.println("게임을 종료합니다.");
                return;
            } else {
                System.out.println("y 또는 n을 입력해주세요.");
            }
        }

        // 플레이어 생성
        Player player = new Player(100, 100);

        // 스테이지 생성
        // 테스트용
//        Region[] stages = new Region[MAX_STAGE_NUM];
//        for (int i = 0; i < stages.length; i++) {
//            stages[i] = new Dungeon();
//        }
        Region[] stages = new Region[MAX_STAGE_NUM];
        generateDungeon(stages);

        // 스테이지 진행
        while (isGameOver == false) {
            Region currentRegion = stages[currentStageNum - 1]; // stages 배열은 0부터 시작
            displayCurrentStage(currentRegion);
            currentRegion.enter(player);
            currentStageNum++;
            if (currentStageNum == MAX_STAGE_NUM + 1) {
                // 다음 스테이지가 없으면 게임오버로 만든다.
                Game.setGameOVer();
                isWinner = true;
            }
        }

        if (isWinner == true) {
            System.out.println();
            System.out.println("====== ✨  ✨  ✨  ✨  ✨  ✨  ✨  ✨  ✨  ✨  ✨  ✨ ");
            System.out.println("======   플레이어는 무사히 여행을 마쳤습니다... 플레이어의 승리");
            System.out.println("====== ✨  ✨  ✨  ✨  ✨  ✨  ✨  ✨  ✨  ✨  ✨  ✨ ");
        } else {
            System.out.println();
            System.out.println("======");
            System.out.println("====== 플레이어는 패배했습니다. GAME OVER");
            System.out.println("======");
        }
    }

    public static void generateDungeon(Region[] regions) {
        Random random = new Random();
        for (int i = 0; i < regions.length; i++) {
            int randomNumber = random.nextInt(3); // 3개의 지역이 있으므로
            Region region = null;
            switch (randomNumber) {
                case 0:
                    region = new Dungeon();
                    break;
                case 1:
                    region = new Inn();
                    break;
                case 2:
                    region = new Shop();
                    break;
            }
            regions[i] = region;
        }
    }

    public static void playStartAnimation() throws Exception {
        sb = new StringBuilder();
        sb.append("\r");
        for (int i = 0; i < 29; i++) {
            sb.append("ㅇ");
            System.out.print(sb);
            TimeUnit.MILLISECONDS.sleep(10);
        }
        for (int i = 0; i < 29; i++) {
            sb.deleteCharAt(sb.length() - 1);
            System.out.print(sb);
            TimeUnit.MILLISECONDS.sleep(10);
        }
        for (int i = 0; i < 29; i++) {
            sb.append("ㅇ");
            System.out.print(sb);
            TimeUnit.MILLISECONDS.sleep(10);
        }
        sb.append("\n");
        System.out.println(sb);
        TimeUnit.MILLISECONDS.sleep(500);
    }

    public static void displayCurrentStage(Region region) {
        String regionName = null;
        if (region instanceof Inn) {
            regionName = "여관";
        } else if (region instanceof Shop) {
            regionName = "상점";
        } else if (region instanceof Dungeon) {
            regionName = "던전";
        }
        System.out.println();
        System.out.println("======");
        System.out.println("====== 현재 스테이지 : " + currentStageNum());
        System.out.println("====== 이번 스테이지는 " + regionName + "입니다.");
        System.out.println("======");
        System.out.println();
        Game.delayOutput(3000);
    }

    public static void delayOutput(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new Game().play();
    }
}
