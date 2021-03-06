package edu.umkc.group11;

import edu.umkc.group11.model.*;
import edu.umkc.group11.screen.CheckerBoardUI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class MovementHelperTest {

    String playersNames = "PLayer1,Player2";
    CheckerBoardUI checkerBoardUI = new CheckerBoardUI(playersNames);
    MovementHelper movementHelper = new MovementHelper(checkerBoardUI);


    /**
     * Test if a new movement trajectory map is created.
     */
    @Test
    public void getMovementTrajectoryTest1()
    {
        movementHelper.setMovementTrajectory(null);
        Map<String, BoardPanel> res = movementHelper.getMovementTrajectory();
        assertEquals(res,new HashMap<>());
    }

    /**
     * Test if the same movement Trajectory map is returned when the method is called
     */
    @Test
    public void getMovementTrajectoryTest2()
    {
        Map<String, BoardPanel> movementTrajectory  = new HashMap<>();
        PanelCoordinate pc1 = new PanelCoordinate(1,2);
        PanelCoordinate pc2 = new PanelCoordinate(3,2);
        BoardPanel boardPanelPosition1 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc1);
        BoardPanel boardPanelPosition2 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc2);

        movementTrajectory.put("START",boardPanelPosition1);
        movementTrajectory.put("TARGET",boardPanelPosition2);
        movementHelper.setMovementTrajectory(movementTrajectory);
        Map<String, BoardPanel> res2 = movementHelper.getMovementTrajectory();
        assertEquals(res2,movementTrajectory);
    }


    /**
     * Check if the method sets all the parameters on the BoardPanel required to make a piece disappear.
     */
    @Test
    public void makeOpponentPieceToDisappearTest1()
    {
        BoardPanel opponentBoardPanel = new BoardPanel(checkerBoardUI,1);
        Player player = new Player(1,"Name");
        opponentBoardPanel.setPlayer(player);
        movementHelper.makeOpponentPieceToDisappear(opponentBoardPanel);
        assertEquals( Color.WHITE,opponentBoardPanel.getButton().getBackground());
        assertEquals(0,opponentBoardPanel.getPlayer().getPlayerId());
        assertEquals(false,opponentBoardPanel.getPlayer().isActive());
        assertEquals(false,opponentBoardPanel.getPlayer().isKing());

    }


    /**
     * Convert a piece of player1 to king, checking it's boolean value
     */
    @Test
    public void convertToKingTest1()
    {
        BoardPanel bp = new BoardPanel(checkerBoardUI,1);
        Player player = new Player(1,"Name");
        bp.setPlayer(player);
        PanelCoordinate pc1 = new PanelCoordinate(7,2);
        movementHelper.convertToKing(bp,pc1);

        assertEquals(true,bp.getPlayer().isKing());

    }


    /**
     * Convert a piece of player2 to king, checking it's boolean value
     */

    @Test
    public void convertToKingTest2()
    {
        BoardPanel bp = new BoardPanel(checkerBoardUI,1);
        Player player = new Player(2,"Name");
        bp.setPlayer(player);
        PanelCoordinate pc1 = new PanelCoordinate(0,2);
        movementHelper.convertToKing(bp,pc1);
        assertEquals(true,bp.getPlayer().isKing());
    }


    /**
     * Check to make sure that king is a piece which is in row 7 or 0
     */
    @Test
    public void convertToKingTest3()
    {
        BoardPanel bp = new BoardPanel(checkerBoardUI,1);
        Player player = new Player(2,"Name");
        bp.setPlayer(player);
        PanelCoordinate pc1 = new PanelCoordinate(3,2);
        movementHelper.convertToKing(bp,pc1);
        assertEquals(false,bp.getPlayer().isKing());
    }

    /**
     * Set up method
     */
    public void setUpForswapPanelOccupantsTest()
    {
        Map<String, BoardPanel> movementTrajectory  = new HashMap<>();
        PanelCoordinate pc1 = new PanelCoordinate(2,2);
        PanelCoordinate pc2 = new PanelCoordinate(3,2);
        BoardPanel boardPanelPosition1 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc1);
        BoardPanel boardPanelPosition2 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc2);
        movementTrajectory.put("START",boardPanelPosition1);
        movementTrajectory.put("TARGET",boardPanelPosition2);
        movementHelper.setMovementTrajectory(movementTrajectory);

    }

    /**
     *  Swapping panel occupents. Confirming by checking the panel's color after the swap for playerID = 1
     */
    @Test
    public void swapPanelOccupantsTest1()
    {
        setUpForswapPanelOccupantsTest();
        Player player = new Player(1,"Name");
        movementHelper.getMovementTrajectory().get("START").setPlayer(player);
        movementHelper.swapPanelOccupants();
        Color res1 = movementHelper.getMovementTrajectory().get("TARGET").getButton().getBackground();
        assertEquals(PlayerColor.PLAYER_ONE.getColor(),res1);

        Color res2 = movementHelper.getMovementTrajectory().get("START").getButton().getBackground();
        assertEquals(PlayerColor.BLANK.getColor(),res2);

    }


    /**
     *  Swapping panel occupents. Confirming by checking the panel's color after the swap for playerID = 2
     */
    @Test
    public void swapPanelOccupantsTest2()
    {
        Map<String, BoardPanel> movementTrajectory  = new HashMap<>();
        PanelCoordinate pc1 = new PanelCoordinate(3,2);
        PanelCoordinate pc2 = new PanelCoordinate(2,2);
        BoardPanel boardPanelPosition1 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc1);
        BoardPanel boardPanelPosition2 = this.checkerBoardUI.getBoardPanelByPanelCoordinate(pc2);
        movementTrajectory.put("START",boardPanelPosition1);
        movementTrajectory.put("TARGET",boardPanelPosition2);
        movementHelper.setMovementTrajectory(movementTrajectory);
        Player player = new Player(2,"Name");
        movementHelper.getMovementTrajectory().get("START").setPlayer(player);
        movementHelper.swapPanelOccupants();
        Color res1 = movementHelper.getMovementTrajectory().get("TARGET").getButton().getBackground();
        assertEquals(PlayerColor.PLAYER_TWO.getColor(),res1);

        Color res2 = movementHelper.getMovementTrajectory().get("START").getButton().getBackground();
        assertEquals(PlayerColor.BLANK.getColor(),res2);

    }

    /**
     * With in-valid movement co-ordinates, the player is the same player (since the move would not have got completed)
     */
    @Test
    public void swapPanelOccupantsTest3()
    {
        setUpForswapPanelOccupantsTest();
        Player player = new Player(2,"Name");
        movementHelper.getMovementTrajectory().get("START").setPlayer(player);
        movementHelper.swapPanelOccupants();
        int res = movementHelper.getMovementTrajectory().get("START").getPlayer().getPlayerId();
        assertEquals(2,res);
    }

    /**
     * With valid movement co-ordinates, the player is the other player (since the move would have got completed)
     */
    @Test
    public void swapPanelOccupantsTest4()
    {
        setUpForswapPanelOccupantsTest();
        Player player = new Player(1,"Name");
        Player player2 = new Player(2,"Name");
        movementHelper.getMovementTrajectory().get("START").setPlayer(player);
        movementHelper.getMovementTrajectory().get("TARGET").setPlayer(player2);
        movementHelper.swapPanelOccupants();
        int res = movementHelper.getMovementTrajectory().get("START").getPlayer().getPlayerId();
        assertEquals(2,res);
    }


    /**
     * When rows of from and to movement co-ordinates are same
     */
    @Test
    public void isDiagonalMomentAllowedTest1()
    {
        PanelCoordinate checkerFrom = new PanelCoordinate(5,1);
        PanelCoordinate checkerTo = new PanelCoordinate(5,1);
        boolean res = movementHelper.isDiagonalMomentAllowed(checkerFrom,checkerTo,1,false);
        assertEquals(false,res);
    }

    /**
     * For playerid = 1 when row and column are of a difference 1
     */
    @Test
    public void isDiagonalMomentAllowedTest2()
    {
        PanelCoordinate checkerFrom = new PanelCoordinate(3,2);
        PanelCoordinate checkerTo = new PanelCoordinate(4,2);
        boolean res = movementHelper.isDiagonalMomentAllowed(checkerFrom,checkerTo,1,false);
        assertEquals(true,res);
    }

    /**
     * For playerid = 1 when row and column of from co-ordinate is -2 and row co-ordinate is odd and it is not a king
     */
    @Test
    public void isDiagonalMomentAllowedTest3()
    {
        PanelCoordinate checkerFrom = new PanelCoordinate(3,2);
        Player player = new Player(2,"Name");
        PanelCoordinate checkerTo = new PanelCoordinate(5,1);
        PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol());
        checkerBoardUI.getBoardPanelByPanelCoordinate(pc1).setPlayer(player);

        boolean res = movementHelper.isDiagonalMomentAllowed(checkerFrom,checkerTo,1,false);
        assertEquals(true,res);
    }

    /**
     * For playerid = 1 when row and column of from co-ordinate is -2 and row co-ordinate is even and it is not a king
     */
    @Test
    public void isDiagonalMomentAllowedTest4()
    {
        PanelCoordinate checkerFrom = new PanelCoordinate(4,2);
        Player player = new Player(2,"Name");
        PanelCoordinate checkerTo = new PanelCoordinate(6,1);
        PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol());
        checkerBoardUI.getBoardPanelByPanelCoordinate(pc1).setPlayer(player);

        boolean res = movementHelper.isDiagonalMomentAllowed(checkerFrom,checkerTo,1,false);
        assertEquals(true,res);
    }

    /**
     * For playerid = 1 when row and column of from co-ordinate is -2 and row co-ordinate is even and it is a king
     */
    @Test
    public void isDiagonalMomentAllowedTest5()
    {
        PanelCoordinate checkerFrom = new PanelCoordinate(4,2);
        Player player = new Player(2,"Name");
        PanelCoordinate checkerTo = new PanelCoordinate(6,1);
        PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol());
        checkerBoardUI.getBoardPanelByPanelCoordinate(pc1).setPlayer(player);

        boolean res = movementHelper.isDiagonalMomentAllowed(checkerFrom,checkerTo,1,true);
        assertEquals(true,res);
    }


    /**
     * For playerid = 1 when row and column of from co-ordinate is -2 and row co-ordinate is odd and it is a king
     */
    @Test
    public void isDiagonalMomentAllowedTest6()
    {
        PanelCoordinate checkerFrom = new PanelCoordinate(3,2);
        Player player = new Player(2,"Name");
        PanelCoordinate checkerTo = new PanelCoordinate(5,1);
        PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol());
        checkerBoardUI.getBoardPanelByPanelCoordinate(pc1).setPlayer(player);

        boolean res = movementHelper.isDiagonalMomentAllowed(checkerFrom,checkerTo,1,true);
        assertEquals(true,res);
    }




    /**
     * When rows of from and to movement co-ordinates are same and player is 2
     */
    @Test
    public void isDiagonalMomentAllowedTest7()
    {
        PanelCoordinate checkerFrom = new PanelCoordinate(5,1);
        PanelCoordinate checkerTo = new PanelCoordinate(5,1);
        boolean res = movementHelper.isDiagonalMomentAllowed(checkerFrom,checkerTo,2,false);
        assertEquals(false,res);
    }

    /**
     * For playerid = 2 when row and column are of a difference 1
     */
    @Test
    public void isDiagonalMomentAllowedTest8()
    {
        PanelCoordinate checkerFrom = new PanelCoordinate(4,2);
        PanelCoordinate checkerTo = new PanelCoordinate(3,2);
        boolean res = movementHelper.isDiagonalMomentAllowed(checkerFrom,checkerTo,2,false);
        assertEquals(true,res);
    }


    /**
     * For playerid = 2 when row and column of from co-ordinate is +2 and row co-ordinate is odd and it is not a king
     */
    @Test
    public void isDiagonalMomentAllowedTest9()
    {
        PanelCoordinate checkerFrom = new PanelCoordinate(5,2);
        Player player = new Player(1,"Name");
        PanelCoordinate checkerTo = new PanelCoordinate(3,1);
        PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()-1, checkerFrom.getCol()+1);
        checkerBoardUI.getBoardPanelByPanelCoordinate(pc1).setPlayer(player);
        boolean res = movementHelper.isDiagonalMomentAllowed(checkerFrom,checkerTo,2,false);
        assertEquals(true,res);
    }


    /**
     * For playerid = 2 when row and column of from co-ordinate is +2 and row co-ordinate is even and it is not a king
     */
    @Test
    public void isDiagonalMomentAllowedTest10()
    {
        PanelCoordinate checkerFrom = new PanelCoordinate(6,2);
        Player player = new Player(1,"Name");
        PanelCoordinate checkerTo = new PanelCoordinate(4,1);
        PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()-1, checkerFrom.getCol());
        checkerBoardUI.getBoardPanelByPanelCoordinate(pc1).setPlayer(player);
        boolean res = movementHelper.isDiagonalMomentAllowed(checkerFrom,checkerTo,2,false);
        assertEquals(true,res);
    }


    /**
     * For playerid = 2 when row and column of from co-ordinate is +2 and row co-ordinate is even and it is a king
     */
    @Test
    public void isDiagonalMomentAllowedTest11()
    {
        PanelCoordinate checkerFrom = new PanelCoordinate(6,2);
        Player player = new Player(1,"Name");
        PanelCoordinate checkerTo = new PanelCoordinate(4,1);
        PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()-1, checkerFrom.getCol());
        checkerBoardUI.getBoardPanelByPanelCoordinate(pc1).setPlayer(player);
        boolean res = movementHelper.isDiagonalMomentAllowed(checkerFrom,checkerTo,2,true);
        assertEquals(true,res);
    }


    /**
     * For playerid = 2 when row and column of from co-ordinate is +2 and row co-ordinate is odd and it is a king
     */
    @Test
    public void isDiagonalMomentAllowedTest12()
    {
        PanelCoordinate checkerFrom = new PanelCoordinate(3,2);
        Player player = new Player(1,"Name");
        PanelCoordinate checkerTo = new PanelCoordinate(5,1);
        PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol());
        checkerBoardUI.getBoardPanelByPanelCoordinate(pc1).setPlayer(player);

        boolean res = movementHelper.isDiagonalMomentAllowed(checkerFrom,checkerTo,2,true);
        assertEquals(true,res);
    }



    /**
     * Illegal movements such as sidewise
     */
    @Test
    public void isDiagonalMomentAllowedTest13()
    {
        PanelCoordinate checkerFrom = new PanelCoordinate(3,2);
        Player player = new Player(1,"Name");
        PanelCoordinate checkerTo = new PanelCoordinate(3,1);
        PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol());
        checkerBoardUI.getBoardPanelByPanelCoordinate(pc1).setPlayer(player);

        boolean res = movementHelper.isDiagonalMomentAllowed(checkerFrom,checkerTo,2,true);
        assertEquals(false,res);
    }

    /**
     * Illegal movements such as top
     */
    @Test
    public void isDiagonalMomentAllowedTest14()
    {
        PanelCoordinate checkerFrom = new PanelCoordinate(3,2);
        Player player = new Player(1,"Name");
        PanelCoordinate checkerTo = new PanelCoordinate(5,2);
        PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol());
        checkerBoardUI.getBoardPanelByPanelCoordinate(pc1).setPlayer(player);

        boolean res = movementHelper.isDiagonalMomentAllowed(checkerFrom,checkerTo,2,false);
        assertEquals(false,res);
    }


    /**
     * Illegal movements such as down
     */
    @Test
    public void isDiagonalMomentAllowedTest15()
    {
        PanelCoordinate checkerFrom = new PanelCoordinate(1,2);
        Player player = new Player(1,"Name");
        PanelCoordinate checkerTo = new PanelCoordinate(3,2);
        PanelCoordinate pc1 = new PanelCoordinate(checkerFrom.getRow()+1, checkerFrom.getCol());
        checkerBoardUI.getBoardPanelByPanelCoordinate(pc1).setPlayer(player);

        boolean res = movementHelper.isDiagonalMomentAllowed(checkerFrom,checkerTo,2,false);
        assertEquals(false,res);
    }

    @Test
    public void happyJumpTestPlayerOneKing()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,1)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(4,2)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(5,2)).setPlayer(null);
        MovementHelper movementHelper = new MovementHelper(checkerBoardUI);

        boolean result = movementHelper.isDiagonalMomentAllowed(new PanelCoordinate(3,1), new PanelCoordinate(5,2),1,true);
        assertTrue(result);
    }

    @Test
    public void happyJumpTestPlayerOneKingReverse()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,1)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,1)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(1,0)).setPlayer(null);
        MovementHelper movementHelper = new MovementHelper(checkerBoardUI);

        boolean result = movementHelper.isDiagonalMomentAllowed(new PanelCoordinate(3,1), new PanelCoordinate(1,0),1,true);
        assertTrue(result);
    }
    @Test
    public void happyJumpTestPlayerTwoKingReverse()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,1)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(4,2)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(5,2)).setPlayer(null);
        MovementHelper movementHelper = new MovementHelper(checkerBoardUI);

        boolean result = movementHelper.isDiagonalMomentAllowed(new PanelCoordinate(3,1), new PanelCoordinate(5,2),2,true);
        assertTrue(result);
    }

    @Test
    public void kingConversionTest()
    {
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI("name1,name2");
        BoardPanel boardPanel = new BoardPanel(checkerBoardUI, 10);
        boardPanel.setPlayer(new Player(1, true));
        checkerBoardUI.getMovementHelper().convertToKing(boardPanel,new PanelCoordinate(7,1));
        assertEquals(true, boardPanel.getPlayer().isKing());
    }



    @Test
    public void happyJumpTestPlayerOne()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,1)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(4,2)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(5,2)).setPlayer(null);
        MovementHelper movementHelper = new MovementHelper(checkerBoardUI);

        boolean result = movementHelper.isDiagonalMomentAllowed(new PanelCoordinate(3,1), new PanelCoordinate(5,2),1,false);
        assertTrue(result);
    }
    @Test
    public void notAllowedToJumpTestPlayerOne()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,1)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(4,2)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(5,2)).setPlayer(null);
        MovementHelper movementHelper = new MovementHelper(checkerBoardUI);

        boolean result = movementHelper.isDiagonalMomentAllowed(new PanelCoordinate(3,1), new PanelCoordinate(6,3),1,false);
        assertFalse(result);
    }

    @Test
    public void canNotJumpMoreTestHappyPlayerOneBecauseThereWasNoJump()
    {
        CheckerBoardUI checkerBoardUI = new CheckerBoardUI("pl1,pl2");
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,2)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,2)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(4,3)).setPlayer(null);

        assertFalse(checkerBoardUI.getMovementHelper().canJumpMore(new PanelCoordinate(2,2),1,false,false));

    }
    @Test
    public void canJumpMoreTestHappyPlayerOne()
    {
        CheckerBoardUI checkerBoardUI = new CheckerBoardUI("pl1,pl2");
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,2)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,2)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(4,3)).setPlayer(null);

        assertTrue(checkerBoardUI.getMovementHelper().canJumpMore(new PanelCoordinate(2,2),1,false,true));

    }
    @Test
    public void canJumpMoreTestHappyPlayerOneKing()
    {
        CheckerBoardUI checkerBoardUI = new CheckerBoardUI("pl1,pl2");
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,2)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,2)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(4,3)).setPlayer(null);

        assertTrue(checkerBoardUI.getMovementHelper().canJumpMore(new PanelCoordinate(2,2),1,true,true));
    }
    @Test
    public void canNotJumpMoreTestHappyPlayerOne()
    {
        CheckerBoardUI checkerBoardUI = new CheckerBoardUI("pl1,pl2");
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,2)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,2)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(4,3)).setPlayer(new Player(2, true));

        assertFalse(checkerBoardUI.getMovementHelper().canJumpMore(new PanelCoordinate(2,2),1,false,true));
    }
    @Test
    public void canJumpMoreTestHappyPlayerTwo()
    {
        CheckerBoardUI checkerBoardUI = new CheckerBoardUI("pl1,pl2");
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,2)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(1,1)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(0,1)).setPlayer(null);

        assertTrue(checkerBoardUI.getMovementHelper().canJumpMore(new PanelCoordinate(2,2),2,false,true));

    }
    @Test
    public void canJumpMoreTestHappyPlayerOneKingReverse()
    {
        CheckerBoardUI checkerBoardUI = new CheckerBoardUI("pl1,pl2");
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,2)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(1,1)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(0,1)).setPlayer(null);

        assertTrue(checkerBoardUI.getMovementHelper().canJumpMore(new PanelCoordinate(2,2),2,true,true));

    }
    @Test
    public void canNotJumpMoreTestHappyPlayerTwo()
    {
        CheckerBoardUI checkerBoardUI = new CheckerBoardUI("pl1,pl2");
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,2)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(1,1)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(0,1)).setPlayer(new Player(2, true));

        assertFalse(checkerBoardUI.getMovementHelper().canJumpMore(new PanelCoordinate(2,2),2,false,true));

    }
    @Test
    public void canJumpMoreTestHappyPlayerOneAlternatePath()
    {
        CheckerBoardUI checkerBoardUI = new CheckerBoardUI("pl1,pl2");
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,2)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,1)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(4,1)).setPlayer(null);

        assertTrue(checkerBoardUI.getMovementHelper().canJumpMore(new PanelCoordinate(2,2),1,false,true));

    }
    @Test
    public void canNotJumpMoreTestHappyPlayerOneAlternatePath()
    {
        CheckerBoardUI checkerBoardUI = new CheckerBoardUI("pl1,pl2");
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,2)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,1)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(4,1)).setPlayer(new Player(2, true));

        assertFalse(checkerBoardUI.getMovementHelper().canJumpMore(new PanelCoordinate(2,2),1,false,true));

    }
    @Test
    public void canJumpMoreTestHappyPlayerTwoAlternatePath()
    {
        CheckerBoardUI checkerBoardUI = new CheckerBoardUI("pl1,pl2");
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(5,2)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(4,3)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,3)).setPlayer(null);

        assertTrue(checkerBoardUI.getMovementHelper().canJumpMore(new PanelCoordinate(5,2),2,false,true));

    }
    @Test
    public void canNotJumpMoreTestHappyPlayerTwoAlternatePath()
    {
        CheckerBoardUI checkerBoardUI = new CheckerBoardUI("pl1,pl2");
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(5,2)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(4,3)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,3)).setPlayer(new Player(1, true));

        assertFalse(checkerBoardUI.getMovementHelper().canJumpMore(new PanelCoordinate(2,2),2,false,true));

    }


    /**
     * Check if the other player number is 2 when the input player ID is 1.
     */
    @Test
    public void theOtherPlayerIdTestCase1()
    {
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI("name1,name2");
        BoardPanel boardPanel = new BoardPanel(checkerBoardUI, 10);
        int res = checkerBoardUI.getMovementHelper().theOtherPlayerId(1);
        assertEquals(2, res);
    }

    /**
     * Check if the other player number is 1 when the input player ID is 2.
     */
    @Test
    public void theOtherPlayerIdTestCase2()
    {
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI("name1,name2");
        BoardPanel boardPanel = new BoardPanel(checkerBoardUI, 10);
        int res = checkerBoardUI.getMovementHelper().theOtherPlayerId(2);
        assertEquals(1, res);
    }

    /**
     * Check if the other player number is 0 when the input player ID is invalid.
     */
    @Test
    public void theOtherPlayerIdTestCase3()
    {
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI("name1,name2");
        BoardPanel boardPanel = new BoardPanel(checkerBoardUI, 10);
        int res = checkerBoardUI.getMovementHelper().theOtherPlayerId(4);
        assertEquals(0, res);
    }

    /**
     * Check if the computer player's move is valid.
     */
    @Test
    public void getSelectedBoardPanelPlayerToMoveTestCase1()
    {
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI("name1,name2");
        BoardPanel boardPanel = new BoardPanel(checkerBoardUI, 10);
        boardPanel.setPlayer(new Player(1, true));
        MovePayLoad res = checkerBoardUI.getMovementHelper().getSelectedBoardPanelPlayerToMove(1);

        PanelCoordinate pc1 = new PanelCoordinate(2,1);
        PanelCoordinate pc2 = new PanelCoordinate(3,1);
        MovePayLoad obj = new MovePayLoad(pc1,pc2,false);

        assertEquals(obj.isJump(), res.isJump());
        assertEquals(obj.getFrom().getRow(), res.getFrom().getRow());
        assertEquals(obj.getTo().getRow(), res.getTo().getRow());

    }

    /**
     * Check if the computer player's move is valid.
     */
    @Test
    public void getSelectedBoardPanelPlayerToMoveTestCase2()
    {
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI("name1,name2");
        BoardPanel boardPanel = new BoardPanel(checkerBoardUI, 10);
        boardPanel.setPlayer(new Player(2, true));
        MovePayLoad res = checkerBoardUI.getMovementHelper().getSelectedBoardPanelPlayerToMove(1);

        PanelCoordinate pc1 = new PanelCoordinate(2,1);
        PanelCoordinate pc2 = new PanelCoordinate(3,1);
        MovePayLoad obj = new MovePayLoad(pc1,pc2,false);

        assertEquals(obj.isJump(), res.isJump());
        assertEquals(obj.getFrom().getRow(), res.getFrom().getRow());
        assertEquals(obj.getTo().getRow(), res.getTo().getRow());

    }

    /**
     * Check if the computer player's move is nothing for an invalid player.
     */
    @Test
    public void getSelectedBoardPanelPlayerToMoveTestCase3()
    {
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI("name1,name2");
        BoardPanel boardPanel = new BoardPanel(checkerBoardUI, 10);
        boardPanel.setPlayer(new Player(2, true));
        MovePayLoad res = checkerBoardUI.getMovementHelper().getSelectedBoardPanelPlayerToMove(11);
        assertEquals(null,res);

    }


    /**
     * Check if the computer player is considering all the moves and forming a map out of them for player 1.
     */
    @Test
    public void getMovementOptionsForThePlayerPanelTestCase1()
    {
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI("name1,name2");
        BoardPanel boardPanel = new BoardPanel(checkerBoardUI, 10);
        boardPanel.setPlayer(new Player(1, true));
        Map<BoardPanel, java.util.List<MovePayLoad>> res = checkerBoardUI.getMovementHelper().getMovementOptionsForThePlayerPanel(1);
        assertEquals(32,res.size());

    }

    /**
     * Check if the computer player is considering all the moves and forming a map out of them for player 2.
     */
    @Test
    public void getMovementOptionsForThePlayerPanelTestCase2()
    {
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI("name1,name2");
        BoardPanel boardPanel = new BoardPanel(checkerBoardUI, 10);
        boardPanel.setPlayer(new Player(2, true));
        Map<BoardPanel, java.util.List<MovePayLoad>> res = checkerBoardUI.getMovementHelper().getMovementOptionsForThePlayerPanel(2);
        assertEquals(32,res.size());

    }


    /**
     * Check if the moves are added to the list which are supposed to take place without a jump for player 2.
     */
    @Test
    public void addToMoveListTestCase1()
    {
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI("name1,name2");
        BoardPanel boardPanel = new BoardPanel(checkerBoardUI, 10);
        boardPanel.setPlayer(new Player(2, true));
        PanelCoordinate pc1 = new PanelCoordinate(2,1);
        PanelCoordinate pc2 = new PanelCoordinate(3,1);
        java.util.List<MovePayLoad> movePayLoadList = new ArrayList<>();
       checkerBoardUI.getMovementHelper().addToMoveList(pc1,pc2,movePayLoadList);

        MovePayLoad actual =  new MovePayLoad(pc1, pc2, false);
        assertEquals(actual.isJump(),movePayLoadList.get(0).isJump());
        assertEquals(actual.getTo(),movePayLoadList.get(0).getTo());
        assertEquals(actual.getFrom(),movePayLoadList.get(0).getFrom());
    }


    /**
     * Check if the moves are added to the list which are supposed to take place without a jump for player 1.
     */
    @Test
    public void addToMoveListTestCase2()
    {
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI("name1,name2");
        BoardPanel boardPanel = new BoardPanel(checkerBoardUI, 10);
        boardPanel.setPlayer(new Player(1, true));
        PanelCoordinate pc1 = new PanelCoordinate(2,1);
        PanelCoordinate pc2 = new PanelCoordinate(3,1);
        java.util.List<MovePayLoad> movePayLoadList = new ArrayList<>();
        checkerBoardUI.getMovementHelper().addToMoveList(pc1,pc2,movePayLoadList);

        MovePayLoad actual =  new MovePayLoad(pc1, pc2, false);
        assertEquals(actual.isJump(),movePayLoadList.get(0).isJump());
        assertEquals(actual.getTo(),movePayLoadList.get(0).getTo());
        assertEquals(actual.getFrom(),movePayLoadList.get(0).getFrom());
    }









}
