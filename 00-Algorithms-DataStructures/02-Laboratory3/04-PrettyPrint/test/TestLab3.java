package zLaboratoy3;

import java.util.Iterator;

public class TestLab3 {
	
	public static void main(String[] args) {	
		// Define points for a tree
		Sensor point1 = new Sensor("Root", 8);
		Sensor point2 = new Sensor("A", 10);
		Sensor point3 = new Sensor("B", 3);
		Sensor point4 = new Sensor("C", 1);
		Sensor point5 = new Sensor("D", 6);
		Sensor point6 = new Sensor("E", 9);
		Sensor point7 = new Sensor("F", 7);
		Sensor point8 = new Sensor("G", 14);
		
		// Define the tree using the root as initializer
		SortedBinaryTree<Sensor> myTree = new SortedBinaryTree<Sensor>(point1);
		
		// Insert points (Nodes) to the tree
		myTree.insert(point2);
		myTree.insert(point3);
		myTree.insert(point4);
		myTree.insert(point5);
		myTree.insert(point6);
		myTree.insert(point7);
		myTree.insert(point8);
		
		//myTree.printLevelOrder();
		System.out.println("--- Iterator Visualization ---");
		
		// Print the values using an iterator (in-order printing)
		Iterator<Sensor> it = myTree.iterator();
		while (it.hasNext()) {
			Sensor n = it.next();
			System.out.println(n.getKey());
		}
		
		// Print the raw tree in a visually attractive way
		myTree.printGrid();
        
		// Print "Test Results" to verify that all relevant methods in SortedBinaryTree are working as expected
        System.out.println("\n--- TEST RESULTS ---");
        
        Sensor minNode = myTree.min();
        Sensor maxNode = myTree.max();
        
        if (minNode == null) {
        	System.out.println("MIN Value: " + "null" + " (Expected: 1)");
        } else {
        	System.out.println("MIN Value: " + minNode.getKey() + " (Expected: 1)");
        }
        
        if (maxNode == null) {
        	System.out.println("MAX Value: " + "null" + " (Expected: 14)");
        } else {
        	System.out.println("MAX Value: " + maxNode.getKey() + " (Expected: 14)");
        }

        int searchKey = 6;
        int missingKey = 99;
        Sensor foundNode = myTree.find(new Sensor("dummy", searchKey));
        Sensor missingNode = myTree.find(new Sensor("dummy", missingKey));
        
        if (foundNode == null) {
        	System.out.println("FIND(" + searchKey + "): " + "Not Found");
        } else {
        	System.out.println("FIND(" + searchKey + "): " + "Found Node with Key " + foundNode.getKey());
        }

        if (missingNode == null) {
        	System.out.println("FIND(" + missingKey + "): " + "Correctly returned null");
        } else {
        	System.out.println("FIND(" + missingKey + "): " + "Found " + missingNode.getKey());
        }
        
        // Test methods for completeness
        testSucc(myTree, 6); // Expected 7
        testSucc(myTree, 8); // Expected 9
        testSucc(myTree, 9); // Expected 10
        testSucc(myTree, 14); // Expected null (is max)
	}
	
	// Helper for printing successor tests clearly
    private static void testSucc(SortedBinaryTree<Sensor> tree, int key) {
    	Sensor input = new Sensor("temp", key);
        Sensor s = tree.succ(input);
        
        if (s == null) {
        	System.out.println("SUCC(" + key + "): " + "null");
        } else {
        	System.out.println("SUCC(" + key + "): " + s.getKey());
        }
    }
}
