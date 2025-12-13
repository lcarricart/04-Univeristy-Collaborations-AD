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
import gui.ModernTheme;
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

        if (selectionRectangle != null) {
            drawingJPanel.getScene().zoomTo(selectionRectangle);
            drawingJFrame.getConfigurationJPanel().setSliderEnabled(false);
        }
        
        selectionRectangle = null;
        drawingJPanel.repaint();
    }
    
    public void drawRectangle() {
        if (selectionRectangle != null) {
            // Semi-transparent fill using modern accent color
            Drawing.getPen2D().setColor(new Color(
                ModernTheme.ACCENT_BLUE.getRed(), 
                ModernTheme.ACCENT_BLUE.getGreen(), 
                ModernTheme.ACCENT_BLUE.getBlue(), 
                70
            ));
            Drawing.getPen2D().fill(selectionRectangle);
            
            // Solid border using accent color
            Drawing.getPen2D().setColor(ModernTheme.ACCENT_BLUE);
            Drawing.getPen2D().draw(selectionRectangle);
            Drawing.getPen2D().dispose();
        }
    }
}