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
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import gui.ModernTheme;

public class Background {
    private int height, width;
    private static final int TICK_SIZE = 5;

    public Background(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public void draw(Graphics pen, Rectangle2D.Double viewPoint) {
        Graphics2D g2d = (Graphics2D) pen;
        
        // Enable anti-aliasing for smoother rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        drawGrid(g2d, viewPoint);
        drawYAxis(g2d, viewPoint);
        drawXAxis(g2d, viewPoint);
        drawMarkings(g2d, viewPoint);
    }

    private int toScreenX(double dataX, Rectangle2D.Double viewPoint) {
        return (int) (width * (dataX - viewPoint.getX()) / viewPoint.getWidth());
    }

    private int toScreenY(double dataY, Rectangle2D.Double viewPoint) {
        return (int) (height * (1 - ((dataY - viewPoint.getY()) / viewPoint.getHeight())));
    }
    
    private void drawXAxis(Graphics2D pen, Rectangle2D.Double viewPoint) {
    	
        if (viewPoint.getY() <= 0 && viewPoint.getY() + viewPoint.getHeight() >= 0) {
            int yOrigin = toScreenY(0, viewPoint);
            pen.setColor(ModernTheme.AXIS_COLOR);
            pen.setStroke(new BasicStroke(2.0f));
            pen.drawLine(0, yOrigin, width, yOrigin);
        }
    }

    private void drawYAxis(Graphics2D pen, Rectangle2D.Double viewPoint) {
    	
        if (viewPoint.getX() <= 0 && viewPoint.getX() + viewPoint.getWidth() >= 0) {
            int xOrigin = toScreenX(0, viewPoint);
            pen.setColor(ModernTheme.AXIS_COLOR);
            pen.setStroke(new BasicStroke(2.0f));
            pen.drawLine(xOrigin, 0, xOrigin, height);
        }
    }
    
    private double calculateInterval(double range) {
    	
        if (range <= 0) { return 1; }
        
        double roughStep = range / 5.0;
        double stepPower = Math.pow(10, Math.floor(Math.log10(roughStep)));
        double stepMagnitude = roughStep / stepPower;

        if (stepMagnitude < 2) {
            return 2 * stepPower;
        } else if (stepMagnitude < 5) {
            return 5 * stepPower;
        } else {
            return 10 * stepPower;
        }
    }
    
	private void drawGrid(Graphics2D pen, Rectangle2D.Double viewPoint) {
		double xInterval = calculateInterval(viewPoint.getWidth());
		double yInterval = calculateInterval(viewPoint.getHeight());
		
		// Draw vertical grid lines
		pen.setColor(ModernTheme.GRID_MINOR);
		pen.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5, 5}, 0));
		
		double firstXMark = Math.ceil(viewPoint.getX() / xInterval) * xInterval;
		for (double dataX = firstXMark; dataX < viewPoint.getX() + viewPoint.getWidth(); dataX += xInterval) {
			if (Math.abs(dataX) < 0.001) continue;
			int screenX = toScreenX(dataX, viewPoint);
			pen.drawLine(screenX, 0, screenX, height);
		}
		
		// Draw horizontal grid lines
		double firstYMark = Math.ceil(viewPoint.getY() / yInterval) * yInterval;
		for (double dataY = firstYMark; dataY < viewPoint.getY() + viewPoint.getHeight(); dataY += yInterval) {
			if (Math.abs(dataY) < 0.001) continue;
			int screenY = toScreenY(dataY, viewPoint);
			pen.drawLine(0, screenY, width, screenY);
		}
		
		// Reset stroke to solid
		pen.setStroke(new BasicStroke(1.0f));
	}
	
	private void drawMarkings(Graphics2D pen, Rectangle2D.Double viewPoint) {
		
        double xInterval = calculateInterval(viewPoint.getWidth());
        double yInterval = calculateInterval(viewPoint.getHeight());

        int yOriginOnScreen = toScreenY(0, viewPoint);
        int xOriginOnScreen = toScreenX(0, viewPoint);
        
        pen.setFont(ModernTheme.AXIS_LABEL_FONT);
        pen.setColor(ModernTheme.TEXT_SECONDARY);

        // X-axis markings
        double firstXMark = Math.ceil(viewPoint.getX() / xInterval) * xInterval;
        for (double dataX = firstXMark; dataX < viewPoint.getX() + viewPoint.getWidth(); dataX += xInterval) {
            if (Math.abs(dataX) < 0.001) continue; 
            int screenX = toScreenX(dataX, viewPoint);
            
            pen.setColor(ModernTheme.AXIS_COLOR);
            pen.drawLine(screenX, yOriginOnScreen - TICK_SIZE - 2, screenX, yOriginOnScreen + TICK_SIZE + 2);
            
            pen.setColor(ModernTheme.TEXT_SECONDARY);
            String label = String.format("%.2f", dataX).replaceAll("\\.00$", "");
            int labelWidth = pen.getFontMetrics().stringWidth(label);
            pen.drawString(label, screenX - labelWidth / 2, yOriginOnScreen + 20);
        }

        // Y-axis markings
        double firstYMark = Math.ceil(viewPoint.getY() / yInterval) * yInterval;
        for (double dataY = firstYMark; dataY < viewPoint.getY() + viewPoint.getHeight(); dataY += yInterval) {
            if (Math.abs(dataY) < 0.001) continue;
            int screenY = toScreenY(dataY, viewPoint);
            
            pen.setColor(ModernTheme.AXIS_COLOR);
            pen.drawLine(xOriginOnScreen - TICK_SIZE - 2, screenY, xOriginOnScreen + TICK_SIZE + 2, screenY);
            
            pen.setColor(ModernTheme.TEXT_SECONDARY);
            String label = String.format("%.2f", dataY).replaceAll("\\.00$", "");
            pen.drawString(label, xOriginOnScreen + 10, screenY + 5);
        }
    }
}