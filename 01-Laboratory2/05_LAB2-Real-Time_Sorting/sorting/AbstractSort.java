package sorting;

import java.util.ArrayList;
import files.SensorData;

public abstract class AbstractSort {
    
    protected SensorData sensorData;    
    protected ArrayList<Double> data;
    protected ArrayList<Double> sorted;
    
    protected int swapCount = 0;
    protected int comparisonCount = 0;
    
    // --- VISUALIZATION CHANGES START ---
    protected SortListener listener;
    protected int delay = 1; // Delay in milliseconds

    public interface SortListener {
        void onUpdate(int activeIdx1, int activeIdx2);
    }

    public void setListener(SortListener listener) {
        this.listener = listener;
    }
    
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * Notifies the listener of an update and pauses execution for the visual delay.
     */
    protected void notifyUpdate(int idx1, int idx2) {
        if (listener != null) {
            listener.onUpdate(idx1, idx2);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // --- VISUALIZATION CHANGES END ---
    
    public void start() {
        
        if (data != null && !data.isEmpty()) {
            sorted = new ArrayList<>(data);
            
            swapCount = 0;
            comparisonCount = 0;
            
            sortAlgorithm();
//          printResults();
        }
    }
    
    protected abstract void sortAlgorithm();

    protected void swap(int a, int b) {
        if (a != b) {
            Double temp = sorted.get(a);
            sorted.set(a, sorted.get(b));
            sorted.set(b, temp);
            
            // Notify visualization on swap
            notifyUpdate(a, b);
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