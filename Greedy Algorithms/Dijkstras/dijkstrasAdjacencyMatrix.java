public class dijkstrasAdjacencyMatrix {
	static class Graph {
		int vertices;
		int matrix[][];

		public Graph(int vertex) {
			this.vertices = vertex;
			matrix = new int[vertex][vertex];
		}

		public void addEdge(int source, int destination, int weight) {
			matrix[source][destination] = weight;
			matrix[destination][source] = weight;
		}

		int getMinimumVertex(boolean[] mst, int[] key) {
			int minKey = Integer.MAX_VALUE;
			int vertex = -1;
			for (int i = 0; i < vertices; i++) {
				if (mst[i] == false && minKey > key[i]) {
					minKey = key[i];
					vertex = i;
				}
			}
			return vertex;
		}

		public void dijkstrasSPT(int sourceVertex) {
			boolean[] spt = new boolean[vertices];
			int[] distance = new int[vertices];
			int INFINITY = Integer.MAX_VALUE;

			for (int i = 0; i < vertices; i++) {
				distance[i] = INFINITY;
			}

			distance[sourceVertex] = 0;

			for (int i = 0; i < vertices; i++) {
				int vertex_U = getMinimumVertex(spt, distance);
				spt[vertex_U] = true;

				for (int vertex_V = 0; vertex_V < vertices; vertex_V++) {
					if (matrix[vertex_U][vertex_V] > 0) {
						if (spt[vertex_V] == false && matrix[vertex_U][vertex_V] != INFINITY) {
							int newKey = matrix[vertex_U][vertex_V] + distance[vertex_U];
							if (newKey < distance[vertex_V]) {
								distance[vertex_V] = newKey;
							}
						}
					}
				}
			}
			printDijkstras(distance, sourceVertex);
		}

		public void printDijkstras(int[] distance, int sourceVertex) {
			System.out.println("Dijkstra's SPT Algorithm using Adjacency Matrix: ");
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
