import java.util.Comparator;
import java.util.PriorityQueue;

public class priorityQueue {
	static class Customer implements Comparable<Customer> {
		Long priority;
		String name;

		public Customer(Long priority, String name) {
			this.priority = priority;
			this.name = name;
		}

		public Customer(String name) {
			this.name = name;
		}

		public Long getPriority() {
			return priority;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "Customer [priority=" + priority + ", name=" + name + "]";
		}

		@Override
		public int compareTo(Customer cust) {
			return this.getPriority().compareTo(cust.getPriority());
		}
	}

	public static void main(String[] args) {
		Comparator<Customer> sortByName = Comparator.comparing(Customer::getName);
		PriorityQueue<Customer> customerPQ = new PriorityQueue<>(sortByName);

		customerPQ.add(new Customer(6l, "Jinny"));
		customerPQ.add(new Customer(12l, "Eugene"));
		customerPQ.add(new Customer(4l, "Rozella"));
		customerPQ.add(new Customer(23l, "Manda"));
		customerPQ.add(new Customer(1l, "Douglass"));
		customerPQ.add(new Customer(34l, "Zelma"));
		customerPQ.add(new Customer(13l, "Buford"));
		customerPQ.add(new Customer(9l, "Patricia"));
		customerPQ.add(new Customer(16l, "Kris"));

		while (true) {
			Customer c = customerPQ.poll();
			if (c == null) {
				break;
			}
			System.out.println(c);
		}
	}
}