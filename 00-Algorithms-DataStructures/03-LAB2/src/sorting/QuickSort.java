package sorting;

import java.util.ArrayList;

import files.SensorData;

public class QuickSort {
	
	private SensorData sensorData;
	private ArrayList<Double> values;
	private ArrayList<Double> sorted;
	private int swapCount, comparisonCount;
	
	public void start() {
		if (values != null && !values.isEmpty()) {
			sorted = new ArrayList<>(values);

			swapCount = 0;
			comparisonCount = 0;
			
			sort(0, sorted.size() - 1);
			printResults();
		}
	}
	
	public void sort(int start, int end) {
			
			if (start < end) {
				int median = moveTowards(start, end); 

				sort(start, median - 1);
				sort(median, end);
			}
	}
	
	private int moveTowards(int start, int end) {
		
        Double median = sorted.get((start + end) / 2);
        
        int r = start;
	    int l = end;

	    while (r <= l) {
	    	
	        while (sorted.get(r) < median) {
            comparisonCount++;
	            r++;
	        }

	        while (sorted.get(l) > median) {
	        	comparisonCount++;
	            l--;
	        }

	        if (r <= l) {
	            swap(r, l);
                swapCount++;
	            r++;
	            l--;
            }
        }
	    return r; 
	}
	
	public void swap(int a, int b) {
		if (a != b) {
			Double temp = sorted.get(a);
			sorted.set(a, sorted.get(b));
			sorted.set(b, temp);
		}
	}
	
	private void printResults() {
		
		System.out.println("\nRaw Data\n");
	    
	    int count = 0;
	    for (int i = 0; i < values.size(); i += 10) {
	        
	        if (count != 0 && count % 10 == 0) {
	            System.out.println(); 
	        }
	        System.out.print(values.get(i) + " ");
	        count++;
	    }
	    
	    System.out.println("\n\nSorted Data\n");
	    
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