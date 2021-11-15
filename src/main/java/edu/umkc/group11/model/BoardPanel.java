package edu.umkc.group11.model;

import edu.umkc.group11.GameUtils;
import edu.umkc.group11.client.GamePlayUtil;
import edu.umkc.group11.screen.CheckerBoardUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel {

    private boolean activated;
    private JButton button;
    private Player player;
    private boolean selected;
    private CheckerBoardUI checkerBoardUI;
    private int index;
    private PanelCoordinate panelCoordinate;

    public MovementHelper getMovementHelper() {
        return movementHelper;
    }

    private MovementHelper movementHelper;

    public void resetButtonProperties()
    {
        String tmpIndex = String.valueOf(index);
        button.setName(tmpIndex);
        if ( player !=null && player.getPlayerId() == 1)
        {
            button.setBackground(Color.BLUE);
        }
        else if ( player !=null && player.getPlayerId() == 2)
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
        setPreferredSize(new Dimension(100,100));
        this.movementHelper = checkerBoardUI.getMovementHelper();
        button = new JButton();
        button.setOpaque(true);

        button.setPreferredSize(new Dimension(100,100));
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
        this.movementHelper = checkerBoardUI.getMovementHelper();
        if ( (player != null && player.getPlayerId() > 0))
        {
            util.resetOtherPanelsAndButtons(checkerBoardUI, button);
        }
        if ( player != null && player.getPlayerId() == 1 && checkerBoardUI.isPlayerOneSelected() && !(checkerBoardUI.getPlayerUsageStack().search("player1") == 1))
        {
            setSelected(true);
            checkerBoardUI.getJRadioButtonPlayer1().setEnabled(false);
            checkerBoardUI.getJRadioButtonPlayer2().setEnabled(false);
            button.setBackground(Color.cyan);
            movementHelper.populateMovementTrajectory("START", this);
            checkerBoardUI.getPlayerUsageStack().push("player1");
        }
        else  if (  player != null && player.getPlayerId() == 2 && checkerBoardUI.isPlayerTwoSelected() && !(checkerBoardUI.getPlayerUsageStack().search("player2") == 1))
        {
            setSelected(true);
            checkerBoardUI.getJRadioButtonPlayer1().setEnabled(false);
            checkerBoardUI.getJRadioButtonPlayer2().setEnabled(false);

            button.setBackground(Color.MAGENTA);
            movementHelper.populateMovementTrajectory("START", this);
            checkerBoardUI.getPlayerUsageStack().push("player2");
        }
        else if ( (player == null  || player.getPlayerId() == 0 ) && activated ) {
            setSelected(true);

            if (movementHelper.getMovementTrajectory().get("START") != null) {

                movementHelper.populateMovementTrajectory("TARGET", this);
                movementHelper.swapPanelOccupants();
            }
            checkerBoardUI.getJRadioButtonPlayer1().setEnabled(true);
            checkerBoardUI.getJRadioButtonPlayer2().setEnabled(true);
            boolean tmpKing = false;
            if ( this.getPlayer() != null )
            {
                tmpKing = this.getPlayer().isKing();
            }

            if (!checkerBoardUI.getPlayerUsageStack().empty() && GameUtils.hasThePlayerGotTurn(checkerBoardUI.getPlayerUsageStack(), "player1")) {
                if ( movementHelper.canJumpMore(this.panelCoordinate,1,tmpKing, movementHelper.isJumped()))
                {
                    checkerBoardUI.getJRadioButtonPlayer1().setEnabled(true);
                    checkerBoardUI.getJRadioButtonPlayer2().setSelected(false);
                    checkerBoardUI.getJRadioButtonPlayer1().doClick();
                    checkerBoardUI.getPlayerUsageStack().pop();
                    movementHelper.setJumped(false);
                }
                else {
                    checkerBoardUI.getJRadioButtonPlayer1().setEnabled(false);
                    checkerBoardUI.getJRadioButtonPlayer2().setSelected(true);
                    checkerBoardUI.getJRadioButtonPlayer2().doClick();
                }

            } else if (!checkerBoardUI.getPlayerUsageStack().empty() && GameUtils.hasThePlayerGotTurn(checkerBoardUI.getPlayerUsageStack(), "player2")) {

                if ( movementHelper.canJumpMore(this.panelCoordinate,2,tmpKing, movementHelper.isJumped()))
                {
                    checkerBoardUI.getJRadioButtonPlayer2().setEnabled(true);
                    checkerBoardUI.getJRadioButtonPlayer1().setSelected(false);
                    checkerBoardUI.getJRadioButtonPlayer2().doClick();
                    movementHelper.setJumped(false);
                    checkerBoardUI.getPlayerUsageStack().pop();
                }
                else
                {
                    checkerBoardUI.getJRadioButtonPlayer2().setEnabled(false);
                    checkerBoardUI.getJRadioButtonPlayer1().setSelected(true);
                    checkerBoardUI.getJRadioButtonPlayer1().doClick();
                }

            }
        }
        util.resetOtherPanelsAndButtons(checkerBoardUI, button);
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


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public CheckerBoardUI getCheckerBoardUI() {
        return checkerBoardUI;
    }

    public void setCheckerBoardUI(CheckerBoardUI checkerBoardUI) {
        this.checkerBoardUI = checkerBoardUI;
    }

    public PanelCoordinate getPanelCoordinate() {
        return panelCoordinate;
    }

    public void setPanelCoordinate(PanelCoordinate panelCoordinate) {
        this.panelCoordinate = panelCoordinate;
    }

    public void setMovementHelper(MovementHelper movementHelper) {
        this.movementHelper = movementHelper;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        if ( player != null && player.getPlayerId() == 1) {
            button.setBackground(Color.BLUE);
        }
        else if ( player != null && player.getPlayerId() == 2)
        {
            button.setBackground(Color.RED);
        }
        else
        {
            button.setBackground(Color.ORANGE);
        }


    }

    public int getIndex() {
        return index;
    }
}
