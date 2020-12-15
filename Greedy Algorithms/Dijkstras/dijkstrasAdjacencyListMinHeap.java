import java.util.LinkedList;

public class dijkstrasAdjacencyListMinHeap {
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
		int distance;
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
			int INFINITY = Integer.MAX_VALUE;
			boolean[] SPT = new boolean[vertices];

			HeapNode[] heapNodes = new HeapNode[vertices];
			for (int i = 0; i < vertices; i++) {
				heapNodes[i] = new HeapNode();
				heapNodes[i].vertex = i;
				heapNodes[i].distance = INFINITY;
			}

			heapNodes[sourceVertex].distance = 0;

			MinHeap minHeap = new MinHeap(vertices);
			for (int i = 0; i < vertices; i++) {
				minHeap.insert(heapNodes[i]);
			}

			while (!minHeap.isEmpty()) {
				HeapNode extractedNode = minHeap.extractMin();

				int extractedVertex = extractedNode.vertex;
				SPT[extractedVertex] = true;

				LinkedList<Edge> list = adjacencyList[extractedVertex];
				for (int i = 0; i < list.size(); i++) {
					Edge edge = list.get(i);
					int destination = edge.destination;
					if (SPT[destination] == false) {
						int newKey = heapNodes[extractedVertex].distance + edge.weight;
						int currentKey = heapNodes[destination].distance;
						if (currentKey > newKey) {
							decreaseKey(minHeap, newKey, destination);
							heapNodes[destination].distance = newKey;
						}
					}
				}
			}
			printDijkstras(heapNodes, sourceVertex);
		}

		private void decreaseKey(MinHeap minHeap, int key, int vertex) {
			int index = minHeap.indexes[vertex];

			HeapNode node = minHeap.mH[index];
			node.distance = key;
			minHeap.bubbleUp(index);

		}

		private void printDijkstras(HeapNode[] resultSet, int sourceVertex) {
			System.out.println("Dijkstra's SPT Algorithm using Adjacency List and Min Heap: ");
			for (int i = 0; i < vertices; i++) {
				System.out.println("Source Vertex: " + sourceVertex + " to destination vertex: " + i + ", Distance: "
						+ resultSet[i].distance);
			}
		}
	}

	static class MinHeap {
		int capacity;
		int currentSize;
		HeapNode[] mH;
		int[] indexes;

		public MinHeap(int capacity) {
			this.capacity = capacity;
			mH = new HeapNode[capacity + 1];
			indexes = new int[capacity];
			mH[0] = new HeapNode();
			mH[0].distance = Integer.MIN_VALUE;
			mH[0].vertex = -1;
			currentSize = 0;
		}

		public int heapSize() {
			return currentSize;
		}

		public boolean isEmpty() {
			return currentSize == 0;
		}

		public void insert(HeapNode node) {
			currentSize++;
			int idx = currentSize;
			mH[idx] = node;
			indexes[node.vertex] = idx;
			bubbleUp(idx);
		}

		public void swap(int a, int b) {
			HeapNode temp = mH[a];
			mH[a] = mH[b];
			mH[b] = temp;
		}

		public void bubbleUp(int position) {
			int parentIdx = position / 2;
			int currentIdx = position;
			while (currentIdx > 0 && mH[parentIdx].distance > mH[currentIdx].distance) {
				HeapNode currentNode = mH[currentIdx];
				HeapNode parentNode = mH[parentIdx];

				indexes[currentNode.vertex] = parentIdx;
				indexes[parentNode.vertex] = currentIdx;
				swap(currentIdx, parentIdx);
				currentIdx = parentIdx;
				parentIdx = parentIdx / 2;
			}
		}

		public HeapNode extractMin() {
			HeapNode min = mH[1];
			HeapNode lastNode = mH[currentSize];

			indexes[lastNode.vertex] = 1;
			mH[1] = lastNode;
			mH[currentSize] = null;
			sinkDown(1);
			currentSize--;
			return min;
		}

		private void sinkDown(int k) {
			int smallest = k;
			int leftChildIdx = 2 * k;
			int rightChildIdx = 2 * k + 1;
			if (leftChildIdx < heapSize() && mH[smallest].distance > mH[leftChildIdx].distance) {
				smallest = leftChildIdx;
			}
			if (rightChildIdx < heapSize() && mH[smallest].distance > mH[rightChildIdx].distance) {
				smallest = rightChildIdx;
			}
			if (smallest != k) {
				HeapNode smallestNode = mH[smallest];
				HeapNode kNode = mH[k];

				indexes[smallestNode.vertex] = k;
				indexes[kNode.vertex] = smallest;
				swap(k, smallest);
				sinkDown(smallest);
			}
		}

		public void display() {
			for (int i = 0; i <= currentSize; i++) {
				System.out.println(" " + mH[i].vertex + " distance " + mH[i].distance);
			}
			System.out.println("________________________");
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
