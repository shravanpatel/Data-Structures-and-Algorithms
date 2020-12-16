import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class primsAdjacencyListPriorityQueueDecreaseKey {
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

	static class HeapNode {
		int vertex;
		int key;
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

		public void addEdge(int source, int destination, int weight) {
			Edge edge = new Edge(source, destination, weight);
			adjacencyList[source].addFirst(edge);

			edge = new Edge(destination, source, weight);
			adjacencyList[destination].addFirst(edge);
		}

		public void primsMST() {
			boolean[] inPriorityQueue = new boolean[vertices];
			ResultSet[] resultSet = new ResultSet[vertices];
			int[] key = new int[vertices];
			HeapNode[] heapNodes = new HeapNode[vertices];

			for (int i = 0; i < vertices; i++) {
				heapNodes[i] = new HeapNode();
				heapNodes[i].vertex = i;
				heapNodes[i].key = Integer.MAX_VALUE;
				resultSet[i] = new ResultSet();
				resultSet[i].parent = -1;
				inPriorityQueue[i] = true;
				key[i] = Integer.MAX_VALUE;
			}

			heapNodes[0].key = 0;

			PriorityQueue<HeapNode> pq = new PriorityQueue<>(vertices, new Comparator<HeapNode>() {
				@Override
				public int compare(HeapNode o1, HeapNode o2) {
					return o1.key - o2.key;
				}
			});

			for (int i = 0; i < vertices; i++) {
				pq.offer(heapNodes[i]);
			}

			while (!pq.isEmpty()) {
				HeapNode extractedNode = pq.poll();

				int extractedVertex = extractedNode.vertex;
				inPriorityQueue[extractedVertex] = false;

				LinkedList<Edge> list = adjacencyList[extractedVertex];
				for (int i = 0; i < list.size(); i++) {
					Edge edge = list.get(i);
					if (inPriorityQueue[edge.destination]) {
						int destination = edge.destination;
						int newKey = edge.weight;
						if (key[destination] > newKey) {
							decreaseKey(pq, newKey, destination);
							resultSet[destination].parent = extractedVertex;
							resultSet[destination].weight = newKey;
							key[destination] = newKey;
						}
					}
				}
			}
			printPrims(resultSet);
		}

		public void decreaseKey(PriorityQueue<HeapNode> pq, int key, int vertex) {
			Iterator it = pq.iterator();

			while (it.hasNext()) {
				HeapNode heapNode = (HeapNode) it.next();
				if (heapNode.vertex == vertex) {
					pq.remove(heapNode);
					heapNode.key = key;
					pq.offer(heapNode);
					break;
				}
			}
		}

		public void printPrims(ResultSet[] resultSet) {
			int totalMinWeight = 0;
			System.out.println("Prim’s MST Algorithm using Adjacency List and Priority Queue (with decrease key): ");
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
