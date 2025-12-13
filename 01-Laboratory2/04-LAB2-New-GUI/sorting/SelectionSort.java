package sorting;

public class SelectionSort extends AbstractSort {
	
	@Override
	public void sortAlgorithm() {
		sort(0, sorted.size() - 1);
	}
	
	private void sort(int start, int end) {
		
		for (int i = start; i < end; i++) {
			
			int temp = -1;
			
			Double minValue = sorted.get(i);
			
			for (int j = i; j < end; j++) {
				
				if (sorted.get(j) < minValue) {
					temp = j;
					minValue = sorted.get(j);
				}
			}
			
			if(temp != -1) {
				swap(i, temp);
			}
		}
	}
}
