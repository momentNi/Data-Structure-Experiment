import java.lang.reflect.Array;

public class Queue<Type> {
	private static final int DEFAULT_CAPACITY = 10;
	private int front;
	private int rear;
	private Type[] elements;
	
	@SuppressWarnings("unchecked")
	public Queue() {
		elements = (Type[])Array.newInstance(Event.class, DEFAULT_CAPACITY);
		rear = front = 0;
	}
	
	public void clear() {
		front = rear = 0;
	}
	
	public boolean isEmpty() {
		return front == rear;
	}
	
	public boolean isFull() {
		return (rear + 1) % elements.length == front;
	}
	
	public int size() {
		return (rear - front + elements.length) % elements.length;
	}
	
	public void enQueue(Type item) throws Exception {
		if (isFull())
			throw new Exception("OutOfQueueException");
		rear = (rear + 1) % elements.length;
		elements[rear] = item;
	}
	
	public Type deQueue() throws Exception {
		if(isEmpty())
			throw new Exception("EmptyQueueException");
		front = (front + 1) % elements.length;
		return elements[front];
	}
	
	public Type getFront() throws Exception {
		if (isEmpty())
			throw new Exception("EmptyQueueException");
		return elements[(front + 1) % elements.length];
	}
}
