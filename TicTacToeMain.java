package ss;

import java.util.Scanner;

public class TicTacToeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe! \nHere are the Game Rules:"
                + "\r\n"
                + "- The game is played on a 3x3 grid.\r\n"
                + "- Two players take turns, one using \"X\" and the other using \"O.\"\r\n"
                + "- The first player to get three of their marks in a row (horizontally, vertically, or diagonally) wins.\r\n"
                + "- If the grid is filled without a winner, the game is a draw. \nTo start:");
        while (true) {
            // Get Player X's name
            System.out.print("Enter Player X's name: ");
            String playerNameX = scanner.nextLine();
            Player playerX = new HumanPlayer('X', playerNameX);

            // Get Player O's name
            System.out.print("Enter Player O's name: ");
            String playerNameO = scanner.nextLine();
            Player playerO = new HumanPlayer('O', playerNameO);
            
            // Add the players to the queue
            MyQueue<Player> playersQueue = new MyQueue<>();
            playersQueue.enqueue(playerX);
            playersQueue.enqueue(playerO);

            GameBoard board = new GameBoard();
            TicTacToeGame game = new TicTacToeGame(playersQueue, board);

            // Starting the game by calling play from the game class
            game.play();

            // Display final scores
            game.displayScore();

            // Ask the user if they want to play again
            // Declare playAgain outside the loop
            String playAgain;

            // Keep asking until a valid response is provided
            while (true) {
                System.out.print("Do you want to play again? (yes/no): ");
                playAgain = scanner.nextLine().toLowerCase();

                if (playAgain.equals("yes") || playAgain.equals("no")) {
                    break;
                } else {
                    System.out.println("Invalid response. Please enter 'yes' or 'no'.");
                }
            }

            if (!playAgain.equals("yes")) {
                System.out.println("Goodbye!");
                // Add any additional logic you might need after exiting the loop
                break; // exit the outer loop when the user does not want to play again
            }
        }

        // Close the Scanner after the loop
        scanner.close();
    }
}
