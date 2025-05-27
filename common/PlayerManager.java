package common;

import java.util.ArrayList;

public class PlayerManager {
    private ArrayList<common.Player> players;

    public PlayerManager() {
        players = new ArrayList<>();
    }

    // ✅ 회원가입
    public boolean signUp(String id, String password, String nickname) {
        if (IDEqual(id)) return false;               // 아이디 중복 확인
        if (NameEqual(nickname)) return false;       // 닉네임 중복 확인

        players.add(new common.Player(id, password, nickname));
        return true;
    }

    // ✅ 아이디 존재 확인
    public boolean IDEqual(String id) {
        for (common.Player p : players) {
            if (p.getId().equals(id)) return true;
        }
        return false;
    }

    // ✅ 비밀번호 일치 확인
    public boolean PassWordEqual(String id, String pw) {
        for (common.Player p : players) {
            if (p.getId().equals(id)) {
                return p.getPassword().equals(pw);
            }
        }
        return false;
    }

    // ✅ 닉네임 중복 확인
    public boolean NameEqual(String nickname) {
        for (common.Player p : players) {
            if (p.getNickname().equals(nickname)) return true;
        }
        return false;
    }

    // ✅ 닉네임 변경
    public boolean changeNickname(String id, String newNickname) {
        if (NameEqual(newNickname)) return false;

        for (common.Player p : players) {
            if (p.getId().equals(id)) {
                p.setNickname(newNickname);
                return true;
            }
        }
        return false;
    }

    // ✅ 점수 추가
    public boolean addScore(String id, int delta) {
        for (common.Player p : players) {
            if (p.getId().equals(id)) {
                p.setScore(p.getScore() + delta);
                return true;
            }
        }
        return false;
    }

    // ✅ 플레이어 수 확인
    public int getPlayerCount() {
        return players.size();
    }

    // ✅ 특정 ID로 Player 객체 반환
    public common.Player getPlayer(String id) {
        for (common.Player p : players) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }
}
