/*******************************************************************************************************************
 * Objective of the class: Mouse event handler for implementing rectangular zoom selection functionality.
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
 * 	- mousePressed() - Captures starting point of zoom selection
 * 	- mouseDragged() - Updates selection rectangle as mouse moves
 * 	- mouseReleased() - Applies zoom to selected area and disables slider
 * 	- drawRectangle() - Renders the selection rectangle overlay
 *******************************************************************************************************************/

package drawingTool;

import gui.DrawingJPanel;
import gui.DrawingJFrame;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseZooming extends MouseAdapter {
    private Point startPoint;
    private Rectangle selectionRectangle;
    private final DrawingJPanel drawingJPanel;
    private final DrawingJFrame drawingJFrame;

    public MouseZooming(DrawingJPanel drawingJPanel, DrawingJFrame drawingJFrame) {
        this.drawingJPanel = drawingJPanel;
        this.drawingJFrame = drawingJFrame;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        selectionRectangle = new Rectangle();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point currentPoint = e.getPoint();
        selectionRectangle.setFrameFromDiagonal(startPoint, currentPoint);
        drawingJPanel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // When mouse is released, tell the scene to zoom
        if (selectionRectangle != null) {
            drawingJPanel.getScene().zoomTo(selectionRectangle);
            drawingJFrame.getConfigurationJPanel().setSliderEnabled(false);
        }
        
        // Clear the selection rectangle and repaint
        selectionRectangle = null;
        drawingJPanel.repaint();
    }
    
    public void drawRectangle() {
        if (selectionRectangle != null) {
            Drawing.getPen2D().setColor(new Color(0, 150, 255, 70));
            Drawing.getPen2D().fill(selectionRectangle);
            Drawing.getPen2D().setColor(new Color(0, 150, 255));
            Drawing.getPen2D().draw(selectionRectangle);
            Drawing.getPen2D().dispose();
        }
    }
}