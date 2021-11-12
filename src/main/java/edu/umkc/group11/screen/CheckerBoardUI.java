package edu.umkc.group11.screen;


import edu.umkc.group11.client.WelcomeScreen;
import edu.umkc.group11.model.BoardPanel;
import edu.umkc.group11.model.MovementHelper;
import edu.umkc.group11.model.PanelCoordinate;
import edu.umkc.group11.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
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
    private Player playerOne;
    private Player playerTwo;
    private JButton player1NoMoves;
    private JButton player2NoMoves;
    private JButton exitButton;
    JLabel playerOneScoreField;
    JLabel playerTwoScoreField;

    public CheckerBoardUI(String title)
    {
        initializePlayers(title);

        blackButtons = new BoardPanel[4 * 8];
        whiteButtons = new BoardPanel[4 * 8];
        this.add(getJMasterPanel());
        this.movementHelper = new MovementHelper(this);
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

        player1NoMoves.addActionListener(e ->
        {
            try {
                recordHighScore(playerTwo.getName());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            displayExitGameWindow(playerTwo);
        });

        player2NoMoves.addActionListener(e ->
        {
            try {
                recordHighScore(playerOne.getName());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            displayExitGameWindow(playerOne);
        });
        exitButton.addActionListener(e ->
        {
           System.exit(0);
        });

    }


    public void updateScoresOnBoard()
    {
        playerOneScoreField.setText("Score : "+ playerOne.getScore());
        playerTwoScoreField.setText("Score : "+ playerTwo.getScore());
    }

    public void initializePlayers(String playersNames)
    {
        String[] player_names = playersNames.split(",");
        playerOne = new Player(1,player_names[0]);
        playerTwo = new Player(2,player_names[1]);
    }

    public void displayExitGameWindow(Player playerWon)
    {
        JLabel label1 = new JLabel("Opposite player has ended the game");
        label1.setFont(new Font("Times New Roman", Font.BOLD, 15));

        JLabel label2 = new JLabel("because of no moves left");
        label2.setFont(new Font("Times New Roman", Font.BOLD, 15));

        //
        JLabel name = new JLabel("Winner : " + playerWon.getName() + "  with score : " + playerWon.getScore());
        name.setFont(new Font("Times New Roman", Font.BOLD, 15));

        JFrame f=new JFrame("Game result");//creating instance of JFrame
        f.setBounds(50, 50, 370, 600);
        JButton submitButton=new JButton("End game");//creating instance of JButton
        submitButton.setBackground(Color.green);

        JButton restartGame=new JButton("New game");//creating instance of JButton
        restartGame.setBackground(Color.green);

        label1.setBounds(90,30,300, 140);
        label2.setBounds(100,50,300, 140);
        name.setBounds(120,100,300, 140);//x axis, y axis, width, height
        submitButton.setBounds(130,300,100, 40);
        restartGame.setBounds(230,300,100, 40);

        f.add(label1);
        f.add(label2);
        f.add(name);//adding button in JFrame
        f.add(submitButton);
        f.add(restartGame);
        f.setSize(600,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visibl
        submitButton.addActionListener(e ->
        {
            exitButton.doClick();
            f.dispose();
        });
        restartGame.addActionListener(e ->
        {
            Window w = SwingUtilities.getWindowAncestor(CheckerBoardUI.this);
            w.setVisible(false);
            jMasterPanel.dispatchEvent(new WindowEvent(w, WindowEvent.WINDOW_CLOSING));
            f.dispose();
            restartApplication();
        });
    }

    public void restartApplication()
    {
        new WelcomeScreen().StartGameMethod();
    }


    public void recordHighScore(String playerWonName) throws IOException {
        File file = new File("src/main/java/edu/umkc/group11/client/ScoreRecord.txt");
        FileWriter fr = new FileWriter(file, true);
        fr.write("\nDate Played : " + java.time.LocalDateTime.now() + "  Winner : " + playerWonName.toLowerCase());
        fr.close();
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
            jTopPanel.add(getPlayerOneScoreField());
            jTopPanel.add(getJRadioButtonPlayer2(), null);
            jTopPanel.add(getPlayerTwoScoreField());
            jTopPanel.add(getPlayer1NoMovesButton());
            jTopPanel.add(getPlayer2NoMovesButton());
            jTopPanel.add(getExitButton());
        }
        return jTopPanel;
    }

    public JLabel getPlayerOneScoreField()
    {
        playerOneScoreField = new JLabel("");
        playerOneScoreField.setText("Score : 0");
        playerOneScoreField.setForeground(Color.BLUE);
        playerOneScoreField.setFont(new Font("Dialog", Font.BOLD, 11));
        return playerOneScoreField;
    }

    public JLabel getPlayerTwoScoreField()
    {
        playerTwoScoreField = new JLabel("");
        playerTwoScoreField.setText(" Score : 0");
        playerTwoScoreField.setForeground(Color.RED);
        playerTwoScoreField.setFont(new Font("Dialog", Font.BOLD, 11));
        return playerTwoScoreField;
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
            jRadioButtonPlayer1.setText(playerOne.getName());
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
            jRadioButtonPlayer2.setText(playerTwo.getName());
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
                        blackButtons[4 * i + j].setPlayer(new Player(1, true));
                    }
                    else if ( i >= 5 )
                    {
                        blackButtons[4 * i + j].setPlayer(new Player(2, true));
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
                        blackButtons[4 * i + j].setPlayer(new Player(1,true));
                    }
                    else if (  i >= 5 )
                    {
                        blackButtons[4 * i + j].setPlayer(new Player(2, true));
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

    public BoardPanel getBoardPanelByPanelCoordinate(PanelCoordinate panelCoordinate)
    {
        for ( BoardPanel boardPanel: blackButtons)
        {
            if ( boardPanel.getPanelCoordinate() != null && boardPanel.getPanelCoordinate().equals(panelCoordinate))
            {
                return boardPanel;
            }
        }
        return null;
    }

    public JButton getPlayer1NoMovesButton()
    {
        player1NoMoves = new JButton();
        player1NoMoves.setText(playerOne.getName() + ":Have no moves");
        player1NoMoves.setForeground(Color.BLACK);
        return player1NoMoves;
    }

    public JButton getPlayer2NoMovesButton()
    {
        player2NoMoves = new JButton();
        player2NoMoves.setText(playerTwo.getName() + ":Have no moves");
        player2NoMoves.setForeground(Color.BLACK);
        return player2NoMoves;
    }

    public JButton getExitButton()
    {
        exitButton = new JButton();
        exitButton.setText("Exit game");
        exitButton.setForeground(Color.magenta);
        return exitButton;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }
}
