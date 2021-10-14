package edu.umkc.group11.client;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreen {

    public  String current_user;
    private void StartGameMethod()
    {

        JFrame f=new JFrame("Checkers game application");//creating instance of JFrame
        f.getContentPane().setLayout(new FlowLayout());
        JButton b=new JButton("Start Game");//creating instance of JButton
        JButton exit_Button=new JButton("Exit Game");//creating instance of JButton
        JButton results_Button=new JButton("Show results");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height
        exit_Button.setBounds(130,200,100, 40);
        results_Button.setBounds(130,300,100, 40);
        f.add(b);//adding button in JFrame
        f.add(exit_Button);
        f.add(results_Button);
        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        b.addActionListener(e ->
        {
            f.dispose();
            clientInformation();
        });

        results_Button.addActionListener(e ->
        {
            JOptionPane.showMessageDialog(f,
                    "Highest results of the past games\n Top Score : 50s");
            System.exit(0);
        });

        exit_Button.addActionListener(e-> {
                    f.dispose();
                    JOptionPane.showMessageDialog(f,
                            "Exiting the game");
                    System.exit(0);
                }
                );
    }

    private  void clientInformation()
    {
         JTextField textfield1, textfield2;
        JFrame f = new JFrame("Players information");
        f.getContentPane().setLayout(new FlowLayout());
        textfield1 = new JTextField("Name",10);
        textfield2 = new JTextField("Age",10);
        JButton submitButton=new JButton("Submit");//creating instance of JButton
        submitButton.setBounds(130,200,100, 40);//x axis, y axis, width, height
        submitButton.setBackground(Color.green);
        f.getContentPane().add(textfield1);
        f.getContentPane().add(textfield2);
        f.add(submitButton);
        f.setSize(400,500);//400 width and 500 height
        f.pack();
        f.setVisible(true);
        submitButton.addActionListener(e ->
        {
            current_user = ("Name:"+textfield1.getText()+"  Age:"+textfield2.getText() +" Against Computer");
            f.dispose();
            showTheBoard();
        });

    }
    private void showTheBoard()
    {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                new PlayCheckersGame("Checkers Board Game"+ current_user);
            }
        };
        SwingUtilities.invokeLater(r);
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                new WelcomeScreen().StartGameMethod();
            }
        };
        SwingUtilities.invokeLater(r);

    }
}
