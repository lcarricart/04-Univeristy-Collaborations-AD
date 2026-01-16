  /*******************************************************************************************************************
 * Objective: implement a Java program that represents a graph structure using both an adjacency matrix and adjacency 
 * lists simultaneously. Additionally, the lab requires developing a visualization component and a pathfinding 
 * algorithm to generate and display cycle-free paths of a specific length.
 *******************************************************************************************************************
 * Remarks: The code provided by the professor shows lines such as "AdjacencyList adjList = g.getNeighboursFor(v);" 
 * but that creates great confusion during the learning process. An adjacency list is a way to represent a graph
 * (the whole graph, not just one list). It is a list of lists, containing the information about all neighbours 
 * for a specific vertex.
 * 
 * Gottfried's convention:
 * 			- AdjacencyLists class (plural) --> typical AdjacencyList class
 * 			- AdjacencyList class (singular) --> typically not defined. In my projects it's called AdjacencyRow
 * 
 * Another clear standard (which will not be used in this LAB) is:
 * 	   class Node {
 * 	  		int id;
 * 	  		List<Node> neighbors; // <--- The "AdjacencyRow" is just a field here
 * 	   }
 * 
 * Possible bugs:
 * 			- aPath not initialized in the GraphPainter class constructor
 * 			- In drawEdges(), the function assumes that the matrix represents a directed graph. This doesn't have
 * 			  to be assumed by the function, but rather passed as a parameter.
 * 			- 
 *******************************************************************************************************************
 * Authors: 
 * 			- Luciano Carricart
 * 			- Georgii Molyboga
 * Status: Information Engineering student, HAW Hamburg, Germany.
 *******************************************************************************************************************/

package worksheet4;

// TODO This is the entry-point of the program
public class Application {
	
}
