package ss;

import java.util.Scanner;

public class TicTacToeGame {
    private MyQueue<Player> players;
    private GameBoard board;
    private Player currentPlayer;

    public TicTacToeGame(MyQueue<Player> players, GameBoard board) {
        this.players = players;
        this.board = board;
        this.currentPlayer = null;
    }

    public void play() {
    	System.out.println("Start game: ");
        initializePlayers();
        ScoreTracker.setPlayers(players.toArray());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            board.display();

            if (currentPlayer instanceof HumanPlayer) {
                handleHumanPlayerTurn(scanner);
            } else if (currentPlayer instanceof ComputerPlayer) {
                handleComputerPlayerTurn();
            }

            if (checkGameOutcome()) {
                break;
            }

            switchToNextPlayer();
        }

        ScoreTracker.displayScores(); // Display scores at the end of each round
    }

    private void initializePlayers() {
        Player playerX = players.dequeue();
        Player playerO = players.dequeue();
        playerX.setSymbol('X');
        playerO.setSymbol('O');

        players.enqueue(playerO);
        players.enqueue(playerX);

        currentPlayer = playerX;
    }

    private void handleHumanPlayerTurn(Scanner scanner) {
        System.out.println("Player " + currentPlayer.getName() + ", enter row (1-3): ");
        int row = scanner.nextInt();

        System.out.println("Player " + currentPlayer.getName() + ", enter column (1-3): ");
        int col = scanner.nextInt();
        scanner.nextLine();

        if (board.markPosition(row, col, currentPlayer.getSymbol())) {
            if (board.checkWin(currentPlayer.getSymbol())) {
                board.display();
                System.out.println("Player " + currentPlayer.getName() + " wins!");
                ScoreTracker.incrementScore(currentPlayer);
            } else if (board.checkDraw()) {
                board.display();
                System.out.println("It's a draw!");
            }
        } else {
            System.out.println("Invalid move. Try again.");
            handleHumanPlayerTurn(scanner);
        }
    }

    private void handleComputerPlayerTurn() {
        ((ComputerPlayer) currentPlayer).makeMove(board);
        if (board.checkWin(currentPlayer.getSymbol())) {
            board.display();
            System.out.println("Player " + currentPlayer.getName() + " (Computer) wins!");
            ScoreTracker.incrementScore(currentPlayer);
        } else if (board.checkDraw()) {
            board.display();
            System.out.println("It's a draw!");
        }
    }

    private void switchToNextPlayer() {
        Player nextPlayer = players.dequeue();
        players.enqueue(nextPlayer);
        currentPlayer = nextPlayer;
    }

    private boolean checkGameOutcome() {
        return board.checkWin(currentPlayer.getSymbol()) || board.checkDraw();
    }
}
