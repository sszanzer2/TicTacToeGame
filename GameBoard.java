package ss;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
   
    private List<List<Character>> grid;

    public GameBoard() {
        // Initialize the grid with empty spaces
        grid = new ArrayList<>();
        for (int row = 0; row < 3; row++) {
            List<Character> rowList = new ArrayList<>();
            for (int col = 0; col < 3; col++) {
                rowList.add(' ');
            }
            grid.add(rowList);
        }
    }

    public int getSize() {
        return grid.size();
    }
    public boolean isEmpty(int row, int col) {
        return grid.get(row - 1).get(col - 1) == ' ';
    }
    public char getSymbol(int row, int col) {
        return grid.get(row - 1).get(col - 1);
    }

    // Displays the board
    public void display() {
        System.out.println("-------------");
        for (List<Character> rowList : grid) {
            System.out.print("| ");
            for (char cell : rowList) {
                System.out.print(cell + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Checks if the move the player wants to make is valid (not occupied and in bounds)
    public boolean markPosition(int row, int col, char symbol) {
        if (isValidMove(row, col) && grid.get(row - 1).get(col - 1) == ' ') {
            // Marks the position with the given symbol
            grid.get(row - 1).set(col - 1, symbol);
            // Move is valid, return true
            return true;
        } else {
            // Position is already occupied or out of bounds, return false
            return false;
        }
    }

    // Checks if the move is within bounds
    private boolean isValidMove(int row, int col) {
        return row >= 1 && row <= 3 && col >= 1 && col <= 3;
    }

    // Checks if any of the players won
    public boolean checkWin(char symbol) {
        // Check rows and columns to see if they contain the same symbol
        for (int i = 0; i < 3; i++) {
            if ((grid.get(i).get(0) == symbol && grid.get(i).get(1) == symbol && grid.get(i).get(2) == symbol) ||
                (grid.get(0).get(i) == symbol && grid.get(1).get(i) == symbol && grid.get(2).get(i) == symbol)) {
                // Player has won in a row or column, return true
                return true;
            }
        }

        // Check diagonals
        if ((grid.get(0).get(0) == symbol && grid.get(1).get(1) == symbol && grid.get(2).get(2) == symbol) ||
            (grid.get(0).get(2) == symbol && grid.get(1).get(1) == symbol && grid.get(2).get(0) == symbol)) {
            // Player has won in a diagonal, return true
            return true;
        }
        // No win yet
        return false;
    }

    // Checks if there is a draw and no player won
    public boolean checkDraw() {
        return !hasEmptySpaces() && !checkWin('X') && !checkWin('O');
    }

    // Checks if there are any empty spaces on the board 
    private boolean hasEmptySpaces() {
        for (List<Character> rowList : grid) {
            for (char cell : rowList) {
                if (cell == ' ') {
                    // If there is an empty space, the game is not a draw
                    return true;
                }
            }
        }
        // If there are no empty spaces, the game may be a draw
        return false;
    }

    public void reset() {
        // Reset the board to empty spaces
        for (List<Character> row : grid) {
            for (int j = 0; j < row.size(); j++) {
                row.set(j, ' ');
            }
        }
    }
}
