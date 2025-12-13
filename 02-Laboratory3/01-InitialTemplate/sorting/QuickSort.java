package sorting;

public class QuickSort extends AbstractSort {
    
    @Override
    public void sortAlgorithm() {
        if (sorted != null && !sorted.isEmpty()) {
            sort(0, sorted.size() - 1);
        }
    }
    
    private void sort(int start, int end) {
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
                // Visualize the scan
                notifyUpdate(r, l);
                r++;
            }

            while (sorted.get(l) > median) {
                comparisonCount++;
                // Visualize the scan
                notifyUpdate(r, l);
                l--;
            }

            if (r <= l) {
                swap(r, l); // Swap handles notification too
                swapCount++;
                r++;
                l--;
            }
        }
        return r; 
    }
}