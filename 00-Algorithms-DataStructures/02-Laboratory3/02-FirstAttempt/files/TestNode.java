package files;

import drawingTool.RandomNumber;
import sorting.QuickSort;
import sorting.SelectionSort;

public class TestNode {
	private static SelectionSort selectionSort = new SelectionSort();
	private static QuickSort quickSort = new QuickSort();
	
	public static void main(String[] args) {
		SensorData sensorData = new SensorData();
		
		sensorData.addDataPoint(new DataPoint(3.0, 0.2, 0.1, 1.20, 0.2, 0.1, 0.2, 20.8));
		sensorData.addDataPoint(new DataPoint(7.0, 0.1, 0.2, 1.20, 0.2, 0.1, 0.2, 20.9));
		sensorData.addDataPoint(new DataPoint(8.0, 0.1, 0.1, 1.00, 0.1, 0.0, 0.1, 20.7));
		sensorData.addDataPoint(new DataPoint(4.0, 0.2, 0.3, 1.50, 0.3, 0.2, 0.3, 21.0));
		sensorData.addDataPoint(new DataPoint(5.0, 0.3, 0.4, 1.80, 0.4, 0.3, 0.4, 21.2));
		sensorData.addDataPoint(new DataPoint(6.0, 0.2, 0.3, 1.60, 0.3, 0.2, 0.3, 21.1));
		sensorData.addDataPoint(new DataPoint(1.0, 0.1, 0.1, 0.98, 0.0, 0.0, 0.0, 20.5));
		sensorData.addDataPoint(new DataPoint(2.0, 0.1, 0.2, 1.05, 0.1, 0.0, 0.1, 20.6));
		sensorData.addDataPoint(new DataPoint(9.0, 0.0, 0.1, 0.99, 0.0, 0.0, 0.0, 20.6));
		sensorData.addDataPoint(new DataPoint(1.0, 0.0, 0.0, 0.98, 0.0, 0.0, 0.0, 20.5));
		
//		System.out.println("--- Flight Data Points ---");
//		for (Node1 node : data) {
//			System.out.println(node.toString());
//		}
		
		// System.out.println("----------- Selection Sort Case (Single Run) -----------\n");
		selectionSort.setSensorDataForTest(sensorData);
		selectionSort.start();
		
		// System.out.println("-------------- Quick Sort Case (Single Run) --------------\n");
		quickSort.setSensorDataForTest(sensorData);
		quickSort.start();
		
		System.out.println("-------------- Average Computation --------------\n");
		
		int sumSwaps = 0;
		int sumCompares = 0;
		
		for (int i = 0; i < 1000; i++) {
			
			SensorData sensorData2 = new SensorData();
			sensorData2 = generateRandomData();
			
			quickSort.setSensorDataForTest(sensorData2);
			quickSort.start();
			
			sumSwaps += quickSort.getSwapCount();
			sumCompares += quickSort.getComparisonCount();
		}
		
		double averageSwaps = sumSwaps / 1000;
		double averageCompares = sumCompares / 1000;
		
		System.out.println("Average swaps for 1000 runs = " + averageSwaps);
		System.out.println("Average compares for 1000 runs = " + averageCompares + "\n");
	}
	
	private static SensorData generateRandomData() {
		SensorData randomData = new SensorData();
		Double randomPoint;
		
		for (int i = 0; i < 1000; i++) {
    		double timestamp = i;
    		randomPoint = RandomNumber.between(-100, 100) / 100.0;
    		randomData.addDataPoint(timestamp, randomPoint, 0, 0, 0, 0, 0, 0);
    	}
		
		return randomData;
	}
}
