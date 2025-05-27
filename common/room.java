package common;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {
    private int roomId;
    private String title;
    private int maxPlayers;
    private ArrayList<Player> players;
    private boolean isStarted;
    private String category;

    public Room(int roomId, String title, int maxPlayers, String category) {
        this.roomId = roomId;
        this.title = title;
        this.maxPlayers = maxPlayers;
        this.category = category;
        this.players = new ArrayList<>();
        this.isStarted = false;
    }

    // === Getter ===
    public int getRoomId() { return roomId; }
    public String getTitle() { return title; }
    public int getMaxPlayers() { return maxPlayers; }
    public ArrayList<Player> getPlayers() { return players; }
    public boolean isStarted() { return isStarted; }
    public String getCategory() { return category; }

    // === Setter ===
    public void setStarted(boolean started) { this.isStarted = started; }

    // === 유틸 ===
    public boolean addPlayer(Player p) {
        if (players.size() < maxPlayers) {
            players.add(p);
            return true;
        }
        return false;
    }

    public void removePlayer(String id) {
        players.removeIf(p -> p.getId().equals(id));
    }

    public boolean containsPlayer(String id) {
        for (Player p : players) {
            if (p.getId().equals(id)) return true;
        }
        return false;
    }
}
