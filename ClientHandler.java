import java.io.*;
import java.net.*;

public class ClientHandler extends Thread{
    private Socket incoming; // 클라이언트 소켓.
    private int counter; // 클라이언트 구분용 변수.
    private GameManager gameManager;
    private QuizManager quizManager;
    private PrintWriter out;
    // 외부로 정보를 보낼 변수.
    Private BufferedReader in;
    // 외부에서 정보를 받을 변수.

    public ClientHandler(Socket i, int c){
        this.incoming = i;
        this.counter = c;
        this.gameManager = GameManager.getInstance(); // 객체 참조
        this.quizManager = new QuizManager(); // 각 클라이언트에 사용될 전담 비서.
        try {
            this.out = new PrintWriter(incoming.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
        } catch (IOException e) {
            System.out.println("스트림 초기화 실패");
        }
    }
    
    public QuizManager getQuizManager() {
            return quizManager;
    }

    public void run(){
        try{
            handler.out.println("COMMAND:MAINPANEL:메인화면");
            while(true){
                String message = in.readLine(); // 입력값 받기
                String[] parts = message.split(":", 3); // :을 기준으로 입력값 등분
                if (parts.length < 3) {
                    handler.out.println("ERROR: Invalid message format.");
                    continue;
                }
                String command = parts[1];  //구분용 명령.
                String subCommand = parts[2]; // 구체적 명령.
                switch (command){
                    case "SHOP":
                        GameManager.HandleShop();
                        //상점 최신 업데이트
                        break;
                    case "RANK":
                        GameManager.HandleRank();
                        // 랭킹 최신 업데이트
                        break;
                    case "MAKE_ROOM":
                        GameManager.HandleRoom();
                        // 대기방을 생성하는 코드
                        break;
                    case "FIND_ROOM":
                        // 대기방 클릭시 들어가는 코드
                        break;
                    case "CATEGORY":
                        // 준비완료 전 카테고리 선택 코드.
                        break;
                    case "PLAY_GAME":
                        // 준비완료 누를시 게임 시작, QuizManager사용
                        break;
                    default:
                        break;
                }
                if (command.equals("EXIT")) {
                    handler.out.println("BYE");
                    break;
                }
            }
            }
            incoming.close();
        } catch (Exception e){
            out.println("클라이언트 연결 실패. 재접속해주세요.");
        }
    }
}
