/*******************************************************************************************************************
 * Objective of the class: Panel with checkboxes for toggling analysis features like zeros, extremas, and histogram.
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
 * 	- getZerosBox() - Returns checkbox for displaying zero crossings in the plot
 * 	- getExtremasBox() - Returns checkbox for showing local maxima and minima points
 * 	- getHistogramBox() - Returns checkbox for switching beetween line plot and histogram mode
 *******************************************************************************************************************/

package gui;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CheckBoxPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JCheckBox showZerosCheckBox = new JCheckBox("Show Zeros");
    private JCheckBox showExtremasCheckBox = new JCheckBox("Show Extremas");
    private JCheckBox showHistogramCheckBox = new JCheckBox("Histogram Mode");
    
    public CheckBoxPanel() {
    	
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	
    	showZerosCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(showZerosCheckBox);
        add(Box.createVerticalStrut(5));

        showExtremasCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(showExtremasCheckBox);
        add(Box.createVerticalStrut(5));

        showHistogramCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(showHistogramCheckBox);
        add(Box.createVerticalStrut(20));
    }
    
    public JCheckBox getZerosBox() {
    	return showZerosCheckBox;
    }
    
    public JCheckBox getExtremasBox() {
    	return showExtremasCheckBox;
    }
    
    public JCheckBox getHistogramBox() {
    	return showHistogramCheckBox;
    }
}
