package edu.umkc.group11.client;

import edu.umkc.group11.screen.CheckerBoardUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PlayCheckersGame extends JFrame {

    public PlayCheckersGame(String title)
    {
        super(title);
        JFrame window = new JFrame("Checkers");
        CheckerBoardUI content = new CheckerBoardUI();
        window.setContentPane(content);
        window.pack();
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation( (screensize.width - window.getWidth())/2,
                (screensize.height - window.getHeight())/2 );
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setResizable(false);
        window.setVisible(true);

    }
    public static void main(String[] args) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                new PlayCheckersGame("Checkers Board Game");
            }
        };
        SwingUtilities.invokeLater(r);
    }


}


