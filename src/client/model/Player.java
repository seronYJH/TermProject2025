package client.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 클라이언트 유저 상태를 관리하는 클래스
 * 닉네임, 점수, 코인, 아이템, 방 상태 등 포함
 */
public class Player {

    private String nickname;
    private int score;
    private int coin;

    private Set<String> ownedItems = new HashSet<>();
    private String currentRoomId;
    private boolean connected;

    public Player(String nickname) {
        this.nickname = nickname;
        this.score = 0;
        this.coin = 0;
        this.connected = false;
    }

    // ===== 점수 관련 =====
    public void increaseScore(int value) {
        this.score += value;
    }

    public void resetScore() {
        this.score = 0;
    }

    // ===== 코인 관련 =====
    public void addCoin(int amount) {
        this.coin += amount;
    }

    public boolean spendCoin(int amount) {
        if (coin >= amount) {
            this.coin -= amount;
            return true;
        }
        return false;
    }

    // ===== 아이템 관련 =====
    public void addItem(String itemName) {
        ownedItems.add(itemName);
    }

    public boolean hasItem(String itemName) {
        return ownedItems.contains(itemName);
    }

    public void useItem(String itemName) {
        ownedItems.remove(itemName);
    }

    // ===== 기타 =====
    public void setRoomId(String roomId) {
        this.currentRoomId = roomId;
    }

    public String getRoomId() {
        return currentRoomId;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getNickname() {
        return nickname;
    }

    public int getScore() {
        return score;
    }

    public int getCoin() {
        return coin;
    }

    public Set<String> getOwnedItems() {
        return ownedItems;
    }

    @Override
    public String toString() {
        return "[닉네임: " + nickname + ", 점수: " + score + ", 코인: " + coin + ", 방: " + currentRoomId + "]";
    }
}
