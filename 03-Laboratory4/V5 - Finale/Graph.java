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

	
	public AdjacencyList somePath(int vertex, int length) {
		int currentVertex = vertex;
		int counter = 0;
		
		AdjacencyList aPath = new AdjacencyList(vertex);
		AdjacencyList visitedNeighbours = new AdjacencyList(vertex);
			
		for(int lonelyNeighbour = 0; lonelyNeighbour < numOfVertices() && counter < length - 1; lonelyNeighbour++) {
			
			AdjacencyList neighbours = getNeighboursFor(currentVertex);
			
			if (neighbours != null && neighbours.size() != 0 && !visitedNeighbours.contains(lonelyNeighbour) && neighbours.contains(lonelyNeighbour)) {
				
				currentVertex = lonelyNeighbour;
				aPath.add(currentVertex);
				visitedNeighbours.add(currentVertex);
				counter++;
			}
        }

		return aPath;
	}
	
	public AdjacencyList funnyPath(int vertex, int length, int multiple) {
		int currentVertex = vertex;
		AdjacencyList aPath = new AdjacencyList(vertex);
		
		for (int i = 0; i < length - 1; i++) {
			
			int tempVertex = (currentVertex + multiple) % numOfVertices();
			
			currentVertex = tempVertex;
			aPath.add(currentVertex);
		}
		
		return aPath;
	}
}
