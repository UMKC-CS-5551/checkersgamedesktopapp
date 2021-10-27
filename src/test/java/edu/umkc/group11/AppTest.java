package edu.umkc.group11;

import edu.umkc.group11.client.PlayCheckersGame;
import edu.umkc.group11.screen.CheckerBoardUI;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

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
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI();
        assertTrue(checkerBoardUI.getBlackButtons().length == 32);
        assertTrue(checkerBoardUI.getWhiteButtons().length == 32);
        assertTrue(checkerBoardUI.getBlackButtons()[1].getBackground().equals(Color.GREEN));
        assertTrue(checkerBoardUI.getWhiteButtons()[1].getBackground().equals(Color.WHITE));
        assertTrue(checkerBoardUI.getPanel()  != null );
    }

    @Test
    public void exitOptionTest()
    {
        PlayCheckersGame tmpPlayCheckersGame=  new PlayCheckersGame("Checkers Board Game");
        assertTrue(tmpPlayCheckersGame.getDefaultCloseOperation() == JFrame.EXIT_ON_CLOSE);
    }

}
