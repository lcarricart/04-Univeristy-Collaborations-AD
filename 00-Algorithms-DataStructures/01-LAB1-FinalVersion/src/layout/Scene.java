/*******************************************************************************************************************
 * Objective of the class: Manages the viewport transformation and coordinates beetween data space and screen space.
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
 * 	- setScale() - Adjusts the zoom scale factor
 * 	- getScale() - Returns current scale value
 * 	- draw() - Renders background and function plots to the graphics context
 * 	- zoomTo() - Zooms into a specified rectangular area
 * 	- sliderZoom() - Applies zoom based on slider position
 * 	- resetZoom() - Restores original viewport settings
 * 	- setSensorData() - Loads new sensor data and auto-fits viewport
 * 	- getSensorData() - Returns currently loaded data
 * 	- getFunction() - Returns the Function object for plot configuration
 * 	- rescaleToSelectedData() - Adjusts viewport to fit only selected data columns
 *******************************************************************************************************************/

package layout;

import drawingTool.Function;
import files.SensorData;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import drawingTool.Drawing;

public class Scene {
    private Background background;
    private Function function;
    private int width, height; // Careful, this looks like a bad future of size control. Integrate into scale
    private int scale = 10; // The scene now manages the scale, with a default value

    private final Rectangle2D.Double initialViewPoint;
    private Rectangle2D.Double currentViewPoint;
    
    private SensorData sensorData;
    
    public Scene(int width, int height) {
        this.width = width;
        this.height = height;
        this.background = new Background(width, height);
        this.function = new Function();
        
        double initialRange = 300;
        // The initial view is centered on (0,0)
        this.initialViewPoint = new Rectangle2D.Double(-initialRange / 2, -initialRange / 2, initialRange, initialRange);
        this.currentViewPoint = this.initialViewPoint;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getScale() {
        return this.scale;
    }

    public void draw() {
    	background.draw(Drawing.getPen(), currentViewPoint);
    	function.draw(Drawing.getPen(), currentViewPoint, width, height);
    }
    
    public void zoomTo(Rectangle selectionArea) {
        // Ensures the selection has a valid size to prevent division by zero.
        if (selectionArea != null && selectionArea.width > 0 && selectionArea.height > 0) {
        	double oldX = currentViewPoint.getX();
            double oldY = currentViewPoint.getY();
            double oldWidth = currentViewPoint.getWidth();
            double oldHeight = currentViewPoint.getHeight();

            // Map pixel coordinates to data coordinates
            double newX = oldX + (selectionArea.x / (double) width) * oldWidth;
            // Y-axis is inverted in Swing (0 is at top)
            double newY = oldY + ((height - selectionArea.y - selectionArea.height) / (double) height) * oldHeight;
            double newWidth = (selectionArea.width / (double) width) * oldWidth;
            double newHeight = (selectionArea.height / (double) height) * oldHeight;

            this.currentViewPoint = new Rectangle2D.Double(newX, newY, newWidth, newHeight);
        }
    }
    
    public void sliderZoom(int sliderValue) {
        // A value of 5 is 1x zoom. A value of 50 is 10x zoom.
        double scaleFactor = sliderValue / 5.0;
        
        // Prevent scaleFactor from being zero or negative
        if (scaleFactor <= 0) { scaleFactor = 0.01; }

        double newWidth = initialViewPoint.width / scaleFactor;
        double newHeight = initialViewPoint.height / scaleFactor;
        double newX = -newWidth / 2;
        double newY = -newHeight / 2;
        
        this.currentViewPoint = new Rectangle2D.Double(newX, newY, newWidth, newHeight);
    }
    
    public void resetZoom() {
        this.currentViewPoint = this.initialViewPoint;
    }
    
    public void setSensorData(SensorData data) {
        this.sensorData = data;
        function.setSensorData(data);
        autoFitViewport();
    }
    
    public SensorData getSensorData() {
        return sensorData;
    }
    
    public Function getFunction() {
        return function;
    }
    
    public void rescaleToSelectedData() {
        if (sensorData == null || sensorData.isEmpty()) return;
        
        // Get currently selected columns from Function
        String col1 = function.getSelectedColumn1();
        String col2 = function.getSelectedColumn2();
        String col3 = function.getSelectedColumn3();
        
        // If no columns selected, don't rescale
        if (col1 == null && col2 == null && col3 == null) return;
        
        // Find min/max for timestamps (X-axis)
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        
        for (Double t : sensorData.getTimestamps()) {
            if (t < minX) minX = t;
            if (t > maxX) maxX = t;
        }
        
        // Find min/max only for selected columns (Y-axis)
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        
        if (col1 != null) {
            for (Double value : sensorData.getColumnData(col1)) {
                if (value < minY) minY = value;
                if (value > maxY) maxY = value;
            }
        }
        if (col2 != null) {
            for (Double value : sensorData.getColumnData(col2)) {
                if (value < minY) minY = value;
                if (value > maxY) maxY = value;
            }
        }
        if (col3 != null) {
            for (Double value : sensorData.getColumnData(col3)) {
                if (value < minY) minY = value;
                if (value > maxY) maxY = value;
            }
        }
        
        // Add 10% padding around the data
        double xPadding = (maxX - minX) * 0.1;
        double yPadding = (maxY - minY) * 0.1;
        
        // Handle case where all values are the same
        if (xPadding == 0) xPadding = 1;
        if (yPadding == 0) yPadding = 0.1;
        
        double newX = minX - xPadding;
        double newY = minY - yPadding;
        double newWidth = (maxX - minX) + 2 * xPadding;
        double newHeight = (maxY - minY) + 2 * yPadding;
        
        // Update both initial and current viewport
        this.initialViewPoint.setRect(newX, newY, newWidth, newHeight);
        this.currentViewPoint = new Rectangle2D.Double(newX, newY, newWidth, newHeight);
    }
    
    private void autoFitViewport() {
        if (sensorData == null || sensorData.isEmpty()) return;
        
        // Find min/max for timestamps (X-axis)
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        
        // Find min/max for all data columns (Y-axis)
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        
        // Check timestamps
        for (Double t : sensorData.getTimestamps()) {
            if (t < minX) minX = t;
            if (t > maxX) maxX = t;
        }
        
        // Check all data columns
        String[] columns = sensorData.getDataColumnNames();
        for (String column : columns) {
            for (Double value : sensorData.getColumnData(column)) {
                if (value < minY) minY = value;
                if (value > maxY) maxY = value;
            }
        }
        
        // Add 10% padding around the data
        double xPadding = (maxX - minX) * 0.1;
        double yPadding = (maxY - minY) * 0.1;
        
        // Handle case where all values are the same
        if (xPadding == 0) xPadding = 1;
        if (yPadding == 0) yPadding = 0.1;
        
        double newX = minX - xPadding;
        double newY = minY - yPadding;
        double newWidth = (maxX - minX) + 2 * xPadding;
        double newHeight = (maxY - minY) + 2 * yPadding;
        
        // Update both initial and current viewport
        this.initialViewPoint.setRect(newX, newY, newWidth, newHeight);
        this.currentViewPoint = new Rectangle2D.Double(newX, newY, newWidth, newHeight);
    }
}