import java.util.*;

public class PlayerManager {
    HashMap<String, Integer> userScores = new HashMap<>();

    public synchronized void updateScore(String username, int newScore) {
        userScores.put(username, newScore);
    }

    public String getRankingInfo() {
        List<Map.Entry<String, Integer>> rankingList = new ArrayList<>(userScores.entrySet());
        rankingList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        StringBuilder sb = new StringBuilder();
        sb.append("RANKING:\n");
        int rank = 1;
        for (Map.Entry<String, Integer> entry : rankingList) {
            sb.append(rank++).append(". ").append(entry.getKey()).append(" - ").append(entry.getValue()).append("점\n");
        }
        return sb.toString();
    }

    public static void handleLoginOrRegister(String username, String password, ClientHandler handler) {
        // 로그인과 회원가입을 동시에 처리함.

        Player player = new Player(username, password, handler);

        boolean isNew = playerDatabase.add(player); // 중복이면 false 반환 // player 데이터베이스에 필요한 기능///////////

        if (isNew) {
            handler.getOut().println("회원가입 성공! 환영합니다.");
        } else {
            // 이미 존재하는 사용자 → 기존 객체 찾아서 재연결
            for (Player p : playerDatabase) {
                if (p.equals(player)) { // 오버라이딩을 통해 새롭게 정의해야함 // player 데이터베이스에 필요한 기능///////////
                    p.setHandler(handler); // 기존 객체에 핸들러 업데이트
                    this.player = p; // 핸들러를 통해 호출한 기존 객체를 지금 핸들러에 연결.
                    handler.getOut().println("로그인 성공! 환영합니다.");
                    break;
                }
            }
        }
    }

}
