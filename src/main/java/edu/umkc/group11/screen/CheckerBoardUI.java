package edu.umkc.group11.screen;


import edu.umkc.group11.model.BoardPanel;
import edu.umkc.group11.model.MovementHelper;
import edu.umkc.group11.model.PanelCoordinate;

import javax.swing.*;
import java.awt.*;

public class CheckerBoardUI extends JPanel  {
    private BoardPanel[] blackButtons;
    private BoardPanel[] whiteButtons;
    private JPanel panel;
    MovementHelper movementHelper;
    public CheckerBoardUI()
    {
        blackButtons = new BoardPanel[4 * 8];
        whiteButtons = new BoardPanel[4 * 8];

        createBoard();
        this.movementHelper = new MovementHelper();
    }

    public void createBoard()
    {

        for(int i = 0; i < blackButtons.length; i++)
        {
            blackButtons[i] = new BoardPanel(this, i);
            blackButtons[i].setBackground(Color.GREEN);
            blackButtons[i].setMovementHelper(this.movementHelper);
            blackButtons[i].setActivated(true);
        }
        for(int i = 0; i < whiteButtons.length; i++)
        {
            whiteButtons[i] = new BoardPanel(this,i);
            whiteButtons[i].setBackground(Color.WHITE);
            whiteButtons[i].setMovementHelper(this.movementHelper);

        }
        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));
        panel.setSize(1000, 1000);

        for (int i = 0; i < 8; i++) {
            whiteButtons[i].setPreferredSize(new Dimension(100,100));
            blackButtons[i].setPreferredSize(new Dimension(100,100));
            blackButtons[i].setActivated(true);
            whiteButtons[i].getButton().setEnabled(false);
            if (i % 2 == 0) {
                for (int j = 0; j < 4; j++) {
                    String tmp = String.valueOf(i) + "," + String.valueOf(j);

                    blackButtons[4 * i + j].getButton().setText(tmp);
                    blackButtons[4 * i + j].setPanelCoordinate(new PanelCoordinate(i,j));
                    if ( i <= 2)
                    {
                        blackButtons[4 * i + j].setXpos(i);
                        blackButtons[4 * i + j].setYpos(j);
                        blackButtons[4 * i + j].setPlayerId(1);
                    }
                    else if ( i >= 5 )
                    {
                        blackButtons[4 * i + j].setXpos(i);
                        blackButtons[4 * i + j].setYpos(j);
                        blackButtons[4 * i + j].setPlayerId(2);
                    }
                    panel.add(blackButtons[4 * i + j]);
                    panel.add(whiteButtons[4 * i + j]);
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String tmp = String.valueOf(i) + "," + String.valueOf(j);

                    blackButtons[4 * i + j].getButton().setText(tmp);
                    blackButtons[4 * i + j].setPanelCoordinate(new PanelCoordinate(i,j));


                    if ( i <= 1)
                    {
                        blackButtons[4 * i + j].setXpos(i);
                        blackButtons[4 * i + j].setYpos(j);
                        blackButtons[4 * i + j].setPlayerId(1);
                    }
                    else if (  i >= 5 )
                    {
                        blackButtons[4 * i + j].setXpos(i);
                        blackButtons[4 * i + j].setYpos(j);
                        blackButtons[4 * i + j].setPlayerId(2);
                    }

                    panel.add(whiteButtons[4 * i + j]);
                    panel.add(blackButtons[4 * i + j]);
                }
            }
        }
        add(panel);
        panel.repaint();
    }

    public BoardPanel[] getBlackButtons() {
        return blackButtons;
    }

    public BoardPanel[] getWhiteButtons() {
        return whiteButtons;
    }

    public JPanel getPanel() {
        return panel;
    }

    public MovementHelper getMovementHelper() {
        return movementHelper;
    }

    public void setMovementHelper(MovementHelper movementHelper) {
        this.movementHelper = movementHelper;
    }
}
