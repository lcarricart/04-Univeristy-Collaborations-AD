/*******************************************************************************************************************
 * Objective of the class: Static utility class for managing the graphics pen used across all drawing operations.
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
 * 	- setPen() - Sets the current graphics context for drawing
 * 	- getPen() - Returns the graphics context as Graphics object
 * 	- getPen2D() - Returns the graphics context casted to Graphics2D for advanced operations
 *******************************************************************************************************************/

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