package worksheet4;

public class Graph {
	// Nodes are just int (not a separate class) in this program
	private AdjacencyMatrix neighboursMatrix;
	private AdjacencyLists neighboursLists;
	
	public Graph(int[][] adjMatrix) {
		this.neighboursMatrix = new AdjacencyMatrix(adjMatrix);
		this.neighboursLists = new AdjacencyLists(neighboursMatrix);
	}
	
	public AdjacencyList getNeighboursFor(int vertice) {
		return null;
	}

	public int numOfVertices() {
		int numVertices = 0;
		
		return numVertices;
	}
	
	public int numOfEdges() {
		int counter = 0;
		
		for(int i = 0; i < numOfVertices(); i++) {
			for(int j = 0; j < numOfVertices(); j++) {
				if(getWeight(i, j) == 1) {
					counter++;
				}
			}
		}
		
		return counter;
	}

	// (do not delete) I think node1 < node2 is assumed. Otherwise it doesn't work
	public int getWeight(int vertice, int u) {
		return neighboursMatrix.get(vertice, u);
	}

	public AdjacencyList somePath(int node, int length) {
		return null;
	}
}
