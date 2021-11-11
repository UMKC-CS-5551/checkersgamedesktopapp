package edu.umkc.group11.model;

import edu.umkc.group11.client.GamePlayUtil;
import edu.umkc.group11.screen.CheckerBoardUI;

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

    public void makeOpponentPieceToDisappear(BoardPanel opponentBoardPanel)
    {
        opponentBoardPanel.getButton().setBackground(Color.WHITE);
        opponentBoardPanel.getPlayer().setPlayerId(0);
        opponentBoardPanel.getPlayer().setActive(false);
        opponentBoardPanel.getPlayer().setKing(false);
    }

    public void playerScoreTracker(int playerId)
    {
        if(playerId == 1)
        {
            int score =  checkerBoardUI.getPlayerOne().getScore() + 1;
            checkerBoardUI.getPlayerOne().setScore(score);
        }

        if(playerId == 2)
        {
            int score = checkerBoardUI.getPlayerTwo().getScore() + 1;
            checkerBoardUI.getPlayerTwo().setScore(score);
        }

        checkerBoardUI.updateScoresOnBoard();

    }
    public boolean isDiagonalMomentAllowed(PanelCoordinate checkerFrom, PanelCoordinate checkerTo, int playerId, boolean isKing)
    {
        if ( checkerFrom.getRow() == checkerTo.getRow() )
        {
            return false;
        }
        if ( playerId == 1 )
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
                int tempCol1 = 0;
                if ( checkerFrom.getRow() % 2 == 0)
                {
                    tempCol1 = -1;
                }
                else if ( checkerFrom.getRow() % 2 != 0 )
                {
                    tempCol1 = 1;
                }
                PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol());
                PanelCoordinate pc2 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol()+tempCol1);
                BoardPanel boardPanelPosition1 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc1);
                BoardPanel boardPanelPosition2 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc2);
                if ( boardPanelPosition1 != null  && boardPanelPosition1.getPlayer() != null  && boardPanelPosition1.getPlayer().getPlayerId() == 2 )
                {
                    makeOpponentPieceToDisappear(boardPanelPosition1);
                    playerScoreTracker(playerId);
                    return true;
                }
                else if ( boardPanelPosition2 != null  && boardPanelPosition2.getPlayer() !=  null && boardPanelPosition2.getPlayer().getPlayerId() == 2 )
                {
                    makeOpponentPieceToDisappear(boardPanelPosition2);
                    playerScoreTracker(playerId);
                    return true;
                }
            }
            if ( isKing )
            {
                if ( checkerFrom.getRow() - checkerTo.getRow() == 1)
                {
                    if ( (checkerFrom.getCol() - checkerTo.getCol() )<= 1 )
                    {
                        return true;
                    }
                }
                else if ( (checkerFrom.getRow() - checkerTo.getRow()) == 2 )
                {
                    int tempCol1 = 0;
                    if ( checkerFrom.getRow() % 2 == 0)
                    {
                        tempCol1 = -1;
                    }
                    else if ( checkerFrom.getRow() % 2 != 0 )
                    {
                        tempCol1 = 1;
                    }

                    PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()-1, checkerFrom.getCol() + tempCol1);
                    PanelCoordinate pc2 = new PanelCoordinate(checkerFrom.getRow()-1, checkerFrom.getCol());
                    BoardPanel boardPanelPosition1 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc1);
                    BoardPanel boardPanelPosition2 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc2);
                    if ( boardPanelPosition1 != null  && boardPanelPosition1.getPlayer() != null && boardPanelPosition1.getPlayer().getPlayerId() == 2 )
                    {
                        makeOpponentPieceToDisappear(boardPanelPosition1);
                        playerScoreTracker(playerId);
                        return true;
                    }
                    if ( boardPanelPosition2 != null  && boardPanelPosition2.getPlayer() != null && boardPanelPosition2.getPlayer().getPlayerId() == 2 )
                    {
                        makeOpponentPieceToDisappear(boardPanelPosition2);
                        playerScoreTracker(playerId);
                        return true;
                    }
                }

            }
        }
       else if ( playerId == 2 )
        {
            if ( checkerFrom.getRow() - checkerTo.getRow() == 1)
            {
                if ( (checkerFrom.getCol() - checkerTo.getCol() )<= 1 )
                {
                    return true;
                }
            }
            else if ( (checkerFrom.getRow() - checkerTo.getRow()) == 2 )
            {
                int tempCol1 = 0;
                if ( checkerFrom.getRow() % 2 == 0)
                {
                    tempCol1 = -1;
                }
                else if ( checkerFrom.getRow() % 2 != 0 )
                {
                    tempCol1 = 1;
                }

                PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()-1, checkerFrom.getCol() + tempCol1);
                PanelCoordinate pc2 = new PanelCoordinate(checkerFrom.getRow()-1, checkerFrom.getCol());
                BoardPanel boardPanelPosition1 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc1);
                BoardPanel boardPanelPosition2 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc2);
                if ( boardPanelPosition1 != null  && boardPanelPosition1.getPlayer() != null && boardPanelPosition1.getPlayer().getPlayerId() == 1 )
                {
                    makeOpponentPieceToDisappear(boardPanelPosition1);
                    playerScoreTracker(playerId);
                    return true;
                }
                if ( boardPanelPosition2 != null  && boardPanelPosition2.getPlayer() != null && boardPanelPosition2.getPlayer().getPlayerId() == 1 )
                {
                    makeOpponentPieceToDisappear(boardPanelPosition2);
                    playerScoreTracker(playerId);
                    return true;
                }
            }
            if ( isKing )
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
                    int tempCol1 = 0;
                    if ( checkerFrom.getRow() % 2 == 0)
                    {
                        tempCol1 = -1;
                    }
                    else if ( checkerFrom.getRow() % 2 != 0 )
                    {
                        tempCol1 = 1;
                    }
                    PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol());
                    PanelCoordinate pc2 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol()+tempCol1);
                    BoardPanel boardPanelPosition1 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc1);
                    BoardPanel boardPanelPosition2 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc2);
                    if ( boardPanelPosition1 != null  && boardPanelPosition1.getPlayer() != null  && boardPanelPosition1.getPlayer().getPlayerId() == 1 )
                    {
                        makeOpponentPieceToDisappear(boardPanelPosition1);
                        playerScoreTracker(playerId);
                        return true;
                    }
                    else if ( boardPanelPosition2 != null  && boardPanelPosition2.getPlayer() !=  null && boardPanelPosition2.getPlayer().getPlayerId() == 1 )
                    {
                        makeOpponentPieceToDisappear(boardPanelPosition2);
                        playerScoreTracker(playerId);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void swapPanelOccupants()
    {
        PanelCoordinate to = getMovementTrajectory().get("TARGET").getPanelCoordinate();
        PanelCoordinate from = getMovementTrajectory().get("START").getPanelCoordinate();
        int playerId = 0;
        if ( getMovementTrajectory().get("START").getPlayer() != null ){
            playerId = getMovementTrajectory().get("START").getPlayer().getPlayerId();
        }

        if ( getMovementTrajectory().get("START").getPlayer() != null && getMovementTrajectory().get("START").getPlayer().getPlayerId() == 1 && isDiagonalMomentAllowed(from, to, playerId, getMovementTrajectory().get("START").getPlayer().isKing() )){
            Player player = getMovementTrajectory().get("TARGET").getPlayer();
            getMovementTrajectory().get("TARGET").getButton().setBackground(PlayerColor.PLAYER_ONE.getColor());

            int index = getMovementTrajectory().get("START").getIndex();
            getMovementTrajectory().get("TARGET").setPlayer(getMovementTrajectory().get("START").getPlayer());
            getMovementTrajectory().get("START").setPlayer(player);
            convertToKing( getMovementTrajectory().get("TARGET"), to);
            getMovementTrajectory().get("START").getCheckerBoardUI().getBlackButtons()[index].getButton().setBackground(PlayerColor.BLANK.getColor());
            getMovementTrajectory().get("START").getCheckerBoardUI().getPanel().getParent().repaint();
        }
        else if ( getMovementTrajectory().get("START").getPlayer() != null && getMovementTrajectory().get("START").getPlayer().getPlayerId() == 2  && isDiagonalMomentAllowed(from, to, playerId, getMovementTrajectory().get("START").getPlayer().isKing())){
            Player player = getMovementTrajectory().get("TARGET").getPlayer();
            getMovementTrajectory().get("TARGET").getButton().setBackground(PlayerColor.PLAYER_TWO.getColor());
            int index = getMovementTrajectory().get("START").getIndex();
            getMovementTrajectory().get("TARGET").setPlayer(getMovementTrajectory().get("START").getPlayer());
            getMovementTrajectory().get("START").setPlayer(player);
            getMovementTrajectory().get("START").getCheckerBoardUI().getBlackButtons()[index].getButton().setBackground(PlayerColor.BLANK.getColor());
            convertToKing( getMovementTrajectory().get("TARGET"), to);

            getMovementTrajectory().get("START").getCheckerBoardUI().getPanel().getParent().repaint();
        }

    }

    public void convertToKing(BoardPanel boardPanel, PanelCoordinate fromTo)
    {
        if ( boardPanel.getPlayer().getPlayerId() == 1 && fromTo.getRow() == 7 )
        {
            boardPanel.getPlayer().setKing(true);
        }
        else if ( boardPanel.getPlayer().getPlayerId() == 2 && fromTo.getRow() == 0 )
        {
            boardPanel.getPlayer().setKing(true);
        }
    }

}
