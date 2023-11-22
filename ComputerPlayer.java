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
        int size = board.getSize();

        // Check for winning moves or block opponent wins
        for (int row = 1; row <= size; row++) {
            for (int col = 1; col <= size; col++) {
                if (board.isEmpty(row, col)) {
                    // Check if the current move leads to a win
                    if (isWinningMove(board, row, col, getSymbol())) {
                        board.markPosition(row, col, getSymbol());
                        System.out.println("Player " + getName() + " (Computer) chooses row " + row + ", column " + col);
                        return;
                    }

                    // Check if the opponent wins with the current move, and block it
                    if (isWinningMove(board, row, col, getSymbol())) {
                        board.markPosition(row, col, getSymbol());
                        System.out.println("Player " + getName() + " (Computer) blocks opponent at row " + row + ", column " + col);
                        return;
                    }
                }
            }
        }

        // Choose the center or a random corner if available
        int[] center = { size / 2 + 1, size / 2 + 1 };
        int[][] corners = { { 1, 1 }, { 1, size }, { size, 1 }, { size, size } };

        if (board.isEmpty(center[0], center[1])) {
            board.markPosition(center[0], center[1], getSymbol());
            System.out.println("Player " + getName() + " (Computer) chooses center at row " + center[0] + ", column " + center[1]);
            return;
        }

        for (int[] corner : corners) {
            if (board.isEmpty(corner[0], corner[1])) {
                board.markPosition(corner[0], corner[1], getSymbol());
                System.out.println("Player " + getName() + " (Computer) chooses corner at row " + corner[0] + ", column " + corner[1]);
                return;
            }
        }

        // If no specific strategy is applicable, make a random move
        makeRandomMove(board);
    }
    private boolean isWinningMove(GameBoard board, int row, int col, char symbol) {
        // Check for a win in the row
        boolean winInRow = true;
        for (int i = 1; i <= board.getSize(); i++) {
            if (board.getSymbol(row, i) != symbol) {
                winInRow = false;
                break;
            }
        }
        if (winInRow) {
            return true;
        }

        // Check for a win in the column
        boolean winInCol = true;
        for (int i = 1; i <= board.getSize(); i++) {
            if (board.getSymbol(i, col) != symbol) {
                winInCol = false;
                break;
            }
        }
        if (winInCol) {
            return true;
        }

        // Check for a win in the main diagonal
        if (row == col) {
            boolean winInMainDiagonal = true;
            for (int i = 1; i <= board.getSize(); i++) {
                if (board.getSymbol(i, i) != symbol) {
                    winInMainDiagonal = false;
                    break;
                }
            }
            if (winInMainDiagonal) {
                return true;
            }
        }

        // Check for a win in the secondary diagonal
        if (row + col == board.getSize() + 1) {
            boolean winInSecondaryDiagonal = true;
            for (int i = 1; i <= board.getSize(); i++) {
                if (board.getSymbol(i, board.getSize() - i + 1) != symbol) {
                    winInSecondaryDiagonal = false;
                    break;
                }
            }
            if (winInSecondaryDiagonal) {
                return true;
            }
        }

        return false;
    }


  
    public void makeRandomMove(GameBoard board) {
        // Initializing the size of the board
        int size = 3; 

        // Keep making random moves until a valid move is found
        while (true) {
            // Generate random row and column indices
            int row = random.nextInt(size) + 1;
            int col = random.nextInt(size) + 1;

            // Check if the chosen position is empty and displaying the move the computer makes
            if (board.markPosition(row, col, getSymbol())) {
                System.out.println("Player " + getName() + " (Computer) chooses row " + row + ", column " + col);
                break;
            }
        }
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
