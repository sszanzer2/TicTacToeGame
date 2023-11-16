package ss;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QueueTest {

	@Test
	public void TestCheckingIfEnqueuedCorrectly() {
		MyQueue<String> myqueue = new MyQueue<>();
		myqueue.enqueue("playerX");
		assertEquals(1, myqueue.size());
		assertFalse(myqueue.isEmpty());
		assertEquals("playerX", myqueue.peek());
		
	}
	@Test
	public void TestCheckingIfDequeuedCorrectly() {
		MyQueue<String> myqueue = new MyQueue<>();
		myqueue.enqueue("PlayerX");
		myqueue.enqueue("PlayerO");
		myqueue.dequeue();
		assertEquals(1, myqueue.size());
		assertEquals("PlayerO", myqueue.peek());
	}
	
	@Test
	public void TestingClearQueue() {
		MyQueue<String> myqueue = new MyQueue<>();
		myqueue.enqueue("PlayerX");
		myqueue.enqueue("PlayerO");
		assertFalse(myqueue.isEmpty());
		myqueue.clear();
		assertEquals(0, myqueue.size());
		
	}
	
	/*@Test
	public void TestingToArrayIfCorrect() {
		MyQueue<String> myqueue = new MyQueue<>();
		myqueue.enqueue("PlayerX");
		myqueue.enqueue("PlayerO");
		
		String[] array = myqueue.toArray();
		assertArrayEquals(new String[]{"PlayerX", "PlayerO"}, array);
	}*/
	
	

}
