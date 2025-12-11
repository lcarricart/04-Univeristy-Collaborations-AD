package zLaboratoy3;

import java.util.LinkedList;
import java.util.Queue;

public class SortedBinaryTree<E extends Node<E>> { // This is a type bound; we accept any Element that extends Node
	private E root;
	
	public SortedBinaryTree(E root) {
		this.root = root;
		root.setLeft(null);
		root.setRight(null);
		root.setParent(null);
	}
	
	public E getRoot() {
		return root;
	}
	
	public void insert(E newNode) {
		insertRecursive(root, newNode); // Following structure: current, Node to be added, parent
	}
	
	// Probably part of the iterator pattern, explained in the lecture slides "04 - Foundations of graph and tree structures"
	public TreeIterator<E> iterator() {
		TreeIterator<E> myIterator = new TreeIterator<E>(this);
		return myIterator;
	}   

    public E find(E k) {
        E tempNode = root;
        
        while (tempNode != null) {
            if (k.getKey() == tempNode.getKey()) {
                return tempNode;
            } 
            else if (k.getKey() < tempNode.getKey()) {
                tempNode = tempNode.getLeft();
            } 
            else {
                tempNode = tempNode.getRight();
            }
        }
        return null;
    }

    public E succ(E k) {
    	
        E node = find(k);
        if (node == null) {
        	return null;
        }

        if (node.getRight() != null) {
            E tempNode = node.getRight();
            while (tempNode.getLeft() != null) {
                tempNode = tempNode.getLeft();
            }
            return tempNode;
        }

        E parent = node.getParent();
        while (parent != null && node == parent.getRight()) {
            node = parent;
            parent = parent.getParent();
        }
        return parent;
    }
	
	public E min() {
		E tempNode = root;
		
		while(tempNode.getLeft() != null) {
			tempNode = tempNode.getLeft();
		}
		
		return tempNode;
	}

	public E max() {
		E tempNode = root;
		
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
		Queue<E> queue = new LinkedList<>();
		E myNode;
		
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
	private void insertRecursive(E current, E newOne) {
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
