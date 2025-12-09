package zLaboratoy3;

public abstract class Node {
	private Node parent, left, right;
	private int key; // Our timestamp
	
	public Node(int key) {
		this.key = key;
	}
	
	public int getKey() {
		return key;
	}
	
	public Node getParent() {
		return parent;
	} 
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public void setLeft(Node left) {
		this.left= left;
	}
	
	public Node getRight() {
		return right;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}
}
