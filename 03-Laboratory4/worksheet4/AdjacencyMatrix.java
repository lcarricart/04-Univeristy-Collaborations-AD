package worksheet4;

public class AdjacencyMatrix {
	private int[][] adjMatrix;
	
	public AdjacencyMatrix(int[][] adjMatrix) {
		this.adjMatrix = adjMatrix;
	}

	// indexes
	public int get(int v, int u) {
		return adjMatrix[v][u];
	}

	public int numOfVertices() {
		return adjMatrix.length;
	}
}
