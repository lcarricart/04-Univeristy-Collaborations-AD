package worksheet4;

import java.util.ArrayList;

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

	public int getWeight(int v, int u) {
		return neighbourMatrix.get(v, u);
	}

	public AdjacencyList somePath(int node1, int length) {
		AdjacencyList aPath = new AdjacencyList(node1);
		AdjacencyList currentNeighbours = new AdjacencyList();
		
		int currentNode, randomIndex;
		int rowsVisited = 1;
		boolean reachedEnd = false;
		boolean foundOneNode = false;
		
		currentNeighbours = neighbourLists.getNeighboursFor(node1);
		
		while ((currentNeighbours != null) && (currentNeighbours.size() != 0) && (rowsVisited <= length) && !reachedEnd) {
			ArrayList<Integer> visitedNeighbours = new ArrayList<Integer>();
			foundOneNode = false;
			
			// This while could be significantly improved. Finding the last node is statistically hard among matrices of several nodes.
			do {
				randomIndex = RandomNumber.between(0, currentNeighbours.size() - 1);
				currentNode = currentNeighbours.getVertex(randomIndex);
				
				if (!visitedNeighbours.contains(currentNode)) {
					visitedNeighbours.add(currentNode);
				}
				
				if (!aPath.contains(currentNode)) {
					foundOneNode = true;
				}
				
			} while (!foundOneNode && visitedNeighbours.size() < currentNeighbours.size());
			
			if (foundOneNode) {
				aPath.add(currentNode);
				currentNeighbours = neighbourLists.getNeighboursFor(currentNode);
				rowsVisited++;
			} else {
				reachedEnd = true;
			}
		}
		return aPath;
	}
	
//	public AdjacencyList longestPath(int node1, int length) {
//		
//	}
}
