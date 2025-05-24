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
}
