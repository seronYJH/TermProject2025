import java.io.*;
import java.net.*;

public class GameManager{

    private static GameManager instance = new GameManager();
      // 클래스 내부에 자기 자신 타입의 static 변수 선언 (유일한 인스턴스)
      // 초기화 전에 이미 클래스가 메서드 영역에 정의됨 > 객체 생성 가능.
    private PlayerManager playerManager;

    private GameManager(PlayerManager playerManager) {
      // 외부에서 생성자에 접근하지 못하도록 private으로 선언.
        this.playerManager = playerManager; // 유저들의 정보가 담긴 데이터베이스를 이용하기 위해 가져옴.
    }

    public static GameManager getInstance() {
        return instance;
         // 외부에서 인스턴스를 참조하는 유일한 방법
         // Thread가 오직 하나의 공통된 객체만 사용하도록 하기 위함.
    }

    // 전체 로직을 담당할 메서드
    public PlayGame(ClientHandler handler){
        handler.out.println("COMMAND:MAINPANEL:메인화면");



    }

    public String handleShop(){
        getShopInfo();
        return "SHOP:ITEMS=item1;100,item2;200,item3;300";
    }

    public String getShopInfo(String username) {
        Player player = playerManager.getPlayer(username);
        // player의 돈, 구매한 아이템 정보 등을 활용해서 상점 구성
        ...
        return shopInfo;
    }

    public String handleRank(){
        String resutl = PlayerManager.getRankingInfo();
        return result;
    }






}
