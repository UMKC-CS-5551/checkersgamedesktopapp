package edu.umkc.group11;

import edu.umkc.group11.model.BoardPanel;
import edu.umkc.group11.model.Player;
import edu.umkc.group11.screen.CheckerBoardUI;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class BoardPanelTest {

    String playersNames = "PLayer1,Player2";
    CheckerBoardUI checkerBoardUI = new CheckerBoardUI(playersNames);
    BoardPanel boardPanel = new BoardPanel(checkerBoardUI,1);
    /**
     * Checking if all the attributes are set in the constructor for player 1
     */

    @Test
    public void BoardPanelTest1()
    {

        Player player = new Player(1,"Name");
        boardPanel.setPlayer(player);
        assertNotNull(boardPanel.getMovementHelper());
        assertNotNull(boardPanel.getCheckerBoardUI());
        assertNotNull(boardPanel.getButton());
        assertEquals(Color.BLUE,boardPanel.getButton().getBackground());
    }

    /**
     * Checking if all the attributes are set in the constructor for player 2
     */

    @Test
    public void BoardPanelTest2()
    {

        Player player = new Player(2,"Name");
        boardPanel.setPlayer(player);
        assertNotNull(boardPanel.getMovementHelper());
        assertNotNull(boardPanel.getCheckerBoardUI());
        assertNotNull(boardPanel.getButton());
        assertEquals(Color.RED,boardPanel.getButton().getBackground());
    }


    /**
     * Checking if all the attributes are set in the constructor for player = null or other
     */

    @Test
    public void resetButtonPropertiesTest1()
    {
        boardPanel.setPlayer(null);
        boardPanel.resetButtonProperties();
        assertEquals(Color.WHITE,boardPanel.getButton().getBackground());
    }

    /**
     * Checking if all the attributes are set in the constructor for player = null or other
     */

    @Test
    public void resetButtonPropertiesTest2()
    {
        Player player = new Player(1,"Name");
        boardPanel.setPlayer(player);
        boardPanel.resetButtonProperties();
        assertEquals(Color.BLUE,boardPanel.getButton().getBackground());
    }

    /**
     * Checking if all the attributes are set in the constructor for player = null or other
     */

    @Test
    public void resetButtonPropertiesTest3()
    {
        Player player = new Player(2,"Name");
        boardPanel.setPlayer(player);
        boardPanel.resetButtonProperties();
        assertEquals(Color.RED,boardPanel.getButton().getBackground());
    }

    @Test
    public void setPlayerTest1()
    {
        Player player = new Player(1,"Name");
        assertNull(boardPanel.getPlayer());
        boardPanel.setPlayer(player);
        assertNotNull(boardPanel.getPlayer());
        assertEquals(Color.BLUE,boardPanel.getButton().getBackground());
    }

    @Test
    public void setPlayerTest2()
    {
        Player player = new Player(2,"Name");
        assertNull(boardPanel.getPlayer());
        boardPanel.setPlayer(player);
        assertNotNull(boardPanel.getPlayer());
        assertEquals(Color.RED,boardPanel.getButton().getBackground());
    }

    @Test
    public void setPlayerTest3()
    {
        Player player = new Player(0,"Name");
        assertNull(boardPanel.getPlayer());
        boardPanel.setPlayer(player);
        assertNotNull(boardPanel.getPlayer());
        assertEquals(Color.ORANGE,boardPanel.getButton().getBackground());
    }

    @Test
    public void setPlayerTest4()
    {
        boardPanel.setPlayer(null);
        assertEquals(Color.ORANGE,boardPanel.getButton().getBackground());
    }




}
