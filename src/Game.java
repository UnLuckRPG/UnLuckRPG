import region.Dungeon;
import region.Inn;
import region.Region;
import region.Shop;
import unit.Player;

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

    public void play() {
        StringBuilder sb = new StringBuilder();
        // 1번째 줄
        for (int i = 0; i < 31; i++) sb.append("ㅁ");
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
        for (int i = 0; i < 31; i++) sb.append("ㅁ");

        System.out.println(sb);

        // 플레이어 생성
        Player player = new Player(100, 100);

        // 스테이지 생성
        // 임시 : 일단 상점으로만 채움
        Region[] stages = new Region[MAX_STAGE_NUM];
        for (int i = 0; i < stages.length; i++) {
            stages[i] = new Inn();
        }

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
            System.out.println("플레이어는 무사히 여행을 마쳤습니다... 플레이어의 승리");
        } else {
            System.out.println("플레이어는 패배했습니다. GAME OVER");
        }
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
        System.out.println("======");
        System.out.println("====== 현재 스테이지 : " + currentStageNum());
        System.out.println("====== 이번 스테이지는 " + regionName + "입니다.");
        System.out.println("======");
    }

    public static void main(String[] args) {
        new Game().play();
    }
}
