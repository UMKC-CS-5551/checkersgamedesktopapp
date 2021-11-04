package edu.umkc.group11.model;

import edu.umkc.group11.screen.CheckerBoardUI;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MovementHelper {

    private Map<String, BoardPanel> movementTrajectory;
    private CheckerBoardUI checkerBoardUI;
    public MovementHelper(CheckerBoardUI checkerBoardUI)
    {
    this.checkerBoardUI = checkerBoardUI;
    }
    public void populateMovementTrajectory(String startEnd, BoardPanel boardPanel){
            getMovementTrajectory().put(startEnd, boardPanel);
    }

    public Map<String, BoardPanel> getMovementTrajectory() {
        if ( movementTrajectory == null)
        {
            movementTrajectory = new HashMap<>();
        }
        return movementTrajectory;
    }

    public void setMovementTrajectory(Map<String, BoardPanel> movementTrajectory) {
        this.movementTrajectory = movementTrajectory;
    }

    public void resetMovementTrajectory()
    {
        movementTrajectory = null;
    }

  //  public boolean isJumpAllowed()

    public boolean isDiagonalMomentAllowed(PanelCoordinate checkerFrom, PanelCoordinate checkerTo, int playerId)
    {
        if ( checkerFrom.getRow() == checkerTo.getRow() )
        {
            return false;
        }
        if ( playerId == 1)
        {
            if ( checkerFrom.getRow() - checkerTo.getRow() == -1)
            {
                if ( (checkerFrom.getCol() - checkerTo.getCol() )<= 1 )
                {
                    return true;
                }
            }
            else if ( (checkerFrom.getRow() - checkerTo.getRow()) == -2 )
            {
                PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol()+1);
                PanelCoordinate pc2 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol());
                BoardPanel boardPanel = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc1);
                if ( boardPanel != null  && boardPanel.getPlayerId() == 2 )
                {
                    return true;
                }
            }
        }
       else if ( playerId == 2)
        {
            if ( checkerFrom.getRow() - checkerTo.getRow() == 1)
            {
                if ( (checkerFrom.getCol() - checkerTo.getCol() )<= 1 )
                {
                    return true;
                }
            }
            else if ( (checkerFrom.getRow() - checkerTo.getRow()) == -2 )
            {
                PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol()+1);
                PanelCoordinate pc2 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol());
                BoardPanel boardPanel = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc1);
                if ( boardPanel != null  && boardPanel.getPlayerId() == 1 )
                {
                    return true;
                }
            }


        }
        return false;
    }


    public void swapPanelOccupants()
    {
        PanelCoordinate to = getMovementTrajectory().get("TARGET").getPanelCoordinate();
        PanelCoordinate from = getMovementTrajectory().get("START").getPanelCoordinate();
        int playerId = getMovementTrajectory().get("START").getPlayerId();

        if ( getMovementTrajectory().get("START").getPlayerId() == 1 && isDiagonalMomentAllowed(from, to, playerId)){
            int pid = getMovementTrajectory().get("TARGET").getPlayerId();
            getMovementTrajectory().get("TARGET").getButton().setBackground(PlayerColor.PLAYER_ONE.getColor());

            int index = getMovementTrajectory().get("START").getIndex();
            getMovementTrajectory().get("TARGET").setPlayerId(getMovementTrajectory().get("START").getPlayerId());
            getMovementTrajectory().get("START").setPlayerId(pid);

            getMovementTrajectory().get("START").getCheckerBoardUI().getBlackButtons()[index].getButton().setBackground(PlayerColor.BLANK.getColor());
            getMovementTrajectory().get("START").getCheckerBoardUI().getPanel().getParent().repaint();
        }
        else if ( getMovementTrajectory().get("START").getPlayerId() == 2  && isDiagonalMomentAllowed(from, to, playerId)){
            int pid = getMovementTrajectory().get("TARGET").getPlayerId();
            getMovementTrajectory().get("TARGET").getButton().setBackground(PlayerColor.PLAYER_TWO.getColor());
            int index = getMovementTrajectory().get("START").getIndex();
            getMovementTrajectory().get("TARGET").setPlayerId(getMovementTrajectory().get("START").getPlayerId());
            getMovementTrajectory().get("START").setPlayerId(pid);
            getMovementTrajectory().get("START").getCheckerBoardUI().getBlackButtons()[index].getButton().setBackground(PlayerColor.BLANK.getColor());
            getMovementTrajectory().get("START").getCheckerBoardUI().getPanel().getParent().repaint();
            resetMovementTrajectory();
        }

    }

}
