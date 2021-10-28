package edu.umkc.group11.model;

import java.awt.*;

public enum PlayerColor {

    PLAYER_ONE(Color.BLUE),
    PLAYER_TWO(Color.RED),
    BLANK(Color.WHITE);

    private Color color;

    PlayerColor(final Color color)
    {
        this.color = color;
    }
    public Color getColor()
    {
        return color;
    }

}
