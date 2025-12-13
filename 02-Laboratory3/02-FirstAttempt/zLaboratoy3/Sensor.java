package zLaboratoy3;

public class Sensor extends Node {
	private String id; // Name of something
	
	public Sensor(String id, int value) {
		super(value);
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
