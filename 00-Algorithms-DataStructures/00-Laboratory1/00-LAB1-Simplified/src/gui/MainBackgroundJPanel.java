package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import background.GeometricShape;
import drawingTool.Drawing;

public class MainBackgroundJPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final Color NAVY_BACKGROUND = new Color(25, 42, 86);
    private final Color GRID_LINE = new Color(35, 52, 96);
    private final Color SENSOR_GRAY = new Color(100, 100, 120);
    private final Color CIRCUIT_GREEN = new Color(50, 200, 100);
    private final Color SIGNAL_ORANGE = new Color(255, 150, 50);
    private final int X_MARGIN = 0;
    private final int Y_MARGIN = 1900;
    private final int GRID_SPACING = 50;
    
    private int showSignals = 1;              
    private GeometricShape myGeometricShape;   //aggregate

    public MainBackgroundJPanel() {
        super();
        
        initialization();
    }

    private void initialization() {
    	setBackground(NAVY_BACKGROUND);
    }
    
    @Override
    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);

        Drawing.setPen(pen);
        
        // Draw simple grid
        drawSimpleGrid(pen);
        
        myGeometricShape = new GeometricShape(getWidth(), SIGNAL_ORANGE);
        
        for (int i = 0; i < Y_MARGIN; i = i + 500)
        {
        	myGeometricShape.draw(X_MARGIN, i, showSignals);
        }
    }
    
    private void drawSimpleGrid(Graphics pen) {
        pen.setColor(GRID_LINE);
        
        // Draw simple grid lines
        for (int x = 0; x < getWidth(); x += GRID_SPACING) {
            pen.drawLine(x, 0, x, getHeight());
        }
        
        for (int y = 0; y < getHeight(); y += GRID_SPACING) {
            pen.drawLine(0, y, getWidth(), y);
        }
    }
}