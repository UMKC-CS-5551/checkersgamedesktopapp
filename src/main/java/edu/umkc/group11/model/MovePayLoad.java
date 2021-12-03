package edu.umkc.group11.model;

public class MovePayLoad {

    private PanelCoordinate from;
    private PanelCoordinate to;
    private boolean jump;

    public MovePayLoad(PanelCoordinate from, PanelCoordinate to, boolean jump) {
        this.from = from;
        this.to = to;
        this.jump = jump;
    }

    public PanelCoordinate getFrom() {
        return from;
    }

    public PanelCoordinate getTo() {
        return to;
    }

    public boolean isJump() {
        return jump;
    }
}
