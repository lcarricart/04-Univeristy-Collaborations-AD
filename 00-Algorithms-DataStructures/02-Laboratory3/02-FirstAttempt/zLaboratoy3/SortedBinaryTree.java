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
//	
//	public Node succ(Node k) {
//		
//	}
//	
//	public Node find(Node k) {
//		
//	}
//	
//	public Node min(Node k) {
//		
//	}
	
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
	}
}
