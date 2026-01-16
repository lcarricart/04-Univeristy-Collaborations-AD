/*
 * B-REE3-VSP HAW Hamburg
 * 
 * Created on : 10-12-2020
 * Author     : Björn Gottfried
 *
 *-----------------------------------------------------------------------------
 * Revision History (Release 1.0.0.0)
 *-----------------------------------------------------------------------------
 * VERSION     AUTHOR/      DESCRIPTION OF CHANGE
 * OLD/NEW     DATE                RFC NO
 *-----------------------------------------------------------------------------
 * --/1.0  | B. Gottfried  | Initial Create.
 *         | 10-12-20      |
 *---------|---------------|---------------------------------------------------
 *         | author        | 
 *         | dd-mm-yy      | 
 *---------|---------------|---------------------------------------------------
 */

package utilities;

import worksheet4.AdjacencyList;
import worksheet4.Graph;

public class GraphPainter{
	
	/********************************************************************************
	 * Testprogram
	 ********************************************************************************/
	
	public static void main(String[] args) {
		final int H = 0;
		int[][] adjMatrix = {
				{H, 0, 0, 1, 1, 0, 0, 0}, // vertice 0 has two neighbours 3 and 4
				{0, H, 1, 0, 0, 0, 1, 0}, 
				{0, 0, H, 0, 0, 0, 0, 1}, 
				{0, 0, 0, H, 1, 0, 0, 1},
				{0, 0, 0, 0, H, 1, 0, 1},
				{0, 0, 0, 0, 0, H, 1, 1},
				{0, 0, 0, 0, 0, 0, H, 1},
				{0, 0, 0, 0, 0, 0, 0, H}};

		Graph g = new Graph(adjMatrix);
		
		Visualization visualization = new Visualization(g);

		AdjacencyList aPath = g.somePath(3, 4);
		visualization.setAPath(aPath);
		
		// Print all the vertices and their neighbours
		for (int v = 0; v < g.numOfVertices(); v++) {
			System.out.print("Neighbors on the corner " + v + ":");
			AdjacencyList adjList = g.getNeighboursFor(v);
			for (Integer neighbour: adjList) {
				System.out.print(" " + neighbour.toString());
			}
			System.out.println();
		}
		System.out.println("Number of edges: "+g.numOfEdges());
		
		for (Integer i : aPath) {
			System.out.println("Path i = " + i);
		}
	}
}