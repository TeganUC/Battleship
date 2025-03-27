import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BattleshipFrame extends JFrame implements ActionListener{

    private JButton[][] buttons = new JButton[10][10];
    private String[][] board = new String[10][10];
    private JPanel mainPnl, cmdPnl, countPnl, btnPnl;
    private JButton newBtn, quitBtn;
    private JLabel hits, misses, strikes, totalMisses;
    private boolean gameWon = false;
    private boolean gameEnded = false;
    private Random random = new Random();

    int numHits = 0;
    int numMisses = 0;
    int numStrikes = 0;
    int numTotalMisses = 0;
    int flukes = 0;

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

        mainPnl.add(btnPnl);

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

        createCounters();
        createControlPanel();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {

                if(buttons[i][j] == buttonClicked){
                    if(buttons[i][j] == buttonClicked && board[i][j] == "S") {

                        buttonClicked.setForeground(new Color(255, 41, 84));
                        buttonClicked.setText("X");

                        board[i][j] = "H";

                        numHits++;
                        hits.setText("Hits: " + numHits);

                        numMisses = 0;
                        misses.setText("Misses: " + numMisses);

                    }else if(((buttons[i][j] == buttonClicked && board[i][j] == "H") || (buttons[i][j] == buttonClicked && board[i][j] == "M")) && (board[i][j] != "W") && ((board[i][j] != "S"))) {

                        flukes++;

                    }else{

                        buttonClicked.setForeground(new Color(252, 232, 96));
                        buttonClicked.setText("M");
                        board[i][j] = "M";

                        numMisses++;
                        misses.setText("Misses: " + numMisses);

                        numTotalMisses++;
                        totalMisses.setText("Miss Total: " + numTotalMisses);


                        if(numMisses == 5){
                            numStrikes++;
                            strikes.setText("Strikes: " + numStrikes);

                            numMisses = 0;
                            misses.setText("Misses: " + numMisses);
                        }

                        if(numStrikes == 3){
                            restartGame();
                            int popUp = JOptionPane.showConfirmDialog(null, "You lose. Game restarted!", "Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }
                if(checkForWin()){
                    restartGame();
                    int popUp = JOptionPane.showConfirmDialog(null, "You win! Game restarted!", "Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }

    private void createCounters(){

        countPnl = new JPanel();
        countPnl.setLayout(new GridLayout(2,2));

        hits = new JLabel("Hits: " + numHits);
        countPnl.add(hits);

        misses = new JLabel("Misses: " + numMisses);
        countPnl.add(misses);

        strikes = new JLabel("Strikes: " + numStrikes);
        countPnl.add(strikes);

        totalMisses = new JLabel("Miss Total: " + numTotalMisses);
        countPnl.add(totalMisses);

        mainPnl.add(countPnl);
    }

    private void createControlPanel() {
        Random rnd = new Random();
        cmdPnl = new JPanel();
        cmdPnl.setLayout(new GridLayout(1,2));
        newBtn = new JButton("New Game!");

        newBtn.addActionListener((ActionEvent ae) -> {

            int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                restartGame();
                JOptionPane.showMessageDialog(null, "Game restarted!");
            }
        });

        quitBtn = new JButton("Quit!");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        cmdPnl.add(newBtn);
        cmdPnl.add(quitBtn);

        mainPnl.add(cmdPnl);
    }

    private void restartGame(){

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                board[i][j] = null;

            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                board[i][j] = "W";

            }
        }



        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {

                buttons[i][j].setText("W");
                buttons[i][j].setForeground(new Color(25, 163, 255));
                buttons[i][j].addActionListener(this);
                btnPnl.add(buttons[i][j]);
            }
        }

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

        numHits = 0;
        numMisses = 0;
        numTotalMisses = 0;
        numStrikes = 0;

        hits.setText("Hits: " + numHits);
        misses.setText("Misses: " + numStrikes);
        totalMisses.setText("Miss Total: " + numStrikes);
        strikes.setText("Strikes: " + numStrikes);
    }

    private boolean checkForWin(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if(board[i][j] == "S"){
                    return false;
                }
            }
        }
        return true;
    }
}
