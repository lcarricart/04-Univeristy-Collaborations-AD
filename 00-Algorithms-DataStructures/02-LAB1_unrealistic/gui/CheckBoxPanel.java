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
