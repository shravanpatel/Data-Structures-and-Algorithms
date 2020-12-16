import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class kruskals {
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
		ArrayList<Edge> edges = new ArrayList<>();

		public Graph(int vertices) {
			this.vertices = vertices;
		}

		public void addEdge(int source, int destination, int weight) {
			Edge edge = new Edge(source, destination, weight);
			edges.add(edge);
		}

		public void kruskalsMST() {
			PriorityQueue<Edge> pq = new PriorityQueue<>(edges.size(), Comparator.comparingDouble(o -> o.weight));

			for (int i = 0; i < edges.size(); i++) {
				pq.add(edges.get(i));
			}

			int[] parent = new int[vertices];
			for (int i = 0; i < vertices; i++) {
				parent[i] = i;
			}

			ArrayList<Edge> mst = new ArrayList<>();
			int index = 0;

			while (index < vertices - 1) {
				Edge edge = pq.remove();
				int mSet= find(parent, edge.source);
				int nSet = find(parent, edge.destination);

				if (mSet == nSet) {

				} else {
					mst.add(edge);
					index++;
					union(parent, mSet, nSet);
				}
			}
			System.out.println("Kruskal's MST Algorithm: ");
			printGraph(mst);
		}

		private int find(int[] parent, int vertex) {
			if (parent[vertex] != vertex) {
				return find(parent, parent[vertex]);
			}
			return vertex;
		}

		private void union(int[] parent, int mSet, int nSet) {
			int mSetParent = find(parent, mSet);
			int nSetParent = find(parent, nSet);
			parent[nSetParent] = mSetParent;
		}

		private void printGraph(ArrayList<Edge> edgeList) {
			for (int i = 0; i < edgeList.size(); i++) {
				Edge edge = edgeList.get(i);
				System.out.println("Vertex: " + edge.source + " to vertex: " + edge.destination + ", Weight: " + edge.weight);
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
		graph.kruskalsMST();
	}
}
