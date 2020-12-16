public class primsAdjacencyMatrix {
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

		class ResultSet {
			int parent;
			int weight;
		}

		public void primsMST() {
			boolean[] mst = new boolean[vertices];
			ResultSet[] resultSet = new ResultSet[vertices];
			int[] key = new int[vertices];

			for (int i = 0; i < vertices; i++) {
				key[i] = Integer.MAX_VALUE;
				resultSet[i] = new ResultSet();
			}

			key[0] = 0;
			resultSet[0] = new ResultSet();
			resultSet[0].parent = -1;

			for (int i = 0; i < vertices; i++) {
				int vertex = getMinimumVertex(mst, key);
				mst[vertex] = true;

				for (int j = 0; j < vertices; j++) {
					if (matrix[vertex][j] > 0) {
						if (mst[j] == false && matrix[vertex][j] < key[j]) {
							key[j] = matrix[vertex][j];
							resultSet[j].parent = vertex;
							resultSet[j].weight = key[j];
						}
					}
				}
			}
			printPrims(resultSet);
		}

		public void printPrims(ResultSet[] resultSet) {
			int totalMinWeight = 0;
			System.out.println("Prim’s MST Algorithm using Adjacency Matrix: ");
			for (int i = 1; i < vertices; i++) {
				System.out.println("Vertex: " + i + " to vertex: " + resultSet[i].parent + ", Weight: " + resultSet[i].weight);
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