import java.util.LinkedList;

public class primsAdjacencyListMinHeap {
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
			boolean[] inHeap = new boolean[vertices];
			ResultSet[] resultSet = new ResultSet[vertices];
			int[] key = new int[vertices];
			HeapNode[] heapNodes = new HeapNode[vertices];
			for (int i = 0; i < vertices; i++) {
				heapNodes[i] = new HeapNode();
				heapNodes[i].vertex = i;
				heapNodes[i].key = Integer.MAX_VALUE;
				resultSet[i] = new ResultSet();
				resultSet[i].parent = -1;
				inHeap[i] = true;
				key[i] = Integer.MAX_VALUE;
			}

			heapNodes[0].key = 0;

			MinHeap minHeap = new MinHeap(vertices);
			for (int i = 0; i < vertices; i++) {
				minHeap.insert(heapNodes[i]);
			}

			while (!minHeap.isEmpty()) {
				HeapNode extractedNode = minHeap.extractMin();

				int extractedVertex = extractedNode.vertex;
				inHeap[extractedVertex] = false;

				LinkedList<Edge> list = adjacencyList[extractedVertex];
				for (int i = 0; i < list.size(); i++) {
					Edge edge = list.get(i);
					if (inHeap[edge.destination]) {
						int destination = edge.destination;
						int newKey = edge.weight;
						if (key[destination] > newKey) {
							decreaseKey(minHeap, newKey, destination);
							resultSet[destination].parent = extractedVertex;
							resultSet[destination].weight = newKey;
							key[destination] = newKey;
						}
					}
				}
			}
			printPrims(resultSet);
		}

		public void decreaseKey(MinHeap heap, int key, int vertex) {
			int index = heap.indexes[vertex];
			HeapNode node = heap.mH[index];
			node.key = key;
			heap.bubbleUp(index);
		}

		public void printPrims(ResultSet[] resultSet) {
			int totalMinWeight = 0;
			System.out.println("Prim’s MST Algorithm using Adjacency List and Min Heap: ");
			for (int i = 1; i < vertices; i++) {
				System.out.println("Vertex: " + i + " to vertex: " + resultSet[i].parent + ", Weight: " + resultSet[i].weight);
				totalMinWeight += resultSet[i].weight;
			}
			System.out.println("Total minimum key: " + totalMinWeight);
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
			mH[0].key = Integer.MIN_VALUE;
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
			while (currentIdx > 0 && mH[parentIdx].key > mH[currentIdx].key) {
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
			if (leftChildIdx < heapSize() && mH[smallest].key > mH[leftChildIdx].key) {
				smallest = leftChildIdx;
			}
			if (rightChildIdx < heapSize() && mH[smallest].key > mH[rightChildIdx].key) {
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
				System.out.println(" " + mH[i].vertex + " distance " + mH[i].key);
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
        graph.primsMST();
    }
}
