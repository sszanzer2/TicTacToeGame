package ss;

import java.util.Random;

public class ComputerPlayer implements Player {

    private char symbol;
    private String name;
    private int score;
    private Random random;

    public ComputerPlayer(char symbol, String name) {
        this.symbol = symbol;
        this.name = name;
        this.score = 0;
        this.random = new Random();
    }

    public void makeMove(GameBoard board) {
        // Check for winning moves or block opponent wins
        if (!makeWinningMoveOrBlock(board, getSymbol())) {
            // Choose the center or a random corner if available
            if (!chooseCenterOrCorner(board)) {
                // If no specific strategy is applicable, make a random move
                makeRandomMove(board);
            }
        }
    }

    private boolean makeWinningMoveOrBlock(GameBoard board, char playerSymbol) {
        char opponentSymbol = (playerSymbol == 'X') ? 'O' : 'X';

        for (int row = 1; row <= board.getSize(); row++) {
            for (int col = 1; col <= board.getSize(); col++) {
                if (board.isEmpty(row, col)) {
                	//I was trying to get this logic to work so that the computer makes intelligent moves but it doesn't 
                	//work yet however I did get my computer to prioritize center and corner moves
                    // Check if the current move leads to a win for the opponent, and block it
                    if (isWinningMove(board, row, col, playerSymbol)) {
                        makeMoveAndPrint(board, row, col);
                        return true;
                    }

                    // Check if the opponent wins with the current move, and block it
                    if (isWinningMove(board, row, col, opponentSymbol)) {
                        makeMoveAndPrint(board, row, col);
                        return true;
                    }
                }
            }
        }
        return false;
    }



    private boolean chooseCenterOrCorner(GameBoard board) {
        int size = board.getSize();
        int[] center = { size / 2 + 1, size / 2 + 1 };
        int[][] corners = { { 1, 1 }, { 1, size }, { size, 1 }, { size, size } };

        if (board.isEmpty(center[0], center[1])) {
            makeMoveAndPrint(board, center[0], center[1]);
            return true;
        }

        for (int[] corner : corners) {
            if (board.isEmpty(corner[0], corner[1])) {
                makeMoveAndPrint(board, corner[0], corner[1]);
                return true;
            }
        }

        return false;
    }

    private void makeMoveAndPrint(GameBoard board, int row, int col) {
        board.markPosition(row, col, getSymbol());
        System.out.println("Player " + getName() + " (Computer) chooses row " + row + ", column " + col);
    }

    private void makeRandomMove(GameBoard board) {
        int size = board.getSize();

        while (true) {
            int row = random.nextInt(size) + 1;
            int col = random.nextInt(size) + 1;

            if (board.markPosition(row, col, getSymbol())) {
                makeMoveAndPrint(board, row, col);
                break;
            }
        }
    }
    private boolean isWinningMove(GameBoard board, int row, int col, char symbol) {
        return checkWinningCombination(board, row, col, symbol, 1, 0) ||  // Check horizontally
               checkWinningCombination(board, row, col, symbol, 0, 1) ||  // Check vertically
               checkWinningCombination(board, row, col, symbol, 1, 1) ||  // Check diagonally 1
               checkWinningCombination(board, row, col, symbol, 1, -1);    // Check diagonally 2
    }

    private boolean checkWinningCombination(GameBoard board, int row, int col, char symbol, int rowIncrement, int colIncrement) {
        int size = board.getSize();
        int count = 0; // Count of matching symbols in a combination

        // Check forward direction
        int r = row + rowIncrement;
        int c = col + colIncrement;
        while (r >= 1 && r <= size && c >= 1 && c <= size && board.getSymbol(r, c) == symbol) {
            count++;
            r += rowIncrement;
            c += colIncrement;
        }

        // Check backward direction
        r = row - rowIncrement;
        c = col - colIncrement;
        while (r >= 1 && r <= size && c >= 1 && c <= size && board.getSymbol(r, c) == symbol) {
            count++;
            r -= rowIncrement;
            c -= colIncrement;
        }

        // Check if there is a potential winning combination
        return count == size && board.isEmpty(row - rowIncrement, col - colIncrement);
    }



    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setSymbol(char c) {
        this.symbol = c;
    }

    @Override
    public void incrementScore() {
        score++;
    }
}
