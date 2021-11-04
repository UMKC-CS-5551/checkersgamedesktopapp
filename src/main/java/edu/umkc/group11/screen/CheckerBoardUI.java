package edu.umkc.group11.screen;


import edu.umkc.group11.model.BoardPanel;
import edu.umkc.group11.model.MovementHelper;
import edu.umkc.group11.model.PanelCoordinate;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class CheckerBoardUI extends JPanel  {
    private BoardPanel[] blackButtons;
    private BoardPanel[] whiteButtons;
    private JPanel panel;
    private JPanel jMasterPanel;
    private JRadioButton jRadioButtonPlayer1;
    private JRadioButton jRadioButtonPlayer2;
    private JPanel jTopPanel;
    MovementHelper movementHelper;
    private boolean playerOneSelected;
    private boolean playerTwoSelected;
    private ButtonGroup buttonGroupPlayers;
    private Stack<String> playerUsageStack;

    public CheckerBoardUI()
    {
        blackButtons = new BoardPanel[4 * 8];
        whiteButtons = new BoardPanel[4 * 8];
        this.add(getJMasterPanel());
        this.movementHelper = new MovementHelper();
        getButtonGroupPlayers().add(getJRadioButtonPlayer1());
        getButtonGroupPlayers().add(getJRadioButtonPlayer2());

        getJRadioButtonPlayer1().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                playerOneSelected = true;
                playerTwoSelected = false;
            }
        });
        getJRadioButtonPlayer2().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                playerOneSelected = false;
                playerTwoSelected = true;
            }
        });

    }

    public ButtonGroup getButtonGroupPlayers()
    {
        if ( this.buttonGroupPlayers == null )
        {
            this.buttonGroupPlayers = new ButtonGroup();
        }

        return this.buttonGroupPlayers;
    }

    public JPanel getJTopPanel()
    {
        if ( jTopPanel == null )
        {
            jTopPanel = new JPanel();
            GridLayout gridLayout = new GridLayout();
            jTopPanel.add(getJRadioButtonPlayer1(), null);
            jTopPanel.add(getJRadioButtonPlayer2(), null);
        }
        return jTopPanel;
    }

    public JPanel getJMasterPanel()
    {
        if ( jMasterPanel == null )
        {
            jMasterPanel = new JPanel();
            jMasterPanel.setLayout(new BorderLayout());

            jMasterPanel.add(getJTopPanel(), BorderLayout.NORTH);
            jMasterPanel.add(createBoard(), BorderLayout.CENTER);
        }
        return jMasterPanel;
    }


    public JRadioButton getJRadioButtonPlayer1()
    {
        if ( jRadioButtonPlayer1 == null )
        {
            jRadioButtonPlayer1 = new JRadioButton();
            jRadioButtonPlayer1.setText("Player 1");
            jRadioButtonPlayer1.setForeground(Color.BLUE);
            jRadioButtonPlayer1.setFont(new Font("Dialog", Font.BOLD, 11));
        }
        return jRadioButtonPlayer1;
    }

    public JRadioButton getJRadioButtonPlayer2()
    {
        if ( jRadioButtonPlayer2 == null )
        {
            jRadioButtonPlayer2 = new JRadioButton();
            jRadioButtonPlayer2.setText("Player 2");
            jRadioButtonPlayer2.setForeground(Color.RED);

            jRadioButtonPlayer2.setFont(new Font("Dialog", Font.BOLD, 11));
        }
        return jRadioButtonPlayer2;
    }

    public JPanel createBoard()
    {

        for(int i = 0; i < blackButtons.length; i++)
        {
            blackButtons[i] = new BoardPanel(this, i);
            blackButtons[i].setBackground(Color.GREEN);
            blackButtons[i].setMovementHelper(this.movementHelper);
            blackButtons[i].setActivated(true);
        }
        for(int i = 0; i < whiteButtons.length; i++)
        {
            whiteButtons[i] = new BoardPanel(this,i);
            whiteButtons[i].setBackground(Color.WHITE);
            whiteButtons[i].setMovementHelper(this.movementHelper);

        }
        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));
        panel.setSize(1000, 1000);

        for (int i = 0; i < 8; i++) {
            whiteButtons[i].setPreferredSize(new Dimension(100,100));
            blackButtons[i].setPreferredSize(new Dimension(100,100));
            blackButtons[i].setActivated(true);
            whiteButtons[i].getButton().setEnabled(false);
            if (i % 2 == 0) {
                for (int j = 0; j < 4; j++) {
                    String tmp = String.valueOf(i) + "," + String.valueOf(j);

                    blackButtons[4 * i + j].getButton().setText(tmp);
                    blackButtons[4 * i + j].setPanelCoordinate(new PanelCoordinate(i,j));
                    if ( i <= 2)
                    {
                        blackButtons[4 * i + j].setXpos(i);
                        blackButtons[4 * i + j].setYpos(j);
                        blackButtons[4 * i + j].setPlayerId(1);
                    }
                    else if ( i >= 5 )
                    {
                        blackButtons[4 * i + j].setXpos(i);
                        blackButtons[4 * i + j].setYpos(j);
                        blackButtons[4 * i + j].setPlayerId(2);
                    }
                    panel.add(blackButtons[4 * i + j]);
                    panel.add(whiteButtons[4 * i + j]);
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String tmp = String.valueOf(i) + "," + String.valueOf(j);

                    blackButtons[4 * i + j].getButton().setText(tmp);
                    blackButtons[4 * i + j].setPanelCoordinate(new PanelCoordinate(i,j));


                    if ( i <= 1)
                    {
                        blackButtons[4 * i + j].setXpos(i);
                        blackButtons[4 * i + j].setYpos(j);
                        blackButtons[4 * i + j].setPlayerId(1);
                    }
                    else if (  i >= 5 )
                    {
                        blackButtons[4 * i + j].setXpos(i);
                        blackButtons[4 * i + j].setYpos(j);
                        blackButtons[4 * i + j].setPlayerId(2);
                    }

                    panel.add(whiteButtons[4 * i + j]);
                    panel.add(blackButtons[4 * i + j]);
                }
            }
        }
     //   add(panel);
        panel.repaint();
        return panel;
    }

    public BoardPanel[] getBlackButtons() {
        return blackButtons;
    }

    public BoardPanel[] getWhiteButtons() {
        return whiteButtons;
    }

    public JPanel getPanel() {
        return panel;
    }

    public MovementHelper getMovementHelper() {
        return movementHelper;
    }

    public void setMovementHelper(MovementHelper movementHelper) {
        this.movementHelper = movementHelper;
    }

    public boolean isPlayerOneSelected() {
        return playerOneSelected;
    }

    public boolean isPlayerTwoSelected() {
        return playerTwoSelected;
    }

    public Stack<String> getPlayerUsageStack() {
        if ( playerUsageStack == null )
        {
            playerUsageStack = new Stack<>();
        }
        return playerUsageStack;
    }
}
