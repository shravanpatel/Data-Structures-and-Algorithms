import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;
import javafx.util.Pair;

public class dijkstrasAdjacencyListTreeSet {
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

	static class PairComparator implements Comparator<Pair<Integer, Integer>> {
		@Override
		public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
			int key1 = o1.getKey();
			int key2 = o2.getKey();
			return key1 - key2;
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

			TreeSet treeSet = new TreeSet<>(new PairComparator());
			Pair<Integer, Integer> p0 = new Pair<>(distance[0], 0);

			distance[0] = 0;
			treeSet.add(p0);

			while (!treeSet.isEmpty()) {
				Pair<Integer, Integer> extractedPair = (Pair<Integer, Integer>) treeSet.pollFirst();

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
								treeSet.add(p);
								distance[destination] = newKey;
							}
						}
					}
				}
			}
			printDijkstras(distance, sourceVertex);
		}

		private void printDijkstras(int[] distance, int sourceVertex) {
			System.out.println("Dijkstra's SPT Algorithm using Adjacency List and TreeSet: ");
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
