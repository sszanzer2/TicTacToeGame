package ss;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	public void IncrementScoreCorrectlyTest() {
		Player player = new Player('X', "playerX");		
		player.incrementScore();
		
		assertEquals(1, player.getScore());
	}
	
	@Test
	public void SetSymbolSetsCorrectlyTest() {
		Player player = new Player('X', "playerX");
		assertEquals('X', player.getSymbol());
		
	}

}
