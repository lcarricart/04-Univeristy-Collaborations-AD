package utilities;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;

import worksheet4.AdjacencyList;
import worksheet4.Graph;

public class Visualization extends Canvas{
	
	private Dimension screenSize;
	private static final long serialVersionUID = 1L;
	
	private Graph aGraph;
	private AdjacencyList aPath;
	
	public Visualization(Graph g) {
		this.aGraph = g;
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		JFrame frame = new JFrame("A Graph");
		setSize(screenSize);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setAPath(AdjacencyList l) {
		aPath = l;
	}

	/********************************************************************************
	 * Painting the Graph on the screen
	 ********************************************************************************/
	
	public void paint(Graphics g) {		
		int[][] positionsOfVertices = layoutOfGraph();
		
		drawVertices(g, positionsOfVertices);
		drawEdges2(g, positionsOfVertices);
	}

	private void drawVertices(Graphics g, int[][] positions){
		g.setColor(Color.black);
		for (int v = 0; v < aGraph.numOfVertices(); v++) {
			g.drawOval(positions[v][0], positions[v][1], 50, 50);
			g.drawString(""+v, positions[v][0]+20, positions[v][1]+30);
		}
	}
	
	// (do not delete 1) Probably this is the bug. If you skip half the values of the matrix you're assuming that your graph is undirected. But this is not the case in your test program
		// Secondly, my aPath has only 3 values but the painter is connecting 4 of them and in mistaken ways. Something is wrong somewhere
		private void drawEdges(Graphics g, int[][] positions){		
			int currentIndex = 0;
			int currentVertex = aPath.getVertex(currentIndex);
			
			for (int u = 0; u < aGraph.numOfVertices(); u++) {
				for (int v = u + 1; v < aGraph.numOfVertices(); v++) {
					if (aGraph.getWeight(u, v) != 0) {
						if (aPath != null && aPath.contains(v)) {
							if (currentIndex < aPath.size() - 1) {
								g.setColor(Color.red);
								currentIndex++;
								currentVertex = aPath.getVertex(currentIndex);
							} else {
								g.setColor(Color.blue);
							}
						} else {
							g.setColor(Color.blue);
						}
						g.drawLine(
								positions[u][0]+25, positions[u][1]+25,
								positions[v][0]+25, positions[v][1]+25);
					}
				}
			}
		}
		
		// Since I cannot follow such a complex structure without making errors, I will separate the logic of the matrix weights (blue) and the path drawing (red)
		private void drawEdges2(Graphics g, int[][] positions){		
			g.setColor(Color.blue);
		    
			// Draw according to matrix weights (blue)
		    for (int u = 0; u < aGraph.numOfVertices(); u++) {
		        for (int v = u + 1; v < aGraph.numOfVertices(); v++) {
		            if (aGraph.getWeight(u, v) != 0) {
		                g.drawLine(
		                    positions[u][0]+25, positions[u][1]+25,
		                    positions[v][0]+25, positions[v][1]+25
		                );
		            }
		        }
		    }
		    // Draw the path (red)
		    if (aPath != null && aPath.size() > 1) {
		        g.setColor(Color.red);
		        
		        for (int i = 0; i < aPath.size() - 1; i++) {
		            int u = aPath.getVertex(i);
		            int v = aPath.getVertex(i + 1);
		            
		            g.drawLine(
		                positions[u][0]+25, positions[u][1]+25,
		                positions[v][0]+25, positions[v][1]+25
		            );
		        }
		    }	
		}
	
	private int[][] layoutOfGraph(){
		int xMin = screenSize.width / 2;
		return new int[][] {
				{xMin +   0, 100}, 
				{xMin + 200, 200},
				{xMin + 400, 400},
				{xMin + 200, 600},
				{xMin -   0, 700},
				{xMin - 200, 600},
				{xMin - 400, 400},
				{xMin - 200, 200}};
	}
	
}