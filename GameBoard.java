package ss;

public class GameBoard {
    private char[][] grid;

    public GameBoard() {
        grid = new char[3][3];
        // Initialize the grid with empty spaces
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                grid[row][col] = ' ';
            }
        }
    }

    public void display() {
        System.out.println("-------------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(grid[row][col] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public boolean markPosition(int row, int col, char symbol) {
        if (row >= 1 && row <= 3 && col >= 1 && col <= 3 && grid[row - 1][col - 1] == ' ') {
            // Adjust row and column indices and check if the position is within bounds and not already occupied
            grid[row - 1][col - 1] = symbol; // Mark the position with the symbol
            return true; // Move is valid
        } else {
            return false; // Position is already occupied or out of bounds
        }
    }

    public boolean checkWin(char symbol) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) ||
                (grid[0][i] == symbol && grid[1][i] == symbol && grid[2][i] == symbol)) {
                return true; // Player has won in a row or column
            }
        }

        // Check diagonals
        if ((grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) ||
            (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol)) {
            return true; // Player has won in a diagonal
        }

        return false; // No win yet
    }

    public boolean checkDraw() {
        if (!hasEmptySpaces() && !checkWin('X') && !checkWin('O')) {
            // If there are no empty spaces and no player has won, it's a draw
            return true;
        }
        return false;
    }

    private boolean hasEmptySpaces() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (grid[row][col] == ' ') {
                    // If there is an empty space, the game is not a draw
                    return true;
                }
            }
        }
        // If there are no empty spaces, the game may be a draw
        return false;
    }
}
