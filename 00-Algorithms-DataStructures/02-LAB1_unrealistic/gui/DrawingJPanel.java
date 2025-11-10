package gui;

import java.awt.Dimension;
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
    private boolean isZoomed = false;

    public DrawingJPanel(int width, int height, DrawingJFrame parentJFrame) {
        super();
        
        super.setPreferredSize(new Dimension(width, height));
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