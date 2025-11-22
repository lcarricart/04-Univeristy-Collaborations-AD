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
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import files.SensorData;
import sorting.QuickSort;
import gui.ModernTheme;

public class DataPlotter {
    private SensorData sensorData;
    private QuickSort quickSort;
    private String selectedColumn1;
    private String selectedColumn2;
    private String selectedColumn3;
    private boolean showZeros = false;
    private boolean showExtremas = false;
    private boolean showHistogram = false;
    private boolean valuesSorted = false;

    public void draw(Graphics pen, Rectangle2D.Double viewPoint, int panelWidth, int panelHeight) {
        Graphics2D g2d = (Graphics2D) pen;
        
        // Enable anti-aliasing for smoother plots
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        if (sensorData != null && !sensorData.isEmpty()) {

            if (selectedColumn1 != null) {
                g2d.setColor(ModernTheme.PLOT_1);
                drawDataSeries(g2d, viewPoint, panelWidth, panelHeight, selectedColumn1);
            }
            if (selectedColumn2 != null) {
                g2d.setColor(ModernTheme.PLOT_2);
                drawDataSeries(g2d, viewPoint, panelWidth, panelHeight, selectedColumn2);
            }
            if (selectedColumn3 != null) {
                g2d.setColor(ModernTheme.PLOT_3);
                drawDataSeries(g2d, viewPoint, panelWidth, panelHeight, selectedColumn3);
            }
      
            if (showZeros && selectedColumn1 != null) {
                drawZeros(g2d, viewPoint, panelWidth, panelHeight, selectedColumn1);
            }
            if (showExtremas && selectedColumn1 != null) {
                drawExtremas(g2d, viewPoint, panelWidth, panelHeight, selectedColumn1);
            }
            
            if (valuesSorted && quickSort != null && !quickSort.isEmpty()) {
            	drawSortedFunction(g2d, viewPoint, panelWidth, panelHeight, quickSort.getSorted());
            }
        }
    }
    
    private int toScreenX(double dataX, Rectangle2D.Double viewPoint, int panelWidth) {
        double xRange = viewPoint.getWidth();
        return (int) (panelWidth * (dataX - viewPoint.getX()) / xRange);
    }

    private int toScreenY(double dataY, Rectangle2D.Double viewPoint, int panelHeight) {
        double yRange = viewPoint.getHeight();
        return (int) (panelHeight * (1 - ((dataY - viewPoint.getY()) / yRange)));
    }
    
    private void drawSortedFunction(Graphics2D pen, Rectangle2D.Double viewPoint, int panelWidth, int panelHeight, ArrayList<Double> sorted) {
        pen.setColor(ModernTheme.PLOT_SORTED);
        pen.setStroke(new BasicStroke(2.5f));
        
        int n = sorted.size();
        if (n != 0) {
	        ArrayList<Double> timestamps = sensorData.getTimestamps();
	        if (timestamps.isEmpty()) return;
	        double minTime = Collections.min(timestamps);
	        double maxTime = Collections.max(timestamps);
	        double timeRange = maxTime - minTime;
	        
	        int[] screenXPoints = new int[n];
	        int[] screenYPoints = new int[n];
	        
	        for (int i = 0; i < n; i++) {
	
	            double x = minTime + timeRange * i / (n - 1.0);
	            screenXPoints[i] = toScreenX(x, viewPoint, panelWidth);
	            screenYPoints[i] = toScreenY(sorted.get(i), viewPoint, panelHeight);
	        }
	        
	        pen.drawPolyline(screenXPoints, screenYPoints, n);
	        pen.setStroke(new BasicStroke(1.0f));
        }
    }
    
    private void drawDataSeries(Graphics2D pen, Rectangle2D.Double viewPoint, int panelWidth, int panelHeight, String columnName) {
        pen.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

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
            pen.setStroke(new BasicStroke(3.0f));
            for (int i = 0; i < screenXPoints.length; i++) {
                int zeroY = toScreenY(0, viewPoint, panelHeight);
                pen.drawLine(screenXPoints[i], zeroY, screenXPoints[i], screenYPoints[i]);
            }
        } else {
            pen.drawPolyline(screenXPoints, screenYPoints, screenXPoints.length);
        }
        pen.setStroke(new BasicStroke(1.0f));
    }
    
    private void drawZeros(Graphics2D pen, Rectangle2D.Double viewPoint, int panelWidth, int panelHeight, String columnName) {
    	
    	ArrayList<Double> timestamps = sensorData.getTimestamps();
        ArrayList<Double> values = sensorData.getColumnData(columnName);
        
        pen.setColor(ModernTheme.ZEROS_COLOR);
        
        for (int i = 0; i < values.size() - 1; i++) {
            double v1 = values.get(i);
            double v2 = values.get(i + 1);
            
            if ((v1 <= 0 && v2 >= 0) || (v1 >= 0 && v2 <= 0)) {
                double t1 = timestamps.get(i);
                double t2 = timestamps.get(i + 1);
                double zeroT = t1 + (t2 - t1) * (-v1) / (v2 - v1);
                
                int screenX = toScreenX(zeroT, viewPoint, panelWidth);
                int screenY = toScreenY(0, viewPoint, panelHeight);
                
                pen.fillOval(screenX - 5, screenY - 5, 10, 10);
            }
        }
    }
    
    private void drawExtremas(Graphics2D pen, Rectangle2D.Double viewPoint, int panelWidth, int panelHeight, String columnName) {
    	
    	ArrayList<Double> timestamps = sensorData.getTimestamps();
    	ArrayList<Double> values = sensorData.getColumnData(columnName);
        
        if (values.size() >= 3) {
	        for (int i = 1; i < values.size() - 1; i++) {
	            double prev = values.get(i - 1);
	            double curr = values.get(i);
	            double next = values.get(i + 1);
	            
	            int screenX = toScreenX(timestamps.get(i), viewPoint, panelWidth);
	            int screenY = toScreenY(curr, viewPoint, panelHeight);

	            if (curr > prev && curr > next) {
	                pen.setColor(ModernTheme.EXTREMA_MAX);
	                pen.fillOval(screenX - 5, screenY - 5, 10, 10);
	            }

	            else if (curr < prev && curr < next) {
	                pen.setColor(ModernTheme.EXTREMA_MIN);
	                pen.fillOval(screenX - 5, screenY - 5, 10, 10);
	            }
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
    
    public void setQuickSort(QuickSort quickSort) {
        this.quickSort = quickSort;
    }
    
    public void isSorted(boolean show) {
        this.valuesSorted = show;
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