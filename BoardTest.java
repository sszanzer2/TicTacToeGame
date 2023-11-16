package ss;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

	@Test
	public void TestingMarkingPositionCorrectly() {
		GameBoard board = new GameBoard();
		assertTrue(board.markPosition(1,1, 'X'));
		assertFalse(board.markPosition(1,1 ,'O'));
		assertFalse(board.markPosition(0, 0, 'O'));
	}
	
	@Test
	public void TestingCheckWinWorks() {
		GameBoard board = new GameBoard();
		assertFalse(board.checkWin('X'));
		board.markPosition(1, 1, 'X');
		board.markPosition(2, 2, 'X');
		board.markPosition(3, 3, 'X');
		
		assertTrue(board.checkWin('X'));
		
		board = new GameBoard();
		board.markPosition(1, 1, 'X');
		board.markPosition(1, 2, 'X');
		board.markPosition(1, 3, 'X');
		
		assertTrue(board.checkWin('X'));
		
		board = new GameBoard();
		board.markPosition(1, 1, 'X');
		board.markPosition(2, 1, 'X');
		board.markPosition(3, 1, 'X');
		
		assertTrue(board.checkWin('X'));
	}
	
	@Test
	public void TestCheckDrawWorks() {
		GameBoard board = new GameBoard();
		board.markPosition(1, 1, 'X');
		board.markPosition(2, 1, 'O');
		board.markPosition(3, 1, 'X');
		board.markPosition(1, 2, 'O');
		board.markPosition(1, 3, 'X');
		board.markPosition(2, 2, 'O');
		board.markPosition(2, 3, 'X');
		board.markPosition(3, 2, 'O');
		board.markPosition(3, 3, 'X');
		
		assertTrue(board.checkDraw());
		
		
		
	}

}
