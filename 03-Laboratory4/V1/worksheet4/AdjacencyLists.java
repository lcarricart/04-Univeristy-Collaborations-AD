package worksheet4;

import java.util.ArrayList;

public class AdjacencyLists {
	private ArrayList<AdjacencyList> neighboursLists;
	
	public AdjacencyLists(AdjacencyMatrix neighboursMatrix) {
		int rows = neighboursMatrix.numOfVertices();
		
		for(int i = 0; i < rows; i++) {
			AdjacencyList adjacencyRow = new AdjacencyList();
			
			for(int j = 0; j < rows; j++) {
				if(neighboursMatrix.get(i, j) == 1) {
					adjacencyRow.add(j*10);
				}
			}
		}
	}

	public AdjacencyList getNeighboursFor(int vertice) {
		return neighboursLists.get(vertice);
	}
}
