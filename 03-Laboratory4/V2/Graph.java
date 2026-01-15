package worksheet4;

public class Graph {
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

	public int getWeight(int v, int u) {
		return neighbourMatrix.get(v, u);
	}

	
	public AdjacencyList somePath(int vertice, int length) {
		
		AdjacencyList aPath = new AdjacencyList(vertice);
		
		int currentVertice = vertice;
		

		for (int i = 0; i < length; i++) {
			
			AdjacencyList neighbours = getNeighboursFor(currentVertice);
			
			if (neighbours != null && neighbours.size() != 0) {

				currentVertice = neighbours.getVertex(0);
				aPath.add(currentVertice);
	        }
		}

		return aPath;
	}
}
