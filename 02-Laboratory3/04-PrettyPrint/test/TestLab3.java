package zLaboratoy3;

import java.util.Iterator;

public class TestLab3 {
	
	public static void main(String[] args) {	
		// Define points for a first tree
		Sensor point1 = new Sensor("Root", 8);
		Sensor point2 = new Sensor("A", 10);
		Sensor point3 = new Sensor("B", 3);
		Sensor point4 = new Sensor("C", 1);
		Sensor point5 = new Sensor("D", 6);
		Sensor point6 = new Sensor("E", 9);
		Sensor point7 = new Sensor("F", 7);
		Sensor point8 = new Sensor("G", 14);
		
		// Define points for a first tree
		Sensor point9 = new Sensor("Root", 2);
		Sensor point10 = new Sensor("A", 11);
		Sensor point11 = new Sensor("B", 5);
		Sensor point12 = new Sensor("C", 4);
		Sensor point13 = new Sensor("D", 12);
		Sensor point14 = new Sensor("E", 15);
		Sensor point15 = new Sensor("F", 13);
		Sensor point16 = new Sensor("G", 16);
		
		Sensor point17 = new Sensor("Root", 20);
		Sensor point18 = new Sensor("A", 21);
		Sensor point19 = new Sensor("B", 45);
		Sensor point20 = new Sensor("C", 50);

		
		// Define the tree using the root as initializer
		SortedBinaryTree<Sensor> myTree1 = new SortedBinaryTree<Sensor>(point1);
		SortedBinaryTree<Sensor> myTree2 = new SortedBinaryTree<Sensor>(point9);
		
		// Insert points (Nodes) to the tree
		myTree1.insert(point2);
		myTree1.insert(point3);
		myTree1.insert(point4);
		myTree1.insert(point5);
		myTree1.insert(point6);
		myTree1.insert(point7);
		myTree1.insert(point8);
		
		myTree2.insert(point10);
		myTree2.insert(point11);
		myTree2.insert(point12);
		myTree2.insert(point13);
		myTree2.insert(point14);
		myTree2.insert(point15);
		myTree2.insert(point16);
		myTree2.insert(point17);
		myTree2.insert(point18);
		myTree2.insert(point19);
		myTree2.insert(point20);
		
		//myTree.printLevelOrder();
		System.out.println("--- Iterator Visualization ---");
		
		// Print the values using an iterator (in-order printing)
		for (Sensor n : myTree1) {
			System.out.print(n.getKey() + " ");
		}
		
		System.out.println();
		for (Sensor n : myTree2) {
			System.out.print(n.getKey() + " ");
		}
		
		// Print the raw tree in a visually attractive way
		myTree1.printGrid();
		myTree2.printGrid();
		
		SortedBinaryTree<Sensor> mergedTree = merge(myTree1, myTree2);

		System.out.println("Merged (Unbalanced) Tree Grid:");
		mergedTree.printGrid();

		System.out.println("--- Balancing Tree ---");
		mergedTree.balance();
		
		// Verify iterator still works
		System.out.println("\n--- Iterator Visualization (Post-Balance) ---");
		for (Sensor n : mergedTree) {
		    System.out.print(n.getKey() + " ");
		}

		System.out.println("\n\nMerged (Balanced) Tree Grid:");
		mergedTree.printGrid();
		
//		// Print "Test Results" to verify that all relevant methods in SortedBinaryTree are working as expected
//        System.out.println("--- TEST RESULTS ---");
//        
//        Sensor minNode = myTree1.min();
//        Sensor maxNode = myTree1.max();
//        
//        if (minNode == null) {
//        	System.out.println("MIN Value: " + "null" + " (Expected: 1)");
//        } else {
//        	System.out.println("MIN Value: " + minNode.getKey() + " (Expected: 1)");
//        }
//        
//        if (maxNode == null) {
//        	System.out.println("MAX Value: " + "null" + " (Expected: 14)");
//        } else {
//        	System.out.println("MAX Value: " + maxNode.getKey() + " (Expected: 14)");
//        }
//
//        int searchKey = 6;
//        int missingKey = 99;
//        Sensor foundNode = myTree1.find(new Sensor("dummy", searchKey));
//        Sensor missingNode = myTree1.find(new Sensor("dummy", missingKey));
//        
//        if (foundNode == null) {
//        	System.out.println("FIND(" + searchKey + "): " + "Not Found");
//        } else {
//        	System.out.println("FIND(" + searchKey + "): " + "Found Node with Key " + foundNode.getKey());
//        }
//
//        if (missingNode == null) {
//        	System.out.println("FIND(" + missingKey + "): " + "Correctly returned null");
//        } else {
//        	System.out.println("FIND(" + missingKey + "): " + "Found " + missingNode.getKey());
//        }
//        
//        // Test methods for completeness
//        testSucc(myTree1, 6); // Expected 7
//        testSucc(myTree1, 8); // Expected 9
//        testSucc(myTree1, 9); // Expected 10
//        testSucc(myTree1, 14); // Expected null (is max)
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
    
    public static SortedBinaryTree<Sensor> merge(SortedBinaryTree<Sensor> myTree1, SortedBinaryTree<Sensor> myTree2) {
		
		for(Sensor n : myTree2) {
			
			Sensor copy = new Sensor("new ID", n.getKey());
					
			myTree1.insert(copy);
		}
		return myTree1;
	}
}
