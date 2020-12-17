public class queueUsingLinkedList {
	static class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
		}
	}

	Node head, tail;
	int size = 0;

	public boolean isEmpty() {
		System.out.println("Queue is empty: " + (size == 0));
		return (size == 0);
	}

	public int size() {
		return size;
	}

	public int first() {
		if (size == 0) {
			return Integer.MIN_VALUE;
		}
		System.out.println("First element in the queue: " + head.data);
		return head.data;
	}

	public int last() {
		if (size == 0) {
			return Integer.MIN_VALUE;
		}
		System.out.println("Last element in the queue: " + tail.data);
		return tail.data;
	}

	public void enqueue(int number) {
		Node node = tail;
		tail = new Node(number);
		if (size == 0) {
			head = tail;
		} else {
			node.next = tail;
		}
		size++;
		System.out.println(number + " enqueued to queue");
	}

	public int dequeue() {
		int number = head.data;
		head = head.next;
		if (size == 0) {
			tail = null;
		}
		size--;
		System.out.println(number + " dequeued from queue");
		return number;
	}

	public void display() {
		System.out.print("Queue (from head to tail): [ ");
		Node node = head;
		while (node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}
		System.out.print("]");
	}

	public static void main(String[] args) {
		queueUsingLinkedList queue = new queueUsingLinkedList();
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
		queue.display();
	}
}
