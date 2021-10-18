package edu.umkc.group11.screen;

import javax.swing.*;
import java.awt.*;

public class CheckerBoardUI extends JPanel {

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(900, 900);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int row;   // Row number, from 0 to 7
        int col;   // Column number, from 0 to 7
        int x,y;   // Top-left corner of square

        for ( row = 0;  row < 8;  row++ ) {
            for ( col = 0;  col < 8;  col++) {
                x = col * 100;
                y = row * 100;
                if ( (row % 2) == (col % 2) )
                {
                    g.setColor(Color.white);
                }
                else{
                    g.setColor(Color.black);
                }
                g.fillRect(x, y, 100, 100);
            }
        }
    }
}
