package ss;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	public void HumanIncrementScoreCorrectlyTest() {
		Player player = new HumanPlayer('X', "playerX");		
		((HumanPlayer) player).incrementScore();
		
		assertEquals(1, player.getScore());
		
		// Increment the score and test again
        player.incrementScore();
        assertEquals(2, player.getScore());
	}
	
	@Test
	public void HumanSetSymbolSetsCorrectlyTest() {
		Player player = new HumanPlayer('X', "playerX");
		assertEquals('X', player.getSymbol());
		
	}
	

    @Test
    public void ComputerTestGetSymbol() {
        ComputerPlayer computerPlayer = new ComputerPlayer('O', "Computer");
        assertEquals('O', computerPlayer.getSymbol());
    }

    @Test
    public void ComputerTestGetName() {
        ComputerPlayer computerPlayer = new ComputerPlayer('O', "Computer");
        assertEquals("Computer", computerPlayer.getName());
    }

    @Test
    public void ComputerTestGetScore() {
        ComputerPlayer computerPlayer = new ComputerPlayer('O', "Computer");
        assertEquals(0, computerPlayer.getScore());

        // Increment the score and test again
        computerPlayer.incrementScore();
        assertEquals(1, computerPlayer.getScore());
    }

    @Test
    public void ComputerTestSetSymbol() {
        ComputerPlayer computerPlayer = new ComputerPlayer('O', "Computer");
        assertEquals('O', computerPlayer.getSymbol());

        // Set a new symbol and test again
        computerPlayer.setSymbol('X');
        assertEquals('X', computerPlayer.getSymbol());
    }

    @Test
    public void ComputerTestIncrementScore() {
        ComputerPlayer computerPlayer = new ComputerPlayer('O', "Computer");
        assertEquals(0, computerPlayer.getScore());

        // Increment the score and test again
        computerPlayer.incrementScore();
        assertEquals(1, computerPlayer.getScore());
    }

}
