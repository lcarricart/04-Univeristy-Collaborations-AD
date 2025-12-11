package zLaboratoy3;

import java.util.Iterator;

public class TestLab3 {
	
	public static void main(String[] args) {
	//	sensorData.addDataPoint(new DataPoint(3.0, 0.2, 0.1, 1.20, 0.2, 0.1, 0.2, 20.8));
	//	sensorData.addDataPoint(new DataPoint(7.0, 0.1, 0.2, 1.20, 0.2, 0.1, 0.2, 20.9));
	//	sensorData.addDataPoint(new DataPoint(8.0, 0.1, 0.1, 1.00, 0.1, 0.0, 0.1, 20.7));
	//	sensorData.addDataPoint(new DataPoint(4.0, 0.2, 0.3, 1.50, 0.3, 0.2, 0.3, 21.0));
	//	sensorData.addDataPoint(new DataPoint(5.0, 0.3, 0.4, 1.80, 0.4, 0.3, 0.4, 21.2));
	//	sensorData.addDataPoint(new DataPoint(6.0, 0.2, 0.3, 1.60, 0.3, 0.2, 0.3, 21.1));
	//	sensorData.addDataPoint(new DataPoint(1.0, 0.1, 0.1, 0.98, 0.0, 0.0, 0.0, 20.5));
	//	sensorData.addDataPoint(new DataPoint(2.0, 0.1, 0.2, 1.05, 0.1, 0.0, 0.1, 20.6));
	//	sensorData.addDataPoint(new DataPoint(9.0, 0.0, 0.1, 0.99, 0.0, 0.0, 0.0, 20.6));
	//	sensorData.addDataPoint(new DataPoint(1.0, 0.0, 0.0, 0.98, 0.0, 0.0, 0.0, 20.5));
		
		Sensor point1 = new Sensor("Root", 8);
		Sensor point2 = new Sensor("A", 10);
		Sensor point3 = new Sensor("B", 3);
		Sensor point4 = new Sensor("C", 1);
		Sensor point5 = new Sensor("D", 6);
		Sensor point6 = new Sensor("E", 9);
		Sensor point7 = new Sensor("F", 7);
		Sensor point8 = new Sensor("G", 14);
		
		SortedBinaryTree<Sensor> myTree = new SortedBinaryTree<Sensor>(point1);
		myTree.insert(point2);
		myTree.insert(point3);
		myTree.insert(point4);
		myTree.insert(point5);
		myTree.insert(point6);
		myTree.insert(point7);
		myTree.insert(point8);
		
		//myTree.printLevelOrder();
		System.out.println("--- Iterator Visualization ---\n");
		Iterator<Sensor> it = myTree.iterator();
		
		while (it.hasNext()) {
			Sensor n = it.next();
			System.out.println(n.getKey());
		}
		
		myTree.printGrid();
        
        System.out.println("=== TEST RESULTS ===");
        
        Sensor minNode = myTree.min();
        System.out.println("MIN Value: " + (minNode != null ? minNode.getKey() : "null") + " (Expected: 1)");

        Sensor maxNode = myTree.max();
        System.out.println("MAX Value: " + (maxNode != null ? maxNode.getKey() : "null") + " (Expected: 14)");

        int searchKey = 6;
        Sensor foundNode = myTree.find(new Sensor("dummy", searchKey));
        System.out.println("FIND(" + searchKey + "): " + (foundNode != null ? "Found Node with Key " + foundNode.getKey() : "Not Found"));

        int missingKey = 99;
        Sensor missingNode = myTree.find(new Sensor("dummy", missingKey));
        System.out.println("FIND(" + missingKey + "): " + (missingNode != null ? "Found " + missingNode.getKey() : "Correctly returned null"));
        
        testSucc(myTree, 6); // Expected 7
        testSucc(myTree, 8); // Expected 9
        testSucc(myTree, 9); // Expected 10
        testSucc(myTree, 14); // Expected null (is max)
	}
	
	// Helper for printing successor tests clearly
    private static void testSucc(SortedBinaryTree<Sensor> tree, int key) {
        Sensor input = new Sensor("temp", key);
        Sensor s = tree.succ(input);
        System.out.println("SUCC(" + key + "): " + (s != null ? s.getKey() : "null"));
    }
}
