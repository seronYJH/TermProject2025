import java.io.*;
import java.net.*;

public class ClientHandler extends Thread{
    private Socket incoming; // 클라이언트 소켓.
    private int counter; // 클라이언트 구분용 변수.
    private GameManager gameManager;
    private PrintWriter out;
    // 외부로 정보를 보낼 변수.
    private BufferedReader in;
    private Player player; // player 정보 저장.
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
        // 정보를 보내기 위한 메서드.
        return out;
    }

    public void run(){
        try{
            getOut().println("COMMAND:MAINPANEL:메인화면");
            while(true){
                message = in.readLine(); // 입력값 받기
                parts = message.split(":", 3); // :을 기준으로 입력값 등분
                if (parts.length < 3) {
                    getOut().println("ERROR: Invalid message format.");
                    continue;
                }
                String mainCommand = parts[1];  //구분용 명령.
                String subCommand = parts[2]; // 구체적 명령.

                if (mainCommand.startsWith("LOGIN:")) { // 로그인 정보 저장 밑 유저 데이터베이스 생성.
                    parts = subCommand.split(":", 2);
                    mainCommand = parts[0]; // 닉네임 담김.
                    subCommand = parts[1]; // 비밀번호 담김
                    PlayerManager.handleLoginOrRegister(mainCommand, subCommand, this); // player 객체 생성
                    continue;
                }

                switch (mainCommand){
                    // 메인화면, 로그인화면, 선택화면은 클라이언트가 알아서 실행하도록 함.
                    case "SINGLE":
                        // 개인 코인만 서버에서 받고 나머지는 클라이언트가 함.
                        sendMessage = player.findCoin();  // player에 필요한 기능///////////
                        break;

                    case "MULTI":
                        // 생성된 대기방들 표시, 상점아이콘, 랭킹아이콘,
                         // 유저들이 생성한 모든 방을 불러와서 클라이언트에게 전달.
                        sendMessage = RoomManager.updateRoomListForAllClients();
                         // 상점, 랭킹 아이콘은 클라이언트가 알아서.
                        break;

                    case "SHOP": // 상점 클릭시 이동
                        handleShop(subCommand);
                        //상점 최신 업데이트
                        break;

                    case "RANK": // 랭크 클릭시 이동
                        handleRank(subCommand);
                        // 랭킹 최신 업데이트
                        break;

                    case "ROOM": // 방 생성,참가,나가기,시작시 이동
                        handleRoom(subCommand);
                        break;
                    default:
                        break;
                }
                getOut().println(sendMessage);
                if (command.equals("EXIT")) {
                    getOut().println("BYE");
                    break;
                }
            }
            incoming.close();
        } catch (Exception e){
            getOut().println("클라이언트 연결 실패. 재접속해주세요.");
        }
    }

    // 대기방 관련 명령 처리 메서드.
    void handleRoom(String subCommand, String data) {
        if (subCommand.startsWith("LEAVE")){ // 나갈시 대기방 최신정보 얻어야 함.
            RoomManager.leaveRoom(client, data);
            sendMessage = RoomManager.updateRoomListForAllClients();
            getOut().println(sendMessage);
        }
        switch (subCommand) {
            case "CREATE": // 생성 버튼
                RoomManager.createRoom(client, Integer.parseInt(data));
                break;
            case "JOIN": // 참가 버튼
                RoomManager.joinRoom(client, data);
                break;
            case "READY": // 준비완료 버튼
                RoomManager.setReady(client, data, true);
                // 모두가 ready(true)이면 5초뒤 자동으로 게임시작.
                break;
            case "UNREADY": // 준비취소 버튼
                RoomManager.setReady(client, data, false);
                break;
        }
    }
    void handleShop(String subCommand) {
        if (subCommand.startsWith("ENTER")) {
            // 유저 코인, 아이템 개수 정보 전달.
            sendMessage = player.findCoin() + ":" + player.getItems   // player에 필요한 기능///////////
        }

        if (subCommand.startsWith("BUY")) { // 물품 구매
            parts = subCommand.split(":", 2);
            subCommand = parts[1]; // 구매할 아이템명 표시.
            switch (subCommand) {                                      // player에 필요한 기능///////////
                case "ERASE":
                    sendMessage = player.buyErase();
                    break;
                case "TIME":
                    sendMessage = player.butTime();
                    break;
                case "SKIP":
                    sendMessage = player.buySkip();
                    break;
                default:
                    sendMessage = "ERROR"
                    break;
            }
        }

        if (subCommand.startsWith("LEAVE")){ // 나갈시 대기방 최신정보 얻어야 함.
            sendMessage = RoomManager.updateRoomListForAllClients();
        }
        getOut().println(sendMessage);
    }
}
