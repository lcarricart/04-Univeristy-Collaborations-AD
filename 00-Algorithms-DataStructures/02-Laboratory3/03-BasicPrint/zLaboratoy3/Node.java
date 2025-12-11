package zLaboratoy3;

public abstract class Node<E extends Node<E>> {
	private E parent, left, right;
	private int key; // Our value (key for ordering; if timestamp, its already ordered)
	
	public Node(int key) {
		this.key = key;
	}
	
	public int getKey() {
		return key;
	}
	
	public E getParent() {
		return parent;
	} 
	
	public void setParent(E parent) {
		this.parent = parent;
	}
	
	public E getLeft() {
		return left;
	}
	
	public void setLeft(E left) {
		this.left= left;
	}
	
	public E getRight() {
		return right;
	}
	
	public void setRight(E right) {
		this.right = right;
	}
}
