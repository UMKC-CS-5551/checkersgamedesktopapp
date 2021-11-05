package edu.umkc.group11.client;

import edu.umkc.group11.model.BoardPanel;
import edu.umkc.group11.screen.CheckerBoardUI;

import javax.swing.*;


public class GamePlayUtil {
    CheckerBoardUI checkerBoardUI;
    public GamePlayUtil(CheckerBoardUI checkerBoardUI)
    {
        this.checkerBoardUI = checkerBoardUI;
    }

    public void resetOtherPanelsAndButtons(CheckerBoardUI checkerBoardUI, JButton activatedButton)
    {
        BoardPanel[] boardPanels = checkerBoardUI.getBlackButtons();
        for ( BoardPanel boardPanel: boardPanels)
        {
            if ( boardPanel.getPlayer() != null && boardPanel.getButton().getName() != null &&  !boardPanel.getButton().getName().equals(activatedButton.getName()) && boardPanel.getPlayer().getPlayerId() > 0)
            {
                boardPanel.resetButtonProperties();
            }
        }
    }


}
