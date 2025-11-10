/*******************************************************************************************************************
 * Objective of the class: Welcome screen content panel with interactive buttons and text fields for user feedback.
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
 * 	- None (all event handlers are defined as anonymous inner classes)
 *******************************************************************************************************************/

package welcoming;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.DrawingJFrame;

public class WelcomeMenuJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private MainWelcomeJFrame granParentFrame;
	private DrawingJFrame drawingJFrame;
	private final Color LIGHT_BLUE = new Color(245, 245, 250);
	private final Font TITLE_FONT  = new Font("Lucida Bright", Font.BOLD, 18);
    private final Font LABEL_FONT  = new Font("Lucida Bright", Font.PLAIN, 14);
    private final Font FIELD_FONT  = new Font("Lucida Bright", Font.PLAIN, 14);
    private final Font BUTTON_FONT = new Font("Lucida Bright", Font.PLAIN, 14);

    public WelcomeMenuJPanel(MainWelcomeJFrame grandParentFrame) {
    	super();	
    	this.granParentFrame = grandParentFrame;
    	
    	initialization();
    }
    
    private void initialization() {
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	setBackground(LIGHT_BLUE);
    	
    	JLabel title = new JLabel("Sensor Data Plotting Toolkit!");
    	title.setFont(TITLE_FONT);
    	title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);
        add(Box.createVerticalStrut(15));	
        
        JTextField authors = new JTextField("Authors: Georgii & Luciano");
        authors.setFont(FIELD_FONT);
        authors.setEditable(false);
        authors.setMaximumSize(authors.getPreferredSize());
        authors.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(authors);
        add(Box.createVerticalStrut(15));
        
        JLabel greetingLabel = new JLabel("Write your feedback for the project");
        greetingLabel.setFont(LABEL_FONT);
        greetingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(greetingLabel);
        add(Box.createVerticalStrut(5));
        
        JTextField greetingField = new JTextField(20);
        greetingField.setFont(FIELD_FONT);
        greetingField.setMaximumSize(greetingField.getPreferredSize());
        greetingField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(greetingField);
        add(Box.createVerticalStrut(20));
        
        JButton continueBtn = new JButton(" Continue");
        continueBtn.setFont(BUTTON_FONT);
        continueBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(continueBtn);
        add(Box.createVerticalStrut(20));
        
        JButton exitBtn = new JButton("Exit NOW");
        exitBtn.setFont(BUTTON_FONT);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(exitBtn);
        add(Box.createVerticalStrut(20));
        
        JButton fakeBtn = new JButton("I am fake!");
        fakeBtn.setFont(BUTTON_FONT);
        fakeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(fakeBtn);
        
        continueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                granParentFrame.dispose(); // close background
                drawingJFrame = new DrawingJFrame("Algorithms and Data Structures - Drawing Window"); // open drawing stage
            }
        });
        
        exitBtn.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        
        fakeBtn.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		fakeBtn.setVisible(false);
        		
                JLabel fakeActionMessage = new JLabel(">:(");
                fakeActionMessage.setFont(LABEL_FONT);
                fakeActionMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(fakeActionMessage);
        	}
        });
    }
}