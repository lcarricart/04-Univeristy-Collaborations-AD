package sorting;

import java.util.ArrayList;

import javax.swing.JComboBox;

import files.SensorData;

public class QuickSort {
	
	private SensorData sensorData;
	private ArrayList<Double> values;
	
	public void Sort() {
		if (values != null && !values.isEmpty()) {
			int count = 0;
			for (int i = 0; i < values.size() - 1; i++) {
				if (count % 30 == 0) {
					System.out.println("");
				}
				System.out.print(values.get(i) + " ");
				count++;
			}
		}
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
}