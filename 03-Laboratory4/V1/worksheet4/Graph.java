package worksheet4;

import utilities.RandomNumber;

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

	// (do not delete) I think node1 < node2 is assumed. Otherwise it doesn't work
	public int getWeight(int v, int u) {
		return neighbourMatrix.get(v, u);
	}

	// Connect two nodes through a random path. The key will be generating a random number (length of the path) and iterate also randomly through nodes but skipping the one I want to connect.
	// Then, return a list of the created path (starts with node1, ends with node2).
	// TODO For some weird reason, even though I coded this to not repeat nodes, sometimes they repeat (I can put pathLength = 30 and continues to run properly, tho it should not)
	public AdjacencyList somePath(int node1, int node2) {
		AdjacencyList aPath = new AdjacencyList(node1);
		
		// Max length of path is 6 because at the end we add the desired node (final length = 7)
		int pathLength = RandomNumber.between(1, 6);
		int newNode;
		
		for (int i = 0; i < pathLength; i++) {
			do {
				newNode = RandomNumber.between(0, 7);
			} while (aPath.contains(newNode) && newNode != node2);
			
			aPath.add(newNode);
		}
		aPath.add(node2);
		
		return aPath;
	}
}
