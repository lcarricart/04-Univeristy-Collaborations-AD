package sorting;

import java.util.ArrayList;
import files.SensorData;

public class QuickSort {
	
	private SensorData sensorData;
	private ArrayList<Double> values;
	private ArrayList<Double> sorted;
	private int swapCount = 0;
    private int comparisonCount = 0;
	
	public void sort() {
		
		if (values != null && !values.isEmpty()) {
			sorted = new ArrayList<>(values);
			
			swapCount = 0;
			comparisonCount = 0;
			
			quickSortRecursive(0, sorted.size() - 1);
	        
	        printResults();
		}
	}
	
	private void quickSortRecursive(int low, int high) {
		if (low < high) {
			// partitionIndex is where the pivot is currently placed
			int partitionIndex = partition(low, high);

			// Recursively sort elements before and after partition
			quickSortRecursive(low, partitionIndex - 1);
			quickSortRecursive(partitionIndex + 1, high);
		}
	}

	// Standard QuickSort Partition Scheme
	private int partition(int low, int high) {
	    Double pivot = sorted.get(high); // Take last element as pivot
	    int i = (low - 1); 

	    for (int j = low; j < high; j++) {
	        comparisonCount++;
	        // If current element is smaller than the pivot
	        if (sorted.get(j) < pivot) {
	            i++;
	            swap(i, j);
	            swapCount++;
	        }
	    }
	    // Place pivot in the correct slot
	    swap(i + 1, high);
	    swapCount++;
	    
	    return i + 1;
	}
	
	public void swap(int a, int b) {
		if (a != b) {
			Double temp = sorted.get(a);
			sorted.set(a, sorted.get(b));
			sorted.set(b, temp);
		}
	}
	
	public boolean isAscending(Double lower, Double bigger) {
		if(lower > bigger) {
			return false;
		}
		return true;
	}
	
	private void printResults() {
		
		System.out.println("\nRaw Sample\n");
	    
	    int count = 0;
	    for (int i = 0; i < values.size(); i += 10) {
	    		
	        if (count != 0 && count % 10 == 0) {
	            System.out.println(); 
	        }
	        System.out.print(values.get(i) + " ");
	        count++;
	    }
	    
	    System.out.println("\n\nSorted Sample\n");
	    
	    count = 0;
	    
	    for (int i = 0; i < sorted.size(); i += 10) {
	    		
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
	
	public ArrayList<Double> getSorted() {
		return sorted;
	}

	public void setSensorData(SensorData sensorData) {
		this.sensorData = sensorData;
	}

	public void setSortData(String columnName) {
		if (columnName != null && sensorData != null) {
			values = sensorData.getColumnData(columnName);
		} else {
			values = null;
		}
	}
	
	public boolean isEmpty() {
		return sorted.isEmpty();
	}
}