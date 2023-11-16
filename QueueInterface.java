package ss;

public interface QueueInterface<T> {
	  void enqueue(T element); // Public method
	  T dequeue(); // Public method
	  T peek(); // Public method
	  boolean isEmpty(); // Public method
	  int size(); // Public method
	  void clear(); // Public method
	}
