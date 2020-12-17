public class queueUsingArray {
	private int head;
	private int tail;
	private int size;
	private int capacity;
	private int queue[];

	public queueUsingArray(int capacity) {
		this.head = 0;
		this.tail = capacity - 1;
		this.size = 0;
		this.capacity = capacity;
		this.queue = new int[capacity];
	}

	public boolean isFull() {
		return (this.size == this.capacity);
	}

	public boolean isEmpty() {
		return (this.size == 0);
	}

	public int size() {
		return this.size;
	}

	public int first() {
		if (isEmpty()) {
			return Integer.MIN_VALUE;
		}
		System.out.println("First element in the queue: " + queue[head]);
		return queue[head];
	}

	public int last() {
		if (isEmpty()) {
			return Integer.MIN_VALUE;
		}
		System.out.println("Last element in the queue: " + queue[tail]);
		return queue[tail];
	}

	public void enqueue(int number) {
		if (isFull()) {
			System.out.println("Queue is full");
			return;
		}
		tail = (tail + 1) % capacity;
		queue[tail] = number;
		size++;
		System.out.println(number + " enqueued to queue");
	}

	public int dequeue() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return Integer.MIN_VALUE;
		}
		int number = queue[head];
		head = (head + 1) % capacity;
		size--;
		System.out.println(number + " dequeued from queue");
		return number;
	}

	public void display() {
		if (head == tail) {
			System.out.printf("Queue is empty");
			return;
		}
		System.out.print("Queue (from head to tail): [ ");
		for (int i = head; i <= tail; i++) {
			System.out.print(queue[i] + " ");
		}
		System.out.print("]");
	}

	public static void main(String[] args) {
		int size = 7;
		queueUsingArray queue = new queueUsingArray(size);
		queue.enqueue(14);
		queue.enqueue(2);
		queue.enqueue(6);
		queue.enqueue(9);
		queue.enqueue(21);
		queue.enqueue(10);
		queue.enqueue(5);
		queue.dequeue();
		queue.dequeue();
		queue.first();
		queue.last();
		System.out.println("Queue size: " + queue.size());
		System.out.println("Queue is full: " + queue.isFull());
		queue.display();
	}
}
