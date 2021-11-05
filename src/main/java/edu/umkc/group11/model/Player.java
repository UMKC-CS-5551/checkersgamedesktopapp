package edu.umkc.group11.model;

public class Player {
    private int playerId;
    private boolean active;


    public Player(int playerId, boolean active) {
        this.playerId = playerId;
        this.active = active;
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
}