package edu.umkc.group11.screen;


import edu.umkc.group11.model.BoardPanel;

import javax.swing.*;
import java.awt.*;

public class CheckerBoardUI extends JPanel  {
    private BoardPanel[] blackButtons;
    private BoardPanel[] whiteButtons;
    private JPanel panel;
    public CheckerBoardUI()
    {
        createBoard();
    }

    public void createBoard()
    {

        blackButtons = new BoardPanel[4 * 8];
        whiteButtons = new BoardPanel[4 * 8];

        for(int i = 0; i < blackButtons.length; i++)
        {
            blackButtons[i] = new BoardPanel(this, i);
            blackButtons[i].setBackground(Color.GREEN);
        }
        for(int i = 0; i < whiteButtons.length; i++)
        {
            whiteButtons[i] = new BoardPanel(this,i);
            whiteButtons[i].setBackground(Color.WHITE);
        }
        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));
        panel.setSize(600, 600);

        for (int i = 0; i < 8; i++) {
            whiteButtons[i].setPreferredSize(new Dimension(40,40));

            if (i % 2 == 0) {
                for (int j = 0; j < 4; j++) {
                    if ( i <= 2)
                    {
                        blackButtons[4 * i + j].setXpos(i);
                        blackButtons[4 * i + j].setYpos(j);
                        blackButtons[4 * i + j].setActivated(true);
                        blackButtons[4 * i + j].setPlayerId(1);
                    }
                    else if ( i >= 5 )
                    {
                        blackButtons[4 * i + j].setXpos(i);
                        blackButtons[4 * i + j].setYpos(j);
                        blackButtons[4 * i + j].setActivated(true);
                        blackButtons[4 * i + j].setPlayerId(2);
                    }
                    panel.add(blackButtons[4 * i + j]);
                    panel.add(whiteButtons[4 * i + j]);
                }
            } else {
                for (int j = 0; j < 4; j++) {

                    if ( i <= 1)
                    {
                        blackButtons[4 * i + j].setXpos(i);
                        blackButtons[4 * i + j].setYpos(j);
                        blackButtons[4 * i + j].setActivated(true);
                        blackButtons[4 * i + j].setPlayerId(1);
                    }
                    else if (  i >= 5 )
                    {
                        blackButtons[4 * i + j].setXpos(i);
                        blackButtons[4 * i + j].setYpos(j);
                        blackButtons[4 * i + j].setActivated(true);
                        blackButtons[4 * i + j].setPlayerId(2);
                    }

                    panel.add(whiteButtons[4 * i + j]);
                    panel.add(blackButtons[4 * i + j]);
                }
            }
        }
        add(panel);
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
}
