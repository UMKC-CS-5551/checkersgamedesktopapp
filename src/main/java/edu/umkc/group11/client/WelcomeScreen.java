package edu.umkc.group11.client;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WelcomeScreen {

    public  String current_user;
    public void StartGameMethod() {

        JFrame f=new JFrame("Checkers game application");//creating instance of JFrame
        f.setBounds(10, 10, 370, 600);
        f.setTitle("Login Form");
        JButton b=new JButton("Start Game");//creating instance of JButton
        JButton exit_Button=new JButton("Exit Game");//creating instance of JButton
        JButton results_Button=new JButton("Show results");//creating instance of JButton
        JButton userHistory=new JButton("Search my scores");//creating instance of JButton
        JTextField textfieldName = new JTextField("",10);
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height
        exit_Button.setBounds(130,200,100, 40);
        results_Button.setBounds(130,300,100, 40);
        userHistory.setBounds(20,400,200,40);
        textfieldName.setBounds(250,400,100,40);
        f.add(b);//adding button in JFrame
        f.add(exit_Button);
        f.add(results_Button);
        f.add(textfieldName);
        f.add(userHistory);
        f.setSize(400,600);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        b.addActionListener(e ->
        {
            f.dispose();

            clientInformation1();

        });

        results_Button.addActionListener(e ->
        {
            try {
                String score =  showresults();
                JOptionPane.showMessageDialog(f, score);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        userHistory.addActionListener(e ->
        {
            String score =  filterOneUsersScore(textfieldName.getText());
            JOptionPane.showMessageDialog(f, score);
        });

        exit_Button.addActionListener(e-> {
                    f.dispose();
                    JOptionPane.showMessageDialog(f,
                            "Exiting the game");
                    System.exit(0);
                }
        );
    }

    public String filterOneUsersScore(String text)
    {
        String res = "";
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/java/edu/umkc/group11/client/ScoreRecord.txt"))) {
            for(String line; (line = br.readLine()) != null; ) {
               if(line.contains(text.toLowerCase()))
               {
                   res += line+"\n";
               }
            }
            // line is not visible here.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
    private String showresults() throws IOException {

        File yourFile = new File("src/main/java/edu/umkc/group11/client/ScoreRecord.txt");
        yourFile.createNewFile();
        String score = new String(Files.readAllBytes(Paths.get("src/main/java/edu/umkc/group11/client/ScoreRecord.txt")));
        if(score.equals("")) return "No previous scores recorded";
        return score;
    }

    private  int clientInformation1() {
        JLabel player1 = new JLabel("Enter Player 1 information");
        player1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        JTextField textfield1, textfield2;
        JLabel name = new JLabel("Name");
        JLabel age = new JLabel("Age");

        textfield1 = new JTextField("",10);
        textfield2 = new JTextField("",3);


        JFrame f=new JFrame("Players information : Player 1 information");//creating instance of JFrame
        f.setBounds(10, 10, 370, 600);
        JButton submitButton=new JButton("Submit");//creating instance of JButton
        submitButton.setBackground(Color.green);

        player1.setBounds(90,30,300, 40);
        name.setBounds(90,100,100, 40);//x axis, y axis, width, height
        age.setBounds(100,200,100, 40);
        textfield1.setBounds(130,100,100, 40);
        textfield2.setBounds(130,200,100, 40);
        submitButton.setBounds(130,300,100, 40);

        f.add(player1);
        f.add(name);//adding button in JFrame
        f.add(textfield1);
        f.add(age);
        f.add(textfield2);
        f.add(submitButton);
        f.setSize(600,600);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        submitButton.addActionListener(e ->
        {
            if(textfield1.getText().equals(""))
            {
                textfield1.setText("Player 1");
            }
            current_user = (textfield1.getText() +",");
            f.dispose();
            clientInformation2();

        });

        return 1;
    }


    private  void clientInformation2()
    {
        JLabel player1 = new JLabel("Enter Player 2 information");
        player1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        JTextField textfield1, textfield2;
        JLabel name = new JLabel("Name");
        JLabel age = new JLabel("Age");

        textfield1 = new JTextField("",10);
        textfield2 = new JTextField("",3);


        JFrame f=new JFrame("Players information : Player 2 information");//creating instance of JFrame
        f.setBounds(10, 10, 370, 600);
        JButton submitButton=new JButton("Submit");//creating instance of JButton
        submitButton.setBackground(Color.green);

        player1.setBounds(90,30,300, 40);
        name.setBounds(90,100,100, 40);//x axis, y axis, width, height
        age.setBounds(100,200,100, 40);
        textfield1.setBounds(130,100,100, 40);
        textfield2.setBounds(130,200,100, 40);
        submitButton.setBounds(130,300,100, 40);

        f.add(player1);
        f.add(name);//adding button in JFrame
        f.add(textfield1);
        f.add(age);
        f.add(textfield2);
        f.add(submitButton);
        f.setSize(600,600);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        submitButton.addActionListener(e ->
        {
            if(textfield1.getText().equals(""))
            {
                textfield1.setText("Player 2");
            }
            current_user += (textfield1.getText());
            f.dispose();
            showTheBoard();
        });

    }


    private void showTheBoard()
    {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                new PlayCheckersGame(current_user);
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
