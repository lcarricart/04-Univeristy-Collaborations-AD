package zLaboratoy3;

public class Sensor {
	private String id;
	private int sensitivity;
	
	private double accX;
	private double accY;
	private double accZ;
	private double gyroX;
	private double gyroY;
	private double gyroZ;
	private double temperature;
	
	public Sensor(String id, int sensitivity) {
		this.id = id;
		this.sensitivity = sensitivity;
	}
	
	public String getId() {
		return id;
	}
	
	public int getSensitivity() {
		return sensitivity;
	}
}
