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
         // Thread가 오직 하나의 공통된 객체만 사용하도록 하기 위함.
    }

    //게임을 시작할 메서드
    public PlayGame(ClientHandler handler){
        // 게임시작 버튼 누를 경우 작동할 예정
        showInGameJPanel();

    }

    //로그인할 때 사용할 메서드
    public Login(ClientHandler handler){
        // 로그인 버튼 누를 경우 작동할 예정
        showMainJPanel();
    }

    //환경설정할 때 사용할 메서드
    public Setting(ClientHandler handler){
        // 환경설정 버튼 누를 경우 작동할 예정
        showSettingJPanel();
    }

    //다른 유저들이 대기하는 방 메서드
    public WaitingRoom(ClientHandler handler){
        shoeWaittingRoomJPanel();
    }

    //




}
