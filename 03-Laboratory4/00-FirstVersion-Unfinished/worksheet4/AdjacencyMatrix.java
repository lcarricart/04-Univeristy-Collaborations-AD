package worksheet4;

public class AdjacencyMatrix {
	private int[][] adjMatrix;
	
	public AdjacencyMatrix(int[][] adjMatrix) {
		this.adjMatrix = adjMatrix;
	}

	public int get(int vertex, int u) {
		return adjMatrix[vertex][u];	// Not explicit what this needs to return. Probably the value of the matrix at that position (representing the weight). Ask GPT what was the intention of the lab
	}

	public int numOfVertices() {
		return adjMatrix.length;
	}
}
