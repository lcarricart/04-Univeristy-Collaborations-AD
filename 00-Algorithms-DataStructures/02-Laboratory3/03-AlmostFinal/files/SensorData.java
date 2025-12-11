/*******************************************************************************************************************
 * Objective of the class: Data container for storing and retrieving multi-column sensor telemetry measurements.
 *******************************************************************************************************************
 * Context: This is part of a major programming project, where live telemetry is transmitted to a PC, to be later
   manipulated and displayed with a Java GUI application.
 *******************************************************************************************************************
 * Authors: 
 * 	- Luciano Carricart, https://github.com/lcarricart/
 * 	- Georgii Molyboga, https://github.com/Georgemolyboga/
 * Status: Information Engineering students, HAW Hamburg, Germany.
 * Date: November 2024
 *******************************************************************************************************************
 * Public methods:
 * 	- addDataPoint() - Adds a new row of sensor readings with timestamp
 * 	- getTimestamps() - Returns list of all timestamps
 * 	- getColumnData() - Retrieves data for a specific column by name
 * 	- getColumnNames() - Returns array of all column names including timestamp
 * 	- getDataColumnNames() - Returns array of data column names excluding timestamp
 * 	- getSize() - Returns number of data points stored
 * 	- isEmpty() - Checks if data container is empty
 * 	- clear() - Removes all stored data
 *******************************************************************************************************************/

package files;

import java.util.ArrayList;

public class SensorData {
	private ArrayList<DataPoint> dataPoints;
	private String[] columnNames = {"timestamp", "accX", "accY", "accZ", "gyroX", "gyroY", "gyroZ", "temperature"};
	
	public SensorData() {
		dataPoints = new ArrayList<>();
	}
	
	public void addDataPoint(double timestamp, double accX, double accY, double accZ, double gyroX, double gyroY, double gyroZ, double temperature) {
		dataPoints.add(new DataPoint(timestamp, accX, accY, accZ, gyroX, gyroY, gyroZ, temperature));
	}
	
	public void addDataPoint(DataPoint dataPoint) {
		dataPoints.add(dataPoint);
	}
	
	public ArrayList<Double> getTimestamps() {
		ArrayList<Double> timestamps = new ArrayList<>();
		for (DataPoint dataPoint : dataPoints) {
			timestamps.add(dataPoint.getTimestamp());
		}
		return timestamps;
	}
	
	public ArrayList<Double> getColumnData(String columnName) {
		ArrayList<Double> column = new ArrayList<>();
		for (DataPoint dataPoint : dataPoints) {
			switch (columnName) {
				case "timestamp": column.add(dataPoint.getTimestamp()); break;
				case "accX": column.add(dataPoint.getAccX()); break;
				case "accY": column.add(dataPoint.getAccY()); break;
				case "accZ": column.add(dataPoint.getAccZ()); break;
				case "gyroX": column.add(dataPoint.getGyroX()); break;
				case "gyroY": column.add(dataPoint.getGyroY()); break;
				case "gyroZ": column.add(dataPoint.getGyroZ()); break;
				case "temperature": column.add(dataPoint.getTemperature()); break;
			}
		}
		return column;
	}
	
	public String[] getColumnNames() {
		return columnNames;
	}
	
	public String[] getDataColumnNames() {
		String[] dataColumns = new String[columnNames.length - 1];
		System.arraycopy(columnNames, 1, dataColumns, 0, dataColumns.length);
		return dataColumns;
	}
	
	public int getSize() {
		return dataPoints.size();
	}
	
	public boolean isEmpty() {
		return dataPoints.isEmpty();
	}
	
	public void clear() {
		dataPoints.clear();
	}
}