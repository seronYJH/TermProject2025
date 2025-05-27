package common;

import java.io.Serializable;

public class Player implements Serializable {
    private String id;           // 계정용 ID (로그인용)
    private String password;     // 계정용 비밀번호
    private String nickname;     // 게임에서 보여질 이름
    private int score;           // 점수
    private boolean loggedIn;    // 현재 로그인 상태

    public Player(String id, String password, String nickname) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.score = 0;
        this.loggedIn = false;
    }

    // === 로그인 관련 기능 ===

    // 로그인 확인
    public boolean login(String id, String pw) {
        if (this.id.equals(id) && this.password.equals(pw)) {
            this.loggedIn = true;
            return true;
        }
        return false;
    }

    // 로그인 상태 확인
    public boolean isLoggedIn() {
        return loggedIn;
    }

    // 로그아웃
    public void logout() {
        this.loggedIn = false;
    }

    // === getter ===

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public int getScore() {
        return score;
    }

    // === setter ===

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // 점수 더하기
    public void addScore(int delta) {
        this.score += delta;
    }
}
