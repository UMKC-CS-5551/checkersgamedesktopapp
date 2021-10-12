package edu.umkc.group11.screen;

import static org.junit.Assert.assertTrue;

import edu.umkc.group11.client.PlayCheckersGame;
import edu.umkc.group11.screen.CheckerBoardUI;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
        JFrame jFrame = new JFrame();
        CheckerBoardUI checkerBoardUI= new CheckerBoardUI();
        jFrame.add(checkerBoardUI);
        jFrame.pack();
        assertTrue(jFrame.getComponents()[0].getPreferredSize().equals(new Dimension(900,900)));
    }

    @Test
    public void exitOptionTest()
    {
        PlayCheckersGame tmpPlayCheckersGame=  new PlayCheckersGame("Checkers Board Game");
        assertTrue(tmpPlayCheckersGame.getDefaultCloseOperation() == JFrame.EXIT_ON_CLOSE);
    }

    @Test
    public void checkerBoardUIPresentTest()
    {
        PlayCheckersGame tmpPlayCheckersGame=  new PlayCheckersGame("Checkers Board Game");
        Assert.assertEquals(tmpPlayCheckersGame.getContentPane().getComponents()[0].getClass().getName(), "edu.umkc.group11.screen.CheckerBoardUI");
    }
}
