package edu.umkc.group11.client;

import edu.umkc.group11.screen.CheckerBoardUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PlayCheckersGame extends JFrame {

    private PlayCheckersGame(String title)
    {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(content);
        getContentPane().add(new CheckerBoardUI());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


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


