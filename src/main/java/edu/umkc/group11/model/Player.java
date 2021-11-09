package edu.umkc.group11.model;

public class Player {
    private int playerId;
    private boolean active;
    private boolean king;
    private String name;
    private  int score;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player(int playerId, boolean active) {
        this.playerId = playerId;
        this.active = active;
        this.name = name;
    }

    public Player(int playerId,String name) {
        this.playerId = playerId;
        this.active = active;
        this.name = name;
    }


    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isKing() {
        return king;
    }

    public void setKing(boolean king) {
        this.king = king;
    }
}
