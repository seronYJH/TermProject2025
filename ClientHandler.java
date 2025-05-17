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



            boolean running = true;


                // 추후 조율을 통해 작성.
            while(running){

                // 유저의 이벤트에 따라 게임시작, 환경설정, 로그인 등을 실행할 예정.





            }
            incoming.close();

        } catch (Exception e){
            out.println("클라이언트 연결 실패. 재접속해주세요.");
        }
    }
}
