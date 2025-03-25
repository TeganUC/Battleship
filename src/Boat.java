import java.util.Random;

public class Boat {

    public String[][] board;
    public int shipLength;

    public Boat(String[][] board, int shipLength) {
        this.board = board;
        this.shipLength = shipLength;
    }

    public boolean placeShip(String [][] board, int shipLength) {
        Random random = new Random();
        boolean horizontal = random.nextBoolean();
        int row, col;

        if (horizontal) {
            if (shipLength > 10) return false;
            col = random.nextInt(10 - shipLength + 1);
            row = random.nextInt(10);
            if (!isValidPlacement(row, col, shipLength, true)) return false;

            for (int i = 0; i < shipLength; i++) {
                board[row][col + i] = "S";
            }
        } else {
            if (shipLength > 10) return false;
            row = random.nextInt(10 - shipLength + 1);
            col = random.nextInt(10);
            if (!isValidPlacement(row, col, shipLength, false)) return false;
            for (int i = 0; i < shipLength; i++) {
                board[row + i][col] = "S";
            }
        }
        return true;
    }

    private boolean isValidPlacement(int row, int col, int shipLength, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < shipLength; i++) {
                if (board[row][col + i] == "S") {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < shipLength; i++) {
                if (board[row + i][col] == "S") {
                    return false;
                }
            }
        }
        return true;
    }

}
