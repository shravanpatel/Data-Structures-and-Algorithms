public class stackUsingLinkedList {
	static class Node {
		int data;
		Node next;
		
		public Node(int data) {
			this.data = data;
		}
	}
	
	Node top = null;
	int size = 0;
	
	public boolean isEmpty() {
		System.out.println("Stack is empty: " + (size == 0));
		return (size == 0);
	}
	
	public int size() {
		return size;
	}
	
	public void push(int data) {
		Node node = new Node(data);
		if(size() == 0) {
			top = node;
		} else {
			Node temp = top;
			node.next = temp;
			top = node;
		}
		System.out.println("Element " + data + " is added to stack");
		size++;
	}
	
	public Integer peek() {
		if(size() == 0) {
			System.out.println("Stack is Empty");
			return -1;
		} else {
			System.out.println("Top element of stack: " + top.data);
			return top.data;
		}
	}
	
	public void pop() {
		if(size() == 0) {
			System.out.println("Stack is Empty");
		} else {
			Node temp = top;
			top = top.next;
			size--;
			System.out.println("Popped " + temp.data + " out of the stack");
		}
	}
	
	public void display() {
		System.out.print("Stack (from top to bottom): [ ");
		Node node = top;
		while(node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}
		System.out.print("]");
	}

	public static void main(String[] args) {
		stackUsingLinkedList stack = new stackUsingLinkedList();
		stack.isEmpty();
		stack.push(14);
		stack.push(2);
		stack.push(6);
		stack.push(9);
		stack.push(21);
		stack.push(10);
		stack.push(5);
		stack.pop();
		stack.size();
		stack.peek();
		stack.pop();
		stack.display();
	}
}
