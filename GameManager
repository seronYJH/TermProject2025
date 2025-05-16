import java.io.*;
import java.net.*;

public class GameManager{

    private static GameManager instance = new GameManager();
      // 클래스 내부에 자기 자신 타입의 static 변수 선언 (유일한 인스턴스)
      // 초기화 전에 이미 클래스가 메서드 영역에 정의됨 > 객체 생성 가능.

    private GameManager() {
      // 외부에서 생성자에 접근하지 못하도록 private으로 선언.
    }

    public static GameManager getInstance() {
        return instance;
         // 외부에서 인스턴스를 참조하는 유일한 방법
    }

    private List<ClientHandler> players = new ArrayList<>();
    private Map<ClientHandler, Integer> scores = new HashMap<>();
    private boolean gameStarted = false;

    public PlayGame(){

    }





}
