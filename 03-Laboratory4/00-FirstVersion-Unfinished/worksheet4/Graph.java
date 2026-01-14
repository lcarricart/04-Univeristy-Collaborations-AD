package worksheet4;

public class Graph {
	// Nodes are just int (not a separate class) in this program
	private AdjacencyMatrix neighbourMatrix;
	private AdjacencyLists neighbourLists;
	
	public Graph(int[][] adjMatrix) {
		this.neighbourMatrix = new AdjacencyMatrix(adjMatrix);
		this.neighbourLists = new AdjacencyLists(neighbourMatrix);
	}
	
	public AdjacencyList getNeighboursFor(int vertice) {
		return neighbourLists.getNeighboursFor(vertice);
	}

	public int numOfVertices() {
		return neighbourMatrix.numOfVertices();
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
	public int getWeight(int v, int u) {
		return neighbourMatrix.get(v, u);
	}

	public AdjacencyList somePath(int node, int length) {
		return null;
	}
}
