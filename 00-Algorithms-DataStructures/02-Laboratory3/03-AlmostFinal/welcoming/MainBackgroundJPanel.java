/*******************************************************************************************************************
 * Objective of the class: Decorative background panel displaying animated geometric shapes for welcome screen.
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
 * 	- None (only overrides paintComponent for custom rendering)
 *******************************************************************************************************************/

package welcoming;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import drawingTool.Drawing;
import gui.ModernTheme;

public class MainBackgroundJPanel extends JPanel {
    private static final long serialVersionUID = 1L;
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
    	setBackground(ModernTheme.BACKGROUND_DARK);
    }
    
    @Override
    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        Graphics2D g2d = (Graphics2D) pen;
        
        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Drawing.setPen(pen);
        
        // Draw modern grid
        drawSimpleGrid(g2d);
        
        myGeometricShape = new GeometricShape(getWidth(), ModernTheme.ACCENT_CYAN);
        
        for (int i = 0; i < Y_MARGIN; i = i + 500) {
        	myGeometricShape.draw(X_MARGIN, i, showSignals);
        }
    }
    
    private void drawSimpleGrid(Graphics2D pen) {
        pen.setColor(ModernTheme.GRID_MINOR);
        pen.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5, 5}, 0));
        
        // Draw modern dashed grid lines
        for (int x = 0; x < getWidth(); x += GRID_SPACING) {
            pen.drawLine(x, 0, x, getHeight());
        }
        
        for (int y = 0; y < getHeight(); y += GRID_SPACING) {
            pen.drawLine(0, y, getWidth(), y);
        }
        
        pen.setStroke(new BasicStroke(1.0f));
    }
}