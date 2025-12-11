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
		
		Sensor point1 = new Sensor("something", 8);
		Sensor point2 = new Sensor("something", 10);
		Sensor point3 = new Sensor("something", 3);
		Sensor point4 = new Sensor("something", 1);
		Sensor point5 = new Sensor("something", 6);
		Sensor point6 = new Sensor("something", 9);
		Sensor point7 = new Sensor("something", 7);
		
		SortedBinaryTree<Sensor> myTree = new SortedBinaryTree<Sensor>(point1);
		myTree.insert(point2);
		myTree.insert(point3);
		myTree.insert(point4);
		myTree.insert(point5);
		myTree.insert(point6);
		myTree.insert(point7);
		
		myTree.printLevelOrder();
		
//		Iterator<Node> it = myTree.iterator();
//		
//		while (it.hasNext()) {
//			Node n = it.next();
//			System.out.println(n.getKey());
//		}
	}
}
