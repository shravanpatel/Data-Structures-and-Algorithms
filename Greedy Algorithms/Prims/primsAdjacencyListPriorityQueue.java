import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import javafx.util.Pair;

public class primsAdjacencyListPriorityQueue {
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

	static class ResultSet {
		int parent;
		int weight;
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

		public void addEgde(int source, int destination, int weight) {
			Edge edge = new Edge(source, destination, weight);
			adjacencyList[source].addFirst(edge);

			edge = new Edge(destination, source, weight);
			adjacencyList[destination].addFirst(edge);
		}

		public void primsMST() {
			boolean[] mst = new boolean[vertices];
			ResultSet[] resultSet = new ResultSet[vertices];
			int[] key = new int[vertices];

			for (int i = 0; i < vertices; i++) {
				key[i] = Integer.MAX_VALUE;
				resultSet[i] = new ResultSet();
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

			key[0] = 0;
			Pair<Integer, Integer> p0 = new Pair<>(key[0], 0);
			pq.offer(p0);

			resultSet[0] = new ResultSet();
			resultSet[0].parent = -1;

			while (!pq.isEmpty()) {
				Pair<Integer, Integer> extractedPair = pq.poll();

				int extractedVertex = extractedPair.getValue();
				mst[extractedVertex] = true;

				LinkedList<Edge> list = adjacencyList[extractedVertex];
				for (int i = 0; i < list.size(); i++) {
					Edge edge = list.get(i);
					if (mst[edge.destination] == false) {
						int destination = edge.destination;
						int newKey = edge.weight;
						if (key[destination] > newKey) {
							Pair<Integer, Integer> p = new Pair<>(newKey, destination);
							pq.offer(p);
							resultSet[destination].parent = extractedVertex;
							resultSet[destination].weight = newKey;
							key[destination] = newKey;
						}
					}
				}
			}
			printPrims(resultSet);
		}

		public void printPrims(ResultSet[] resultSet) {
			int totalMinWeight = 0;
			System.out.println("Prim’s MST Algorithm using Adjacency List and Priority Queue: ");
			for (int i = 1; i < vertices; i++) {
				System.out.println(
						"Vertex: " + i + " to vertex: " + resultSet[i].parent + ", Weight: " + resultSet[i].weight);
				totalMinWeight += resultSet[i].weight;
			}
			System.out.println("Total minimum weight: " + totalMinWeight);
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
        graph.primsMST();
	}
}
