package edu.umkc.group11;

import edu.umkc.group11.client.PlayCheckersGame;
import edu.umkc.group11.model.BoardPanel;
import edu.umkc.group11.model.MovementHelper;
import edu.umkc.group11.model.PanelCoordinate;
import edu.umkc.group11.model.Player;
import edu.umkc.group11.screen.CheckerBoardUI;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

/**
 * Unit tests.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void checkerBoardUITestHappy()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        assertNotNull(playersNames);
        assertTrue(checkerBoardUI.getBlackButtons().length == 32);
        assertTrue(checkerBoardUI.getWhiteButtons().length == 32);
        assertTrue(checkerBoardUI.getBlackButtons()[1].getBackground().equals(Color.GREEN));
        assertTrue(checkerBoardUI.getWhiteButtons()[1].getBackground().equals(Color.WHITE));
        assertTrue(checkerBoardUI.getPanel()  != null );

    }

    @Test
    public void playersNameTest()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        assertTrue(checkerBoardUI.getPlayerOne().getName().equals("PLayer1"));
        assertTrue(checkerBoardUI.getPlayerTwo().getName().equals("Player2"));
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
    public void buttonClickTest()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        checkerBoardUI.getJRadioButtonPlayer1().doClick();
        checkerBoardUI.getBlackButtons()[9].getButton().doClick();
        assertTrue(checkerBoardUI.getBlackButtons()[9].isActivated());
        assertTrue(checkerBoardUI.getBlackButtons()[9].getButton().getBackground().equals(Color.CYAN));
    }

    @Test
    public void diagonalMovementAllowedHappyTestPlayerOne()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        MovementHelper movementHelper = new MovementHelper(checkerBoardUI);
        boolean result = movementHelper.isDiagonalMomentAllowed(new PanelCoordinate(4,1), new PanelCoordinate(5,1), 1, false);
        assertTrue(result);
    }

    @Test
    public void diagonalMovementNotAllowedTestPlayerOne()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        MovementHelper movementHelper = new MovementHelper(checkerBoardUI);
        boolean result = movementHelper.isDiagonalMomentAllowed(new PanelCoordinate(4,1), new PanelCoordinate(3,0), 1, false);
        assertFalse(result);
    }
    @Test
    public void diagonalMovementNotAllowedTestPlayerOneSameRow()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        MovementHelper movementHelper = new MovementHelper(checkerBoardUI);
        boolean result = movementHelper.isDiagonalMomentAllowed(new PanelCoordinate(4,1), new PanelCoordinate(4,0), 1, false);
        assertFalse(result);
    }
    @Test
    public void diagonalMovementAllowedHappyTestPlayerTwo()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        MovementHelper movementHelper = new MovementHelper(checkerBoardUI);
        boolean result = movementHelper.isDiagonalMomentAllowed(new PanelCoordinate(4,1), new PanelCoordinate(3,0), 2, false);
        assertTrue(result);
    }

    @Test
    public void diagonalMovementNotAllowedTestPlayerTwoSameRow()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        MovementHelper movementHelper = new MovementHelper(checkerBoardUI);
        boolean result = movementHelper.isDiagonalMomentAllowed(new PanelCoordinate(4,1), new PanelCoordinate(4,0), 2, false);
        assertFalse(result);
    }

    @Test
    public void diagonalMovementNotAllowedTestPlayerTwo()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        MovementHelper movementHelper = new MovementHelper(checkerBoardUI);
        boolean result = movementHelper.isDiagonalMomentAllowed(new PanelCoordinate(4,1), new PanelCoordinate(5,1), 2, false);
        assertFalse(result);
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
    public void notAllowedJumpTestPlayerOne()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,1)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(4,2)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(5,2)).setPlayer(null);
        MovementHelper movementHelper = new MovementHelper(checkerBoardUI);

        boolean result = movementHelper.isDiagonalMomentAllowed(new PanelCoordinate(3,1), new PanelCoordinate(5,2),1,false);
        assertFalse(result);
    }

    @Test
    public void happyJumpTestPlayerTwo()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,1)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,1)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(1,0)).setPlayer(null);
        MovementHelper movementHelper = new MovementHelper(checkerBoardUI);

        boolean result = movementHelper.isDiagonalMomentAllowed(new PanelCoordinate(3,1), new PanelCoordinate(1,0),2,false);
        assertTrue(result);
    }

    @Test
    public void happyJumpTestPlayerTwoKing()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,1)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,1)).setPlayer(new Player(1, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(1,0)).setPlayer(null);
        MovementHelper movementHelper = new MovementHelper(checkerBoardUI);

        boolean result = movementHelper.isDiagonalMomentAllowed(new PanelCoordinate(3,1), new PanelCoordinate(1,0),2,true);
        assertTrue(result);
    }

    @Test
    public void notAllowedJumpTestPlayerTwo()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,1)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,1)).setPlayer(new Player(2, true));
        checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(1,0)).setPlayer(null);
        MovementHelper movementHelper = new MovementHelper(checkerBoardUI);

        boolean result = movementHelper.isDiagonalMomentAllowed(new PanelCoordinate(3,1), new PanelCoordinate(5,2),1,false);
        assertFalse(result);
    }

}
