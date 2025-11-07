/*******************************************************************************************************************
 * Objective of the class: Custom JPanel that serves as the main drawing canvas for rendering sensor data plots.
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
 * 	- setConfigurationJPanel() - Links the configuration panel for bidirectional communication
 * 	- getScene() - Returns the Scene object that manages viewport and data visualization
 * 	- getConfigurationJPanel() - Returns reference to the associated configuration panel
 *******************************************************************************************************************/

package gui;

import java.awt.Graphics;
import javax.swing.JPanel;

import layout.Scene;
import drawingTool.Drawing;
import drawingTool.MouseZooming;

public class DrawingJPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Scene scene;
    private ConfigurationJPanel configurationJPanel;
    private MouseZooming mouseZooming;

    public DrawingJPanel(int width, int height, DrawingJFrame parentJFrame) {
        super();
        scene = new Scene(width, height);
        mouseZooming = new MouseZooming(this, parentJFrame); // Pass the current DrawingArea instance
        this.addMouseListener(mouseZooming);
        this.addMouseMotionListener(mouseZooming);
    }

    public void setConfigurationJPanel(ConfigurationJPanel configurationJPanel) {
        this.configurationJPanel = configurationJPanel;
    }
    
    public Scene getScene() {
    	return scene;
    }
    
    public ConfigurationJPanel getConfigurationJPanel() {
    	return configurationJPanel;
    }
    
    @Override
    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        Drawing.setPen(pen);
        scene.draw();
        mouseZooming.drawRectangle();
    }
}