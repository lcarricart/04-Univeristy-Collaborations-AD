/*******************************************************************************************************************
 * Objective of the class: Handles rendering of sensor data plots with analysis features like zeros and extremas.
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
 * 	- draw() - Main rendering method that plots selected data series
 * 	- setSensorData() - Assigns data source for plotting
 * 	- setSelectedColumn1/2/3() - Selects which data columns to visualize
 * 	- setShowZeros() - Toggles display of zero crossing markers
 * 	- setShowExtremas() - Toggles display of local minima and maxima
 * 	- setShowHistogram() - Switches beetween line and histogram rendering mode
 * 	- getSelectedColumn1/2/3() - Returns currently selected column names
 *******************************************************************************************************************/

package drawingTool;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import files.SensorData;

public class Function {
    private SensorData sensorData;
    private String selectedColumn1;
    private String selectedColumn2;
    private String selectedColumn3;
    private boolean showZeros = false;
    private boolean showExtremas = false;
    private boolean showHistogram = false;

    public Function() {
        
    }

    public void draw(Graphics pen, Rectangle2D.Double viewPoint, int panelWidth, int panelHeight) {
        
        if (sensorData != null && !sensorData.isEmpty()) {
            // Draw sensor data
            if (selectedColumn1 != null) {
                pen.setColor(Color.BLUE);
                // delegating to a helper method
                drawDataSeries(pen, viewPoint, panelWidth, panelHeight, selectedColumn1);
            }
            if (selectedColumn2 != null) {
                pen.setColor(Color.RED);
                drawDataSeries(pen, viewPoint, panelWidth, panelHeight, selectedColumn2);
            }
            if (selectedColumn3 != null) {
                pen.setColor(Color.GREEN);
                drawDataSeries(pen, viewPoint, panelWidth, panelHeight, selectedColumn3);
            }
            
            // Draw analysis features
            if (showZeros && selectedColumn1 != null) {
            	// delegating to a helper method
                drawZeros(pen, viewPoint, panelWidth, panelHeight, selectedColumn1);
            }
            if (showExtremas && selectedColumn1 != null) {
                drawExtremas(pen, viewPoint, panelWidth, panelHeight, selectedColumn1);
            }
        }
        // No default example data - screen stays empty until data is imported
    }

    // Converts a data X-coordinate to a screen pixel X-coordinate.
    private int toScreenX(double dataX, Rectangle2D.Double viewPoint, int panelWidth) {
        double xRange = viewPoint.getWidth();
        return (int) (panelWidth * (dataX - viewPoint.getX()) / xRange);
    }

    // Converts a data Y-coordinate to a screen pixel Y-coordinate; handles the inverted Y-axis in Swing
    private int toScreenY(double dataY, Rectangle2D.Double viewPoint, int panelHeight) {
        double yRange = viewPoint.getHeight();
        return (int) (panelHeight * (1 - ((dataY - viewPoint.getY()) / yRange)));
    }
    
    private void drawDataSeries(Graphics pen, Rectangle2D.Double viewPoint, int panelWidth, int panelHeight, String columnName) {
    	// delegating to another helper method
        ArrayList<Double> timestamps = sensorData.getTimestamps();
        ArrayList<Double> values = sensorData.getColumnData(columnName);
        
        if (timestamps.isEmpty() || values.isEmpty()) return;
        
        int[] screenXPoints = new int[timestamps.size()];
        int[] screenYPoints = new int[values.size()];
        
        for (int i = 0; i < timestamps.size(); i++) {
            screenXPoints[i] = toScreenX(timestamps.get(i), viewPoint, panelWidth);
            screenYPoints[i] = toScreenY(values.get(i), viewPoint, panelHeight);
        }
        
        if (showHistogram) {
            // Draw as histogram (vertical bars)
            for (int i = 0; i < screenXPoints.length; i++) {
                int zeroY = toScreenY(0, viewPoint, panelHeight);
                pen.drawLine(screenXPoints[i], zeroY, screenXPoints[i], screenYPoints[i]);
            }
        } else {
            // Draw as line
            pen.drawPolyline(screenXPoints, screenYPoints, screenXPoints.length);
        }
    }
    
    private void drawZeros(Graphics pen, Rectangle2D.Double viewPoint, int panelWidth, int panelHeight, String columnName) {
    	ArrayList<Double> timestamps = sensorData.getTimestamps();
        ArrayList<Double> values = sensorData.getColumnData(columnName);
        
        pen.setColor(Color.ORANGE);
        
        // Find zero crossings
        for (int i = 0; i < values.size() - 1; i++) {
            double v1 = values.get(i);
            double v2 = values.get(i + 1);
            
            // Check if sign changes (zero crossing)
            if ((v1 <= 0 && v2 >= 0) || (v1 >= 0 && v2 <= 0)) {
                // Linear interpolation to find approximate zero position
                double t1 = timestamps.get(i);
                double t2 = timestamps.get(i + 1);
                double zeroT = t1 + (t2 - t1) * (-v1) / (v2 - v1);
                
                int screenX = toScreenX(zeroT, viewPoint, panelWidth);
                int screenY = toScreenY(0, viewPoint, panelHeight);
                
                // Draw circle at zero crossing
                pen.fillOval(screenX - 4, screenY - 4, 8, 8);
            }
        }
    }
    
    private void drawExtremas(Graphics pen, Rectangle2D.Double viewPoint, int panelWidth, int panelHeight, String columnName) {
    	
    	ArrayList<Double> timestamps = sensorData.getTimestamps();
    	ArrayList<Double> values = sensorData.getColumnData(columnName);
        
        if (values.size() < 3) return;
        
        // Find local maxima and minima
        for (int i = 1; i < values.size() - 1; i++) {
            double prev = values.get(i - 1);
            double curr = values.get(i);
            double next = values.get(i + 1);
            
            int screenX = toScreenX(timestamps.get(i), viewPoint, panelWidth);
            int screenY = toScreenY(curr, viewPoint, panelHeight);
            
            // Local maximum
            if (curr > prev && curr > next) {
                pen.setColor(Color.RED);
                pen.fillRect(screenX - 4, screenY - 4, 8, 8);
            }
            // Local minimum
            else if (curr < prev && curr < next) {
                pen.setColor(Color.BLUE);
                pen.fillRect(screenX - 4, screenY - 4, 8, 8);
            }
        }
    }
    
    public void setSensorData(SensorData data) {
        this.sensorData = data;
    }
    
    public void setSelectedColumn1(String columnName) {
        this.selectedColumn1 = columnName;
    }
    
    public void setSelectedColumn2(String columnName) {
        this.selectedColumn2 = columnName;
    }
    
    public void setSelectedColumn3(String columnName) {
        this.selectedColumn3 = columnName;
    }
    
    public void setShowZeros(boolean show) {
        this.showZeros = show;
    }
    
    public void setShowExtremas(boolean show) {
        this.showExtremas = show;
    }
    
    public void setShowHistogram(boolean show) {
        this.showHistogram = show;
    }
    
    public String getSelectedColumn1() {
        return selectedColumn1;
    }
    
    public String getSelectedColumn2() {
        return selectedColumn2;
    }
    
    public String getSelectedColumn3() {
        return selectedColumn3;
    }
}