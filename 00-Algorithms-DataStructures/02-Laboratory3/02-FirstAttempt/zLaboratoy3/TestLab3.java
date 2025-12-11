package zLaboratoy3;

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
		
		Sensor point1 = new Sensor("something", 10);
		Sensor point2 = new Sensor("something", 20);
		Sensor point3 = new Sensor("something", 30);
		Sensor point4 = new Sensor("something", 5);
		
		SortedBinaryTree<Sensor> myTree1 = new SortedBinaryTree<Sensor>(point2);
		
		myTree1.insert(point1);
		myTree1.insert(point3);
    	myTree1.insert(point4);
		
		System.out.println(myTree1.find(point2).getKey());
		System.out.println("The successor of " + point4.getKey() + " is " + myTree1.succ(point4).getKey());
	}
}
