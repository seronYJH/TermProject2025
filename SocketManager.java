package client;

import java.io.*;
import java.net.Socket;

/**
 * 클라이언트와 서버 간의 통신을 담당하는 클래스.
 * 서버 연결, 메시지 송수신, 연결 종료, 수신 스레드 시작 기능을 포함한다.
 */
public class SocketManager {

    // 서버와의 소켓 연결 객체
    private Socket socket;

    // 입력 스트림 (서버로부터 수신)
    private BufferedReader in;

    // 출력 스트림 (서버로 전송)
    private PrintWriter out;

    /**
     * 서버에 연결을 시도하는 메서드
     * @param serverIp 서버의 IP 주소
     * @param port     서버 포트 번호
     * @return 연결 성공 여부 (true/false)
     */
    public boolean connectToServer(String serverIp, int port) {
        try {
            socket = new Socket(serverIp, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("[클라이언트] 서버 연결 성공");
            return true;
        } catch (IOException e) {
            System.err.println("[클라이언트] 서버 연결 실패: " + e.getMessage());
            return false;
        }
    }

    /**
     * 서버로 메시지를 전송하는 메서드
     * @param message 전송할 문자열 메시지
     */
    public void sendMessage(String message) {
        out.println(message);
        out.flush();
    }

    /**
     * 서버로부터 메시지를 한 줄 수신하는 메서드
     * @return 수신된 문자열 메시지 (null일 경우 연결 종료)
     */
    public String receiveMessage() {
        try {
            return in.readLine();
        } catch (IOException e) {
            System.err.println("[클라이언트] 메시지 수신 오류: " + e.getMessage());
            return null;
        }
    }

    /**
     * 서버와의 연결을 종료하고 자원을 해제하는 메서드
     */
    public void closeConnection() {
        try {
            if (socket != null) socket.close();
            if (in != null) in.close();
            if (out != null) out.close();
            System.out.println("[클라이언트] 연결 종료");
        } catch (IOException e) {
            System.err.println("[클라이언트] 연결 종료 중 오류: " + e.getMessage());
        }
    }

    /**
     * 서버로부터 지속적으로 메시지를 수신하는 스레드를 시작하는 메서드
     * 수신된 메시지를 콘솔에 출력하며, 향후 메시지 분기 처리 예정.
     */
    public void startReceiver() {
        Thread receiveThread = new Thread(() -> {
            try {
                while (true) {
                    // 한 줄씩 메시지 수신
                    String message = receiveMessage();

                    // 연결이 끊어진 경우 null 반환 → 루프 종료
                    if (message == null) {
                        System.out.println("[클라이언트] 서버 연결이 끊겼습니다.");
                        break;
                    }

                    // 수신된 메시지를 콘솔에 출력
                    System.out.println("[서버로부터 수신] " + message);

                    // TODO: 메시지 타입에 따라 분기 처리 추가 예정
                }
            } catch (Exception e) {
                System.err.println("[클라이언트] 수신 스레드 오류: " + e.getMessage());
            } finally {
                // 예외 발생 또는 연결 종료 시 자원 해제
                closeConnection();
            }
        });

        receiveThread.start(); // 수신 스레드 시작
    }
}
