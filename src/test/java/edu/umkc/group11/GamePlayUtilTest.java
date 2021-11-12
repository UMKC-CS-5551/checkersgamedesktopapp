package edu.umkc.group11;

import edu.umkc.group11.client.GamePlayUtil;
import edu.umkc.group11.model.BoardPanel;
import edu.umkc.group11.model.PanelCoordinate;
import edu.umkc.group11.model.Player;
import edu.umkc.group11.screen.CheckerBoardUI;
import org.junit.Test;
import static org.junit.Assert.*;



public class GamePlayUtilTest {

    @Test
    public void resetOtherPanelsAndButtonsTestKing()
    {
        CheckerBoardUI checkerBoardUI = new CheckerBoardUI(("Test1, Test2"));
        BoardPanel boardPanel = checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,1));
        boardPanel.setPlayer(new Player(1, true));
        boardPanel.getPlayer().setKing(true);
        GamePlayUtil gamePlayUtil = new GamePlayUtil(checkerBoardUI);
        boardPanel.getButton().setName("player1");

        gamePlayUtil.resetOtherPanelsAndButtons(checkerBoardUI, new BoardPanel(checkerBoardUI,9).getButton());
        String expexctedButtonLabel = "2,1 K ";
        assertEquals(expexctedButtonLabel, boardPanel.getButton().getText());
    }
    @Test
    public void resetOtherPanelsAndButtonsTestNullPlayer()
    {
        CheckerBoardUI checkerBoardUI = new CheckerBoardUI(("Test1, Test2"));
        BoardPanel boardPanel = checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,1));
        boardPanel.setPlayer(null);
        GamePlayUtil gamePlayUtil = new GamePlayUtil(checkerBoardUI);

        gamePlayUtil.resetOtherPanelsAndButtons(checkerBoardUI, new BoardPanel(checkerBoardUI,9).getButton());
        String expexctedButtonLabel = "2,1";
        assertEquals(expexctedButtonLabel, boardPanel.getButton().getText());
    }

    @Test
    public void resetOtherPanelsAndButtonsTestPlayerId0()
    {
        CheckerBoardUI checkerBoardUI = new CheckerBoardUI(("Test1, Test2"));
        BoardPanel boardPanel = checkerBoardUI.getBoardPanelByPanelCoordinate(new PanelCoordinate(2,1));
        boardPanel.setPlayer(new Player(0, false));
        GamePlayUtil gamePlayUtil = new GamePlayUtil(checkerBoardUI);
        boardPanel.getButton().setName("player1");

        gamePlayUtil.resetOtherPanelsAndButtons(checkerBoardUI, new BoardPanel(checkerBoardUI,9).getButton());
        String expexctedButtonLabel = "2,1";
        assertEquals(expexctedButtonLabel, boardPanel.getButton().getText());
    }

}
