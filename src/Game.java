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
    * isWin이 true일 경우 게임이 끝날떄 플레이어의 승리
    * false일 경우 게임이 끝날떄 적의 승리
    * */
    private static boolean isWinner;
    public static void setWinner(boolean bool) {
        isWinner = bool;
    }
    // 현재 게임 스테이지 번호를 읽는다.
    public static int currentStageNum() {
        return currentStageNum;
    }

    public void play() {
        // 플레이어 생성
        Player player = new Player(100);

        // 스테이지 생성
        // 임시 : 일단 상점으로만 채움
        Region[] stages = new Region[MAX_STAGE_NUM];
        for (int i = 0; i < stages.length; i++) {
            stages[i] = new Shop();
        }

        // 스테이지 진행
        while (isGameOver == false) {
            Region currentRegion = stages[currentStageNum - 1]; // stages 배열은 0부터 시작
            currentRegion.enter(player);
            currentStageNum++;
            if (currentStageNum == MAX_STAGE_NUM + 1) {
                // 다음 스테이지가 없으면 게임오버로 만든다.
                Game.setGameOVer();
            }
        }
    }

    public static void main(String[] args) {
        new Game().play();
    }
}
