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
	private ArrayList<Double> timestamps;
	private ArrayList<Double> accX;
	private ArrayList<Double> accY;
	private ArrayList<Double> accZ;
	private ArrayList<Double> gyroX;
	private ArrayList<Double> gyroY;
	private ArrayList<Double> gyroZ;
	private ArrayList<Double> temperature;
	private String[] columnNames = {"timestamp", "accX", "accY", "accZ", "gyroX", "gyroY", "gyroZ", "temperature"};
	
	public SensorData() {
		timestamps = new ArrayList<>();
		accX = new ArrayList<>();
		accY = new ArrayList<>();
		accZ = new ArrayList<>();
		gyroX = new ArrayList<>();
		gyroY = new ArrayList<>();
		gyroZ = new ArrayList<>();
		temperature = new ArrayList<>();
	}
	
	public void addDataPoint(double timestamp, double accX, double accY, double accZ, double gyroX, double gyroY, double gyroZ, double temperature) {
		this.timestamps.add(timestamp);
		this.accX.add(accX);
		this.accY.add(accY);
		this.accZ.add(accZ);
		this.gyroX.add(gyroX);
		this.gyroY.add(gyroY);
		this.gyroZ.add(gyroZ);
		this.temperature.add(temperature);
	}
	
	public ArrayList<Double> getTimestamps() {
		return timestamps;
	}
	
	public ArrayList<Double> getColumnData(String columnName) {
		switch (columnName) {
			case "timestamp": return timestamps;
			case "accX": return accX;
			case "accY": return accY;
			case "accZ": return accZ;
			case "gyroX": return gyroX;
			case "gyroY": return gyroY;
			case "gyroZ": return gyroZ;
			case "temperature": return temperature;
			default: return new ArrayList<>();
		}
	}
	
	public String[] getColumnNames() {
		return columnNames;
	}
	
	public String[] getDataColumnNames() {
		String[] dataColumns = new String[columnNames.length - 1];
		System.arraycopy(columnNames, 1, dataColumns, 0, dataColumns.length);
		return dataColumns;
	}
	
	// Only used to print in the Command Window how many data points were imported (debugging purposes)
	public int getSize() {
		return timestamps.size();
	}
	
	public boolean isEmpty() {
		return timestamps.isEmpty();
	}
	
	public void clear() {
		timestamps.clear();
		accX.clear();
		accY.clear();
		accZ.clear();
		gyroX.clear();
		gyroY.clear();
		gyroZ.clear();
		temperature.clear();
	}
}