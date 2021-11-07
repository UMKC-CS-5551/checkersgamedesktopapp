package edu.umkc.group11.model;

public class Piece {

    private int pieceId;
    private boolean active;
    private String label;
    private Player player;
    private boolean king;


    public Piece(int pieceId, boolean active, String label, Player player) {
        this.pieceId = pieceId;
        this.active = active;
        this.label = label;
        this.player = player;
    }

    public int getPieceId() {
        return pieceId;
    }

    public void setPieceId(int pieceId) {
        this.pieceId = pieceId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isKing() {
        return king;
    }

    public void setKing(boolean king) {
        this.king = king;
    }
}
