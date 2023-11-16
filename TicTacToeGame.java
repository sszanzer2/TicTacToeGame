package ss;

import java.util.Scanner;

public class TicTacToeGame implements Game{
    private MyQueue<Player> players;
    private GameBoard board;
    private HumanPlayer currentPlayer;

    public TicTacToeGame(MyQueue<Player> players, GameBoard board) {
        this.players = players;
        this.board = board;
        this.currentPlayer = null;
    }
    @Override
    public void play() {
        // Assign players to X and O
        Player playerX = players.dequeue();
        Player playerO = players.dequeue();
        playerX.setSymbol('X');
        playerO.setSymbol('O');
        
        players.enqueue(playerO);
        players.enqueue(playerX);

        // Initialize the game with player X
        currentPlayer = (HumanPlayer) playerX;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            board.display();
            System.out.println("Player " + currentPlayer.getName() + ", enter row (1-3): ");
            int row = scanner.nextInt();

            System.out.println("Player " + currentPlayer.getName() + ", enter column (1-3): ");
            int col = scanner.nextInt();

            if (board.markPosition(row, col, currentPlayer.getSymbol())) {
                if (board.checkWin(currentPlayer.getSymbol())) {
                    board.display();
                    System.out.println("Player " + currentPlayer.getName() + " wins!");
                    currentPlayer.incrementScore();
                    break;
                } else if (board.checkDraw()) {
                    board.display();
                    System.out.println("It's a draw!");
                    break;
                }

                // Switch to the next player
                currentPlayer = (currentPlayer == playerX) ? (HumanPlayer) playerO : (HumanPlayer) playerX;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }
    @Override
    public void displayScore() {
        System.out.println("Scoreboard:");
        for (Player player : players.toArray()) {
            System.out.println(player.getName() + ": " + player.getScore());
        }
    }
}
