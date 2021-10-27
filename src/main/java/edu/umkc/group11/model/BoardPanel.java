package edu.umkc.group11.model;

import edu.umkc.group11.client.GamePlayUtil;
import edu.umkc.group11.screen.CheckerBoardUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel {

    private int xpos;
    private int ypos;
    private boolean activated;
    private JButton button;
    private int playerId;
    private boolean selected;
    private CheckerBoardUI checkerBoardUI;
    private int index;

    public void resetButtonProperties()
    {
        String tmpIndex = String.valueOf(index);
        button.setName(tmpIndex);
        if ( playerId == 1)
        {
            button.setBackground(Color.BLUE);
        }
        else if ( playerId == 2)
        {
            button.setBackground(Color.RED);
        }
        else
        {
            button.setBackground(Color.WHITE);
        }

    }
    public BoardPanel(CheckerBoardUI checkerBoardUI, int index)
    {
        this.index = index;
        this.checkerBoardUI = checkerBoardUI;
        setPreferredSize(new Dimension(40,40));
        button = new JButton();
        button.setPreferredSize(new Dimension(30,30));
        resetButtonProperties();
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickAction();
            }
        });
        add(button);
    }

    void buttonClickAction()
    {
        setSelected(false);
        GamePlayUtil util = new GamePlayUtil(checkerBoardUI);
        if ( playerId > 0)
        {
            util.resetOtherPanelsAndButtons(checkerBoardUI, button);
        }
        if ( playerId == 1)
        {
            setSelected(true);
            button.setBackground(Color.cyan);
        }
        else  if (  playerId == 2)
        {
            setSelected(true);
            button.setBackground(Color.MAGENTA);
        }

    }
    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public int getPlayerId() {
        return playerId;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
        if ( playerId == 1)
        {
            button.setBackground(Color.BLUE);
        }
        else if ( playerId == 2)
        {
            button.setBackground(Color.RED);
        }
        else
        {
            button.setBackground(Color.ORANGE);
        }

    }
}
