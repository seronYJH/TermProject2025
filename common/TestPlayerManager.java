package common;

import common.PlayerManager;

import java.util.Scanner;

public class TestPlayerManager {
    public static void main(String[] args) {
        PlayerManager manager = new PlayerManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- 메뉴 ---");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 닉네임 변경");
            System.out.println("4. 유저 수 보기");
            System.out.println("0. 종료");
            System.out.print("선택: ");
            int choice = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    System.out.print("아이디 입력: ");
                    String id = sc.nextLine();
                    System.out.print("비밀번호 입력: ");
                    String pw = sc.nextLine();
                    System.out.print("닉네임 입력: ");
                    String nickname = sc.nextLine();

                    boolean signupSuccess = manager.signUp(id, pw, nickname);
                    System.out.println(signupSuccess ? "회원가입 성공!" : "회원가입 실패 (아이디 또는 닉네임 중복)");
                    break;

                case 2:
                    System.out.print("아이디 입력: ");
                    String loginId = sc.nextLine();
                    System.out.print("비밀번호 입력: ");
                    String loginPw = sc.nextLine();

                    boolean loginSuccess = manager.IDEqual(loginId) && manager.PassWordEqual(loginId, loginPw);
                    System.out.println(loginSuccess ? "로그인 성공!" : "로그인 실패");
                    break;

                case 3:
                    System.out.print("아이디 입력: ");
                    String nickId = sc.nextLine();
                    System.out.print("새 닉네임 입력: ");
                    String newNick = sc.nextLine();

                    boolean changed = manager.changeNickname(nickId, newNick);
                    System.out.println(changed ? "닉네임 변경 성공!" : "닉네임 변경 실패 (중복 또는 존재하지 않는 아이디)");
                    break;

                case 4:
                    System.out.println("전체 유저 수: " + manager.getPlayerCount());
                    break;

                case 0:
                    System.out.println("종료합니다.");
                    return;

                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }
}