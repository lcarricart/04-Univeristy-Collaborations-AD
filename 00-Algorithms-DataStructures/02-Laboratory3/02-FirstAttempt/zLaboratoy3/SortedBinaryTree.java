package zLaboratoy3;

import java.util.LinkedList;
import java.util.Queue;

public class SortedBinaryTree<E> { // Where should this E be defined within this class?
	private Node root;
	
	public SortedBinaryTree(Node root) {
		this.root = root;
		root.setLeft(null);
		root.setRight(null);
		root.setParent(null);
	}
	
	public Node getRoot() {
		return root;
	}
	
	public void insert(Node newNode) {
		insertRecursive(root, newNode); // Following structure: current, Node to be added, parent
	}
	
	// Probably part of the iterator pattern, explained in the lecture slides "04 - Foundations of graph and tree structures"
//	public Iterator<Node> iterator() {
//		
//	}
	
public Node succ(Node k) {
		Node follower = find(k);
		
		if(follower != null) {	
			while(follower.getParent() != null) {
				if(follower.getParent().getKey() < follower.getKey()) {
					follower = follower.getParent();
				}
			}
			return follower;
		}
		else {
			return null;
		}
	}
	
	public Node find(Node k) {
		Node tempNode = root;

	    while (tempNode != null && tempNode.getKey() != k.getKey()) {
	        if (k.getKey() < tempNode.getKey()) {
	            tempNode = tempNode.getLeft();
	        } 
	        else {
	            tempNode = tempNode.getRight();
	        }
	    }
	    
	    return tempNode;
	}
	
	public Node min() {
		Node tempNode = root;
		
		while(tempNode.getLeft() != null) {
			tempNode = tempNode.getLeft();
		}
		
		return tempNode;
	}

	public Node max() {
		Node tempNode = root;
		
		while(tempNode.getRight() != null) {
			tempNode = tempNode.getRight();
		}
		
		return tempNode;
	}
	
	public void printLevelOrder() {
		/* In Java, Queue is an interface, not a class. This means it defines what a queue should do (methods like add, poll, peek), but it does not provide the code for how to store the data.
		 * To create a queue, you must choose a specific implementation (a concrete class that acts as the underlying data structure). Even if LinkedLists allow to access elements in the middle,
		 * Java puts "blinders" on the variable and you won't be able to use something like .get(3)
		 */
		Queue<Node> queue = new LinkedList<>();
		Node myNode;
		
		queue.add(root);

		while (!queue.isEmpty()) {
			myNode = queue.poll();
			System.out.println(myNode.getKey() + " ");
			
			if (myNode.getLeft() != null) {
				queue.add(myNode.getLeft());
			}
			
			if (myNode.getRight() != null) {
				queue.add(myNode.getRight());
			}
		}
	}
	
	// Helper function. Insert a node in a tree (EQUAL CASE does nothing)
	private void insertRecursive(Node current, Node newOne) {
		if (newOne.getKey() < current.getKey()) {
			if (current.getLeft() != null) {
				insertRecursive(current.getLeft(), newOne);
			} else {
				current.setLeft(newOne);
				newOne.setParent(current);
			}
		} else if (newOne.getKey() > current.getKey()) {
			if (current.getRight() != null) {
				insertRecursive(current.getRight(), newOne);
			} else {
				current.setRight(newOne);
				newOne.setParent(current);
			}
		}
	}
}
