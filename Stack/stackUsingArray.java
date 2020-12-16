public class stackUsingArray {
	private int top = -1;  
	private int capacity; 
	private int stack[]; 
	
	public stackUsingArray(int capacity) {
		this.capacity = capacity;
		this.stack = new int[capacity];
	}
	
	public boolean isEmpty() {
		System.out.println("Stack is empty: " + (top < 0));
		return (top < 0);
	}
	
	public int size() {
		System.out.println("Stack size: " + (top + 1));
		return (top + 1);
	}

	public void push(int number) {
		if(top == capacity - 1) {
			System.out.println("Stack Overflow.");
			return;
		}
		top++;
		stack[top] = number;
		System.out.println("Element " + stack[top] + " is added to stack");
	}
	
	public Integer peek() {
		if(top < 0) {
			System.out.println("Stack Underflow.");
			return null;
		}
		System.out.println("Top element of stack: " + stack[top]);
		return stack[top];
	}
	
	public void pop() {
		if(top < 0) {
			System.out.println("Stack Underflow/");
		}
		System.out.println("Popped " + stack[top] + " out of the stack");
		top--;
	}
	
	
	public void display() {
		System.out.print("Stack (from top to bottom): [ ");
		for(int i = top; i >= 0; i--) {
			System.out.print(stack[i] + " ");
		}
		System.out.print("]");
	}
	
	public static void main(String[] args) {
		int size = 7;
		stackUsingArray stack = new stackUsingArray(size);
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
