package sorting;

import java.util.ArrayList;

import files.DataPoint;
import files.SensorData;

public abstract class AbstractSort {
	
	protected SensorData sensorData;	
	protected ArrayList<Double> data;
	protected ArrayList<Double> sorted;
	
	protected int swapCount = 0;
    protected int comparisonCount = 0;
	
	public void start() {
		
		if (data != null && !data.isEmpty()) {
			sorted = new ArrayList<>(data);
			
			swapCount = 0;
			comparisonCount = 0;
			
			sortAlgorithm();
//	        printResults();
		}
	}
	
	protected abstract void sortAlgorithm();

	protected void swap(int a, int b) {
		if (a != b) {
			Double temp = sorted.get(a);
			sorted.set(a, sorted.get(b));
			sorted.set(b, temp);
		}
	}
	
	protected void printResults() {
		if (data != null) {
			System.out.println("\nRaw Data\n");
		    
		    int count = 0;
		    for (int i = 0; i < data.size(); i ++) {
		    		
		        if (count != 0 && count % 10 == 0) {
		            System.out.println(); 
		        }
		        System.out.print(data.get(i) + " ");
		        count++;
		    }
		    
		    System.out.println("\n\nSorted Data\n");
		    
		    count = 0;
		    
		    for (int i = 0; i < sorted.size(); i ++) {
		    		
		        if (count != 0 && count % 10 == 0) {
		            System.out.println(); 
		        }
		        System.out.print(sorted.get(i) + " ");
		        count++;
		    }
		    
		    System.out.println("\n");
		    System.out.println("Total Swaps: " + swapCount);
		    System.out.println("Total Comparisons: " + comparisonCount);
		}
	}
	
	public ArrayList<Double> getSorted() {
		return sorted;
	}

	public void setSensorData(SensorData sensorData) {
		this.sensorData = sensorData;
	}

	public void setSensorDataForTest(SensorData sensorData) {
		this.sensorData = sensorData;
		this.data = sensorData.getColumnData("accX");
	}

	public void setSortData(String columnName) {
		if (columnName != null && sensorData != null) {
			data = sensorData.getColumnData(columnName);
		} else {
			data = null;
		}
	}
	
	public boolean isEmpty() {
		return sorted == null || sorted.isEmpty();
	}
	
	public int getSwapCount() {
		return swapCount;
	}
	
	public int getComparisonCount() {
		return comparisonCount;
	}
}
