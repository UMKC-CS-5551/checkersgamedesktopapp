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
    public void buttonClickTest()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        checkerBoardUI.getJRadioButtonPlayer1().doClick();
        checkerBoardUI.getBlackButtons()[9].getButton().doClick();
        assertTrue(checkerBoardUI.getBlackButtons()[9].isActivated());
        assertTrue(checkerBoardUI.getBlackButtons()[9].getButton().getBackground().equals(Color.CYAN));
        checkerBoardUI.getBlackButtons()[13].getButton().doClick();
        assertTrue(checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(3,1)).getPlayer().getPlayerId() == 1);
        assertFalse(checkerBoardUI.getJRadioButtonPlayer1().isEnabled());
        assertTrue(checkerBoardUI.getJRadioButtonPlayer2().isSelected());

    }

    @Test
    public void buttonClickTest2()
    {
        String playersNames = "PLayer1,Player2";
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI(playersNames);
        checkerBoardUI.getJRadioButtonPlayer2().doClick();
        checkerBoardUI.getBlackButtons()[21].getButton().doClick();
        assertTrue(checkerBoardUI.getBlackButtons()[21].isActivated());
        assertTrue(checkerBoardUI.getBlackButtons()[21].getButton().getBackground().equals(Color.MAGENTA));
        checkerBoardUI.getBlackButtons()[17].getButton().doClick();
        assertTrue(checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(4,1)).getPlayer().getPlayerId() == 2);
        assertFalse(checkerBoardUI.getJRadioButtonPlayer2().isEnabled());
        assertTrue(checkerBoardUI.getJRadioButtonPlayer1().isSelected());
    }

}
