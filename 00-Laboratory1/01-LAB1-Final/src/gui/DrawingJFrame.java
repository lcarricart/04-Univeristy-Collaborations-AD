/*******************************************************************************************************************
 * Objective of the class: Main application window that hosts the drawing canvas and configuration panel.
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
 * 	- getConfigurationJPanel() - Returns reference to the configuration panel for external access
 *******************************************************************************************************************/

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class DrawingJFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private DrawingJPanel drawingJPanel;
    private ConfigurationJPanel configJPanel;

    public DrawingJFrame(String title) {
        super(title);

        Dimension screen = this.getToolkit().getScreenSize();
        int frameW = screen.width;
        int frameH = screen.height;
        
        initialization(frameW, frameH);
    }
    
    private void initialization(int frameW, int frameH) {
    	setBounds(0, 0, frameW, frameH);
        getContentPane().setLayout(new BorderLayout());
        
        drawingJPanel = new DrawingJPanel(frameW, frameH, this);
        configJPanel = new ConfigurationJPanel(drawingJPanel);
        
        getContentPane().add(drawingJPanel, BorderLayout.CENTER);
        getContentPane().add(configJPanel, BorderLayout.EAST);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public ConfigurationJPanel getConfigurationJPanel() {
    	return configJPanel;
    }
}