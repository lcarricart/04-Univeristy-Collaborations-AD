package zLaboratoy3;

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
		insertRecursive(root, newNode, null); // Following structure: current, Node to be added, parent
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
	
	// Helper function. Insert a node in a tree
	private void insertRecursive(Node current, Node newOne, Node parent) {
		if (newOne.getKey() < current.getKey()) {
			if (current.getLeft() != null) {
				insertRecursive(current.getLeft(), newOne, current);
			} else {
				current.setLeft(newOne);
			}
		} else if (newOne.getKey() > current.getKey()) {
			if (current.getRight() != null) {
				insertRecursive(current.getRight(), newOne, current);
			} else {
				current.setRight(newOne);
			}
		}
		newOne.setParent(current);
	}
}
