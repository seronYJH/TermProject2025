package client;

/**
 * 클라이언트 프로그램의 진입점 클래스.
 * 서버와의 연결을 시도하고, 연결 성공 시 수신 스레드를 시작한다.
 * 
 * 현재는 GUI 없이 콘솔 기반으로만 작동하며,
 * 실제 문제 수신 및 정답 처리 로직은 추후 서버/공통 구조 확정 후 추가 예정.
 */
public class ClientMain {
    public static void main(String[] args) {
        // 서버 IP 주소와 포트 번호를 설정한다.
        // 현재는 로컬 테스트용으로 설정됨 (localhost:12345)
        String serverIp = "127.0.0.1";
        int port = 12345;

        // SocketManager 객체를 생성하여 서버와의 통신을 담당시킨다.
        SocketManager socketManager = new SocketManager();

        // 서버에 연결을 시도하고, 성공 여부에 따라 분기 처리한다.
        boolean connected = socketManager.connectToServer(serverIp, port);

        if (connected) {
            // 연결이 성공하면 서버로부터 메시지를 수신하는 스레드를 시작한다.
            socketManager.startReceiver();

            // 테스트용 메시지를 서버로 전송하고 싶을 경우 아래 주석을 해제할 수 있음.
            // socketManager.sendMessage("테스트 메시지");

        } else {
            // 연결에 실패했을 경우 사용자에게 메시지를 출력한다.
            System.out.println("서버에 연결할 수 없습니다.");
        }
    }
}
