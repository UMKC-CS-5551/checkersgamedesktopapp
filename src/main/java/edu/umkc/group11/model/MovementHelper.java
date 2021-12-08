package edu.umkc.group11.model;

import edu.umkc.group11.client.GamePlayUtil;
import edu.umkc.group11.screen.CheckerBoardUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MovementHelper {

    private Map<String, BoardPanel> movementTrajectory;
    private CheckerBoardUI checkerBoardUI;
    private boolean jumped;

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
                if ( checkerFrom.getRow() % 2 == 0 )
                {
                    if ( (checkerFrom.getCol() - checkerTo.getCol() )== 0 || (checkerFrom.getCol() - checkerTo.getCol() )  == 1)
                    {
                        return true;
                    }

                }
                else if ( checkerFrom.getRow() % 2 != 0  )
                {
                    if ( (checkerFrom.getCol() - checkerTo.getCol() )== 0 || (checkerFrom.getCol() - checkerTo.getCol() )  == -1)
                    {
                        return true;
                    }
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
                    this.jumped = true;
                    return true;
                }
                else if ( boardPanelPosition2 != null  && boardPanelPosition2.getPlayer() !=  null && boardPanelPosition2.getPlayer().getPlayerId() == 2 )
                {
                    makeOpponentPieceToDisappear(boardPanelPosition2);
                    playerScoreTracker(playerId);
                    this.jumped = true;
                    return true;
                }
            }
            if ( isKing )
            {
                if ( checkerFrom.getRow() - checkerTo.getRow() == 1)
                {
                    if ( checkerFrom.getRow() % 2 == 0 )
                    {
                        if ( (checkerFrom.getCol() - checkerTo.getCol() )== 0 || (checkerFrom.getCol() - checkerTo.getCol() )  == 1)
                        {
                            return true;
                        }

                    }
                    else if ( checkerFrom.getRow() % 2 != 0  )
                    {
                        if ( (checkerFrom.getCol() - checkerTo.getCol() )== 0 || (checkerFrom.getCol() - checkerTo.getCol() )  == -1)
                        {
                            return true;
                        }
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
                        this.jumped = true;
                        return true;
                    }
                    if ( boardPanelPosition2 != null  && boardPanelPosition2.getPlayer() != null && boardPanelPosition2.getPlayer().getPlayerId() == 2 )
                    {
                        makeOpponentPieceToDisappear(boardPanelPosition2);
                        playerScoreTracker(playerId);
                        this.jumped = true;
                        return true;
                    }
                }

            }
        }
       else if ( playerId == 2 )
        {
            if ( checkerFrom.getRow() - checkerTo.getRow() == 1)
            {
                if ( checkerFrom.getRow() % 2 == 0 )
                {
                    if ( (checkerFrom.getCol() - checkerTo.getCol() )== 0 || (checkerFrom.getCol() - checkerTo.getCol() )  == 1)
                    {
                        return true;
                    }

                }
                else if ( checkerFrom.getRow() % 2 != 0  )
                {
                    if ( (checkerFrom.getCol() - checkerTo.getCol() )== 0 || (checkerFrom.getCol() - checkerTo.getCol() )  == -1)
                    {
                        return true;
                    }
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
                    this.jumped = true;
                    return true;
                }
                if ( boardPanelPosition2 != null  && boardPanelPosition2.getPlayer() != null && boardPanelPosition2.getPlayer().getPlayerId() == 1 )
                {
                    makeOpponentPieceToDisappear(boardPanelPosition2);
                    playerScoreTracker(playerId);
                    this.jumped = true;
                    return true;
                }
            }
            if ( isKing )
            {
                if ( checkerFrom.getRow() - checkerTo.getRow() == -1)
                {
                    if ( (checkerFrom.getCol() - checkerTo.getCol() ) == 0 ||  (checkerFrom.getCol() - checkerTo.getCol() ) == 1 )
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
                        this.jumped = true;
                        return true;
                    }
                    else if ( boardPanelPosition2 != null  && boardPanelPosition2.getPlayer() !=  null && boardPanelPosition2.getPlayer().getPlayerId() == 1 )
                    {
                        makeOpponentPieceToDisappear(boardPanelPosition2);
                        playerScoreTracker(playerId);
                        this.jumped = true;
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

    public boolean canJumpMore(PanelCoordinate panelCoordinate, int playerId, boolean isKing, boolean jumped)
    {
        if ( !jumped )
        {
            return false;
        }

        if ( playerId == 1 || ( playerId == 2 && isKing ))
        {
            int tempOpponentRow = panelCoordinate.getRow() + 1;
            int tempTargetRow = panelCoordinate.getRow() + 2;
            int tmpTargetColPossibility1  = panelCoordinate.getCol() + 1;
            int tmpTargetColPossibility2  = panelCoordinate.getCol() - 1;


            if ( panelCoordinate.getRow() % 2 == 0 )
            {
                int tmpOpponentColPossibility1  = panelCoordinate.getCol();
                int tmpOpponentColPossibility2  = panelCoordinate.getCol() - 1;

                BoardPanel tmpBoardPanelPossibility1 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempOpponentRow,tmpOpponentColPossibility1));
                BoardPanel tmpBoardPanelPossibility2 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempOpponentRow,tmpOpponentColPossibility2));

                BoardPanel tmpBoardPanelTargetPossibility1 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempTargetRow,tmpTargetColPossibility1));
                BoardPanel tmpBoardPanelTargetPossibility2 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempTargetRow,tmpTargetColPossibility2));

                if (tmpBoardPanelPossibility1 != null && tmpBoardPanelPossibility1.getPlayer() != null && tmpBoardPanelPossibility1.getPlayer().getPlayerId() == theOtherPlayerId(playerId ))
                {
                    if ( tmpBoardPanelTargetPossibility1 != null && (tmpBoardPanelTargetPossibility1.getPlayer() == null || tmpBoardPanelTargetPossibility1.getPlayer().getPlayerId() == 0 ))
                    {
                        return true;
                    }
                }
                else if ( tmpBoardPanelPossibility2 != null && (tmpBoardPanelPossibility2.getPlayer() != null && tmpBoardPanelPossibility2.getPlayer().getPlayerId() == theOtherPlayerId(playerId)))
                {
                    if ( tmpBoardPanelTargetPossibility2 != null && tmpBoardPanelTargetPossibility2.getPlayer() == null || tmpBoardPanelTargetPossibility2.getPlayer().getPlayerId() == 0 )
                    {
                        return true;
                    }
                }
            }
            else
            {
                int tmpOpponentColPossibility1  = panelCoordinate.getCol() + 1;
                int tmpOpponentColPossibility2  = panelCoordinate.getCol();

                BoardPanel tmpBoardPanelPossibility1 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempOpponentRow,tmpOpponentColPossibility1));
                BoardPanel tmpBoardPanelPossibility2 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempOpponentRow,tmpOpponentColPossibility2));

                BoardPanel tmpBoardPanelTargetPossibility1 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempTargetRow,tmpTargetColPossibility1));
                BoardPanel tmpBoardPanelTargetPossibility2 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempTargetRow,tmpTargetColPossibility2));

                if ( tmpBoardPanelPossibility1 != null && tmpBoardPanelPossibility1.getPlayer() != null && tmpBoardPanelPossibility1.getPlayer().getPlayerId() == 2)
                {
                    if ( tmpBoardPanelTargetPossibility1 != null && (tmpBoardPanelTargetPossibility1.getPlayer() == null || tmpBoardPanelTargetPossibility1.getPlayer().getPlayerId() == 0 ))
                    {
                        return true;
                    }
                }
                 if ( tmpBoardPanelPossibility2 != null && tmpBoardPanelPossibility2.getPlayer() != null && tmpBoardPanelPossibility2.getPlayer().getPlayerId() == 2)
                {
                    if ( tmpBoardPanelTargetPossibility2 != null && (tmpBoardPanelTargetPossibility2.getPlayer() == null || tmpBoardPanelTargetPossibility2.getPlayer().getPlayerId() == 0 ))
                    {
                        return true;
                    }
                }
            }
        }
        if ( playerId == 2 || ( playerId == 1 && isKing))
        {
            int tempOpponentRow = panelCoordinate.getRow() - 1;
            int tempTargetRow = panelCoordinate.getRow() - 2;
            int tmpTargetColPossibility1  = panelCoordinate.getCol() - 1;
            int tmpTargetColPossibility2  = panelCoordinate.getCol() + 1;


            if ( panelCoordinate.getRow() % 2 == 0 )
            {
                int tmpOpponentColPossibility1  = panelCoordinate.getCol() - 1;
                int tmpOpponentColPossibility2  = panelCoordinate.getCol();

                BoardPanel tmpBoardPanelPossibility1 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempOpponentRow,tmpOpponentColPossibility1));
                BoardPanel tmpBoardPanelPossibility2 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempOpponentRow,tmpOpponentColPossibility2));

                BoardPanel tmpBoardPanelTargetPossibility1 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempTargetRow,tmpTargetColPossibility1));
                BoardPanel tmpBoardPanelTargetPossibility2 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempTargetRow,tmpTargetColPossibility2));

                if ( tmpBoardPanelPossibility1 != null &&  tmpBoardPanelPossibility1.getPlayer() != null && tmpBoardPanelPossibility1.getPlayer().getPlayerId() == theOtherPlayerId(playerId))
                {
                    if ( tmpBoardPanelTargetPossibility1.getPlayer() == null || tmpBoardPanelTargetPossibility1.getPlayer().getPlayerId() == 0 )
                    {
                        return true;
                    }
                }
                else if ( tmpBoardPanelPossibility2 != null &&  (tmpBoardPanelPossibility2.getPlayer() != null && tmpBoardPanelPossibility2.getPlayer().getPlayerId() == theOtherPlayerId(playerId)))
                {
                    if ( tmpBoardPanelTargetPossibility2.getPlayer() == null || tmpBoardPanelTargetPossibility2.getPlayer().getPlayerId() == 0 )
                    {
                        return true;
                    }
                }
            }
            else
            {
                int tmpOpponentColPossibility1  = panelCoordinate.getCol();
                int tmpOpponentColPossibility2  = panelCoordinate.getCol() + 1;

                BoardPanel tmpBoardPanelPossibility1 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempOpponentRow,tmpOpponentColPossibility1));
                BoardPanel tmpBoardPanelPossibility2 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempOpponentRow,tmpOpponentColPossibility2));

                BoardPanel tmpBoardPanelTargetPossibility1 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempTargetRow,tmpTargetColPossibility1));
                BoardPanel tmpBoardPanelTargetPossibility2 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(tempTargetRow,tmpTargetColPossibility2));

                if ( tmpBoardPanelPossibility1 != null && tmpBoardPanelPossibility1.getPlayer() != null && tmpBoardPanelPossibility1.getPlayer().getPlayerId() == theOtherPlayerId(playerId))
                {
                    if ( tmpBoardPanelTargetPossibility1 != null && (tmpBoardPanelTargetPossibility1.getPlayer() == null || tmpBoardPanelTargetPossibility1.getPlayer().getPlayerId() == 0 ))
                    {
                        return true;
                    }
                }
                else if ( tmpBoardPanelPossibility2 != null && tmpBoardPanelPossibility2.getPlayer() != null && tmpBoardPanelPossibility2.getPlayer().getPlayerId() == theOtherPlayerId(playerId))
                {
                    if ( tmpBoardPanelTargetPossibility2 !=null && (tmpBoardPanelTargetPossibility2.getPlayer() == null || tmpBoardPanelTargetPossibility2.getPlayer().getPlayerId() == 0 ))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public int theOtherPlayerId(int playerId)
    {
        if ( playerId == 1 )
        {
            return 2;
        }
        if ( playerId == 2 )
        {
            return 1;
        }
        return 0;
    }

    public MovePayLoad getSelectedBoardPanelPlayerToMove(int playerId)
    {
        Map<BoardPanel, java.util.List<MovePayLoad>> boardPanelPlayerMap = getMovementOptionsForThePlayerPanel(playerId);
        for ( BoardPanel bp: boardPanelPlayerMap.keySet())
        {
            java.util.List<MovePayLoad> tmpMovePayLoadList = boardPanelPlayerMap.get(bp);
            for (MovePayLoad mpl: tmpMovePayLoadList)
            {
                if ( mpl.isJump())
                {
                    return mpl;
                }
            }
            for (MovePayLoad mpl: tmpMovePayLoadList)
            {
                if ( !mpl.isJump())
                {
                    return mpl;
                }
            }

        }
        return  null;
    }

    public Map<BoardPanel, java.util.List<MovePayLoad>> getMovementOptionsForThePlayerPanel(int playerId)
    {
        Map<BoardPanel, java.util.List<MovePayLoad>> movementMap = new HashMap<>();
        for ( BoardPanel boardPanel: checkerBoardUI.getBlackButtons())
        {
            java.util.List<MovePayLoad> tmpListMovements = new ArrayList<>();
            if ( boardPanel.getPlayer() != null && boardPanel.getPlayer().getPlayerId() == playerId && playerId == 1)
            {
                if ( boardPanel.getPlayer().isActive() )
                {
                    PanelCoordinate from = boardPanel.getPanelCoordinate();
                    PanelCoordinate to1  = new PanelCoordinate(boardPanel.getPanelCoordinate().getRow() + 1, boardPanel.getPanelCoordinate().getCol());
                    PanelCoordinate to2  = new PanelCoordinate(boardPanel.getPanelCoordinate().getRow() + 1, boardPanel.getPanelCoordinate().getCol() + 1);
                    PanelCoordinate to3  = new PanelCoordinate(boardPanel.getPanelCoordinate().getRow() + 1, boardPanel.getPanelCoordinate().getCol() - 1);
                    PanelCoordinate to4  = new PanelCoordinate(boardPanel.getPanelCoordinate().getRow() + 2, boardPanel.getPanelCoordinate().getCol() - 1);
                    PanelCoordinate to5  = new PanelCoordinate(boardPanel.getPanelCoordinate().getRow() + 2, boardPanel.getPanelCoordinate().getCol() + 1);

                    addToMoveList(from, to1, tmpListMovements);
                    addToMoveList(from, to2, tmpListMovements);
                    addToMoveList(from, to3, tmpListMovements);
                    addToMoveList(from, to4, tmpListMovements);
                    addToMoveList(from, to5, tmpListMovements);

                }
            }
         movementMap.put(boardPanel,tmpListMovements);
        }
        return movementMap;
    }

    public void addToMoveList(PanelCoordinate from, PanelCoordinate to, java.util.List<MovePayLoad> movePayLoadList) {
        if (to.getCol() >= 0 && to.getCol() <= 3 && to.getRow() <= 7 && to.getRow() >= 0) {

            if (from.getRow() - to.getRow() == -1) {
                if ( from.getCol() == to.getCol() || ((from.getCol() - to.getCol()) == 1 && from.getRow()%2==0) || ((from.getCol() - to.getCol()) == -1 && from.getRow()%2 != 0)) {
                    if (checkerBoardUI.getBoardPanelByPanelCoordinate(to).getPlayer() == null || checkerBoardUI.getBoardPanelByPanelCoordinate(to).getPlayer().getPlayerId() == 0) {
                        movePayLoadList.add(new MovePayLoad(from, to, false));
                    }
                }
            }

            if ( from.getRow() - to.getRow() == -2 ) {
                if (from.getCol() - to.getCol() == 1) {
                    if (from.getRow() % 2 == 0) {
                        if (checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(from.getRow() + 1, from.getCol() - 1)).getPlayer() != null &&
                                checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(from.getRow() + 1, from.getCol() - 1)).getPlayer().getPlayerId() == 2) {
                            movePayLoadList.add(new MovePayLoad(from, to, true));

                        }
                    } else {
                        if (checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(from.getRow() + 1, from.getCol())).getPlayer() != null &&
                                checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(from.getRow() + 1, from.getCol())).getPlayer().getPlayerId() == 2) {
                            movePayLoadList.add(new MovePayLoad(from, to, true));

                        }

                    }
                } else if (from.getCol() - to.getCol() == -1) {
                    if (from.getRow() % 2 == 0) {
                        if (checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(from.getRow() + 1, from.getCol())).getPlayer() != null &&
                                checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(from.getRow() + 1, from.getCol())).getPlayer().getPlayerId() == 2) {
                            movePayLoadList.add(new MovePayLoad(from, to, true));
                        }
                    } else {
                        if (checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(from.getRow() + 1, from.getCol() + 1)).getPlayer() != null &&
                                checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(from.getRow() + 1, from.getCol() + 1)).getPlayer().getPlayerId() == 2) {
                            movePayLoadList.add(new MovePayLoad(from, to, true));
                        }

                    }

                }
            }

        }
    }


    public boolean isJumped() {
        return jumped;
    }

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }
}
