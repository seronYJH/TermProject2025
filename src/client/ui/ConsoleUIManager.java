package client.ui;

import java.util.Scanner;

public class ConsoleUIManager {

    private final Scanner scanner = new Scanner(System.in);

    public String login() {
        System.out.print("아이디 입력: ");
        String id = scanner.nextLine();

        System.out.print("비밀번호 입력: ");
        String pw = scanner.nextLine();

        // 실제로는 서버에 검증 요청해야 함
        System.out.println("[로그인 시도] " + id + "/" + pw);
        return id;
    }

    public String chooseMode() {
        System.out.println("\n모드를 선택하세요");
        System.out.println("1. 싱글 모드");
        System.out.println("2. 멀티 모드");

        System.out.print("선택 (1/2): ");
        return scanner.nextLine();
    }
}
