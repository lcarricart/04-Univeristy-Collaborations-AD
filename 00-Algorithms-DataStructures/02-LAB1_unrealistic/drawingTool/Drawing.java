package drawingTool;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Drawing {

	private static Graphics pen = null;
	
	public static void setPen(Graphics pen) {
		Drawing.pen = pen;
	}
	
	public static Graphics getPen() {
		return Drawing.pen;
	}
	
	public static Graphics2D getPen2D() {
		return (Graphics2D) Drawing.pen;
	}
}