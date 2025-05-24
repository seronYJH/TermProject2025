import java.io.*;
import java.net.*;

public class ClientHandler extends Thread{
    private Socket incoming; // 클라이언트 소켓.
    private int counter; // 클라이언트 구분용 변수.
    private GameManager gameManager;
    private PrintWriter out;
    // 외부로 정보를 보낼 변수.
    private BufferedReader in;
    //
    String message; // 외부의 정보를 저장할 변수.
    String[] parts; // 외부의 정보를 분할하여 담아둘 배열.
    String sendMessage; //외부로 보낼 정보를 저장할 변수.


    public ClientHandler(Socket i, int c){
        this.incoming = i;
        this.counter = c;
        this.gameManager = GameManager.getInstance(); // 객체 참조
        try {
            this.out = new PrintWriter(incoming.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
        } catch (IOException e) {
            System.out.println("스트림 초기화 실패");
        }
    }

    public PrintWriter getOut() {
        return out;
    }

    public void run(){
        try{
            out.println("COMMAND:MAINPANEL:메인화면");
            while(true){
                message = in.readLine(); // 입력값 받기
                parts = message.split(":", 3); // :을 기준으로 입력값 등분
                if (parts.length < 3) {
                    out.println("ERROR: Invalid message format.");
                    continue;
                }
                String mainCommand = parts[1];  //구분용 명령.
                String subCommand = parts[2]; // 구체적 명령.
                switch (mainCommand){
                    // 메인화면, 선택화면은 클라이언트가 알아서 실행하도록 함.
                    case "LOGIN":
                        player = new Player(username, this);
                        sendMessage =
                        break;

                    case "SINGLE":
                        // 개인 코인만 서버에서 받고 나머지는 클라이언트가 함.
                        sendMessage = gameManager.findCoin();
                        break;

                    case "MULTI":
                        // 생성된 대기방들 표시, 상점아이콘, 랭킹아이콘,
                        sendMessage = gameManager.handleMulti(subCommand);
                        break;

                    case "SHOP":
                        sendMessage = gameManager.handleShop(subCommand);
                        //상점 최신 업데이트
                        break;

                    case "RANK":
                        sendMessage = gameManager.handleRank(subCommand);
                        // 랭킹 최신 업데이트
                        break;

                    case "ROOM":
                        handleRoom(subCommand);

                        break;
                    default:
                        break;
                }
                out.println(sendMessage);
                if (command.equals("EXIT")) {
                    out.println("BYE");
                    break;
                }
            }
            incoming.close();
        } catch (Exception e){
            out.println("클라이언트 연결 실패. 재접속해주세요.");
        }
    }

    // 대기방 관련 명령 처리 메서드.
    void handleRoom(String subCommand, String data) {
        switch (subCommand) {
            case "CREATE": // 생성 버튼
                RoomManager.createRoom(client, Integer.parseInt(data));
                break;
            case "JOIN": // 참가 버튼
                RoomManager.joinRoom(client, data);
                break;
            case "LEAVE": // 나가기 버튼
                RoomManager.leaveRoom(client, data);
                break;
            case "READY": // 준비완료 버튼
                RoomManager.setReady(client, data, true); // 모두가 ready(true)이면 5초뒤 자동으로 게임시작.
                break;
            case "UNREADY": // 준비취소 버튼
                RoomManager.setReady(client, data, false);
                break;
        }
    }
}
