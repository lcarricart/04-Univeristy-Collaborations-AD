package zLaboratoy3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SortedBinaryTree<E extends Node<E>> implements Iterable<E>{ // This is a type bound; we accept any Element that extends Node
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
	
	public void printGrid() {
        if (root == null) {
            System.out.println("(Empty Tree)");
            return;
        }
        
        int maxLevel = getHeight(root);
        List<List<String>> rows = new ArrayList<>();
        
        // Initialize the grid with spaces
        for (int i = 0; i < maxLevel + 1; i++) {
            List<String> row = new ArrayList<>();
            // Width of the grid is roughly 2^height - 1
            int width = (int) Math.pow(2, maxLevel) - 1; 
            for (int j = 0; j < width; j++) {
                row.add(" "); // Empty cell
            }
            rows.add(row);
        }

        // Fill the grid recursively
        fillGrid(root, 0, 0, rows.get(0).size(), rows);

        // Print the grid
        System.out.println("\n\n--- Visual Tree Grid ---\n");
        for (List<String> row : rows) {
            for (String cell : row) {
                System.out.print(cell);
            }
            System.out.println(); 
            System.out.println(); 
        }
        System.out.println("------------------------\n");
    }

    // Helper to fill the grid (recursively places nodes in the middle of their allocated window)
    private void fillGrid(E node, int level, int left, int right, List<List<String>> rows) {
        if (node == null) return;

        int mid = (left + right) / 2;
        rows.get(level).set(mid, String.valueOf(node.getKey()));

        fillGrid(node.getLeft(), level + 1, left, mid, rows);
        fillGrid(node.getRight(), level + 1, mid + 1, right, rows);
    }

    public int getHeight(E node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
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
	
    public void balance() {

        List<E> nodes = new ArrayList<>();
        for (E node : this) {
            nodes.add(node);
        }

        this.root = buildBalancedTree(nodes, 0, nodes.size() - 1, null);
    }

    private E buildBalancedTree(List<E> nodes, int start, int end, E parent) {
    	
        if (start <= end) {
	        int middle = (start + end) / 2;
	        E node = nodes.get(middle);
	
	        node.setParent(parent);
	        node.setLeft(buildBalancedTree(nodes, start, middle - 1, node));
	        node.setRight(buildBalancedTree(nodes, middle + 1, end, node));
	
	        return node;
        }
        return null;
    }
}
