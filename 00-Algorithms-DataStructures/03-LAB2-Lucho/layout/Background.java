/*******************************************************************************************************************
 * Objective of the class: Draws coordinate system axes with dynamic tick marks and labels adapted to current zoom.
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
 * 	- draw() - Renders the coordinate axes and markings based on current viewport
 *******************************************************************************************************************/

package layout;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Background {
    private int height, width;
    private static final int TICK_SIZE = 5;

    public Background(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public void draw(Graphics pen, Rectangle2D.Double viewPoint) {	
        drawYAxis(pen, viewPoint);
        drawXAxis(pen, viewPoint);
        
        drawMarkings(pen, viewPoint);
    }

    // Helper to convert data coordinates to screen coordinates
    private int toScreenX(double dataX, Rectangle2D.Double viewPoint) {
        return (int) (width * (dataX - viewPoint.getX()) / viewPoint.getWidth());
    }

    private int toScreenY(double dataY, Rectangle2D.Double viewPoint) {
        return (int) (height * (1 - ((dataY - viewPoint.getY()) / viewPoint.getHeight())));
    }
    
    private void drawXAxis(Graphics pen, Rectangle2D.Double viewPoint) {
        // Check if the X-axis (y=0) is within the viewport's Y-range
        if (viewPoint.getY() <= 0 && viewPoint.getY() + viewPoint.getHeight() >= 0) {
            int yOrigin = toScreenY(0, viewPoint);
            pen.drawLine(0, yOrigin, width, yOrigin);
        }
    }

    private void drawYAxis(Graphics pen, Rectangle2D.Double viewPoint) {
        // Check if the Y-axis (x=0) is within the viewport's X-range
        if (viewPoint.getX() <= 0 && viewPoint.getX() + viewPoint.getWidth() >= 0) {
            int xOrigin = toScreenX(0, viewPoint);
            pen.drawLine(xOrigin, 0, xOrigin, height);
        }
    }
    
    private double calculateInterval(double range) {
    	
        if (range <= 0) { return 1; }
        
        // Calculate a 'rough' step size that would give us about 5 ticks
        double roughStep = range / 5.0;
        
        // Normalize the step to a power of 10 (e.g., 0.1, 1, 10, 100)
        double stepPower = Math.pow(10, Math.floor(Math.log10(roughStep)));
        
        // Get the magnitude of the rough step (e.g., for 65, magnitude is 6.5)
        double stepMagnitude = roughStep / stepPower;

        // Choose a 'nice' multiplier (1, 2, or 5)
        if (stepMagnitude < 2) {
            return 2 * stepPower;
        } else if (stepMagnitude < 5) {
            return 5 * stepPower;
        } else {
            return 10 * stepPower;
        }
    }
    
	private void drawMarkings(Graphics pen, Rectangle2D.Double viewPoint) {
		
        double xInterval = calculateInterval(viewPoint.getWidth());
        double yInterval = calculateInterval(viewPoint.getHeight());

        int yOriginOnScreen = toScreenY(0, viewPoint);
        int xOriginOnScreen = toScreenX(0, viewPoint);
        
        // Draw X-axis markings
        double firstXMark = Math.ceil(viewPoint.getX() / xInterval) * xInterval;
        for (double dataX = firstXMark; dataX < viewPoint.getX() + viewPoint.getWidth(); dataX += xInterval) {
            if (Math.abs(dataX) < 0.001) continue; // Skip the origin itself
            int screenX = toScreenX(dataX, viewPoint);
            pen.drawLine(screenX, yOriginOnScreen - TICK_SIZE, screenX, yOriginOnScreen + TICK_SIZE);
            String label = String.format("%.2f", dataX).replaceAll("\\.00$", ""); // Format nicely
            pen.drawString(label, screenX - 7, yOriginOnScreen + 20);
        }

        // Draw Y-axis markings
        double firstYMark = Math.ceil(viewPoint.getY() / yInterval) * yInterval;
        for (double dataY = firstYMark; dataY < viewPoint.getY() + viewPoint.getHeight(); dataY += yInterval) {
            if (Math.abs(dataY) < 0.001) continue; // Skip the origin
            int screenY = toScreenY(dataY, viewPoint);
            pen.drawLine(xOriginOnScreen - TICK_SIZE, screenY, xOriginOnScreen + TICK_SIZE, screenY);
            String label = String.format("%.2f", dataY).replaceAll("\\.00$", "");
            pen.drawString(label, xOriginOnScreen + 10, screenY + 5);
        }
    }
}