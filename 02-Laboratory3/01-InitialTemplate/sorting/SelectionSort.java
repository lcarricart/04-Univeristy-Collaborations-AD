package sorting;

public class SelectionSort extends AbstractSort {
	
	@Override
	public void sortAlgorithm() {
		if (sorted != null && !sorted.isEmpty()) {
			sort(0, sorted.size() - 1);
		}
	}
	
	private void sort(int start, int end) {
		
		for (int i = start; i < end; i++) {
			
			int minIndex = i;
			Double minValue = sorted.get(i);
			
			for (int j = i + 1; j <= end; j++) {
				
				comparisonCount++;
				
				notifyUpdate(i, j); 
				// ---------------------

				if (sorted.get(j) < minValue) {
					minIndex = j;
					minValue = sorted.get(j);
				}
			}
			
			if(minIndex != i) {
				swap(i, minIndex);
				swapCount++;
			}
		}
	}
}