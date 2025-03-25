import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BattleshipFrame extends JFrame implements ActionListener{

    private JButton[][] buttons = new JButton[10][10];
    private String[][] board = new String[10][10];
    private JPanel mainPnl, cmdPnl, btnPnl;
    private JButton newBtn, quitBtn;
    private boolean gameWon = false;
    private boolean gameEnded = false;
    private Random random = new Random();

    public BattleshipFrame() {
        setTitle("Battleship");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPnl = new JPanel();
        add(mainPnl);

        btnPnl = new JPanel();
        btnPnl.setLayout(new GridLayout(10,10));

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {

                buttons[i][j] = new JButton("W");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
                buttons[i][j].setForeground(new Color(25, 163, 255));
                buttons[i][j].addActionListener(this);
                btnPnl.add(buttons[i][j]);

                board[i][j] = "W";
            }
        }

        add(btnPnl);

        Boat boat1 = new Boat(board, 5);
        boat1.placeShip(board, 5);

        Boat boat2 = new Boat(board, 4);
        boat2.placeShip(board, 4);

        Boat boat3 = new Boat(board, 3);
        boat3.placeShip(board, 3);

        Boat boat4 = new Boat(board, 3);
        boat4.placeShip(board, 3);

        Boat boat5 = new Boat(board, 2);
        boat5.placeShip(board, 2);

        createControlPanel();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {

                if(buttons[i][j] == buttonClicked){
                    if(buttons[i][j] == buttonClicked && board[i][j] == "S"){

                        buttonClicked.setForeground(new Color(255, 41, 84));
                        buttonClicked.setText("X");

                        //add hit to the counter
                    }else{

                        buttonClicked.setForeground(new Color(252, 232, 96));
                        buttonClicked.setText("M");

                        //add to counter
                        //second if statement for 5 misses

                    }
                }


            }
        }


    }

    //add buttons & counters here
    private void createControlPanel() {
        Random rnd = new Random();
        cmdPnl = new JPanel();
        cmdPnl.setLayout(new GridLayout(1,2));
        newBtn = new JButton("New Game!");

        newBtn.addActionListener((ActionEvent ae) -> {

            // code to reset everything

        });

        quitBtn = new JButton("Quit!");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        cmdPnl.add(newBtn);
        cmdPnl.add(quitBtn);

        mainPnl.add(cmdPnl);

    }




}
