package edu.umkc.group11;

import edu.umkc.group11.client.PlayCheckersGame;
import edu.umkc.group11.screen.CheckerBoardUI;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

    }

}
