import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import javafx.util.Pair;

public class dijkstrasAdjacencyListPriorityQueue {
	static class Edge {
		int source;
		int destination;
		int weight;

		public Edge(int source, int destination, int weight) {
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}
	}

	static class Graph {
		int vertices;
		LinkedList<Edge>[] adjacencyList;

		Graph(int vertices) {
			this.vertices = vertices;
			adjacencyList = new LinkedList[vertices];
			for (int i = 0; i < vertices; i++) {
				adjacencyList[i] = new LinkedList<>();
			}
		}

		public void addEdge(int source, int destination, int weight) {
			Edge edge = new Edge(source, destination, weight);
			adjacencyList[source].addFirst(edge);

			edge = new Edge(destination, source, weight);
			adjacencyList[destination].addFirst(edge);
		}

		public void dijkstrasSPT(int sourceVertex) {
			boolean[] SPT = new boolean[vertices];
			int[] distance = new int[vertices];

			for (int i = 0; i < vertices; i++) {
				distance[i] = Integer.MAX_VALUE;
			}

			PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertices,
					new Comparator<Pair<Integer, Integer>>() {
						@Override
						public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
							int key1 = p1.getKey();
							int key2 = p2.getKey();
							return key1 - key2;
						}
					});

			distance[0] = 0;
			Pair<Integer, Integer> p0 = new Pair<>(distance[0], 0);
			pq.offer(p0);

			while (!pq.isEmpty()) {
				Pair<Integer, Integer> extractedPair = pq.poll();

				int extractedVertex = extractedPair.getValue();
				if (SPT[extractedVertex] == false) {
					SPT[extractedVertex] = true;
					LinkedList<Edge> list = adjacencyList[extractedVertex];
					for (int i = 0; i < list.size(); i++) {
						Edge edge = list.get(i);
						int destination = edge.destination;
						if (SPT[destination] == false) {
							int newKey = distance[extractedVertex] + edge.weight;
							int currentKey = distance[destination];
							if (currentKey > newKey) {
								Pair<Integer, Integer> p = new Pair<>(newKey, destination);
								pq.offer(p);
								distance[destination] = newKey;
							}
						}
					}
				}
			}
			printDijkstras(distance, sourceVertex);
		}

		private void printDijkstras(int[] distance, int sourceVertex) {
			System.out.println("Dijkstra's SPT Algorithm using Adjacency List and Priority Queue: ");
			for (int i = 0; i < vertices; i++) {
				System.out.println("Source Vertex: " + sourceVertex + " to destination vertex: " + i + ", Distance: "
						+ distance[i]);
			}
		}
	}

	public static void main(String[] args) {
		int vertices = 7;
		Graph graph = new Graph(vertices);
		graph.addEdge(0, 1, 7);
		graph.addEdge(0, 2, 15);
		graph.addEdge(1, 3, 50);
		graph.addEdge(1, 4, 29);
		graph.addEdge(1, 6, 58);
		graph.addEdge(2, 3, 25);
		graph.addEdge(2, 4, 33);
		graph.addEdge(3, 4, 20);
		graph.addEdge(3, 5, 47);
		graph.addEdge(4, 5, 38);
		int sourceVertex = 0;
		graph.dijkstrasSPT(sourceVertex);
	}
}