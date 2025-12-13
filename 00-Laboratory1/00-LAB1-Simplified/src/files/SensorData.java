package files;

import java.util.ArrayList;
import java.util.List;

public class SensorData {
	private List<Double> timestamps;
	private List<Double> accX;
	private List<Double> accY;
	private List<Double> accZ;
	private List<Double> gyroX;
	private List<Double> gyroY;
	private List<Double> gyroZ;
	private List<Double> temperature;
	
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
	
	public List<Double> getTimestamps() {
		return timestamps;
	}
	
	public List<Double> getColumnData(String columnName) {
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
		// Return only the data columns (exclude timestamp)
		String[] dataColumns = new String[columnNames.length - 1];
		System.arraycopy(columnNames, 1, dataColumns, 0, dataColumns.length);
		return dataColumns;
	}
	
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
