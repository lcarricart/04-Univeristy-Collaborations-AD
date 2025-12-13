package files;

public class DataPoint implements Node {
	
	private double timestamp;
	private double accX;
	private double accY;
	private double accZ;
	private double gyroX;
	private double gyroY;
	private double gyroZ;
	private double temperature;
	
	public DataPoint(double timestamp, double accX, double accY, double accZ, double gyroX, double gyroY, double gyroZ, double temperature) {
		this.timestamp = timestamp;
		this.accX = accX;
		this.accY = accY;
		this.accZ = accZ;
		this.gyroX = gyroX;
		this.gyroY = gyroY;
		this.gyroZ = gyroZ;
		this.temperature = temperature;
	}
	
	@Override
	public int getKey() {
		return (int) timestamp;
	}
	
	public double getTimestamp() {
		return timestamp;
	}
	
	public double getAccX() {
		return accX;
	}
	
	public double getAccY() {
		return accY;
	}
	
	public double getAccZ() {
		return accZ;
	}
	
	public double getGyroX() {
		return gyroX;
	}
	
	public double getGyroY() {
		return gyroY;
	}
	
	public double getGyroZ() {
		return gyroZ;
	}
	
	public double getTemperature() {
		return temperature;
	}
	
	@Override
	public String toString() {
		return "Time: " + timestamp + " | Acc: [" + accX + ", " + accY + ", " + accZ + "] | Gyro: [" + gyroX + ", " + gyroY + ", " + gyroZ + "] | Temp: " + temperature;
	}
}
