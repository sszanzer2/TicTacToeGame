package ss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreTracker {
    private static List<Player> players;
    private static Map<String, Integer> playerScores = new HashMap<>();

    public static void setPlayers(List<Player> players) {
        ScoreTracker.players = players;
        if (playerScores.isEmpty()) {
            initializePlayerScores(players);
        }
    }

    private static void initializePlayerScores(List<Player> players) {
        for (Player player : players) {
            playerScores.put(player.getName(), 0); // Initialize scores to 0
        }
    }

    public static void incrementScore(Player player) {
        player.incrementScore();
        updateTotalScore(player);
    }

    private static void updateTotalScore(Player player) {
        String playerName = player.getName();
        int totalScore = playerScores.getOrDefault(playerName, 0) + 1; // Increment by 1, not player.getScore()
        playerScores.put(playerName, totalScore);
    }

    public static void displayScores() {
        System.out.println("Total Scores:");
        displayPlayerScores(playerScores);
    }

    private static void displayPlayerScores(Map<String, Integer> scoreMap) {
        for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static String getFinalWinner() {
        String winner = null;
        int maxScore = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : playerScores.entrySet()) {
            if (entry.getValue() > maxScore) {
                maxScore = entry.getValue();
                winner = entry.getKey();
            }
        }

        return winner;
    }
}
