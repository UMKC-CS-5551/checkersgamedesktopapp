package edu.umkc.group11.client;

import edu.umkc.group11.screen.CheckerBoardUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PlayCheckersGame extends JFrame {

    public PlayCheckersGame(String title)
    {
        super(title);
       /* setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout( new GridBagLayout() );
        CheckerBoardUI tmpCheckerBoardUI = new CheckerBoardUI();
        add(tmpCheckerBoardUI, new GridBagConstraints());
        pack();
        setVisible(true);
        Box box = new Box(BoxLayout.Y_AXIS);
        box.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        box.add(Box.createVerticalGlue());
        box.add(new CheckerBoardUI());
        box.add(Box.createVerticalGlue());
        add(box);
        pack();
        setVisible(true);*/

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


