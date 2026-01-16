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

package worksheet4;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Iterator;

import javax.swing.JFrame;

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
		
		int[][] undirectedMatrix = {
				{H, 0, 0, 1, 1, 0, 0, 0}, // vertice 0 has two neighbours 3 and 4
				{0, H, 1, 0, 0, 0, 1, 0}, 
				{0, 1, H, 0, 0, 0, 0, 1}, 
				{1, 0, 0, H, 1, 0, 0, 1},
				{1, 0, 0, 1, H, 1, 0, 1},
				{0, 0, 0, 0, 1, H, 1, 1},
				{0, 1, 0, 0, 0, 1, H, 1},
				{0, 0, 1, 1, 1, 1, 1, H}};
		
		int[][] fullMatrix = {
				{H, 1, 1, 1, 1, 1, 1, 1}, // vertice 0 has two neighbours 3 and 4
				{1, H, 1, 1, 1, 1, 1, 1}, 
				{1, 1, H, 1, 1, 1, 1, 1}, 
				{1, 1, 1, H, 1, 1, 1, 1},
				{1, 1, 1, 1, H, 1, 1, 1},
				{1, 1, 1, 1, 1, H, 1, 1},
				{1, 1, 1, 1, 1, 1, H, 1},
				{1, 1, 1, 1, 1, 1, 1, H}};
		
		int[][] emptyMatrix = {
				{H, 0, 0, 0, 0, 0, 0, 0}, // vertice 0 has two neighbours 3 and 4
				{0, H, 0, 0, 0, 0, 0, 0}, 
				{0, 0, H, 0, 0, 0, 0, 0}, 
				{0, 0, 0, H, 0, 0, 0, 0},
				{0, 0, 0, 0, H, 0, 0, 0},
				{0, 0, 0, 0, 0, H, 0, 0},
				{0, 0, 0, 0, 0, 0, H, 0},
				{0, 0, 0, 0, 0, 0, 0, H}};

		Graph g = new Graph(adjMatrix);
		
		Visualization visualization = new Visualization(g);

		AdjacencyList aPath = g.funnyPath(0, 20, 3);
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