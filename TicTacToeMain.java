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

        GameBoard board = new GameBoard();

        while (true) {
            int choice;
            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Play against the computer");
                System.out.println("2. Play against 2 human players");
                System.out.print("Enter your choice (1 or 2): ");

                try {
                    choice = Integer.parseInt(scanner.nextLine());

                    if (choice == 1 || choice == 2) {
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            }

            Player playerX, playerO;
            if (choice == 1) {
                playerX = new HumanPlayer('X', getPlayerName(scanner, "X"));
                playerO = new ComputerPlayer('O', "Computer");
            } else {
                playerX = new HumanPlayer('X', getPlayerName(scanner, "X"));
                playerO = new HumanPlayer('O', getPlayerName(scanner, "O"));
            }

            MyQueue<Player> playersQueue = new MyQueue<>();
            playersQueue.enqueue(playerX);
            playersQueue.enqueue(playerO);

            TicTacToeGame game = new TicTacToeGame(playersQueue, board);

            game.play();

            if (!playAgain(scanner)) {
                ScoreTracker.displayScores(); // Display total scores at the end of all games
                ScoreTracker.getFinalWinner();
                System.out.println("The Grand Winner is: " +  ScoreTracker.getFinalWinner());
                System.out.println("Goodbye!");
                break;
            } else {
                board.reset();
            }
        }

        scanner.close();
    }

    private static String getPlayerName(Scanner scanner, String symbol) {
        while (true) {
            System.out.print("Enter Player " + symbol + "'s name: ");
            String playerName = scanner.nextLine().trim();

            if (!playerName.isEmpty()) {
                return playerName;
            } else {
                System.out.println("Invalid name. Please enter a non-empty name.");
            }
        }
    }

    private static boolean playAgain(Scanner scanner) {
        while (true) {
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.nextLine().toLowerCase();

            if (playAgain.equals("yes")) {
                return true;
            } else if (playAgain.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid response. Please enter 'yes' or 'no'.");
            }
        }
    }
}
