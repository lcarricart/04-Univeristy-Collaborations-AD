package worksheet4;

import java.util.Iterator;
import java.util.LinkedList;

// Based on the UML, this class needs NOT to implement the Iterator interface, just know about it
public class AdjacencyList implements Iterable<Integer>{
	private LinkedList<Integer> adjacencies;
	
	public AdjacencyList(int node) {
		this.adjacencies = new LinkedList<Integer>();
		this.adjacencies.add(node);
	}
	
	public AdjacencyList() {
		this.adjacencies = new LinkedList<Integer>();
	}
	
	// (This is the so called getId() method in the UML)
	public int getVertex(int index) { // index here is correct
		return adjacencies.get(index);
	}
	
	public boolean contains(int vertex) {
		return adjacencies.contains(vertex);
	}
	
	public int size() {
		return adjacencies.size();
	}
	
	public void add(int neighbour) {
		adjacencies.add(neighbour);
	}
	
	public Iterator<Integer> iterator() {
		Iterator<Integer> it = adjacencies.iterator();
		return it;
	}
}
