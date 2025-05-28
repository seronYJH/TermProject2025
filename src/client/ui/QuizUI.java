package client.ui;

import client.model.Player;
import client.net.SocketManager;

import java.util.Scanner;

/**
 * 콘솔 기반의 간이 퀴즈 UI
 */
public class QuizUI {

    private SocketManager socketManager;
    private Player player;

    private final Scanner scanner = new Scanner(System.in);

    public void setSocketManager(SocketManager sm) {
        this.socketManager = sm;
    }

    public void setPlayer(Player p) {
        this.player = p;
    }

    /**
     * 문제를 출력하고 사용자 입력을 받아 전송
     */
    public void setQuestion(String questionText) {
        System.out.println("\n[문제 도착] " + questionText);
        System.out.print("정답 입력: ");
        String answer = scanner.nextLine();

        sendAnswer(answer);
    }

    /**
     * 정답을 서버에 전송
     */
    public void sendAnswer(String answer) {
        socketManager.sendMessage("COMMAND:ANSWER:" + answer);
    }

    /**
     * 결과 메시지 출력
     */
    public void showResult(String result) {
        System.out.println("[결과] " + result);
    }

    /**
     * 점수 갱신 표시
     */
    public void updateScore() {
        System.out.println("[점수] 현재 점수: " + player.getScore());
    }
}
