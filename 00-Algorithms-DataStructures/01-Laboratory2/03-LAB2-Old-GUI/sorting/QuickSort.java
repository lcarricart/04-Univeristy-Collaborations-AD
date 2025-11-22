package sorting;

import java.util.ArrayList;

import drawingTool.RandomNumber;
import files.DataPoint;

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
//		Double median = sorted.get((int) (start + Math.random()*(end-start+1)));
//		Double median = sorted.get(start);
//		Double median = sorted.get(end-1);
		
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
}